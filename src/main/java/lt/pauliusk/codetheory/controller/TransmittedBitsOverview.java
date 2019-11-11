package lt.pauliusk.codetheory.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import lt.pauliusk.codetheory.communicator.ICommunicator;
import lt.pauliusk.codetheory.controller.constant.Mode;
import lt.pauliusk.codetheory.controller.debug.DataChangedControler;
import lt.pauliusk.codetheory.util.gui.IAlertBuilder;
import lt.pauliusk.codetheory.util.gui.IWindow;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import lt.pauliusk.codetheory.util.gui.impl.AbstractController;
import lt.pauliusk.codetheory.util.gui.javafx.IStringBooleanConverter;
import lt.pauliusk.codetheory.util.math.IArrayConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TransmittedBitsOverview extends AbstractController {
    @Autowired
    private IWindowLoader mWindowLoader;
    @Autowired
    private IAlertBuilder mAlertBuilder;
    @Autowired
    private ICommunicator mCommunicator;
    @Autowired
    private IArrayConverter mArrayConverter;
    @Autowired
    private IStringBooleanConverter mStringBooleanConverter;

    @FXML
    private TextArea mBitsOverview;
    @FXML
    private TextArea mEncodedBitsOverview;
    @FXML
    private TextArea mDecodedBitsOverview;
    @FXML
    private ListView<String> mEventListView;
    @FXML
    private Label mEventCountLabel;

    private IWindow mPreviousWindow;

    private double mErrorRate;
    private int mM;

    private Boolean mDebugMode;
    private IWindow mDebugOriginal;
    private IWindow mDebugTransmitted;
    private IWindow mDebugDecoded;
    private Mode mMode;
    private byte[] mHeader;

    private boolean[][] mTransmittedBits;

    private Map<String, Object> mOldParams;

    @Override
    public void setParameters(Map<String, Object> args) {
        byte[] data = (byte[]) args.get("data");
        mM = (Integer) args.get("m");
        mErrorRate = (Double) args.get("errorRate");
        mPreviousWindow = (IWindow) args.get("previousWindow");
        mDebugMode = (Boolean) args.get("debug");

        mOldParams = args;

        // build vectors
        boolean[][] vectors = mCommunicator.buildVectors(data, mM);

        mBitsOverview.setText(
                mStringBooleanConverter.convertFromBooleanArray(vectors)
        );

        // DEBUG MODE
        mMode = (Mode) mOldParams.get("mode");
        if (mDebugMode && mMode != Mode.BINARY_INPUT) {
            if (mMode == Mode.BMP_FILE) {
                mDebugOriginal = mWindowLoader.getWindow("DebugBMPResult");
                mDebugTransmitted = mWindowLoader.getWindow("DebugBMPResult");
                mDebugDecoded = mWindowLoader.getWindow("DebugBMPResult");

                mHeader = (byte[]) args.get("headerData");

                mDebugOriginal.setParameters(Map.of("title", "Original BMP"));
                mDebugTransmitted.setParameters(Map.of("title", "Transmitted BMP"));
                mDebugDecoded.setParameters(Map.of("title", "Decoded BMP"));
                ((DataChangedControler) mDebugOriginal.getController()).dataChanged(data, mHeader);
            } else if (mMode == Mode.TEXT_INPUT) {
                mDebugOriginal = mWindowLoader.getWindow("DebugTextResult");
                mDebugTransmitted = mWindowLoader.getWindow("DebugTextResult");
                mDebugDecoded = mWindowLoader.getWindow("DebugTextResult");

                mDebugOriginal.setParameters(Map.of("title", "Original text"));
                mDebugTransmitted.setParameters(Map.of("title", "Transmitted text"));
                mDebugDecoded.setParameters(Map.of("title", "Decoded text"));
                ((DataChangedControler) mDebugOriginal.getController()).dataChanged(data);
            }

            mDebugOriginal.render();
            mDebugTransmitted.render();
            mDebugDecoded.render();
        }
    }

    @FXML
    private void onBackButtonClicked() {
        this.getParentWindow().close();
        mPreviousWindow.render();
    }

    @FXML
    private void onEncodeButtonClicked() {
        if (mBitsOverview.getText().isEmpty()) {
            mAlertBuilder.error("There's nothing to encode");
            return;
        }

        boolean[][] inputBits = mStringBooleanConverter.convertFromString(
                mBitsOverview.getText()
        );

        boolean[][] encodedBits = mCommunicator.encode(inputBits, mM);

        mEncodedBitsOverview.setText(
                mStringBooleanConverter.convertFromBooleanArray(
                        encodedBits
                )
        );
    }

    @FXML
    private void onTransmitButtonClicked() {
        if (mEncodedBitsOverview.getText().isEmpty()) {
            mAlertBuilder.error("There's nothing to transmit");
            return;
        }

        if (mDebugMode) {
            byte[] bytes = mArrayConverter.convertFromBoolean2DArray(
                    mCommunicator.transmit(
                            mStringBooleanConverter.convertFromString(
                                    mBitsOverview.getText()
                            ), mM
                    )
            );

            if (mMode == Mode.TEXT_INPUT) {
                ((DataChangedControler) mDebugTransmitted.getController()).dataChanged(bytes);
            } else if (mMode == Mode.BMP_FILE) {
                ((DataChangedControler) mDebugTransmitted.getController()).dataChanged(bytes, mHeader);
            }
        }

        boolean[][] toTransmitBits = mStringBooleanConverter.convertFromString(
                mEncodedBitsOverview.getText()
        );

        mTransmittedBits = mCommunicator.transmit(
                toTransmitBits,
                mErrorRate
        );

        mEventListView.setItems(
                FXCollections.observableList(
                        mCommunicator.getMessages()
                )
        );
        mEventCountLabel.setText(
                Integer.toString(
                        mCommunicator.getMessages().size()
                )
        );
    }

    @FXML
    private void onDecodeButtonButtonClicked() {
        if (mTransmittedBits == null) {
            mAlertBuilder.error("You haven't transmitted the encoded bits yet");
            return;
        }

        boolean[][] decodedBits = mCommunicator.decode(mTransmittedBits, mM);

        if (mDebugMode) {
            byte[] bytes = mArrayConverter.convertFromBoolean2DArray(decodedBits);

            if (mMode == Mode.TEXT_INPUT) {
                ((DataChangedControler) mDebugDecoded.getController()).dataChanged(bytes);
            } else if (mMode == Mode.BMP_FILE) {
                ((DataChangedControler) mDebugDecoded.getController()).dataChanged(bytes, mHeader);
            }
        }

        mDecodedBitsOverview.setText(
                mStringBooleanConverter.convertFromBooleanArray(decodedBits)
        );
    }

    @FXML
    private void onNextButtonClicked() {
        if (mDecodedBitsOverview.getText().isEmpty()) {
            mAlertBuilder.error("The transmitted bits haven't been decoded yet!");
            return;
        }

        Mode mode = (Mode) mOldParams.get("mode");

        IWindow window = null;
        switch (mode) {
            case TEXT_INPUT:
                window = mWindowLoader.getWindow("TextInputResult");
                break;
            case BMP_FILE:
                window = mWindowLoader.getWindow("BMPInputResult");
                break;
            case BINARY_INPUT:
                window = mWindowLoader.getWindow("BinaryInputResult");
                break;
        }

        mOldParams.put(
                "result", mArrayConverter.convertFromBoolean2DArray(
                        mStringBooleanConverter.convertFromString(
                                mDecodedBitsOverview.getText()
                        )
                )
        );
        window.getController().setParameters(mOldParams);
        window.render();

        getParentWindow().close();
    }
}

package lt.pauliusk.codetheory.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import lt.pauliusk.codetheory.communicator.ICommunicator;
import lt.pauliusk.codetheory.controller.constant.Mode;
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

    private IWindow mPreviousWindow;

    private double mErrorRate;
    private int mM;

    private boolean[][] mTransmittedBits;

    private Map<String, Object> mOldParams;

    @Override
    public void setParameters(Map<String, Object> args) {
        byte[] data = (byte[]) args.get("data");
        mM = (Integer) args.get("m");
        mErrorRate = (Double) args.get("errorRate");
        mPreviousWindow = (IWindow) args.get("previousWindow");

        mOldParams = args;

        // build vectors
        boolean[][] vectors = mCommunicator.buildVectors(data, mM);

        mBitsOverview.setText(
                mStringBooleanConverter.convertFromBooleanArray(vectors)
        );
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
    }

    @FXML
    private void onDecodeButtonButtonClicked() {
        if (mTransmittedBits == null) {
            mAlertBuilder.error("You haven't transmitted the encoded bits yet");
            return;
        }

        boolean[][] decodedBits = mCommunicator.decode(mTransmittedBits, mM);

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

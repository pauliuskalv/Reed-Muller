package lt.pauliusk.codetheory.controller.input;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import lt.pauliusk.codetheory.util.file.IBMPLoader;
import lt.pauliusk.codetheory.util.gui.IAlertBuilder;
import lt.pauliusk.codetheory.util.gui.IWindow;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import lt.pauliusk.codetheory.util.gui.impl.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BMPInputController extends AbstractController {
    @Autowired
    private IWindowLoader mWindowLoader;
    @Autowired
    private IAlertBuilder mAlertBuilder;
    @Autowired
    private IBMPLoader mBMPLoader;

    @FXML
    private Label mFileNameLabel;
    @FXML
    private Label mFilePathLabel;
    @FXML
    private Label mFileSizeLabel;

    private File mChosenFile;

    private Map<String, Object> mOldArgs;

    @Override
    public void setParameters(Map<String, Object> args) {
        mOldArgs = args;
    }

    @FXML
    private void onFileChooserButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a .bmp file");
        fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter(".bmp files", "*.bmp")
        );

        mChosenFile = fileChooser.showOpenDialog((Stage) getParentWindow());

        if (mChosenFile == null) {
            return;
        }

        mFileNameLabel.setText(
                mChosenFile.getName()
        );
        mFilePathLabel.setText(
                mChosenFile.getAbsolutePath()
        );
        mFileSizeLabel.setText(
                (mChosenFile.length() / 1024) + " Kb"
        );
    }

    @FXML
    private void onBackButtonClicked() {
        getParentWindow().close();
        IWindow window = mWindowLoader.getWindow("MainMenu");
        window.getController().setParameters(mOldArgs);

        window.render();
    }

    @FXML
    private void onNextButtonClicked() {
        byte[] bytes, headerBytes;

        try {
            bytes = mBMPLoader.load(mChosenFile);
            headerBytes = mBMPLoader.loadHeader(mChosenFile);
        } catch (IOException e) {
            mAlertBuilder.error("An error occurred while reading the bmp image", e.getMessage());
            return;
        }

        mOldArgs.put("data", bytes);
        mOldArgs.put("headerData", headerBytes);
        mOldArgs.put("previousWindow", getParentWindow());

        IWindow window = mWindowLoader.getWindow("TransmittedBitsOverview");
        window.getController().setParameters(mOldArgs);
        window.render();

        getParentWindow().close();
    }
}

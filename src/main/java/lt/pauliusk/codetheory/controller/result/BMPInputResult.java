package lt.pauliusk.codetheory.controller.result;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import lt.pauliusk.codetheory.util.file.IFileWriter;
import lt.pauliusk.codetheory.util.gui.IAlertBuilder;
import lt.pauliusk.codetheory.util.gui.IWindow;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import lt.pauliusk.codetheory.util.gui.impl.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BMPInputResult extends AbstractController {
    @Autowired
    private IWindowLoader mWindowLoader;
    @Autowired
    private IAlertBuilder mAlertBuilder;
    @Autowired
    private IFileWriter mFileWriter;

    @FXML
    private Label mFilePathLabel;

    private File mChosenFile;

    private byte[] mFileHeader;
    private byte[] mFileContent;

    private Map<String, Object> mOldArgs;

    @Override
    public void setParameters(Map<String, Object> args) {
        mFileHeader = (byte[]) args.get("headerData");
        mFileContent = (byte[]) args.get("result");

        mOldArgs = args;
    }

    @FXML
    private void onFileChooserButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save decoded .bmp file");
        mChosenFile = fileChooser.showOpenDialog((Stage) getParentWindow());

        mFilePathLabel.setText(
                mChosenFile.getAbsolutePath()
        );
    }

    @FXML
    private void onSaveButtonClicked() {
        byte[] toWrite = new byte[mFileHeader.length + mFileContent.length];

        System.arraycopy(mFileHeader, 0, toWrite, 0, mFileHeader.length);
        System.arraycopy(mFileContent, 0, toWrite, mFileHeader.length, mFileContent.length);

        try {
            mFileWriter.write(mChosenFile, toWrite);
        } catch (IOException e) {
            mAlertBuilder.error("An error occurred while saving the file!", e.getMessage());
            return;
        }

        mAlertBuilder.info("Saved file at " + mChosenFile.getAbsolutePath());
    }

    @FXML
    private void onBackToMenuButtonClicked() {
        IWindow window = mWindowLoader.getWindow("MainMenu");
        window.getController().setParameters(mOldArgs);
        window.render();

        getParentWindow().close();
    }
}

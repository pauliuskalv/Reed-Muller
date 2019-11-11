package lt.pauliusk.codetheory.controller.input;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lt.pauliusk.codetheory.util.gui.IAlertBuilder;
import lt.pauliusk.codetheory.util.gui.IWindow;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import lt.pauliusk.codetheory.util.gui.impl.AbstractController;
import lt.pauliusk.codetheory.util.math.IArrayConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BinaryInputController extends AbstractController {
    @Autowired
    private IWindowLoader mWindowLoader;
    @Autowired
    private IArrayConverter mArrayConverter;
    @Autowired
    private IAlertBuilder mAlertBuilder;

    @FXML
    private TextField mBinaryInputTextArea;

    private Map<String, Object> mOldArgs;

    @Override
    public void setParameters(Map<String, Object> args) {
        mOldArgs = args;
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
        if (!mBinaryInputTextArea.getText().matches("^[0-1]{1,}$")) {
            mAlertBuilder.error("The entered text is not binary!");
            return;
        }

        if (mBinaryInputTextArea.getText().length() % 8 != 0) {
            mAlertBuilder.error("Entered binary string must be divisible by 8!");
            return;
        }

        mOldArgs.put("data", mArrayConverter.convertFromBooleanArray(
                    mArrayConverter.binaryStringToBooleanArray(
                            mBinaryInputTextArea.getText()
                    )
                )
        );
        mOldArgs.put("previousWindow", getParentWindow());

        IWindow window = mWindowLoader.getWindow("TransmittedBitsOverview");
        window.getController().setParameters(mOldArgs);
        window.render();

        getParentWindow().close();
    }
}

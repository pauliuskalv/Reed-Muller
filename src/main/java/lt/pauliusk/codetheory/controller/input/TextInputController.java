package lt.pauliusk.codetheory.controller.input;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import lt.pauliusk.codetheory.util.gui.IAlertBuilder;
import lt.pauliusk.codetheory.util.gui.IWindow;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import lt.pauliusk.codetheory.util.gui.impl.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TextInputController extends AbstractController {
    @Autowired
    private IWindowLoader mWindowLoader;
    @Autowired
    private IAlertBuilder mAlertBuilder;

    @FXML
    private TextArea mInputText;

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
        if (mInputText.getText().isEmpty()) {
            mAlertBuilder.error("Text cannot be empty!");
            return;
        }

        mOldArgs.put("data", mInputText.getText().getBytes());
        mOldArgs.put("previousWindow", getParentWindow());

        IWindow window = mWindowLoader.getWindow("TransmittedBitsOverview");
        window.getController().setParameters(mOldArgs);
        window.render();

        getParentWindow().close();
    }
}

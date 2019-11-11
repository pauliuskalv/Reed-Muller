package lt.pauliusk.codetheory.controller.result;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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
public class TextInputResult extends AbstractController {
    @Autowired
    private IWindowLoader mWindowLoader;

    @FXML
    private TextArea mTextResultArea;

    private Map<String, Object> mOldArgs;

    @Override
    public void setParameters(Map<String, Object> args) {
        mTextResultArea.setText(
                new String(
                        (byte[]) args.get("result")
                )
        );

        mOldArgs = args;
    }

    @FXML
    private void onBackToMenuButtonClicked() {
        IWindow window = mWindowLoader.getWindow("MainMenu");
        window.getController().setParameters(mOldArgs);
        window.render();

        getParentWindow().close();
    }
}

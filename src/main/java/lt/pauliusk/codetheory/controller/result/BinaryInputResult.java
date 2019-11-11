package lt.pauliusk.codetheory.controller.result;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
public class BinaryInputResult extends AbstractController {
    @Autowired
    private IWindowLoader mWindowLoader;
    @Autowired
    private IArrayConverter mArrayConverter;

    @FXML
    private TextField mBinaryInputResultTextField;

    private Map<String, Object> mOldArgs;

    @Override
    public void setParameters(Map<String, Object> args) {
        byte[] data = (byte[]) args.get("result");
        boolean[] converted = mArrayConverter.convertFromByteArray(data);

        StringBuilder builder = new StringBuilder();
        for (boolean bool : converted) {
            builder.append(
                    bool ? '1' : '0'
            );
        }
        mBinaryInputResultTextField.setText(builder.toString());

        mOldArgs = args;
    }

    @FXML
    private void onBackToMainMenuButtonClicked() {
        IWindow window = mWindowLoader.getWindow("MainMenu");
        window.getController().setParameters(mOldArgs);
        window.render();

        getParentWindow().close();
    }
}

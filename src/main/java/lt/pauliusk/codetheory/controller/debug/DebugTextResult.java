package lt.pauliusk.codetheory.controller.debug;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lt.pauliusk.codetheory.util.gui.impl.AbstractController;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DebugTextResult extends AbstractController implements DataChangedControler {
    @FXML
    private TextArea mTextArea;

    @Override
    public void setParameters(Map<String, Object> args) {

    }

    @Override
    public void dataChanged(byte[] bytes) {
        mTextArea.setText(
                new String(
                        bytes
                )
        );
    }

    @Override
    public void dataChanged(byte[] bytes, byte[] header) {
        // ignore
    }
}

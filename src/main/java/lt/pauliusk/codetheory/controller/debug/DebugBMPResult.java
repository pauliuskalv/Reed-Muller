package lt.pauliusk.codetheory.controller.debug;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lt.pauliusk.codetheory.util.gui.impl.AbstractController;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DebugBMPResult extends AbstractController implements DataChangedControler {
    @FXML
    private ImageView mImageView;

    @Override
    public void setParameters(Map<String, Object> args) {

    }

    @Override
    public void dataChanged(byte[] bytes) {
        // ignore
    }

    @Override
    public void dataChanged(byte[] bytes, byte[] header) {
        byte[] toSet = new byte[bytes.length + header.length];

        System.arraycopy(header, 0, toSet, 0, 54);
        System.arraycopy(bytes, 0, toSet, 54, bytes.length);

        mImageView.setImage(
                new Image(
                        new ByteArrayInputStream(
                                toSet
                        )
                )
        );
    }
}

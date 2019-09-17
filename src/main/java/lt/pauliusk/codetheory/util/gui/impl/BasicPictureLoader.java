package lt.pauliusk.codetheory.util.gui.impl;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import javafx.scene.image.Image;
import lt.pauliusk.codetheory.util.gui.IPictureLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class BasicPictureLoader implements IPictureLoader {
    private Logger mLogger = LoggerFactory.getLogger(BasicPictureLoader.class);

    @Override
    public Image load(URL url) {
        try {
            return new Image(url.openStream());
        } catch (IOException e) {
            mLogger.error("An error occurred while loading the picture " + url.getPath(), e);
        }

        return null;
    }
}

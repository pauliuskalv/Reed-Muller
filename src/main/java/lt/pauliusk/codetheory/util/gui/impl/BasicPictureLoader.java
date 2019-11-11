package lt.pauliusk.codetheory.util.gui.impl;

import javafx.scene.image.Image;

import lt.pauliusk.codetheory.util.gui.IPictureLoader;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BasicPictureLoader implements IPictureLoader {
    private Logger mLogger = Logger.getLogger(BasicPictureLoader.class.getName());

    @Override
    public Image load(URL url) {
        try {
            return new Image(url.openStream());
        } catch (IOException e) {
            mLogger.log(Level.SEVERE, "An error occurred while loading the picture " + url.getPath(), e);
        }

        return null;
    }
}

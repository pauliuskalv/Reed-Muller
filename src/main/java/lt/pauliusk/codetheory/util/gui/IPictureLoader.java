package lt.pauliusk.codetheory.util.gui;

import javafx.scene.image.Image;

import java.net.URL;

/**
 * A picture loader which loads graphical images
 */
public interface IPictureLoader {
    /**
     * Load a graphical image and convert it as needed from the specified URL
     * @param url the path to the graphical image
     * @return the loaded graphical image
     */
    Image load(URL url);
}

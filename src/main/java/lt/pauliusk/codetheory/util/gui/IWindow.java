package lt.pauliusk.codetheory.util.gui;

import java.util.Map;

/**
 * Defines a window
 *
 * A window is a collection of graphical user interface layouts and controllers
 */
public interface IWindow {
    /**
     * Set parameters for the window
     * @param args the arguments for the window
     */
    void setParameters(Map<String, Object> args);

    /**
     * Sets the main controller of the window
     * @param controller the controller
     */
    void setController(IController controller);

    /**
     * Gets the window controller
     * @return the window controller
     */
    IController getController();

    /**
     * Render the window and block until it's closed
     */
    void render();

    /**
     * Render the window and block until it's closed
     */
    void renderAndWait();

    /**
     * Close the window, if it is open
     */
    void close();
}

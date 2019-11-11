package lt.pauliusk.codetheory.util.gui;

import java.util.Map;

/**
 * Defines a controller
 *
 * A controller is a class, which accepts events from the graphical user interface and performs actions as necessary
 */
public interface IController {
    /**
     * Tells if the controller has a parent window
     * @return true if the controller has a parent window. False otherwise
     */
    boolean hasParentWindow();

    /**
     * Set the parent window for the controller
     * @param window the parent window
     */
    void setParentWindow(IWindow window);

    /**
     * Get the parent window of the controller
     * @return the set parent window
     */
    IWindow getParentWindow();

    /**
     * Sets the parameters of the controller to args
     * @param args the parameters for the controller
     */
    void setParameters(Map<String, Object> args);
}

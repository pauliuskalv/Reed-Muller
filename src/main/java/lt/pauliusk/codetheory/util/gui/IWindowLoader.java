package lt.pauliusk.codetheory.util.gui;

import java.util.Map;

/**
 * Loads windows from constructed layout files
 */
public interface IWindowLoader {
    /**
     * Get the window with qualifier name
     * @param name the qualifier of the window
     * @return the constructed window class
     */
    IWindow getWindow(String name);

    /**
     * Get the window with qualifier name and set it's parameters to args
     * @param name the qualifier of the window
     * @param args the arguments, which are passed to the window (not it's controller)
     * @return the constructed window class
     */
    IWindow getWindow(String name, Map<String, Object> args);
}

package lt.pauliusk.codetheory.util.gui;

import java.util.Map;

/**
 * Resolves basic properties of various graphical user interface layouts
 *
 * The properties are read from the window.config and stored in JSON format
 */
public interface IWindowPathResolver {
    /**
     * Resolve the path to the window layout file
     * @param name the qualifier of the window
     * @return the path to the window layout file
     */
    String resolveToPath(String name);

    /**
     * Resolve the window's title
     * @param name the qualifier of the window
     * @return the window's title
     */
    String resolveToWindowTitle(String name);

    /**
     * Resolve the window's name
     * @param name the qualifier of the window
     * @return the window's name
     */
    String resolveToWindowName(String name);

    /**
     * Resolve the preferred window width
     * @param name the qualifier of the window
     * @return the preferred window width
     */
    Double resolveToWindowWidth(String name);

    /**
     * Resolve the preferred window height
     * @param name the qualifier of the window
     * @return the preferred window height
     */
    Double resolveToWindowHeight(String name);

    /**
     * Resolve the minimum window width
     * @param name the qualifier of the window
     * @return the minimum window width
     */
    Double resolveToMinWindowWidth(String name);

    /**
     * Resolve the minimum window height
     * @param name the qualifier of the window
     * @return the minimum window height
     */
    Double resolveToMinWindowHeight(String name);

    /**
     * Resolve all parameters in the window.config file
     * @param name the qualifier of the window
     * @return all parameters for window
     */
    Map<String, Object> resolveAllParameters(String name);
}

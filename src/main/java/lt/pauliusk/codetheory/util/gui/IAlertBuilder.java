package lt.pauliusk.codetheory.util.gui;

/**
 * Defines an alert builder, which constructs alert dialogs, which are modal according to the parent window
 *
 * Alerts are of 3 different types:
 *
 * INFO:
 *  An informational message, to convey some basic information to the user
 *
 * ALERT (WARNING):
 *  An alert to the user, warning him of some event or action
 *
 * ERROR:
 *  An error message to the user, typically shown when things worked not quite the way they needed to
 */
public interface IAlertBuilder {
    /**
     * Show an informational message to the user with msg as its content
     * @param msg the content of the alert
     */
    void info(String msg);

    /**
     * Show an informational message to the user with msg as its content and header as the window title
     * @param header the title of the alert window
     * @param msg the content of the alert
     */
    void info(String header, String msg);

    /**
     * Show a warning to the user with msg as its content
     * @param msg the content of the alert
     */
    void alert(String msg);

    /**
     * Show a warning to the user with msg as its content and header as the window title
     * @param header the title of the alert window
     * @param msg the content of the alert
     */
    void alert(String header, String msg);

    /**
     * Show an error message to the user with msg as its content
     * @param msg the content of the alert
     */
    void error(String msg);

    /**
     * Show an error message to the user with msg as its content and header as the window title
     * @param header the title of the alert window
     * @param msg the content of the alert
     */
    void error(String header, String msg);
}

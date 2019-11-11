package lt.pauliusk.codetheory.util.gui;

import javafx.scene.control.Alert;

public interface IAlertBuilder {
    void info(String msg);
    void info(String header, String msg);

    void alert(String msg);
    void alert(String header, String msg);

    void error(String msg);
    void error(String header, String msg);
}

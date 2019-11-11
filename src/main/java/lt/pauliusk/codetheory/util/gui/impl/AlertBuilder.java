package lt.pauliusk.codetheory.util.gui.impl;

import javafx.scene.control.Alert;
import lt.pauliusk.codetheory.util.gui.IAlertBuilder;
import org.springframework.stereotype.Component;

@Component
public class AlertBuilder implements IAlertBuilder {
    @Override
    public void info(String msg) {
        build(Alert.AlertType.INFORMATION, "Info", msg);
    }

    @Override
    public void info(String header, String msg) {
        build(Alert.AlertType.INFORMATION, header, msg);
    }

    @Override
    public void alert(String msg) {
        build(Alert.AlertType.WARNING, "Warning", msg);
    }

    @Override
    public void alert(String header, String msg) {
        build(Alert.AlertType.WARNING, header, msg);
    }

    @Override
    public void error(String msg) {
        build(Alert.AlertType.ERROR, "Error", msg);
    }

    @Override
    public void error(String header, String msg) {
        build(Alert.AlertType.ERROR, header, msg);
    }

    private void build(Alert.AlertType alertType, String header, String msg) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

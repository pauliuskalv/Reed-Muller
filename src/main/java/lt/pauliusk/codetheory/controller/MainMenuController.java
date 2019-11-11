package lt.pauliusk.codetheory.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lt.pauliusk.codetheory.controller.constant.Mode;
import lt.pauliusk.codetheory.util.gui.IAlertBuilder;
import lt.pauliusk.codetheory.util.gui.IWindow;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import lt.pauliusk.codetheory.util.gui.impl.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MainMenuController extends AbstractController {
    @Autowired
    private IWindowLoader windowLoader;
    @Autowired
    private IAlertBuilder mAlertBuilder;

    @FXML
    private ChoiceBox<Mode> mChoiceBox;

    @FXML
    private TextField mMTextField;
    @FXML
    private TextField mErrorRateTextField;

    @Override
    public void setParameters(Map<String, Object> args) {
        if (args != null) {
            mMTextField.setText(
                    args.get("m").toString()
            );

            mErrorRateTextField.setText(
                    Double.toString(Double.parseDouble(args.get("errorRate").toString()) * 100)
            );
        }
    }

    @PostConstruct
    private void postConstruct() {
        Platform.runLater(() -> {
            mChoiceBox.getItems().addAll(
                    Mode.BMP_FILE,
                    Mode.BINARY_INPUT,
                    Mode.TEXT_INPUT
            );

            mChoiceBox.getSelectionModel().select(0);
        });
    }

    @FXML
    private void onNextButtonClicked() {
        if (mMTextField.getText().isEmpty()) {
            mAlertBuilder.error("No m is specified!");
            return;
        }

        if (mErrorRateTextField.getText().isEmpty()) {
            mAlertBuilder.error("No error rate is specified!");
            return;
        }

        int m;
        try {
            m = Integer.parseInt(mMTextField.getText());
        } catch (NumberFormatException e) {
            mAlertBuilder.error("Entered m is not a number!");
            return;
        }

        if (m < 0) {
            mAlertBuilder.error("m must be positive!");
            return;
        }

        double errorRate;
        try {
            errorRate = Double.parseDouble(mErrorRateTextField.getText());
        } catch (NumberFormatException e) {
            mAlertBuilder.error("Entered error rate is not a number!");
            return;
        }

        if (errorRate < 0 || errorRate > 100) {
            mAlertBuilder.error("Error rate must be between 0 and 100!");
            return;
        }

        IWindow window = null;
        switch (mChoiceBox.getSelectionModel().getSelectedItem()) {
            case TEXT_INPUT:
                window = windowLoader.getWindow("TextInput");
                break;
            case BINARY_INPUT:
                window = windowLoader.getWindow("BinaryInput");
                break;
            case BMP_FILE:
                window = windowLoader.getWindow("BMPInput");
                break;
            default:
                return;
        }

        Map<String, Object> params = new HashMap<>();

        params.put("m", m);
        params.put("errorRate", errorRate / 100);
        params.put("mode", mChoiceBox.getSelectionModel().getSelectedItem());

        window.getController().setParameters(params);

        window.render();

        getParentWindow().close();
    }

    @FXML
    private void onExitButtonClicked() {
        getParentWindow().close();
    }
}

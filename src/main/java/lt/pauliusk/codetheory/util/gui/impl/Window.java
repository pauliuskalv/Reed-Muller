package lt.pauliusk.codetheory.util.gui.impl;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lt.pauliusk.codetheory.util.gui.IController;
import lt.pauliusk.codetheory.util.gui.IWindow;

import java.util.Map;

public class Window extends Stage implements IWindow {
    private Map<String, Object> mArgs;
    private IController mController;

    public Window(Parent root) {
        super();

        this.setScene(new Scene(root));
    }

    public void setParameters(Map<String, Object> args) {
        this.mArgs = args;
    }

    public void setController(IController controller) {
        this.mController = controller;
    }

    public IController getController() {
        return this.mController;
    }

    public void render() {
        parseArguments(this.mArgs);

        show();
    }

    public void renderAndWait() {
        parseArguments(this.mArgs);

        showAndWait();
    }

    public void close() {
        super.close();
    }

    private void parseArguments(Map<String, Object> args) {
        if (args.containsKey("title"))
            this.setTitle((String) args.get("title"));

        if (args.containsKey("height"))
            this.setHeight((Double) args.get("height"));
        if (args.containsKey("width"))
            this.setHeight((Double) args.get("width"));

        if (args.containsKey("min_height"))
            this.setMinHeight((Double) args.get("min_height"));
        if (args.containsKey("min_width"))
            this.setMinWidth((Double) args.get("min_width"));

        if (args.containsKey("resizable"))
            this.setResizable((Boolean) args.get("resizable"));
    }
}

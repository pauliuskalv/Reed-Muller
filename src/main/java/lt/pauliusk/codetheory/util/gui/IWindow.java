package lt.pauliusk.codetheory.util.gui;

import java.util.Map;

public interface IWindow {
    void setParameters(Map<String, Object> args);

    void setController(IController controller);
    IController getController();

    void render();
    void renderAndWait();

    void close();
}

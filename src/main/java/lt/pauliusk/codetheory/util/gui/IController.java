package lt.pauliusk.codetheory.util.gui;

import java.util.Map;

public interface IController {
    boolean hasParentWindow();
    void setParentWindow(IWindow window);
    IWindow getParentWindow();

    void setParameters(Map<String, Object> args);
}

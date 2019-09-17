package lt.pauliusk.codetheory.util.gui;

import java.util.Map;

public interface IWindowLoader {
    IWindow getWindow(String name);
    IWindow getWindow(String name, Map<String, Object> args);
}

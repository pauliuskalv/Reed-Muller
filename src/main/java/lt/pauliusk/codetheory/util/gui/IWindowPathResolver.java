package lt.pauliusk.codetheory.util.gui;

import java.util.Map;

public interface IWindowPathResolver {
    String resolveToPath(String name);

    String resolveToWindowTitle(String name);

    String resolveToWindowName(String name);

    Double resolveToWindowWidth(String name);
    Double resolveToWindowHeight(String name);

    Double resolveToMinWindowWidth(String name);
    Double resolveToMinWindowHeight(String name);

    Map<String, Object> resolveAllParameters(String name);
}

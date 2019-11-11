package lt.pauliusk.codetheory.util.gui.impl;

import lt.pauliusk.codetheory.util.gui.IWindowPathResolver;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WindowPathResolver implements IWindowPathResolver {
    private Logger mLogger = Logger.getLogger(WindowPathResolver.class.getName());

    private JSONArray mWindowDefinitions;

    public WindowPathResolver() throws IOException {
        mWindowDefinitions = new JSONArray(new String(getClass().getResource("/gui/window.config").openStream().readAllBytes()));
    }

    @Override
    public String resolveToPath(String name) {
        return (String) resolveParameter(name, "path");
    }

    @Override
    public String resolveToWindowTitle(String name) {
        return (String) resolveParameter(name, "title");
    }

    @Override
    public String resolveToWindowName(String name) {
        return (String) resolveParameter(name, "name");
    }

    @Override
    public Double resolveToWindowWidth(String name) {
        return (Double) resolveParameter(name, "width");
    }

    @Override
    public Double resolveToWindowHeight(String name) {
        return (Double) resolveParameter(name, "height");
    }

    @Override
    public Double resolveToMinWindowWidth(String name) {
        return (Double) resolveParameter(name, "min_width");
    }

    @Override
    public Double resolveToMinWindowHeight(String name) {
        return (Double) resolveParameter(name, "min_height");
    }

    @Override
    public Map<String, Object> resolveAllParameters(String name) {
        Map<String, Object> toReturn = new HashMap<>();
        JSONObject obj = getJSONObjectByQualifier(name);

        if (obj != null) {
            for (String key : obj.keySet()) {
                toReturn.put(key, obj.get(key));
            }
        }

        return toReturn;
    }

    private Object resolveParameter(String name, String parameter) {
        JSONObject obj = getJSONObjectByQualifier(name);

        if (obj != null)
            return obj.get(parameter);

        return null;
    }

    private JSONObject getJSONObjectByQualifier(String qualifier) {
        for (int i = 0; i < mWindowDefinitions.length(); i ++) {
            JSONObject obj = mWindowDefinitions.getJSONObject(i);

            if (obj.get("qualifier").equals(qualifier))
                return obj;
        }

        mLogger.log(Level.SEVERE, "Window with name " + qualifier + " not found");

        return null;
    }
}

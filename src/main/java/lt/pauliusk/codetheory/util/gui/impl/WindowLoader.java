package lt.pauliusk.codetheory.util.gui.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import lt.pauliusk.codetheory.util.gui.IWindow;
import lt.pauliusk.codetheory.util.gui.IWindowLoader;
import lt.pauliusk.codetheory.util.gui.IWindowPathResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WindowLoader implements IWindowLoader {
    @Autowired
    private GenericApplicationContext mContext;
    @Autowired
    private IWindowPathResolver mWindowPathResolver;

    private Logger mLogger = Logger.getLogger(WindowLoader.class.getName());

    public IWindow getWindow(String name) {
        try {
            IWindow window = createWindowFromPath(mWindowPathResolver.resolveToPath(name));
            window.setParameters(mWindowPathResolver.resolveAllParameters(name));

            return window;
        } catch (IOException e) {
            mLogger.log(Level.SEVERE, "An exception occurred while loading a window from a layout file.", e);

            return null;
        }
    }

    public IWindow getWindow(String name, Map<String, Object> args) {
        try {
            IWindow window = createWindowFromPath(mWindowPathResolver.resolveToPath(name));
            window.setParameters(args);

            return window;
        } catch (IOException e) {
            mLogger.log(Level.SEVERE, "An exception occurred while loading a window from a layout file.", e);

            return null;
        }
    }

    private IWindow createWindowFromPath(String path) throws IOException {
        FXMLLoader loader = createFXMLLoader(path);
        Parent layout = loader.load();
        IWindow window = new Window(layout);

        window.setController(loader.getController());

        // Also set the parent window for main controller
        window.getController().setParentWindow(window);

        return window;
    }

    private FXMLLoader createFXMLLoader(String path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.setControllerFactory(mContext::getBean);

        return loader;
    }
}

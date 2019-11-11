package lt.pauliusk.codetheory.util.gui.impl;

import lt.pauliusk.codetheory.util.gui.IController;
import lt.pauliusk.codetheory.util.gui.IWindow;

public abstract class AbstractController implements IController {
    private IWindow mParentWindow;

    @Override
    public boolean hasParentWindow() {
        return mParentWindow != null;
    }

    @Override
    public void setParentWindow(IWindow window) {
        this.mParentWindow = window;
    }

    @Override
    public IWindow getParentWindow() {
        return mParentWindow;
    }
}

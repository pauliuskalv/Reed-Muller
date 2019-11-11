package lt.pauliusk.codetheory.util.gui.javafx;

import javafx.scene.control.TableView;

public interface IStringBooleanConverter {
    String convertFromBooleanArray(boolean[][] booleans);
    boolean[][] convertFromString(String booleans);
}

package lt.pauliusk.codetheory.util.gui.javafx;

/**
 * Converts the specified strings to boolean arrays and boolean arrays back to strings
 */
public interface IStringBooleanConverter {
    /**
     * Convert the boolean 2d array to a string representation
     * @param booleans the boolean 2d array
     * @return the converted string representation
     */
    String convertFromBooleanArray(boolean[][] booleans);

    /**
     * Convert the string representation back to a 2d boolean array
     * @param booleans the string representation of the 2d boolean array
     * @return the converted 2d boolean array
     */
    boolean[][] convertFromString(String booleans);
}

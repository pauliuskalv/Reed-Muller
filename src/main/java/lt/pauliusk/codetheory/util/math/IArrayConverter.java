package lt.pauliusk.codetheory.util.math;

/**
 * Performs conversion of various types of arrays
 */
public interface IArrayConverter {
    /**
     * Convert a boolean array to an integer array
     * Each boolean is converted to an integer representation
     * @param array the specified boolean array
     * @return the converted integer array
     */
    int[] convertFromBooleanArrayDistinct(boolean[] array);

    /**
     * Convert an integer array to a boolean array
     * Each integer is converted to it's boolean representation
     * @param array the integer array
     * @return the converted boolean array
     */
    boolean[] convertFromIntArrayDistinct(int[] array);

    /**
     * Convert the boolean array to bytes
     * Each 8 bit sequence is constructed into a singular byte
     *
     * The resulting array is 8 times smaller than argument array
     * @param array the boolean array
     * @return the converted byte array
     */
    byte[] convertFromBooleanArray(boolean[] array);

    /**
     * Convert a 2d boolean array to a byte array
     * Each 8 bit sequence is constructed into a singular byte
     *
     * The resulting array, counting all distinct members in both dimensions, is 8 times smaller
     * @param array the boolean 2d array
     * @return the converted byte array
     */
    byte[] convertFromBoolean2DArray(boolean[][] array);

    /**
     * Convert a byte array to a boolean array
     * Each byte is split into 8 booleans
     *
     * The resulting array, counting all distinct members in both dimensions, is 8 times bigger
     * @param array the byte array
     * @return the converted boolean array
     */
    boolean[] convertFromByteArray(byte[] array);

    /**
     * Convert a 2d boolean array to a 2d integer array
     * @param array the boolean 2d array
     * @return the converted integer array
     */
    int[][] convertToIntArray(boolean[][] array);

    /**
     * Convert a 2d integer array to a 2d boolean array
     * @param array the integer 2d array
     * @return the converted boolean 2d array
     */
    boolean[][] convertToBooleanArray(int[][] array);

    /**
     * Convert a binary string to a boolean array
     * @param string the binary string
     * @return the converted boolean array
     */
    boolean[] binaryStringToBooleanArray(String string);
}

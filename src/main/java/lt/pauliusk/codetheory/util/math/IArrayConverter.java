package lt.pauliusk.codetheory.util.math;

public interface IArrayConverter {
    int[] convertFromBooleanArrayDistinct(boolean[] array);
    boolean[] convertFromIntArrayDistinct(int[] array);

    byte[] convertFromBooleanArray(boolean[] array);
    byte[] convertFromBoolean2DArray(boolean[][] array);
    boolean[] convertFromByteArray(byte[] array);

    int[][] convertToIntArray(boolean[][] array);
    boolean[][] convertToBooleanArray(int[][] array);

    boolean[] binaryStringToBooleanArray(String string);
}

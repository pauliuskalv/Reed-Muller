package lt.pauliusk.codetheory.util.math.impl;

import lt.pauliusk.codetheory.util.math.IArrayConverter;
import org.springframework.stereotype.Component;

@Component
public class ArrayConverter implements IArrayConverter {
    @Override
    public int[] convertFromBooleanArrayDistinct(boolean[] array) {
        int[] toReturn = new int[array.length];

        for (int i = 0; i < toReturn.length; i ++) {
            toReturn[i] = array[i] ? 1 : 0;
        }

        return toReturn;
    }

    @Override
    public boolean[] convertFromIntArrayDistinct(int[] array) {
        boolean[] toReturn = new boolean[array.length];

        for (int i = 0; i < toReturn.length; i ++) {
            toReturn[i] = array[i] % 2 != 0;
        }

        return toReturn;
    }

    @Override
    public byte[] convertFromBooleanArray(boolean[] array) {
        int length = array.length - array.length % 8;
        byte[] toReturn = new byte[length / 8];

        for (int i = 0; i*8 < length; i ++) {
            byte value = 0;

            for (int j = 0; j < 8; j ++) {
                value <<= 1;
                value += array[i*8 + j] ? 1 : 0;
            }

            toReturn[i] = value;
        }

        return toReturn;
    }

    @Override
    public byte[] convertFromBoolean2DArray(boolean[][] array) {
        boolean[] oneDimensional = new boolean[array.length * array[0].length];

        for (int i = 0, k = 0; i < oneDimensional.length; i += array[0].length, k ++) {
            for (int j = 0; j < array[k].length; j ++) {
                oneDimensional[i + j] = array[k][j];
            }
        }

        return convertFromBooleanArray(oneDimensional);
    }

    @Override
    public boolean[] convertFromByteArray(byte[] array) {
        int length = array.length;
        boolean[] toReturn = new boolean[length * 8];

        for (int i = 0; i < length; i ++) {
            int byteValue = array[i];

            for (int j = 0; j < 8; j ++) {
                toReturn[i*8 + j] = (byteValue & 128) != 0;
                byteValue <<= 1;
            }
        }

        return toReturn;
    }

    @Override
    public int[][] convertToIntArray(boolean[][] array) {
        int length = array.length;
        int[][] toReturn = new int[length][];

        for (int i = 0; i < length; i ++) {
            toReturn[i] = new int[array[i].length];

            for (int j = 0; j < array[i].length; j ++) {
                toReturn[i][j] = array[i][j] ? 1 : 0;
            }
        }

        return toReturn;
    }

    @Override
    public boolean[][] convertToBooleanArray(int[][] array) {
        int length = array.length;
        boolean[][] toReturn = new boolean[length][];

        for (int i = 0; i < length; i ++) {
            toReturn[i] = new boolean[array[i].length];

            for (int j = 0; j < array[i].length; j ++) {
                toReturn[i][j] = array[i][j] > 0;
            }
        }

        return toReturn;
    }

    @Override
    public boolean[] binaryStringToBooleanArray(String string) {
        boolean[] toReturn = new boolean[string.length()];

        for (int i = 0; i < string.length(); i ++) {
            toReturn[i] = string.charAt(i) == '1';
        }

        return toReturn;
    }
}

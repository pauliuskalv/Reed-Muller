package lt.pauliusk.codetheory.communicator.impl;

import lt.pauliusk.codetheory.communicator.IDecoder;
import lt.pauliusk.codetheory.util.math.IArrayConverter;
import lt.pauliusk.codetheory.util.math.IHadamardMatrix;
import lt.pauliusk.codetheory.util.math.IMatrix;
import lt.pauliusk.codetheory.util.math.impl.Matrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Decoder implements IDecoder {
    @Autowired
    private IArrayConverter mArrayConverter;
    @Autowired
    private IHadamardMatrix mHadamardMatrix;

    @Override
    public boolean[] decodeData(boolean[] data, int m) {
        int[] integerValues = mArrayConverter.convertFromBooleanArrayDistinct(data);

        integerValues = switchZeroesWithMinusOne(integerValues);
        IMatrix integerMatrix = new Matrix(new int[][] { integerValues });

        for (int i = 1; i <= m; i ++) {
            integerMatrix = mHadamardMatrix.generate(
                    i, m
            ).multiply(
                    integerMatrix.transpose()
            ).transpose();
        }

        int[] result = integerMatrix.getData()[0];

        int min = result[0], max = result[0], minPos = 0, maxPos = 0;
        for (int i = 0; i < result.length; i ++) {
            if (min > result[i]) {
                min = result[i];
                minPos = i;
            }

            if (max < result[i]) {
                max = result[i];
                maxPos = i;
            }
        }

        int val = Math.abs(max) > Math.abs(min) ? max : min;
        int sign = val > 0 ? 1 : 0;

        int[] reversed = toBinaryFormAndReverse(val == max ? maxPos : minPos, m);
        int[] toReturn = new int[m + 1];
        toReturn[0] = val > 0 ? 1 : 0;
        System.arraycopy(reversed, 0, toReturn, 1, reversed.length);

        return mArrayConverter.convertFromIntArrayDistinct(toReturn);
    }

    private int[] switchZeroesWithMinusOne(int[] arr) {
        int[] ret = new int[arr.length];

        for (int i = 0; i < arr.length; i ++) {
            ret[i] = arr[i] == 0 ? -1 : 1;
        }

        return ret;
    }

    private int[] toBinaryFormAndReverse(int number, int length) {
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(number));
        int[] ret = new int[length];

        for (int i = builder.length(); i < length; i ++) {
            builder.insert(0, "0");
        }

        for (int i = 0; i < builder.length(); i ++) {
            ret[length - 1 - i] = Character.getNumericValue(
                    builder.charAt(i)
            );
        }

        return ret;
    }
}

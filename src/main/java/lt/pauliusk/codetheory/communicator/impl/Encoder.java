package lt.pauliusk.codetheory.communicator.impl;

import lt.pauliusk.codetheory.communicator.IEncoder;
import lt.pauliusk.codetheory.util.math.IArrayConverter;
import lt.pauliusk.codetheory.util.math.IGenerativeMatrix;
import lt.pauliusk.codetheory.util.math.IMatrix;
import lt.pauliusk.codetheory.util.math.impl.Matrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Encoder implements IEncoder {
    @Autowired
    private IGenerativeMatrix mGenerativeMatrix;
    @Autowired
    private IArrayConverter mArrayConverter;

    @Override
    public boolean[] encodeData(boolean[] data, int m) {
        int[] integerValues = mArrayConverter.convertFromBooleanArrayDistinct(data);

        IMatrix generativeMatrix = mGenerativeMatrix.generate(m).transpose();
        IMatrix dataMatrix = new Matrix(new int[][] { integerValues }).transpose();

        int[] result = generativeMatrix.multiply(
                dataMatrix
        ).transpose().getData()[0];

        return mArrayConverter.convertFromIntArrayDistinct(
                generativeMatrix.multiply(
                        dataMatrix
                ).transpose().getData()[0]
        );
    }
}

package lt.pauliusk.codetheory.util.math.impl;

import lt.pauliusk.codetheory.util.math.IGenerativeMatrix;
import lt.pauliusk.codetheory.util.math.IMatrix;
import org.springframework.stereotype.Component;

@Component
public class GenerativeMatrix implements IGenerativeMatrix {
    @Override
    public IMatrix generate(int m) {
        int rowCount = m + 1;
        int columnCount = (int) Math.pow(2, m);

        IMatrix matrix = new Matrix(rowCount, columnCount);

        for (int i = 0; i < rowCount; i ++) {
            for (int j = 0; j < columnCount; j ++) {
                int value;

                if (i == 0) {
                    value = 1;
                } else {
                    value = (j / ((int)Math.pow(2, i - 1))) % 2;
                }

                matrix.getData()[i][j] = value;
            }
        }

        return matrix;
    }
}

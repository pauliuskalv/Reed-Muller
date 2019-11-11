package lt.pauliusk.codetheory.util.math.impl;

import lt.pauliusk.codetheory.util.math.IKroeneckerProduct;
import lt.pauliusk.codetheory.util.math.IMatrix;

import org.springframework.stereotype.Component;

@Component
public class KroeneckerProduct implements IKroeneckerProduct {
    @Override
    public IMatrix generateBase(IMatrix matrix, int size) {
        int matrixColumnCount = matrix.getColumnCount();
        int matrixRowCount = matrix.getRowCount();

        int width = matrixColumnCount * size;
        int height = matrixRowCount * size;

        int[][] toReturnMatrix = new int[height][width];
        for (int i = 0; i < height; i ++) {
            for (int j = 0; j < width; j ++) {
                int value = (i / matrixRowCount == j / matrixColumnCount) ? 1 : 0;

                toReturnMatrix[i][j] = matrix.getData()[i % matrixRowCount][j % matrixColumnCount] * value;
            }
        }

        return new Matrix(toReturnMatrix);
    }

    @Override
    public IMatrix generate(IMatrix matrix, int size) {
        int width = matrix.getColumnCount() * size;
        int height = matrix.getRowCount() * size;

        int[][] toReturnMatrix = new int[height][width];
        for (int i = 0; i < height; i ++) {
            for (int j = 0; j < width; j ++) {
                int value = matrix.getData()[i / size][j / size];

                toReturnMatrix[i][j] = (i % size == j % size) ? value : 0;
            }
        }

        return new Matrix(toReturnMatrix);
    }
}

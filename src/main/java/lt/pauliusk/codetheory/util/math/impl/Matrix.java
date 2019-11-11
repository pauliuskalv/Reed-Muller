package lt.pauliusk.codetheory.util.math.impl;

import lt.pauliusk.codetheory.util.math.IMatrix;

public class Matrix implements IMatrix {
    private int mRowCount;
    private int mColumnCount;

    private int[][] mData;

    Matrix(int rowCount, int colCount) {
        mRowCount = rowCount;
        mColumnCount = colCount;

        mData = new int[rowCount][colCount];
    }

    public Matrix(int[][] data) {
        mRowCount = data.length;
        mColumnCount = data[0].length;

        mData = new int[mRowCount][mColumnCount];

        for (int i = 0; i < mData.length; i ++) {
            System.arraycopy(data[i], 0, mData[i], 0, mColumnCount);
        }
    }

    @Override
    public int getColumnCount() {
        return mColumnCount;
    }

    @Override
    public int getRowCount() {
        return mRowCount;
    }

    @Override
    public int[][] getData() {
        return mData;
    }

    @Override
    public IMatrix multiply(IMatrix matrix) {
        if (mColumnCount != matrix.getRowCount())
            return null;

        IMatrix newMatrix = new Matrix(mRowCount, matrix.getColumnCount());

        for (int i = 0; i < newMatrix.getRowCount(); i++) {
            for (int j = 0; j < newMatrix.getColumnCount(); j++) {
                for (int k = 0; k < mColumnCount; k++) {
                    newMatrix.getData()[i][j] += (mData[i][k] * matrix.getData()[k][j]);
                }
            }
        }

        return newMatrix;
    }

    @Override
    public IMatrix transpose() {
        IMatrix toReturnTransposed = new Matrix(mColumnCount, mRowCount);

        for (int i = 0; i < mRowCount; i++) {
            for (int j = 0; j < mColumnCount; j++) {
                toReturnTransposed.getData()[j][i] = mData[i][j];
            }
        }

        return toReturnTransposed;
    }
}

package lt.pauliusk.codetheory.util.math.impl;

import lt.pauliusk.codetheory.util.math.IMatrix;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Matrix implements IMatrix {
    private int mRowCount;
    private int mColumnCount;

    private int[][] mData;

    public Matrix(int rowCount, int colCount) {
        mData = new int[rowCount][colCount];
    }

    public Matrix(int[][] data) {
        mRowCount = data.length;
        mColumnCount = data[0].length;

        mData = new int[mRowCount][mColumnCount];

        for (int i = 0; i < mData.length; i ++) {
            System.arraycopy(mData[i], 0, mData[i], 0, mColumnCount);
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

        return null;
    }

    @Override
    public IMatrix kroneckerProduct(IMatrix matrix) {
        return null;
    }

    @Override
    public Iterator<int[]> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super int[]> action) {

    }

    @Override
    public Spliterator<int[]> spliterator() {
        return null;
    }
}

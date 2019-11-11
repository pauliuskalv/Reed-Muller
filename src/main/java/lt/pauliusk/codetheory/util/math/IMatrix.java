package lt.pauliusk.codetheory.util.math;

/**
 * Defines a matrix, which can be multiplied and transposed
 */
public interface IMatrix {
    /**
     * Return the column count of the matrix (also known as its length)
     * @return
     */
    int getColumnCount();

    /**
     * Return the row count of the matrix (also known as its height)
     * @return
     */
    int getRowCount();

    /**
     * Returns the data stored in the matrix
     * @return
     */
    int[][] getData();

    /**
     * Multiplies this matrix with the given matrix Matrix
     * @param matrix the matrix, which is multiplied
     * @return the multiplication result
     */
    IMatrix multiply(IMatrix matrix);

    /**
     * Transpose the matrix
     *
     * Rows become columns and columns become rows
     * @return the transposed matrix
     */
    IMatrix transpose();
}

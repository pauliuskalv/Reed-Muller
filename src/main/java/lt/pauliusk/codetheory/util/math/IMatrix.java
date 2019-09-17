package lt.pauliusk.codetheory.util.math;

public interface IMatrix extends Iterable<int[]> {
    int getColumnCount();
    int getRowCount();

    int[][] getData();

    IMatrix multiply(IMatrix matrix);
    IMatrix kroneckerProduct(IMatrix matrix);
}

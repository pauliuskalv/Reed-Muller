package lt.pauliusk.codetheory.util.math;

public interface IMatrixFactory {
    IMatrix create(int cols, int rows);
    IMatrix create(int[][] data);
}

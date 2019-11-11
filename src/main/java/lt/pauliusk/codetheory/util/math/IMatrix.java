package lt.pauliusk.codetheory.util.math;

public interface IMatrix {
    int getColumnCount();
    int getRowCount();

    int[][] getData();

    IMatrix multiply(IMatrix matrix);
    IMatrix transpose();
}

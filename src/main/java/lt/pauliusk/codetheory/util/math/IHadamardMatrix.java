package lt.pauliusk.codetheory.util.math;

/**
 * Creates a hadamard matrix using the specified
 */
public interface IHadamardMatrix {
    /**
     * Generates a hadamard matrix using the given i and m values
     * @param i
     * @param m
     * @return the generated hadamard matrix
     */
    IMatrix generate(int i, int m);
}

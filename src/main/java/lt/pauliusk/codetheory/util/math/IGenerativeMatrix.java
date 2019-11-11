package lt.pauliusk.codetheory.util.math;

/**
 * Generates a generative matrix using the given m value
 *
 * m must be bigger than 1
 */
public interface IGenerativeMatrix {
    /**
     * Generate a generative matrix using the given m
     * @param m the m value
     * @return a generative matrix
     */
    IMatrix generate(int m);
}

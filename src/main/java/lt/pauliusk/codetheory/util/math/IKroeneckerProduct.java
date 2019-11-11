package lt.pauliusk.codetheory.util.math;

/**
 * Generates a kroenecker product matrix from the given matrix
 */
public interface IKroeneckerProduct {
    IMatrix generateBase(IMatrix matrix, int size);
    IMatrix generate(IMatrix matrix, int size);
}

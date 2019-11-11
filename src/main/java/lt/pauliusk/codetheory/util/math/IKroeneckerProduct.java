package lt.pauliusk.codetheory.util.math;

public interface IKroeneckerProduct {
    IMatrix generateBase(IMatrix matrix, int size);
    IMatrix generate(IMatrix matrix, int size);
}

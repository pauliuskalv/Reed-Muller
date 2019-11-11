package lt.pauliusk.codetheory.communicator;

/**
 * Defines an abstract encoded, which can encode data using the value m
 */
public interface IEncoder {
    /**
     * Encode the given data using the value m
     * @param data the data, which is to be encoded
     * @param m the value m, which is used in the encoding process
     * @return encoded bits
     */
    boolean[] encodeData(boolean[] data, int m);
}

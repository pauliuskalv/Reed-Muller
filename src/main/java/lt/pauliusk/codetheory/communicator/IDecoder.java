package lt.pauliusk.codetheory.communicator;

/**
 * Defines an abstract decoder
 *
 * Decodes the given bits using the value m
 */
public interface IDecoder {
    /**
     * Decode the given data using the value m
     * @param data the data, which is to be decoded
     * @param m the value m, which is used in the decoding process
     * @return decoded bits
     */
    boolean[] decodeData(boolean[] data, int m);
}

package lt.pauliusk.codetheory.communicator;

import java.util.List;

/**
 * Defines an abstract communicator, which contains an encoder, an abstract channel and a decoder
 *
 * The lifecycle of the communicator is as follows:
 * 1.) Vectors need to be built by passing a byte array and an m value
 * 2.) The built vectors are encoded before transmission
 * 3.) The encoded vectors are transmitted through the abstract channel and received
 * 4.) The received vectors are decoded
 */
public interface ICommunicator {
    /**
     * Build vectors using the specified byte array
     *
     * Vectors are built by constructing an array of m + 1 length bit sequences
     * If the length of the array is reached, zero bits are appended
     * @param data the bytes, from which the vectors are to be built
     * @param m the m value, which is used in defining the size of bit sequences
     * @return an array of constructed bit sequences, all of which are of m + 1 length
     */
    boolean[][] buildVectors(byte[] data, int m);

    /**
     * Encode the specified vectors using the value m
     * @param data the vectors, which are to be encoded
     * @param m the value m, which is used in the encoding process
     * @return an array of encoded vectors
     */
    boolean[][] encode(boolean[][] data, int m);

    /**
     * Transmit the bits through the channel, which has an error rate of errorRate
     * @param data the bits, which are to be transmitted
     * @param errorRate the channel error rate. If reached, the bit's value changes while in transit
     * @return bits, which are received from the channel
     */
    boolean[][] transmit(boolean[][] data, double errorRate);

    /**
     * Gets the messages, which are generated while bits are being transmitted through the channel
     * @return a list of messages of type String
     */
    List<String> getMessages();

    /**
     * Decode the given data using the value m
     * @param data the bits, which are to be decoded
     * @param m the value m, which is used in the decoding process
     * @return decoded bits
     */
    boolean[][] decode(boolean[][] data, int m);
}

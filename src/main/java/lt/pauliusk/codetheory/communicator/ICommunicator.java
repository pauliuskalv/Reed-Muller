package lt.pauliusk.codetheory.communicator;

import java.util.List;

public interface ICommunicator {
    boolean[][] buildVectors(byte[] data, int m);

    boolean[][] encode(boolean[][] data, int m);

    boolean[][] transmit(boolean[][] data, double errorRate);
    List<String> getMessages();

    boolean[][] decode(boolean[][] data, int m);
}

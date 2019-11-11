package lt.pauliusk.codetheory.communicator;

import java.util.List;

public interface IChannel {
    boolean[] transmit(boolean[] original, double errorRate, int row);

    void resetMessages();
    List<String> getMessages();
}

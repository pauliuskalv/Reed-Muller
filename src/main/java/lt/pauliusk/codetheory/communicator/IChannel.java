package lt.pauliusk.codetheory.communicator;

import java.util.List;

/**
 * Defines an abstract channel, through which bits travel
 * While in transit, bits can change their values (if the specified error rate is reached)
 *
 * When the transmit function completes, events through transmission are collected and can be
 * retrieved using the "getMessages()" function
 */
public interface IChannel {
    /**
     * Transmit the specified bits through the channel with the specified error rate
     * @param original original bits, which are to be transmitted
     * @param errorRate the error rate, which, if reached, will change the transmitted bit's value
     * @param row used for constructing events through transmission (for row and column information)
     * @return transmitted bits
     */
    boolean[] transmit(boolean[] original, double errorRate, int row);

    /**
     * Resets the collected messages
     */
    void resetMessages();

    /**
     * Gets the collected messages, which are accumulated while transmitting bits
     * @return messages, accumulated while transmitting bits
     */
    List<String> getMessages();
}

package lt.pauliusk.codetheory.communicator.impl;

import lt.pauliusk.codetheory.communicator.IChannel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Channel implements IChannel {
    private Random mRandom = new Random();
    private List<String> mMessages;

    @Override
    public boolean[] transmit(boolean[] original, double errorRate, int row) {
        boolean[] transmitted = new boolean[original.length];

        for (int i = 0; i < original.length; i ++) {
            if (mRandom.nextDouble() < errorRate) {
                mMessages.add("Bit shifted at row: " + row + " column: " + i);
                transmitted[i] = !original[i];
            } else {
                transmitted[i] = original[i];
            }
        }

        return transmitted;
    }

    @Override
    public void resetMessages() {
        mMessages = new ArrayList<>();
    }

    @Override
    public List<String> getMessages() {
        return mMessages;
    }
}

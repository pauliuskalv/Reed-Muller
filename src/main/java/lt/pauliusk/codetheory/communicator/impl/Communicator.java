package lt.pauliusk.codetheory.communicator.impl;

import lt.pauliusk.codetheory.communicator.IChannel;
import lt.pauliusk.codetheory.communicator.ICommunicator;
import lt.pauliusk.codetheory.communicator.IDecoder;
import lt.pauliusk.codetheory.communicator.IEncoder;
import lt.pauliusk.codetheory.util.math.IArrayConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communicator implements ICommunicator {
    @Autowired
    private IArrayConverter mArrayConverter;

    @Autowired
    private IEncoder mEncoder;
    @Autowired
    private IChannel mChannel;
    @Autowired
    private IDecoder mDecoder;

    @Override
    public boolean[][] buildVectors(byte[] data, int m) {
        boolean[] dataBits = mArrayConverter.convertFromByteArray(data);

        int columnCount = m + 1;
        int rowCount = dataBits.length % columnCount == 0 ? dataBits.length / columnCount : (dataBits.length / columnCount) + 1;

        boolean[][] ret = new boolean[rowCount][columnCount];

        for (int i = 0; i < rowCount; i ++) {
            for (int j = 0; j < columnCount; j ++) {
                if ((i*columnCount + j) < dataBits.length) {
                    ret[i][j] = dataBits[i*columnCount + j];
                } else {
                    ret[i][j] = false;
                }
            }
        }

        return ret;
    }

    @Override
    public boolean[][] encode(boolean[][] data, int m) {
        boolean[][] ret = new boolean[data.length][];

        for (int i = 0; i < data.length; i ++) {
            ret[i] = mEncoder.encodeData(data[i], m);
        }

        return ret;
    }

    @Override
    public boolean[][] transmit(boolean[][] data, double errorRate) {
        boolean[][] ret = new boolean[data.length][];

        mChannel.resetMessages();
        for (int i = 0; i < data.length; i ++) {
            ret[i] = mChannel.transmit(data[i], errorRate, i);
        }

        return ret;
    }

    @Override
    public List<String> getMessages() {
        return mChannel.getMessages();
    }

    @Override
    public boolean[][] decode(boolean[][] data, int m) {
        boolean[][] ret = new boolean[data.length][];

        for (int i = 0; i < ret.length; i ++) {
            ret[i] = mDecoder.decodeData(data[i], m);
        }

        return ret;
    }
}

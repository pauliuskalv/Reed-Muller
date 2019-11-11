package lt.pauliusk.codetheory.util.file.impl;

import lt.pauliusk.codetheory.util.file.IBMPLoader;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class BMPLoader implements IBMPLoader {
    @Override
    public byte[] loadHeader(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);

        // Read the bmp file header for later use
        byte[] toRead = new byte[54];

        inputStream.readNBytes(toRead, 0, 54);

        return toRead;
    }

    @Override
    public byte[] load(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);

        inputStream.skip(54);

        // Read everything except the file header
        byte[] toRead = new byte[(int) (file.length() - 54)];

        inputStream.readNBytes(toRead, 0, toRead.length);

        return toRead;
    }
}

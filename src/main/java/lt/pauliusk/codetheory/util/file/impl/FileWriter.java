package lt.pauliusk.codetheory.util.file.impl;

import lt.pauliusk.codetheory.util.file.IFileWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class FileWriter implements IFileWriter {
    @Override
    public void write(File file, byte[] bytes) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);

        outputStream.write(bytes, 0, bytes.length);
    }
}

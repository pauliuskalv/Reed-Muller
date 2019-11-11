package lt.pauliusk.codetheory.util.file;

import java.io.File;
import java.io.IOException;

public interface IFileWriter {
    void write(File file, byte[] bytes) throws IOException;
}

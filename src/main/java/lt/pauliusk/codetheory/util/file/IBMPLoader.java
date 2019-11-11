package lt.pauliusk.codetheory.util.file;

import java.io.File;
import java.io.IOException;

public interface IBMPLoader {
    byte[] loadHeader(File file) throws IOException;
    byte[] load(File file) throws IOException;
}

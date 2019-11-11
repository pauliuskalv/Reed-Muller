package lt.pauliusk.codetheory.util.file;

import java.io.File;
import java.io.IOException;

/**
 * Defines an abstract file writer, which can write the passed bytes to the specified file
 */
public interface IFileWriter {
    /**
     * Writes the given bytes to the specified file
     * @param file the file, which is to be written
     * @param bytes the content of the file
     * @throws IOException if the specified file does not exist or an error occurs while writing it
     */
    void write(File file, byte[] bytes) throws IOException;
}

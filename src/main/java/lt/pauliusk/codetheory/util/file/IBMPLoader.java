package lt.pauliusk.codetheory.util.file;

import java.io.File;
import java.io.IOException;

/**
 * Defines an abstract .bmp format file reader, which can read both the bmp file header and content
 */
public interface IBMPLoader {
    /**
     * Read the .bmp file header from the specified file
     * @param file the file, from which the .bmp header is read
     * @return the .bmp file header content, which is 54 bytes long
     * @throws IOException if the specified file does not exist or an error occurs while reading it
     */
    byte[] loadHeader(File file) throws IOException;

    /**
     * Read the .bmp file content, reading everything from the specified file except the first 54 bytes, which are the .bmp file's header content
     * @param file the file, from which the .bmp content is read
     * @return the .bmp file content
     * @throws IOException if the specified file does not exist or an error occurs while reading it
     */
    byte[] load(File file) throws IOException;
}

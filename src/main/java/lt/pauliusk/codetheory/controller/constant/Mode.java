package lt.pauliusk.codetheory.controller.constant;

/**
 * Declares the working mode, which can be:
 * BMP_FILE: a bmp file is read, encoded, transmitted and decoded
 * BINARY_INPUT: a binary string is encoded, transmitted and decoded
 * TEXT_INPUT: text, which is encoded, transmitted and decoded
 */
public enum Mode {
    BMP_FILE,
    BINARY_INPUT,
    TEXT_INPUT;

    @Override
    public String toString() {
        switch (this) {
            case BMP_FILE:
                return ".bmp file";
            case BINARY_INPUT:
                return "Binary input";
            case TEXT_INPUT:
                return "Text input";
            default:
                return "";
        }
    }
}

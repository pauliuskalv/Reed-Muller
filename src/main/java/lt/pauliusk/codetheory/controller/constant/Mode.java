package lt.pauliusk.codetheory.controller.constant;

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

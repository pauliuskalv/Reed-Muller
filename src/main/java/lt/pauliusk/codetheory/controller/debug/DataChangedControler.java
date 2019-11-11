package lt.pauliusk.codetheory.controller.debug;

public interface DataChangedControler {
    void dataChanged(byte[] bytes);
    void dataChanged(byte[] bytes, byte[] header);
}

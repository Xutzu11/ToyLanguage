package exc;

public class FileAlreadyClosedException extends MyException {
    public FileAlreadyClosedException(String filename) {
        super("File " + filename + " is already closed.");
    }
}

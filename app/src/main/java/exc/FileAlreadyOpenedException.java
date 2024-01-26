package exc;

public class FileAlreadyOpenedException extends MyException {
    public FileAlreadyOpenedException(String filename) {
        super("File " + filename + " is already opened.");
    }
}

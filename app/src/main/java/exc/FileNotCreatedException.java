package exc;

public class FileNotCreatedException extends MyException {
    public FileNotCreatedException(String filename) {
        super("File " + filename + " was not found.");
    }
}

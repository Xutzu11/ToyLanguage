package exc;

public class RepoStateException extends MyException{
    public RepoStateException() {
        super("Program does not exist.");
    }
}

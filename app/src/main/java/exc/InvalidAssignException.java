package exc;

public class InvalidAssignException extends MyException{
    public InvalidAssignException(String k) {
        super("The type of the variable " + k + " and the assigned expression doesn't match.");
    }
}

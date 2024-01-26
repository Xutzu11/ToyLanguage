package exc;

public class InvalidTypeException extends MyException{
    public InvalidTypeException() {
        super("Invalid value type.");
    }
}

package exc;

public class VariableUndefinedException extends MyException{
    public VariableUndefinedException(String k) {
        super("Variable " + k + " was not defined.");
    }
}

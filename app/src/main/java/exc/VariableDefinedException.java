package exc;

public class VariableDefinedException extends MyException{
    public VariableDefinedException(String k) {
        super("Variable " + k + " is already defined.");
    }
}

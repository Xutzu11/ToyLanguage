package exc;

public class InvalidOperandException extends MyException{
    public InvalidOperandException(String type) {
        super("The operand type must be of " + type + " type.");
    }
}

package exc;

public class EmptyStackException extends MyException{
    public EmptyStackException() {
        super("The execution stack is empty, program is finished.");
    }
}

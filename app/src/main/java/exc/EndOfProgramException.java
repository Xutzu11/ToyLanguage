package exc;

public class EndOfProgramException extends MyException{
    public EndOfProgramException() {
        super("Program is finished.");
    }
}

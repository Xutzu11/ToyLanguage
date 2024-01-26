package exc;

public class InvalidMemoryAccess extends MyException{
    public InvalidMemoryAccess() {
        super("The address does not exist.");
    }
}

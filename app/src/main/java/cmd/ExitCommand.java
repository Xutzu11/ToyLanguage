package cmd;

import controller.Controller;

public class ExitCommand extends Command {
    public ExitCommand(String key, String desc) {
        super(key, desc);
    }
    
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public Controller returnContr() {
        System.exit(0);
        return null;
    }
    
}

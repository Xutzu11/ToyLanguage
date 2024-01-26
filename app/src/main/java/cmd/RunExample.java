package cmd;
import controller.Controller;
import exc.MyException;
import value.Value;

import java.io.IOException;

import adt.MyIList;

public class RunExample extends Command {
    private Controller ctr;
 
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
 
    @Override
    public void execute() {
        try {
            ctr.allStep();
            MyIList < Value > out = ctr.getOutput();
            System.out.println(out.consoleString());
        }
        catch (MyException | IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Controller returnContr() {
        return ctr;
    }

}

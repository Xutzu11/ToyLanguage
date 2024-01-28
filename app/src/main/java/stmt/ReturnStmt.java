package stmt;

import java.io.IOException;
import type.*;
import adt.MyIDict;
import adt.PrgState;
import exc.MyException;

public class ReturnStmt implements IStmt {
    
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        state.getSymTableStack().pop();
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        return typeEnv;
    }  

    @Override
    public String toString() {
        return "return;";
    }
}

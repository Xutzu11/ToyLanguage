package stmt;

import adt.MyIDict;
import adt.PrgState;
import exc.MyException;
import type.*;

public class NopStmt implements IStmt{

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    public String toString() {
        return "NOP";
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
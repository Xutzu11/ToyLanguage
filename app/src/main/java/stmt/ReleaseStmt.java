package stmt;

import java.io.IOException;
import type.*;
import value.IntValue;
import value.Value;
import adt.MyIDict;
import adt.MyICountSemaphore;
import adt.PrgState;
import adt.Pair;
import exc.InvalidMemoryAccess;
import exc.InvalidOperandException;
import exc.MyException;

public class ReleaseStmt implements IStmt {
    private String var;

    public ReleaseStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > tbl = state.getSymTable();
        MyICountSemaphore < Pair > sem = state.getSemaphore();
        int index = ((IntValue)tbl.lookUp(var)).getVal();
        if (!sem.isDefined(index)) throw new InvalidMemoryAccess();
        Pair t = sem.lookup(index);
        if (t.second.contains(state.getId())) {
            t.second.remove(t.second.indexOf(state.getId()));
        }
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type tvar = typeEnv.lookUp(var);
        if (tvar.equals(new IntType())) {
            return typeEnv;
        }
        else throw new InvalidOperandException("int");
    }

    @Override
    public String toString() {
        return "release(" + var + ")";
    }
}

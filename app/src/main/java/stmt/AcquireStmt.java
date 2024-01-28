package stmt;

import java.io.IOException;
import type.*;
import value.IntValue;
import value.Value;
import adt.MyIDict;
import adt.MyIStack;
import adt.MyIToySemaphore;
import adt.PrgState;
import adt.Pair;
import exc.InvalidMemoryAccess;
import exc.InvalidOperandException;
import exc.MyException;

public class AcquireStmt implements IStmt {
    private String var;

    public AcquireStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > tbl = state.getSymTable();
        MyIToySemaphore < Pair > sem = state.getSemaphore();
        MyIStack < IStmt > stk = state.getExeStack();
        int index = ((IntValue)tbl.lookUp(var)).getVal();
        if (!sem.isDefined(index)) throw new InvalidMemoryAccess();
        Pair t = sem.lookup(index);
        if (t.first > t.second.size()) {
            if (!t.second.contains(state.getId())) {
                t.second.add(state.getId());
            }
        }
        else stk.push(this);
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
        return "acquire(" + var + ")";
    }
}

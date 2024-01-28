package stmt;

import java.io.IOException;
import type.*;
import value.IntValue;
import value.Value;
import adt.MyIDict;
import adt.MyIStack;
import adt.MyICyclicBarrier;
import adt.PrgState;
import adt.Pair;
import exc.InvalidMemoryAccess;
import exc.InvalidOperandException;
import exc.MyException;
import exc.VariableUndefinedException;

public class AwaitStmt implements IStmt {
    private String var;

    public AwaitStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > tbl = state.getSymTable();
        MyICyclicBarrier < Pair > bar = state.getBarrier();
        MyIStack < IStmt > stk = state.getExeStack();
        int index = ((IntValue)tbl.lookUp(var)).getVal();
        if (!bar.isDefined(index)) throw new InvalidMemoryAccess();
        Pair t = bar.lookup(index);
        if (t.first > t.second.size()) {
            if (!t.second.contains(state.getId())) {
                t.second.add(state.getId());
            }
            stk.push(this);
        } 
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type tvar = typeEnv.lookUp(var);
        if (tvar == null) throw new VariableUndefinedException(var);
        if (tvar.equals(new IntType())) {
            return typeEnv;
        }
        else throw new InvalidOperandException("int");
    }

    @Override
    public String toString() {
        return "await(" + var + ")";
    }
}

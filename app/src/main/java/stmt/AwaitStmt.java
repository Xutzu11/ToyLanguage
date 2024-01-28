package stmt;

import java.io.IOException;
import type.*;
import adt.MyIDict;
import adt.MyILatch;
import adt.MyIStack;
import adt.PrgState;
import exc.InvalidMemoryAccess;
import exc.InvalidOperandException;
import exc.MyException;
import exc.VariableUndefinedException;
import type.IntType;
import value.*;

public class AwaitStmt implements IStmt{
    private String var;
    
    public AwaitStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > tbl = state.getSymTable();
        MyILatch < Integer > latch = state.getLatch();
        MyIStack < IStmt > stk = state.getExeStack();
        int index = ((IntValue)tbl.lookUp(var)).getVal();
        if (latch.isDefined(index)) {
            if (latch.lookup(index) != 0) {
                stk.push(this);
            }
            return null;
        }
        else throw new InvalidMemoryAccess();
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t = typeEnv.lookUp(var);
        if (t == null) throw new VariableUndefinedException(var);
        if (t.equals(new IntType())) {
            return typeEnv;
        }
        else throw new InvalidOperandException("int");
    }

    @Override
    public String toString() {
        return "await(" + var + ")";
    }
}

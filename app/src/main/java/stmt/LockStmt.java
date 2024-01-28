package stmt;

import java.io.IOException;
import type.*;
import value.IntValue;
import value.Value;
import adt.MyIDict;
import adt.MyIStack;
import adt.MyILock;
import adt.PrgState;
import exc.InvalidMemoryAccess;
import exc.InvalidOperandException;
import exc.MyException;

public class LockStmt implements IStmt {
    private String var;

    public LockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > tbl = state.getSymTable();
        MyILock < Integer > lock = state.getLock();
        MyIStack < IStmt > stk = state.getExeStack();
        int addr = ((IntValue)tbl.lookUp(var)).getVal();
        if (!lock.isDefined(addr)) throw new InvalidMemoryAccess();
        int free = lock.lookup(addr);
        if (free == -1) {
            lock.update(addr, state.getId());
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
        return "lock(" + var + ")";
    }
}

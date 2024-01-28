package stmt;

import java.io.IOException;
import java.util.LinkedList;

import adt.MyIDict;
import adt.MyILock;
import adt.PrgState;
import exc.InvalidOperandException;
import exc.MyException;
import exc.VariableUndefinedException;
import type.*;
import value.IntValue;
import value.Value;

public class NewLockStmt implements IStmt {
    private String var;

    public NewLockStmt(String var) {
        this.var = var; 
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyILock < Integer > lock = state.getLock();
        MyIDict < String, Value > tbl = state.getSymTable();
        int newFreeAdr = lock.allocate(-1);
        tbl.put(var, new IntValue(newFreeAdr));
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
        return "newLock(" + var + ")";
    }
}

package stmt;

import java.io.IOException;
import adt.MyIDict;
import adt.MyILatch;
import adt.PrgState;
import exc.InvalidAssignException;
import exc.InvalidOperandException;
import exc.InvalidTypeException;
import exc.MyException;
import exc.VariableUndefinedException;
import exp.Exp;
import type.*;
import value.IntValue;
import value.Value;

public class NewLatchStmt implements IStmt {
    private String var;
    private Exp e;

    public NewLatchStmt(String var, Exp e) {
        this.var = var; this.e = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyILatch < Integer > latch = state.getLatch();
        MyIDict < String, Value > tbl = state.getSymTable();
        int n = ((IntValue)e.eval(state.getSymTable(), state.getHeap())).getVal();
        if (n<0) throw new InvalidAssignException("positive");
        int newFreeAdr = latch.allocate(n);
        tbl.put(var, new IntValue(newFreeAdr));
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t = e.typecheck(typeEnv);
        Type tvar = typeEnv.lookUp(var);
        if (t.equals(new IntType())) {
            if (tvar == null) throw new VariableUndefinedException(var);
            if (tvar.equals(new IntType())) {
                return typeEnv;
            }
            else throw new InvalidOperandException("int");
        }
        else throw new InvalidTypeException();
    }

    @Override
    public String toString() {
        return "newLatch(" + var + "," + e.toString() + ")";
    }
}
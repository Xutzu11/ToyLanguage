package stmt;

import java.io.IOException;
import type.*;
import adt.MyIDict;
import adt.MyILatch;
import adt.PrgState;
import exc.InvalidOperandException;
import exc.MyException;
import exc.VariableUndefinedException;
import type.IntType;
import value.*;

public class CountdownStmt implements IStmt{
    private String var;
    
    public CountdownStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > tbl = state.getSymTable();
        MyILatch < Integer > latch = state.getLatch();
        int index = ((IntValue)tbl.lookUp(var)).getVal();
        if (latch.isDefined(index)) {
            if (latch.lookup(index) > 0) {
                latch.update(index, latch.lookup(index)-1);
                state.getOut().add(new IntValue(state.getId()));
            }
            return null;
        }
        return null;
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
        return "countdown(" + var + ")";
    }
}

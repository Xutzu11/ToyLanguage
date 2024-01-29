package stmt;

import java.io.IOException;
import type.*;
import adt.MyIDict;
import adt.MyIHeap;
import adt.MyIStack;
import adt.PrgState;
import exc.InvalidAssignException;
import exc.InvalidTypeException;
import exc.MyException;
import exc.VariableUndefinedException;
import exp.Exp;
import value.BoolValue;
import value.Value;

public class CondAssignStmt implements IStmt {

    private String var;
    private Exp e1, e2, e3;
    
    public CondAssignStmt(String var, Exp e1, Exp e2, Exp e3) {
        this.var = var;
        this.e1 = e1; this.e2 = e2; this.e3 = e3;
    }
    
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack < IStmt > stk = state.getExeStack();
        stk.push(new IfStmt(e1, new AssignStmt(var, e2), new AssignStmt(var, e3)));
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookUp(var);
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        Type t3 = e3.typecheck(typeEnv);
        if (typevar == null) throw new VariableUndefinedException(var);
        if (t1.equals(new BoolType())) {
            if (typevar.equals(t2) && typevar.equals(t3)) {
                return typeEnv;
            }
            else throw new InvalidAssignException(var);
        } 
        else throw new InvalidTypeException();
    }

    @Override 
    public String toString() {
        return var + "=" + e1.toString() + "?" + e2.toString() + ":" + e3.toString() + ";";
    }
}

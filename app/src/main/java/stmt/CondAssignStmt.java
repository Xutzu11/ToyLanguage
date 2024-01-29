package stmt;

import java.io.IOException;
import type.*;
import adt.MyIDict;
import adt.MyIHeap;
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
        MyIDict <String, Value> dct = state.getSymTable();
        MyIHeap < Value > hp = state.getHeap();
        if (dct.isDefined(var)) {
            Value val1 = e1.eval(dct, hp);
            Value val2 = e2.eval(dct, hp);
            Value val3 = e3.eval(dct, hp);
            Type typ = (dct.lookUp(var)).getType();
            if (val2.getType().equals(typ) && val3.getType().equals(typ))  {
                if (val1.getType().equals(new BoolType())) {
                    if (val1.equals(new BoolValue(true))) {
                        dct.put(var, val2);
                    }
                    else dct.put(var, val3);
                    return null;
                }
                else throw new InvalidTypeException();                
            }
            else throw new InvalidAssignException(var);
        }
        else throw new VariableUndefinedException(var);
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

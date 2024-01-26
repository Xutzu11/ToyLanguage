package stmt;

import exp.Exp;
import adt.MyIDict;
import adt.MyIHeap;
import adt.PrgState;
import exc.InvalidAssignException;
import exc.VariableUndefinedException;
import value.*;
import type.*;
import exc.MyException;

public class AssignStmt implements IStmt{
    private String id;
    private Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() { 
        return id + "=" + exp.toString() + ";";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict <String, Value> dct = state.getSymTable();
        MyIHeap < Value > hp = state.getHeap();
        if (dct.isDefined(id)) {
            Value val = exp.eval(dct, hp);
            Type typ = (dct.lookUp(id)).getType();
            if (val.getType().equals(typ))  {
                dct.put(id, val);
                return null;
            }
            else throw new InvalidAssignException(id);
        }
        else throw new VariableUndefinedException(id);
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookUp(id);
        Type typeexp = exp.typecheck(typeEnv);
        if (typevar == null) throw new VariableUndefinedException(id);
        if (typevar.equals(typeexp)) {
            return typeEnv;
        }
        else throw new InvalidAssignException(id);
    }
}

package stmt;

import adt.MyIDict;
import adt.PrgState;
import exc.MyException;
import exc.VariableDefinedException;
import type.*;
import value.Value;

public class VarDeclStmt implements IStmt{
    private String var;
    private Type typ;

    public VarDeclStmt(String var, Type typ) {
        this.var = var;
        this.typ = typ;
    }

    public String toString() {
        return typ.toString() + " " + var + ";";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict <String, Value > dct = state.getSymTable();
        if (dct.isDefined(var))
            throw new VariableDefinedException(var);
        dct.put(var, typ.defaultValue());
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookUp(var);
        if (typevar == null) {
            typeEnv.put(var, typ);
            return typeEnv;
        }
        else throw new VariableDefinedException(var);
    }
    
}

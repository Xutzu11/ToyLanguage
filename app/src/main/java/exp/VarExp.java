package exp;

import adt.MyIDict;
import adt.MyIHeap;
import exc.VariableUndefinedException;
import value.*;
import exc.MyException;
import type.*;

public class VarExp implements Exp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    @Override
    public Value eval(MyIDict <String, Value> tbl, MyIHeap < Value > heap) throws MyException {
        if (tbl.isDefined(id) == false)
            throw new VariableUndefinedException(id);
        return tbl.lookUp(id);
    }

    @Override
    public Type typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        if (typeEnv.isDefined(id)==false)
            throw new VariableUndefinedException(id);
        return typeEnv.lookUp(id);
    }
    
}

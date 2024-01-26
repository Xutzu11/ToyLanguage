package exp;

import value.Value;
import adt.MyIDict;
import adt.MyIHeap;
import exc.MyException;
import type.*;

public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    public String toString() {
        return e.toString();
    }

    @Override
    public Value eval(MyIDict <String,Value> tbl, MyIHeap < Value > heap) throws MyException {
        return e;
    }
 
    @Override
    public Type typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        return e.getType();
    }
}

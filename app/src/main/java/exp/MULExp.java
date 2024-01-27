package exp;

import type.*;
import adt.MyIDict;
import adt.MyIHeap;
import exc.InvalidTypeException;
import exc.MyException;
import value.Value;

public class MULExp implements Exp {
    private Exp e1, e2;

    public MULExp(Exp e1, Exp e2) {
        this.e1 = e1; this.e2 = e2;
    }

    @Override
    public Value eval(MyIDict < String, Value > tbl, MyIHeap < Value > heap) throws MyException {
        Exp prod = new ArithExp('*', e1, e2);
        Exp sum = new ArithExp('+', e1, e2);
        Exp mul = new ArithExp('-', prod, sum);
        return mul.eval(tbl, heap);
    }

    @Override
    public Type typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        if (t1.equals(new IntType()) && t2.equals(new IntType())) {
            return new IntType();
        }
        else throw new InvalidTypeException();
    }

    @Override
    public String toString() {
        return "MUL(" + e1.toString() + "," + e2.toString() + ")";
    }
}

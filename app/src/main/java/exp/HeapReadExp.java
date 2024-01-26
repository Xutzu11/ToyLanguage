package exp;

import adt.MyIDict;
import adt.MyIHeap;
import exc.InvalidOperandException;
import exc.InvalidMemoryAccess;
import exc.MyException;
import type.*;
import value.*;


public class HeapReadExp implements Exp{
    private Exp exp;

    public HeapReadExp(Exp e) {
        exp = e;
    }

    @Override
    public Value eval(MyIDict < String, Value > tbl, MyIHeap < Value > heap) throws MyException {
        Value v = exp.eval(tbl, heap);
        if (v.getType().equals(new RefType(null))) {
            RefValue rf = (RefValue)v;
            int addr = rf.getAddr();
            if (heap.isDefined(addr)) {
                return heap.get(addr);
            }
            else throw new InvalidMemoryAccess();
        }
        else throw new InvalidOperandException(new RefType(null).toString());
    }

    public String toString() {
        return "rH("+exp.toString()+")";
    }

    @Override
    public Type typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t = exp.typecheck(typeEnv);
        if (t instanceof RefType) {
            RefType rt = (RefType)t;
            return rt.getInner();
        }
        else throw new InvalidOperandException("Ref");
    }
}
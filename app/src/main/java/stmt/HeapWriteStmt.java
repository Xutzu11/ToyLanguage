package stmt;

import java.io.IOException;


import adt.MyIDict;
import adt.MyIHeap;
import adt.PrgState;
import exc.InvalidMemoryAccess;
import exc.InvalidOperandException;
import exc.VariableUndefinedException;
import exc.MyException;
import exp.*;
import type.*;
import value.*;

public class HeapWriteStmt implements IStmt{
    private String varname;
    private Exp exp;

    public HeapWriteStmt(String vn, Exp e) {
        varname = vn;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIHeap < Value > heap = state.getHeap();
        MyIDict < String, Value > tbl = state.getSymTable();
        if (tbl.isDefined(varname)) {
            Value v = tbl.lookUp(varname);
            if (v.getType().equals(new RefType(null))) {
                RefValue rf = (RefValue)v;
                int addr = rf.getAddr();
                if (heap.isDefined(addr)) {
                    Value e = exp.eval(tbl, heap);
                    if (e.getType().equals(rf.getLocType())) {
                        heap.update(addr, e);
                        return null;
                    }
                    else throw new InvalidOperandException(e.getType().toString());
                }
                else throw new InvalidMemoryAccess();
            }
            else throw new InvalidOperandException(new RefType(null).toString());
        }
        else throw new VariableUndefinedException(varname);
    }

    public String toString() {
        return "wH("+varname+","+exp.toString()+")";
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookUp(varname);
        Type typeexp = exp.typecheck(typeEnv);
        if (typevar == null) throw new VariableUndefinedException(varname);
        if (typevar instanceof RefType) {
            RefType rt = (RefType)typevar;
            if (rt.getInner().equals(typeexp)) {
                return typeEnv;
            }
            else throw new InvalidOperandException(rt.getInner().toString());
        }
        else throw new InvalidOperandException("Ref");
    }
}

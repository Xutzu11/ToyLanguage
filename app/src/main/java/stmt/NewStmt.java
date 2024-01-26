package stmt;


import adt.MyIDict;
import adt.MyIHeap;
import adt.PrgState;
import exc.InvalidOperandException;
import exc.MyException;
import exc.VariableUndefinedException;
import exp.*;
import type.*;
import value.*;

public class NewStmt implements IStmt{
    private String varname;
    private Exp exp;

    public NewStmt(String vn, Exp e) {
        varname = vn;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict < String, Value > symtbl = state.getSymTable();
        MyIHeap < Value > heap = state.getHeap();
        Value v = exp.eval(symtbl, heap);
        if (symtbl.isDefined(varname)) {
            Value var = symtbl.lookUp(varname);
            if (var.getType().equals(new RefType(null))) {
                RefValue rf = (RefValue)var;
                if (v.getType().equals(rf.getLocType())) {
                    int addr = heap.allocate(v);
                    symtbl.put(varname, new RefValue(addr, rf.getLocType()));
                    return null;    
                }
                else throw new InvalidOperandException(v.getType().toString());
            }
            else throw new InvalidOperandException(new RefType(null).toString());
        }
        else throw new VariableUndefinedException(varname);
    }

    public String toString() {
        return "new("+varname+","+exp.toString()+")";
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookUp(varname);
        Type typeexp = exp.typecheck(typeEnv);
        if (typevar == null) throw new VariableUndefinedException(varname);
        if (typevar.equals(new RefType(typeexp))) {
            return typeEnv;
        }
        else throw new InvalidOperandException("Ref");
    }
}
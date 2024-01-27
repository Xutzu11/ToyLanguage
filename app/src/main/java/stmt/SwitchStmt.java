package stmt;

import java.io.IOException;

import adt.MyIDict;
import adt.MyIHeap;
import adt.MyIStack;
import adt.PrgState;
import exc.InvalidTypeException;
import exc.MyException;
import exp.Exp;
import type.Type;
import value.Value;

public class SwitchStmt implements IStmt {
    private Exp e, e1, e2;
    private IStmt st1, st2, def;

    public SwitchStmt(Exp e, Exp e1, Exp e2, IStmt st1, IStmt st2, IStmt def) {
        this.e = e; this.e1 = e1; this.e2 = e2;
        this.st1 = st1; this.st2 = st2; this.def = def;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > tbl = state.getSymTable();
        MyIStack < IStmt > stk = state.getExeStack();
        MyIHeap < Value > hp = state.getHeap();
        if (e.eval(tbl, hp).equals(e1.eval(tbl, hp))) {
            stk.push(st1);
        }
        else if (e.eval(tbl, hp).equals(e2.eval(tbl, hp))) {
            stk.push(st2);
        }
        else {
            stk.push(def);
        }
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type ts = e.typecheck(typeEnv);
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        if (ts.equals(t1) && t1.equals(t2)) {
            st1.typecheck(typeEnv.clone());
            st2.typecheck(typeEnv.clone());
            def.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new InvalidTypeException();
    }

    @Override
    public String toString() {
        return "switch(" + e.toString() + ") (case " + e1.toString() + ": " + st1.toString() +
            ") (case " + e2.toString() + ": " + st2.toString() + ") (default: " + def.toString() + ")"; 
    }
}

package stmt;

import java.io.IOException;
import java.util.LinkedList;

import adt.MyIDict;
import adt.MyIToySemaphore;
import adt.PrgState;
import adt.Tuple;
import exc.InvalidOperandException;
import exc.InvalidTypeException;
import exc.MyException;
import exc.VariableUndefinedException;
import exp.Exp;
import type.*;
import value.IntValue;
import value.Value;

public class NewSemaphoreStmt implements IStmt {
    private String var;
    private Exp e1, e2;

    public NewSemaphoreStmt(String var, Exp e1, Exp e2) {
        this.var = var; this.e1 = e1; this.e2 = e2;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIToySemaphore < Tuple > sem = state.getSemaphore();
        MyIDict < String, Value > tbl = state.getSymTable();
        int n1 = ((IntValue)e1.eval(state.getSymTable(), state.getHeap())).getVal();
        int n2 = ((IntValue)e2.eval(state.getSymTable(), state.getHeap())).getVal();
        int newFreeAdr = sem.allocate(new Tuple(n1, new LinkedList<>(), n2));
        tbl.put(var, new IntValue(newFreeAdr));
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        Type tvar = typeEnv.lookUp(var);
        if (t1.equals(new IntType()) && t2.equals(new IntType())) {
            if (tvar == null) throw new VariableUndefinedException(var);
            if (tvar.equals(new IntType())) {
                return typeEnv;
            }
            else throw new InvalidOperandException("int");
        }
        else throw new InvalidTypeException();
    }

    @Override
    public String toString() {
        return "newSemaphore(" + var + "," + e1.toString() + "," + e2.toString() + ")";
    }
}

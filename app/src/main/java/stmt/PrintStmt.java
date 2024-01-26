package stmt;

import adt.*;
import exp.*;
import value.*;
import exc.MyException;
import type.*;

public class PrintStmt implements IStmt{
    private Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    public String toString() { 
        return "print(" + exp.toString() + ");";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList <Value> out = state.getOut();
        MyIDict <String, Value> dct = state.getSymTable();
        MyIHeap < Value > hp = state.getHeap();
        out.add(exp.eval(dct, hp));
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
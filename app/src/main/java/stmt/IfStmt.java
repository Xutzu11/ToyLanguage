package stmt;

import exp.*;
import adt.MyIDict;
import adt.MyIStack;
import adt.PrgState;
import exc.InvalidTypeException;
import exc.MyException;
import value.*;
import type.*;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;
 
    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e; 
        thenS = t;
        elseS = el;
    }

    public String toString() { 
        return "if("+ exp.toString()+")then{" +thenS.toString() 
        +"}else{"+elseS.toString()+"};";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        Value v = exp.eval(state.getSymTable(), state.getHeap());
        MyIStack < IStmt > st = state.getExeStack();
        if (v.getType().equals(new BoolType())) {
            BoolValue b = (BoolValue)v;
            boolean r = b.getVal();
            if (r == true)  st.push(thenS);
            else st.push(elseS);
            return null;
        }
        else throw new MyException("Conditional expression is not bool type.");
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t = exp.typecheck(typeEnv);
        if (t.equals(new BoolType())) {
            thenS.typecheck(typeEnv.clone());
            elseS.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new InvalidTypeException();
    }

}
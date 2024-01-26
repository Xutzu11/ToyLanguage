package stmt;


import adt.MyIDict;
import adt.MyIStack;
import adt.PrgState;
import exc.InvalidTypeException;
import exc.MyException;
import exp.Exp;
import exp.ValueExp;
import type.*;
import value.IntValue;

public class WaitStmt implements IStmt {
    private Exp exp;

    public WaitStmt(Exp e) {
        this.exp =  e;
    }
    
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack < IStmt > st = state.getExeStack();
        IntValue v = (IntValue)exp.eval(state.getSymTable(), state.getHeap());
        if (v.getVal()>0) {
            st.push(new WaitStmt(new ValueExp(new IntValue(v.getVal()-1))));
            st.push(new PrintStmt(new ValueExp(v)));
        }
        return null;
    }

    public String toString() {
        return "sleep("+exp.toString()+")";
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t = exp.typecheck(typeEnv);
        if (t.equals(new IntType())) {
            return typeEnv;
        }
        else throw new InvalidTypeException();
    }

}

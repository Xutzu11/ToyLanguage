package stmt;
import adt.*;
import exc.MyException;
import type.*;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt second;

    public CompStmt(IStmt f, IStmt s){
        first = f; second = s;
    }
    public String toString() {
        return "("+ first.toString() + second.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack < IStmt > stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}

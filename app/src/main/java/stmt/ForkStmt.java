package stmt;


import adt.MyDict;
import adt.MyIDict;
import adt.MyIStack;
import adt.MyStack;
import adt.PrgState;
import exc.MyException;
import value.*;
import type.*;

public class ForkStmt implements IStmt{
    IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict < String, Value > newSymTbl = new MyDict<String, Value>();
        MyIStack < IStmt > newExeStack = new MyStack<IStmt>();
        newSymTbl.copyContent(state.getSymTable().getContent());
        PrgState newState = new PrgState(newExeStack, newSymTbl, state.getOut(), 
                                state.getFileTable(), state.getHeap(), stmt, state.getBarrier());
        return newState;
    }

    public String toString() {
        return "fork("+stmt.toString()+")";
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.clone());
        return typeEnv;
    }
}
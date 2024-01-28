package stmt;

import java.io.IOException;
import java.util.List;
import type.*;
import value.Value;
import adt.MyDict;
import adt.MyIDict;
import adt.MyIHeap;
import adt.MyIProcTable;
import adt.MyIStack;
import adt.PrgState;
import adt.Procedure;
import exc.MyException;
import exc.VariableUndefinedException;
import exp.Exp;

public class CallFuncStmt implements IStmt {
    private List < Exp > l;
    String fname;

    public CallFuncStmt(String fname, List < Exp > l) {
        this.fname = fname; this.l = l;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > symTbl = state.getSymTable();
        MyIStack < IStmt > stk = state.getExeStack();
        MyIHeap < Value > heap = state.getHeap();
        MyIDict < String, Value > newSymTbl = new MyDict<String, Value>();
        MyIProcTable < Procedure > procTable = state.getProcedures();
        newSymTbl.copyContent(state.getSymTable().getContent());
        if (!procTable.isDefined(fname)) throw new VariableUndefinedException(fname);
        List < String > vars = procTable.getProcedure(fname).vars;
        IStmt funcStmt = procTable.getProcedure(fname).stmt;
        if (l.size() != vars.size()) throw new MyException("Function call doesn't have the required parameters.");
        for (int i=0;i<l.size();i++) {
            newSymTbl.put(vars.get(i), l.get(i).eval(symTbl, heap));
        }
        state.getSymTableStack().push(newSymTbl);
        stk.push(new ReturnStmt());
        stk.push(funcStmt);
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        String s = "call " + fname + "(";
        for (Exp e:l) {
            s += e.toString() + ",";
        }
        return s + ")";
    }
}

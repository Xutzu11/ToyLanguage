package stmt;

import java.io.IOException;
import adt.MyIDict;
import adt.MyIStack;
import adt.PrgState;
import exc.InvalidTypeException;
import exc.MyException;
import exp.Exp;
import exp.RelationalExp;
import exp.ValueExp;
import type.*;
import value.BoolValue;

public class RepeatUntilStmt implements IStmt {
    
    private IStmt stmt;
    private Exp exp;

    public RepeatUntilStmt(IStmt stmt, Exp exp) {
        this.stmt = stmt;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack < IStmt > exestack = state.getExeStack(); 
        exestack.push(new WhileStmt(new RelationalExp("!=", exp, new ValueExp(new BoolValue(true))), stmt));
        exestack.push(stmt);
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t = exp.typecheck(typeEnv);
        if (t.equals(new BoolType())) {
            stmt.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new InvalidTypeException();
    }

    @Override
    public String toString() {
        return "repeat {" + stmt.toString() + "} until(" + exp.toString() + ")"; 
    }
}

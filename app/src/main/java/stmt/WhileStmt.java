package stmt;

import java.io.IOException;

import adt.MyIDict;
import adt.MyIHeap;
import adt.MyIStack;
import adt.PrgState;
import exc.InvalidOperandException;
import exc.InvalidTypeException;
import exc.MyException;
import exp.Exp;
import value.BoolValue;
import value.Value;
import type.*;

public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > symtbl = state.getSymTable();
        MyIStack < IStmt > exestack = state.getExeStack(); 
        MyIHeap < Value > hp = state.getHeap();
        Value val = exp.eval(symtbl, hp);
        if (val.getType().equals(new BoolType())) {
            BoolValue b = (BoolValue)val;
            if (b.getVal() == true) {
                exestack.push(this);
                exestack.push(stmt);
            }
            return null;
        }
        else throw new InvalidOperandException("boolean");
    }

    public String toString() {
        return "while(" + exp.toString() + "){" + stmt.toString() + "};";
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
}

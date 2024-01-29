package stmt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import adt.MyIDict;
import adt.MyIFileTable;
import adt.MyIHeap;
import adt.PrgState;
import exc.FileAlreadyOpenedException;
import exc.InvalidOperandException;
import exc.MyException;
import exp.Exp;
import value.*;
import type.*;

public class OpenFileStmt implements IStmt {
    private Exp exp;

    public OpenFileStmt(Exp e) {
        this.exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIFileTable < StringValue, BufferedReader > fileTable = state.getFileTable();
        MyIDict < String, Value > symTable = state.getSymTable();
        MyIHeap < Value > hp = state.getHeap();
        Value v = exp.eval(symTable, hp);
        if (v.getType().equals(new StringType())) {
            StringValue file = (StringValue)v;
            if (!fileTable.isDefined(file)) {
                BufferedReader buffer = new BufferedReader(new FileReader(file.getVal()));
                fileTable.put(file, buffer);
                return null;
            }
            else throw new FileAlreadyOpenedException(file.toString());
        }
        else throw new InvalidOperandException("string file");
    }

    public String toString() {
        return "open(" + exp.toString() + ");";
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t = exp.typecheck(typeEnv);
        if (t.equals(new StringType())) {
            return typeEnv;
        }
        else throw new InvalidOperandException("string file");
    }
}

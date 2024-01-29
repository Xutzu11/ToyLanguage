package stmt;

import java.io.BufferedReader;
import java.io.IOException;

import adt.MyIDict;
import adt.MyIFileTable;
import adt.MyIHeap;
import adt.PrgState;
import exc.FileAlreadyClosedException;
import exc.InvalidOperandException;
import exc.MyException;
import exp.Exp;
import value.*;
import type.*;

public class CloseFileStmt implements IStmt {
    private Exp exp;

    public CloseFileStmt(Exp e) {
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
            if (fileTable.isDefined(file)) {
                BufferedReader buffer = fileTable.lookUp(file);
                buffer.close();
                fileTable.remove(file);
                return null;
            }
            else throw new FileAlreadyClosedException(file.toString());
        }
        else throw new InvalidOperandException("string file");
    }

    public String toString() {
        return "close(" + exp.toString() + ");";
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

package stmt;
import java.io.BufferedReader;
import java.io.IOException;


import adt.MyIDict;
import adt.MyIFileTable;
import adt.MyIHeap;
import adt.PrgState;
import exc.FileNotCreatedException;
import exc.InvalidOperandException;
import exc.MyException;
import exc.VariableUndefinedException;
import exp.*;
import type.*;
import value.IntValue;
import value.StringValue;
import value.Value;

public class ReadFileStmt implements IStmt {
    private String varname;
    private Exp exp;

    public ReadFileStmt(Exp exp, String v) {
        this.varname = v;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDict < String, Value > symTable = state.getSymTable();
        MyIFileTable < StringValue, BufferedReader > fileTable = state.getFileTable();
        MyIHeap < Value > hp = state.getHeap();
        Value f = exp.eval(symTable, hp);
        Value var = symTable.lookUp(varname);
        if (var.getType().equals(new IntType())) {
            if (f.getType().equals(new StringType())) {
                StringValue file = (StringValue)f;
                if (fileTable.isDefined(file)) {
                    BufferedReader buffer = fileTable.lookUp(file);
                    int newVal = 0;
                    String newString = buffer.readLine();
                    if (newString != null) {
                        newVal = Integer.parseInt(newString);
                    }
                    symTable.put(varname, new IntValue(newVal));
                    return null;
                }
                else throw new FileNotCreatedException(file.toString());
            }
            else throw new InvalidOperandException("string file");
        }
        else throw new InvalidOperandException("int");
    }

    public String toString() {
        return "read(" + exp.toString() + "," + varname + ")";
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type typeexp = exp.typecheck(typeEnv);
        Type typevar = typeEnv.lookUp(varname);
        if (typevar == null) throw new VariableUndefinedException(varname);
        if (typeexp.equals(new StringType())) {
            return typeEnv;
        }
        else throw new InvalidOperandException("string file");
    }
}

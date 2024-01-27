package stmt;

import java.io.IOException;

import adt.MyIDict;
import adt.MyIStack;
import adt.PrgState;
import exc.InvalidTypeException;
import exc.MyException;
import exc.VariableDefinedException;
import exp.Exp;
import exp.RelationalExp;
import exp.VarExp;
import type.IntType;
import type.Type;

public class ForStmt implements IStmt { 
    
    private String var;
    private Exp e1, e2, e3;
    private IStmt stmt;

    public ForStmt(String var, Exp e1, Exp e2, Exp e3, IStmt stmt) {
        this.var = var;
        this.e1 = e1; this.e2 = e2; this.e3 = e3;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack < IStmt > st = state.getExeStack();
        st.push(new WhileStmt(new RelationalExp("<", new VarExp(var), e2), new CompStmt(stmt, new AssignStmt(var, e3))));
        st.push(new AssignStmt(var, e1));
        st.push(new VarDeclStmt(var, new IntType()));
        return null;
    }

    @Override
    public MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookUp(var);
        if (typevar != null) throw new VariableDefinedException(var);
        typeEnv.put(var, new IntType());
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        Type t3 = e3.typecheck(typeEnv);
        if (t1.equals(new IntType()) && t2.equals(new IntType()) && t3.equals(new IntType())) {
            stmt.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new InvalidTypeException();
    }

    @Override
    public String toString() {
        return "for(" + var + "=" + e1.toString() + ";" + var + "<" + e2.toString() + ";" + var + "=" + e3.toString() + "){" + stmt.toString() + "}"; 
    }
}

package exp;

import adt.MyIDict;
import adt.MyIHeap;
import value.BoolValue;
import value.IntValue;
import value.Value;
import exc.InvalidOperandException;
import exc.InvalidTypeException;
import exc.MyException;
import type.*;

public class RelationalExp implements Exp{ 
    private Exp e1, e2;
    private String op; // < , <= , > , >= , == , !=

    public RelationalExp(String op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public String toString() {
        return e1.toString() + op + e2.toString();
    }

    @Override
    public Value eval(MyIDict <String,Value> tbl, MyIHeap < Value > heap) throws MyException {
        Value v1, v2; 
        v1 = e1.eval(tbl, heap);
        v2 = e2.eval(tbl, heap);
        if (v1.getType().equals(v2.getType())) {
            if (v1.getType().equals(new BoolType())) {
                if (op == "<" || op == ">" || op =="<=" || op == ">=")
                    throw new InvalidOperandException("int");
                boolean n1 = ((BoolValue)v1).getVal();
                boolean n2 = ((BoolValue)v2).getVal();
                if (op == "==") return new BoolValue(n1==n2);
                if (op == "!=") return new BoolValue(n1!=n2);
            }
            if (v1.getType().equals(new IntType())) {
                int n1 = ((IntValue)v1).getVal();
                int n2 = ((IntValue)v2).getVal();
                if (op == "==") return new BoolValue(n1==n2);
                if (op == "!=") return new BoolValue(n1!=n2);
                if (op == "<=") return new BoolValue(n1<=n2);
                if (op == "<") return new BoolValue(n1<n2);
                if (op == ">=") return new BoolValue(n1>=n2);
                if (op == ">") return new BoolValue(n1>n2);
            }
            throw new MyException("Invalid operation.");
        }
        else throw new MyException("Operands of different type.");
    }

    @Override
    public Type typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        if (op=="==" || op=="!=") {
            if (t1.equals(t2)) {
                return new BoolType();
            }
            else throw new InvalidTypeException();
        }
        else {
            if (t1.equals(t2) && t1.equals(new IntType())) {
                return new BoolType();
            }
            else throw new InvalidTypeException();
        }

    }
    
}

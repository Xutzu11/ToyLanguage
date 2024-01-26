package exp;

import adt.MyIDict;
import adt.MyIHeap;
import exc.InvalidOperandException;
import exc.MyException;
import value.*;
import type.*;

public class LogicExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op; // or=||, and=&&

    public LogicExp(String op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public String toString() {
        return e1.toString()+op+e2.toString();
    }

    @Override
    public Value eval(MyIDict <String,Value> tbl, MyIHeap < Value > heap) throws MyException{
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue)v1;
                BoolValue b2 = (BoolValue)v2;
                boolean n1, n2;
                n1 = b1.getVal();
                n2 = b2.getVal();
                if (op == "||") return new BoolValue(n1||n2);
                if (op == "&&") return new BoolValue(n1&&n2);
            }
            else throw new InvalidOperandException("boolean");
        }
        else throw new InvalidOperandException("boolean");
        throw new MyException("No operation found");
    }

    @Override
    public Type typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        if (t1.equals(new BoolType())) {
            if (t2.equals(new BoolType())) {
                return new BoolType();
            }
            else throw new InvalidOperandException("boolean");
        }
        else throw new InvalidOperandException("boolean");

    }
    
}

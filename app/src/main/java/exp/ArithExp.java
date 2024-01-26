package exp;

import value.*;
import adt.MyIDict;
import adt.MyIHeap;
import exc.DivisionByZeroException;
import exc.InvalidOperandException;
import exc.MyException;
import type.*;

public class ArithExp implements Exp {
    private Exp e1;
    private Exp e2;
    private char op; // division=/, add=+, subtract=-, multiply=*

    public ArithExp(char op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public String toString() {
        return e1.toString()+op+e2.toString();
    }

    @Override
    public Value eval(MyIDict < String, Value > tbl, MyIHeap < Value > heap) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if (op == '+') return new IntValue(n1+n2);
                if (op == '-') return new IntValue(n1-n2);
                if (op == '*') return new IntValue(n1*n2);
                if (op == '/')
                    if (n2 == 0) throw new DivisionByZeroException();
                    else return new IntValue(n1/n2);
            }
            else throw new InvalidOperandException("int");
        }
        else throw new InvalidOperandException("int");
        throw new MyException("No operation found.");
    }

    @Override
    public Type typecheck(MyIDict <String, Type> typeEnv) throws MyException {
        Type t1 = e1.typecheck(typeEnv);
        Type t2 = e2.typecheck(typeEnv);
        if (t1.equals(new IntType())) {
            if (t2.equals(new IntType())) {
                return new IntType();
            }
            else throw new InvalidOperandException("int");
        }
        else throw new InvalidOperandException("int");
    }

}
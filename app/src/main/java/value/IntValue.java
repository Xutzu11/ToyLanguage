package value;

import type.*;

public class IntValue implements Value{
    private int val;
    public IntValue(int v){
        val = v;
    }
    public int getVal() {
        return val;
    }
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public Type getType() { 
        return new IntType();
    }

    @Override
    public boolean equals(Object oth) {
        if (oth instanceof IntValue) {
            IntValue another = (IntValue)oth;
            return another.getVal() == this.getVal();
        }
        return false;
    }
}
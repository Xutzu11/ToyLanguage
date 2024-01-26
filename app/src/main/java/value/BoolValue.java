package value;

import type.*;

public class BoolValue implements Value{
    private boolean val;
    public BoolValue(boolean v){
        val = v;
    }
    public boolean getVal() {
        return val;
    }
    public String toString() {
        return Boolean.toString(val);
    }

    @Override
    public Type getType() { 
        return new BoolType();
    }

    @Override
    public boolean equals(Object oth) {
        if (oth instanceof BoolValue) {
            BoolValue another = (BoolValue)oth;
            return another.getVal() == this.getVal();
        }
        return false;
    }
}
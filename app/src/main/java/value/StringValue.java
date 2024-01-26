package value;

import type.*;

public class StringValue implements Value{
    private String s;

    public StringValue(String s){
        this.s = s;
    }
    public String getVal() {
        return s;
    }
    public String toString() {
        return s;
    }

    @Override
    public Type getType() { 
        return new StringType();
    }

    @Override
    public boolean equals(Object oth) {
        if (oth instanceof StringValue) {
            StringValue another = (StringValue)oth;
            return another.getVal() == this.getVal();
        }
        return false;
    }
}
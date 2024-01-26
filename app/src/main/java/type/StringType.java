package type;

import value.*;

public class StringType implements Type{
    @Override
    public boolean equals(Object another){
        return (another instanceof StringType);
    }
    public String toString() { 
        return "string"; 
    } 

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }
}
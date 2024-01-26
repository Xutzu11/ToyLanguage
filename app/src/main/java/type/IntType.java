package type;

import value.*;

public class IntType implements Type{
    @Override
    public boolean equals(Object another){
        return (another instanceof IntType);
    }
    
    public String toString() { 
        return "int"; 
    } 

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}
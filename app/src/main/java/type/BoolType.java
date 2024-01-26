package type;

import value.*;

public class BoolType implements Type {
    @Override
    public boolean equals(Object another){
        return (another instanceof BoolType);
    }
    public String toString() { 
        return "bool"; 
    }
    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
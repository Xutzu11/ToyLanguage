package type;

import value.Value;
import value.RefValue;

public class RefType implements Type {

    private Type inner; 

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }
    
    public boolean equals(Object another) { 
        if (another instanceof RefType) {
            RefType o = (RefType)another;
            return o.getInner() == null || inner.equals(o.getInner());
        } 
        return false;
    } 
 
    public String toString() { 
        if (inner == null) return "Ref";
        return "Ref " + inner.toString();
    } 
 
    @Override
    public Value defaultValue() { 
        return new RefValue(0, inner);
    } 
} 
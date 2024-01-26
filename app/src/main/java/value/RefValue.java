package value;

import type.Type;
import type.RefType;

public class RefValue implements Value{
    private int address; 
    private Type locationType; 
    
    public RefValue(int adr, Type locT) {
        address = adr;
        locationType = locT;
    }

    public String toString() {
        return "(" + Integer.toString(address) + "," + locationType.toString() + ")"; 
    }

    public int getAddr() {
        return address;
    }

    public Type getType() { 
        return new RefType(locationType);
    }

    public Type getLocType() {
        return locationType;
    }

    @Override
    public boolean equals(Object oth) {
        if (oth instanceof RefValue) {
            RefValue another = (RefValue)oth;
            return (another.getAddr() == this.getAddr() && locationType.equals(another.getType())); 
        }
        return false;
    }

}

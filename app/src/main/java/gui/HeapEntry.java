package gui;

import value.Value;

public class HeapEntry {
    
    private Value v;
    private int address;

    public HeapEntry(int addr, Value v) {
        this.v = v;
        this.address = addr;
    }

    public String getValue() {
        return v.toString();
    }

    public String getAddress() {
        return Integer.toString(address);
    }
    
}

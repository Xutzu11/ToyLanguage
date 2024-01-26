package gui;

import value.Value;

public class SymEntry {
    
    private Value v;
    private String var;

    public SymEntry(String var, Value v) {
        this.var = var;
        this.v = v;
    }

    public String getVariable() {
        return var;
    }

    public String getValue() {
        return v.toString();
    }
    
}

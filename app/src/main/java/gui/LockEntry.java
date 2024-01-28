package gui;

import java.util.List;

public class LockEntry {
    
    private Integer addr;
    private Integer id;

    public LockEntry(int addr, int id) {
        this.addr = addr; this.id = id;
    }

    public String getValue() {
        return Integer.toString(id);
    }

    public String getLocation() {
        return Integer.toString(addr);
    }
    
}

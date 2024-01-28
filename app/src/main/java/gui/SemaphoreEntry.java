package gui;

import java.util.List;

public class SemaphoreEntry {
    
    private Integer index;
    private Integer sz;
    private List < Integer > lst;

    public SemaphoreEntry(int index, int sz, List < Integer > l) {
        this.index = index; this.sz = sz;
        this.lst = l;
    }

    public String getValue() {
        return Integer.toString(sz);
    }

    public String getIndex() {
        return Integer.toString(index);
    }
    
    public String getListOfValues() {
        String s = "";
        for (int i:lst) {
            s = s + Integer.toString(i) + ",";
        }
        return s;
    }
}
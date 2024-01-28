package gui;

public class LatchEntry {
    
    private int val;
    private int loc;

    public LatchEntry(int loc, int val) {
        this.val = val;
        this.loc = loc;
    }

    public String getValue() {
        return Integer.toString(val);
    }

    public String getLocation() {
        return Integer.toString(loc);
    }
    
}

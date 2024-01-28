package gui;

import java.util.List;

import stmt.IStmt;

public class ProcEntry {
    
    private String fname;
    private List < String > vars;
    private IStmt stmt;

    public ProcEntry(String fname, List < String > vars, IStmt stmt) {
        this.fname = fname; this.vars = vars; this.stmt = stmt;
    }

    public String getHeader() {
        String s = fname + "(";
        for (String v:vars) {
            s += v + ",";
        }
        return s + ")";
    }

    public String getBody() {
        return stmt.toString();
    }
    
}

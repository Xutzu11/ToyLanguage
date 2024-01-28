package adt;

import java.util.List;
import stmt.IStmt;

public class Procedure {
    public IStmt stmt;
    public List < String > vars;
    public Procedure(IStmt stmt, List < String > vars) {
        this.stmt = stmt;
        this.vars = vars;
    }
}

package adt;

import java.util.HashMap;
import java.util.Map;

import exc.MyException;
import exc.VariableDefinedException;


public class MyProcTable < V > implements MyIProcTable < V > {
    private Map < String, V > procedures;

    public MyProcTable() {
        procedures = new HashMap < String, V>();
    }

    public boolean isDefined(String k) {
        return procedures.containsKey(k);
    }

    public void addProcedure(String k, V v) throws MyException {
        if (!this.isDefined(k))
            procedures.put(k, v);
        else throw new VariableDefinedException(k);
    }

    public V getProcedure(String k) {
        return procedures.get(k);
    }

    @Override
    public Map < String, V > getContent() {
        return procedures;
    }

    @Override
    public void copyContent(Map < String, V > mp) {
        procedures.clear();
        procedures.putAll(mp);
    }

    @Override
    public String fileString() {
        return "";
    }

    @Override
    public String toString() {
        return "";
    } 
}


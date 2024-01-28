package adt;

import java.util.Map;
import exc.MyException;

public interface MyIProcTable < V > {
    String fileString();
    String toString();
    void addProcedure(String k, V v) throws MyException;
    V getProcedure(String k);
    boolean isDefined(String k);
    Map < String, V > getContent();
    void copyContent(Map < String, V > mp);
}

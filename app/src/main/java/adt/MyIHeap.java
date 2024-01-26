package adt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import value.Value;

public interface MyIHeap < V > {
    int allocate(V v);
    String fileString();
    boolean isDefined(int addr);
    V get(int addr);
    void update(int addr, V v);
    Map < Integer, V > getContent();
    Map < Integer, V > safeGarbageCollector(List < Integer > symTableAddr, List < Integer > heapAddr, Map < Integer, V > heap);
    List < Integer > getAddrFromSymTable(Collection < Value > symTableValues);
    List < Integer > getAddrFromHeap();
    void setContent(Map < Integer, V > content);
    void copyContent(Map < Integer, V > content);
    void addContent(Map < Integer, V > content);
    MyIHeap < V > copy();
    void clear();
}

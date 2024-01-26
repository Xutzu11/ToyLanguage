package adt;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import value.RefValue;
import value.Value;

public class MyHeap < V > implements MyIHeap < V > {
    private Map < Integer, V > heap;
    private int freeAdr;

    public MyHeap() {
        heap = new HashMap < Integer, V>();
        freeAdr = 1;
    }

    @Override
    public int allocate(V v) {
        heap.put(freeAdr, v);
        freeAdr++;
        return freeAdr-1;
    }

    private void setFreeAddr(int addr) {
        freeAdr = addr;
    }

    @Override
    public String fileString() {
        String res = "";
        for (Map.Entry<Integer, V> p: heap.entrySet()) {
            res += p.getKey() + " -> " + p.getValue() + "\n";
        }
        return res;
    }

    @Override
    public boolean isDefined(int addr) {
        return heap.containsKey(addr);
    }

    @Override
    public V get(int addr) {
        return heap.get(addr);
    }

    @Override
    public void update(int addr, V v) {
        heap.put(addr, v);
    }

    @Override
    public void setContent(Map < Integer, V > content) {
        heap = content;
    }

    @Override
    public Map < Integer, V > safeGarbageCollector(List < Integer > symTableAddr, List < Integer > heapAddr, Map < Integer, V > heap) {
        return heap.entrySet().stream()
        .filter(e->(symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public List < Integer > getAddrFromSymTable(Collection < Value > symTableValues){
        return symTableValues.stream()
        .filter(v-> v instanceof RefValue)
        .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
        .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        heap.clear();
    }

    @Override
    public List < Integer > getAddrFromHeap() {
        return heap.values().stream()
        .filter(v -> v instanceof RefValue)
        .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddr();})
        .collect(Collectors.toList());
    }

    @Override
    public Map < Integer, V > getContent() {
        return heap;
    }

    @Override
    public void copyContent(Map < Integer, V > hp) {
        heap.clear();
        heap.putAll(hp);
    }

    @Override
    public void addContent(Map < Integer, V > hp) {
        heap.putAll(hp);
    }

    @Override
    public MyHeap < V > copy() {
        MyHeap < V > copyHeap = new MyHeap< V >();
        copyHeap.copyContent(heap);
        copyHeap.setFreeAddr(freeAdr);
        return copyHeap;
    }
}


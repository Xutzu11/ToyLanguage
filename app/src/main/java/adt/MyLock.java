package adt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLock < T > implements MyILock < T > {
    AtomicInteger freeAdr; 
    Map <Integer, T> lockTable;

    public MyLock(){
        this.lockTable = new HashMap<>();
        this.freeAdr = new AtomicInteger(0);
    }

    @Override
    public synchronized int allocate(T value) {
        this.lockTable.put(this.freeAdr.incrementAndGet(), value);
        return this.freeAdr.get();
    }

    @Override
    public T lookup(int addr) {
        return this.lockTable.get(addr);
    }

    @Override
    public void update(int addr, T value) {
        this.lockTable.put(addr, value);
    }

    @Override
    public boolean isDefined(int addr) {
        return this.lockTable.containsKey(addr);
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.lockTable;
    }

    @Override
    public String toString() {
        String s = "";
        for(Integer elem: this.lockTable.keySet()) {
            if (elem != null)
                s += elem.toString() + " -> " + this.lockTable.get(elem).toString() + "\n";
        }
        return s;
    }

    @Override
    public String fileString() {
        String s = "";
        for(Integer elem: this.lockTable.keySet()) {
            if (elem != null)
                s += elem.toString() + " -> " + this.lockTable.get(elem).toString() + "\n";
        }
        return s;
    }
}

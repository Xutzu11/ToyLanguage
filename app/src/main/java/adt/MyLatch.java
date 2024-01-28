package adt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLatch < T > implements MyILatch < T > {
    AtomicInteger freeAdr; 
    Map <Integer, T> latchTable;

    public MyLatch(){
        this.latchTable = new HashMap<>();
        this.freeAdr = new AtomicInteger(0);
    }

    @Override
    public synchronized int allocate(T value) {
        this.latchTable.put(this.freeAdr.incrementAndGet(), value);
        return this.freeAdr.get();
    }

    @Override
    public synchronized T lookup(int addr) {
        return this.latchTable.get(addr);
    }

    @Override
    public synchronized void update(int addr, T value) {
        this.latchTable.put(addr, value);
    }

    @Override
    public boolean isDefined(int addr) {
        return this.latchTable.containsKey(addr);
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.latchTable;
    }

    @Override
    public String toString() {
        String s = "";
        for(Integer elem: this.latchTable.keySet()) {
            if (elem != null)
                s += elem.toString() + " -> " + this.latchTable.get(elem).toString() + "\n";
        }
        return s;
    }

    public String fileString() {
        String s = "";
        for(Integer elem: this.latchTable.keySet()) {
            if (elem != null)
                s += elem.toString() + " -> " + this.latchTable.get(elem).toString() + "\n";
        }
        return s;
    }
}

package adt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCyclicBarrier < T > implements MyICyclicBarrier < T > {
    AtomicInteger freeAdr; 
    Map <Integer, T> barrierTable;

    public MyCyclicBarrier(){
        this.barrierTable = new HashMap<>();
        this.freeAdr = new AtomicInteger(0);
    }

    @Override
    public synchronized int allocate(T value) {
        this.barrierTable.put(this.freeAdr.incrementAndGet(), value);
        return this.freeAdr.get();
    }

    @Override
    public synchronized T lookup(int addr) {
        return this.barrierTable.get(addr);
    }

    @Override
    public synchronized void update(int addr, T value) {
        this.barrierTable.put(addr, value);
    }

    @Override
    public boolean isDefined(int addr) {
        return this.barrierTable.containsKey(addr);
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.barrierTable;
    }

    @Override
    public String toString() {
        String s = "";
        for(Integer elem: this.barrierTable.keySet()) {
            if (elem != null)
                s += elem.toString() + " -> " + this.barrierTable.get(elem).toString() + "\n";
        }
        return s;
    }

    public String fileString() {
        String s = "";
        for(Integer elem: this.barrierTable.keySet()) {
            if (elem != null)
                s += elem.toString() + " -> " + this.barrierTable.get(elem).toString() + "\n";
        }
        return s;
    }
}

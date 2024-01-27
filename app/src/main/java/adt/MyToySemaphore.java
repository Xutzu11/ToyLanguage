package adt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyToySemaphore < T > implements MyIToySemaphore < T > {
    AtomicInteger freeAdr; 
    Map <Integer, T> semaphoreTable;

    public MyToySemaphore(){
        this.semaphoreTable = new HashMap<>();
        this.freeAdr = new AtomicInteger(0);
    }

    @Override
    public synchronized int allocate(T value) {
        this.semaphoreTable.put(this.freeAdr.incrementAndGet(), value);
        return this.freeAdr.get();
    }

    @Override
    public synchronized T lookup(int addr) {
        return this.semaphoreTable.get(addr);
    }

    @Override
    public void update(int addr, T value) {
        this.semaphoreTable.put(addr, value);
    }

    @Override
    public boolean isDefined(int addr) {
        return this.semaphoreTable.containsKey(addr);
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.semaphoreTable;
    }

    @Override
    public String toString() {
        String s = "";
        for(Integer elem: this.semaphoreTable.keySet()) {
            if (elem != null)
                s += elem.toString() + " -> " + this.semaphoreTable.get(elem).toString() + "\n";
        }
        return s;
    }

    public String fileString() {
        String s = "";
        for(Integer elem: this.semaphoreTable.keySet()) {
            if (elem != null)
                s += elem.toString() + " -> " + this.semaphoreTable.get(elem).toString() + "\n";
        }
        return s;
    }
}

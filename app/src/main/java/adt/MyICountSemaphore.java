package adt;

import java.util.Map;

public interface MyICountSemaphore < T > {
    int allocate(T value);
    T lookup(int address);
    void update(int address, T value);
    boolean isDefined(int address);
    String fileString();
    Map <Integer, T> getContent();
}

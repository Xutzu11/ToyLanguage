package adt;

import java.util.List;

public interface MyIList < T > {
    void add(T e);
    void clear();
    String fileString();
    String consoleString();
    List < T > getList();
}

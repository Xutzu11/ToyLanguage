package adt;
import java.util.List;
import java.util.Stack;

public interface MyIStack < T > {
    T pop();
    void push(T e);
    boolean isEmpty();
    List < T > getReversed();
    Stack < T > getStack();
    void clear();
    T top();
    String fileString();
    void copyContent(Stack < T > s);
    Stack < T > getContent();
}

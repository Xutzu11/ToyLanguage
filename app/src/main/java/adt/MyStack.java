package adt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack < T > implements MyIStack < T > {
    
    private Stack < T > stack;
    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public T pop() {
        return this.stack.pop();
    }

    @Override
    public T top() {
        return this.stack.peek();
    }

    @Override
    public void push(T e) {
        this.stack.push(e);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public List < T > getReversed() {
        List < T > list = Arrays.asList((T[])stack.toArray());
        Collections.reverse(list);
        return list;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "stack = " + stack +
                "}\n";
    }

    @Override
    public String fileString() {
        String rez = "";
        List < T > l = this.getReversed();
        for (T t:l) {
            rez += t + "\n";
        }
        return rez;
    }

    @Override
    public Stack < T > getStack() {
        return stack;
    }

    @Override
    public void clear() {
        stack.clear();
    }

    public void setStack(Stack < T > stack) {
        this.stack = stack;
    }

    @Override
    public void copyContent(Stack < T > s) {
        Stack < T > k = new Stack<T>();
        while (!s.empty()) {
            k.add(s.pop());
        }
        while (!k.empty()) {
            s.add(k.firstElement());
            stack.add(k.pop());
        }
    }

    @Override
    public Stack < T > getContent() {
        return stack;
    }
}

package adt;

import java.util.LinkedList;
import java.util.List;

public class MyList < T > implements MyIList < T > {
    private List < T > output;

    public MyList() {
        output = new LinkedList < T >();
    }

    @Override
    public void add(T e) {
        output.add(e);
    }

    @Override
    public void clear() {
        output.clear();
    }

    @Override
    public String toString() {
        return "MyList{" +
                "output = " + output +
                "}\n";
    }

    @Override
    public String fileString() {
        String rez = "";
        for (T t:this.output) {
            rez += t + "\n";
        }
        return rez;
    }

    @Override
    public String consoleString() {
        String rez = "";
        for (T t:this.output) {
            rez += t + " ";
        }
        return rez;
    }

    @Override
    public List < T > getList() {
        return output;
    }

    public void setList(List<T> output) {
        this.output = output;
    }
}

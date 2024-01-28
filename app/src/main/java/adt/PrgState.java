package adt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.EmptyStackException;

import exc.MyException;
import stmt.IStmt;
import value.*;

public class PrgState{
    private static int nextId = 0;
    private int id;
    private MyIStack < IStmt > exeStack;
    private MyIDict < String, Value > symTable;
    private MyILatch < Integer > latch;
    private MyIList < Value > out;
    private MyIFileTable < StringValue, BufferedReader > fileTable;
    private MyIHeap < Value > heap;
    // private IStmt originalProgram; //optional field, but good to have
    
    public PrgState(MyIStack < IStmt > stk, MyIDict < String, Value > symtbl, MyIList < Value >
            ot, MyIFileTable < StringValue, BufferedReader > ftb, MyIHeap < Value > heap, IStmt prg, MyILatch < Integer > latch){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ftb;
        this.heap = heap;
        this.latch = latch;
        id = getNextId();
        /// originalProgram = deepCopy(prg);
        stk.push(prg);
    }

    public static synchronized int getNextId() {
        nextId++;
        return nextId;
    }

    public int getId() {
        return id;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIFileTable < StringValue, BufferedReader > getFileTable() {
        return fileTable;
    }

    public MyIHeap < Value > getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap < Value > heap) {
        this.heap = heap;
    }

    public MyILatch < Integer > getLatch() {
        return this.latch;
    }

    public void setExeStack(MyIFileTable < StringValue, BufferedReader > fileTable) {
        this.fileTable = fileTable;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDict<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDict<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public boolean isNotCompleted() {
        return !exeStack.isEmpty();  
    }

    public PrgState oneStep() throws MyException, IOException {
        if (exeStack.isEmpty()) throw new EmptyStackException();
        IStmt stmt = exeStack.pop();
        return stmt.execute(this);
    }

    @Override
    public String toString() {
        return "PrgState {" +
                "\n   exeStack = " + exeStack.getReversed() +
                "\n   symTable = " + symTable +
                "\n   out = " + out +
                "}\n";
    }


    public String tableString() {
        return "Program state " + Integer.toString(id);
    }

    public String fileString() {
        String rez = "";
        rez += "PrgState:\n";
        rez += Integer.toString(id) + "\n";
        rez += "ExeStack:\n";
        rez += this.exeStack.fileString();
        rez += "SymTable:\n";
        rez += this.symTable.fileString();
        rez += "Out:\n";
        rez += this.out.fileString();
        rez += "FileTable:\n";
        rez += this.fileTable.fileString();
        rez += "Latch:\n";
        rez += this.latch.fileString();
        rez += "Heap:\n";
        rez += this.heap.fileString();
        return rez;
    }
}

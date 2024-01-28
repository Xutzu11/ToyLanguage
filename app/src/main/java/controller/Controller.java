package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import adt.MyIStack;
import adt.MyICyclicBarrier;
import adt.MyIList;
import adt.PrgState;
import adt.Pair;
import exc.EndOfProgramException;
import repo.IRepo;
import stmt.IStmt;
import exc.MyException;
import exc.RepoStateException;
import value.Value;
import adt.MyIHeap;

import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;
    private ExecutorService executor;
    private MyIList < Value > finalOutput;

    public Controller(IRepo repo) throws RepoStateException {
        this.repo = repo;
        finalOutput = repo.getPrg().getOut();
    }

    public boolean programEnded() {
        return repo.getPrgList().size() == 0;
    }

    public void add(PrgState state) {
        repo.add(state);
    }

    public MyIHeap < Value > getHeap() throws RepoStateException {
        return repo.getPrg().getHeap();
    }

    public MyIList < Value > getOutput() throws MyException {
        return finalOutput;
    }

    public MyICyclicBarrier < Pair > getBarrier() throws MyException {
        return repo.getPrg().getBarrier();
    }

    public MyIStack < IStmt > getStack() throws RepoStateException {
        return repo.getPrg().getExeStack();
    }

    public List < PrgState > getPrgList() {
        return repo.getPrgList();
    }

    public PrgState crtState() throws MyException{
        return repo.getPrg();
    }

    public PrgState specificState(int index) {
        return repo.getPrgList().get(index);
    }

    public PrgState stateByString(String s) {
        return repo.getPrgByString(s);
    }

    public void allStep() throws MyException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        if (prgList.size()==0) throw new EndOfProgramException();
        for (PrgState prg:prgList) {
            repo.logPrgStateExec(prg);
        }
        while (prgList.size() > 0) {
            oneStepForAllPrg(repo.getPrgList());
            conservativeGarbageCollector(repo.getPrgList());
            finalOutput = this.repo.getPrg().getOut();
            prgList = removeCompletedPrg(repo.getPrgList());
            repo.setPrgList(prgList);
        }
        executor.shutdownNow();
    }

    public void oneStepRun() throws MyException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List < PrgState > prgList = removeCompletedPrg(repo.getPrgList());
        if (prgList.size() == 0) throw new EndOfProgramException();
        for (PrgState prg:prgList) {
            repo.logPrgStateExec(prg);
        }
        oneStepForAllPrg(repo.getPrgList());
        finalOutput = this.repo.getPrg().getOut();
        conservativeGarbageCollector(repo.getPrgList());
        prgList = removeCompletedPrg(repo.getPrgList());
        repo.setPrgList(prgList);
        executor.shutdownNow();
    }

    public void conservativeGarbageCollector(List < PrgState > l) {
        MyIHeap < Value > heap = l.get(0).getHeap();
        MyIHeap < Value > heapCopy = heap.copy();
        HashMap < Integer, Value > result = new HashMap < Integer, Value >();
        heap.clear();
        for (PrgState prg : l) {
            result.putAll(heapCopy.safeGarbageCollector(heapCopy.getAddrFromSymTable(prg.getSymTable().getValues()), 
            heapCopy.getAddrFromHeap(), heapCopy.getContent()));
        }
        heap.setContent(result);
    }

    public void printFile() throws MyException, IOException {
        repo.logPrgStateExec(crtState());
    }

    
    public void oneStepForAllPrg(List < PrgState > prgList) throws MyException, IOException, InterruptedException {
        List < Callable < PrgState > > callList = prgList.stream()
        .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
        .collect(Collectors.toList());
        
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
        .map(future -> {
            try{return future.get();}
            catch(Exception e) {
                return null;
            }
        })
        .filter(p -> p != null)
        .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        for (PrgState prg:prgList) {
            repo.logPrgStateExec(prg);
        }
    }
    
    public List < PrgState > removeCompletedPrg(List < PrgState > prgList) {
        return prgList.stream()
        .filter(p -> p.isNotCompleted())
        .collect(Collectors.toList());
    }
}

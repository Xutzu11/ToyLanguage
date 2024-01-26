package repo;

import adt.PrgState;
import exc.MyException;
import exc.RepoStateException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.LinkedList;

public class Repo implements IRepo{

    private List < PrgState > repo;
    private String logFilePath;

    public Repo(PrgState state, String log) throws IOException{
        this.repo = new LinkedList <PrgState>();
        repo.add(state);
        this.logFilePath = log;
        clearFile();
    }

    public void clearFile() throws IOException{
        PrintWriter clr = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        clr.flush();
        clr.close();
    }

    @Override
    public void add(PrgState e){
        repo.add(e);
    }
    
    @Override
    public PrgState getPrg() throws RepoStateException {
        return repo.get(0);
    }

    @Override
    public String toString() {
        return "Repository {" +
                "repo = " + repo +
                "}\n";
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException, IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(prg.fileString());
        logFile.close();
    }

    @Override
    public List < PrgState > getPrgList() {
        return repo;
    }

    @Override
    public PrgState getPrgByString(String s) {
        for (PrgState p:repo) {
            if (p.tableString().equals(s)) 
                return p;
        }
        return null;
    }

    @Override
    public void setPrgList(List < PrgState > l) {
        repo = l;
    }

    @Override
    public void copyPrgList(List < PrgState > l) {
        repo.clear();
        repo.addAll(l);
    }

}

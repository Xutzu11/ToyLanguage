package repo;

import java.io.IOException;

import adt.PrgState;
import exc.MyException;
import exc.RepoStateException;
import java.util.List;

public interface IRepo {
    PrgState getPrg() throws RepoStateException;
    void add(PrgState e);
    List < PrgState > getPrgList();
    void setPrgList(List < PrgState > l);
    void copyPrgList(List < PrgState > l);
    void logPrgStateExec(PrgState prg) throws MyException, IOException;
    PrgState getPrgByString(String s);
}

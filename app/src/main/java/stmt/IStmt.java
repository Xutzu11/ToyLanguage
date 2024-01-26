package stmt;
import java.io.IOException;

import adt.*;
import exc.MyException;
import type.*;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;
    MyIDict <String, Type> typecheck(MyIDict <String, Type> typeEnv) throws MyException;
}

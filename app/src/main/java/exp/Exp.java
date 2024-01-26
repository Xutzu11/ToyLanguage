package exp;

import adt.MyIDict;
import adt.MyIHeap;
import value.*;
import exc.MyException;
import type.*;

public interface Exp {
    Value eval(MyIDict < String, Value > tbl, MyIHeap < Value > heap) throws MyException;
    Type typecheck(MyIDict <String, Type> typeEnv) throws MyException ;
}

package interpreter;
import view.TextMenu;
import cmd.ExitCommand;
import cmd.RunExample;
import repo.IRepo;
import repo.Repo;
import stmt.AssignStmt;
import stmt.AwaitStmt;
import stmt.CompStmt;
import stmt.CondAssignStmt;
import stmt.CountdownStmt;
import stmt.ForkStmt;
import stmt.HeapWriteStmt;
import stmt.IStmt;
import stmt.IfStmt;
import stmt.NewLatchStmt;
import stmt.NewStmt;
import stmt.OpenFileStmt;
import stmt.PrintStmt;
import stmt.VarDeclStmt;
import stmt.WhileStmt;
import stmt.CloseFileStmt;
import stmt.ReadFileStmt;
import value.BoolValue;
import value.IntValue;
import value.StringValue;
import value.Value;
import type.*;
import java.io.BufferedReader;
import java.io.IOException;

import adt.*;
import controller.*;
import exc.MyException;
import exp.*;

public class Interpreter {

    private static TextMenu tm = new TextMenu();

    public static void createExample1() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("v",new IntType()),
            new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex1.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("1",stmt.toString(), c));
    }

    public static void createExample2() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a",new IntType()),
                    new CompStmt(new VarDeclStmt("b",new IntType()),
                    new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)), 
                    new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                    new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex2.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("2", stmt.toString(), c));
    }

    public static void createExample3() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a",new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                    new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), 
                    new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex3.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("3", stmt.toString(), c));
    }

    public static void createExample4() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a",new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                    new CompStmt(new VarDeclStmt("max", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(11))),
                    new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(15))),
                    new CompStmt(new IfStmt(new RelationalExp(">=", new VarExp("a"), new VarExp("b")),
                    new AssignStmt("max", new VarExp("a")), new AssignStmt("max", new VarExp("b"))),
                    new PrintStmt(new VarExp("max"))))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex4.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("4", stmt.toString(), c));
    }

    public static void createExample5() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("varf",new StringType()),
                    new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("app/src/main/java/files/file.in"))),
                    new CompStmt(new OpenFileStmt(new VarExp("varf")),
                    new CompStmt(new VarDeclStmt("varc", new IntType()),
                    new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                    new CompStmt(new PrintStmt(new VarExp("varc")),
                    new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                    new CompStmt(new PrintStmt(new VarExp("varc")),
                    new CloseFileStmt(new VarExp("varf"))))))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex5.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("5", stmt.toString(), c));
    }

    public static void createExample6() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("x",new IntType()),
                    new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(6))),
                    new WhileStmt(new RelationalExp("!=", new VarExp("x"), new ValueExp(new IntValue(0))), 
                    new CompStmt(new AssignStmt("x", new ArithExp('-', new VarExp("x"), new ValueExp(new IntValue(1)))),new PrintStmt(new VarExp("x"))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex6.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("6", stmt.toString(), c));
    }

    public static void createExample7() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), 
                    new CompStmt(new NewStmt("a", new VarExp("v")),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                new PrintStmt(new HeapReadExp(new VarExp("v"))))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex7.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("7", stmt.toString(), c));
    }

    public static void createExample8() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), 
                    new CompStmt(new NewStmt("a", new VarExp("v")),
                    new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))), new PrintStmt(new ArithExp('+', new HeapReadExp(new HeapReadExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex8.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("8", stmt.toString(), c));
    }

    public static void createExample9() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))), 
                    new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))), 
                    new PrintStmt(new ArithExp('+', new HeapReadExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));            
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex9.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("9", stmt.toString(), c));
    }

    public static void createExample10() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("b", new RefType(new RefType(new IntType()))),
                    new CompStmt(new VarDeclStmt("c", new RefType(new RefType(new RefType(new IntType())))), 
                    new CompStmt(new NewStmt("a", new ValueExp(new IntValue(30))), 
                    new CompStmt(new NewStmt("b", new VarExp("a")),
                    new CompStmt(new NewStmt("c", new VarExp("b")),
                    new CompStmt(new NewStmt("c", new VarExp("b")),
                    new PrintStmt(new HeapReadExp(new HeapReadExp(new HeapReadExp(new VarExp("c"))))))))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex10.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("10", stmt.toString(), c));
    }

    public static void createExample11() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(5))),
                    new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(6))),
                    new CompStmt(new PrintStmt(new VarExp("a")),
                    new CompStmt(new PrintStmt(new VarExp("b")),
                    new CompStmt(new ForkStmt(new CompStmt(new VarDeclStmt("c", new IntType()),
                    new CompStmt(new VarDeclStmt("d", new IntType()),
                    new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
                    new CompStmt(new AssignStmt("d",new ValueExp(new IntValue(6))),
                    new CompStmt(new PrintStmt(new VarExp("c")),
                    new PrintStmt(new VarExp("d")))))))),
                    new CompStmt(new PrintStmt(new ValueExp(new IntValue(50))), 
                    new PrintStmt(new ValueExp(new IntValue(60))))))))))); ;
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex11.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("11", stmt.toString(), c));
    }

    public static void createExample12() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(5))),
                    new CompStmt(new PrintStmt(new VarExp("a")),
                    new CompStmt(new ForkStmt(new CompStmt(new VarDeclStmt("b", new IntType()),
                    new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(6))),
                    new PrintStmt(new VarExp("b"))))),
                    new PrintStmt(new ValueExp(new IntValue(7)))))));
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex12.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("12", stmt.toString(), c));
    }

    public static void createExample13() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(12))),
                    new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                    new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("a", new ValueExp(new IntValue(30))), 
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))), 
                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a"))))))),
                    new CompStmt(new PrintStmt(new VarExp("v")),
                    new PrintStmt(new HeapReadExp(new VarExp("a")))))))));
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex13.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("13", stmt.toString(), c));
    }

    public static void createExample14() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("counter", new IntType()),
                    new WhileStmt(new RelationalExp("<", new VarExp("counter"), new ValueExp(new IntValue(10))),
                    new CompStmt(new ForkStmt(new ForkStmt(new NewStmt("a", new VarExp("counter")))), new AssignStmt("counter", new ArithExp('+', new VarExp("counter"), new ValueExp(new IntValue(1))))))));
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex14.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("14", stmt.toString(), c));
    }

    public static void createExample15() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("counter", new IntType()),
                            new WhileStmt(new RelationalExp("<", new VarExp("counter"), new ValueExp(new IntValue(10))),
                                    new CompStmt(new ForkStmt(new ForkStmt(new CompStmt(new NewStmt("a", new VarExp("counter")), new PrintStmt(
                                            new HeapReadExp(new VarExp("a"))
                                    )))),
                                            new AssignStmt("counter", new ArithExp('+', new VarExp("counter"),
                                                    new ValueExp(new IntValue(1))))))));
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex15.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("15", stmt.toString(), c));
    }

    public static void createExample16() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                     new CompStmt(new VarDeclStmt("b", new IntType()),  
                     new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))), 
                            new CompStmt(new NewStmt("a", new VarExp("b")), new PrintStmt(new HeapReadExp(new VarExp("a")))))), 
                            new CompStmt(new PrintStmt(new ValueExp(new IntValue(10))), new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(15))), 
                            new CompStmt(new NewStmt("a", new VarExp("b")), new PrintStmt(new HeapReadExp(new VarExp("a")))))))));
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex16.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("16", stmt.toString(), c));
    }
/*
Ref int v1; Ref int v2; Ref int v3; int cnt; 
new(v1,2);new(v2,3);new(v3,4);newLatch(cnt,rH(v2));
fork(wh(v1,rh(v1)*10);print(rh(v1));countDown(cnt); 
 fork(wh(v2,rh(v2)*10);print(rh(v2));countDown(cnt);
 fork(wh(v3,rh(v3)*10);print(rh(v3));countDown(cnt))));
await(cnt);
print(100);
countDown(cnt);
print(100)
The final Out should be 
{20, id-first-child, 30, id-second-child, 40, id-third-child, 100, id_parent, 100} 
where id-first-child, id-second-child and id-third-child are the 
unique identifiers of those three new threads created by fork, 
while id_parent is the identifier of the main thread. */
    public static void createExample17() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())), 
        new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())), 
        new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())), 
        new CompStmt(new VarDeclStmt("cnt", new IntType()), 
        new CompStmt(new NewStmt("v1", new ValueExp(new IntValue(2))),
        new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(3))), 
        new CompStmt(new NewStmt("v3", new ValueExp(new IntValue(4))), 
        new CompStmt(new NewLatchStmt("cnt", new HeapReadExp(new VarExp("v2"))), 
        new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("v1", new ArithExp('*', new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))), 
        new CompStmt(new CountdownStmt("cnt"), 
        new ForkStmt(new CompStmt(new HeapWriteStmt("v2", new ArithExp('*', new HeapReadExp(new VarExp("v2")), new ValueExp(new IntValue(10)))),
        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v2"))),
        new CompStmt(new CountdownStmt("cnt"),
        new ForkStmt(new CompStmt(new HeapWriteStmt("v3", new ArithExp('*', new HeapReadExp(new VarExp("v3")), new ValueExp(new IntValue(10)))),
        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v3"))), 
        new CountdownStmt("cnt")))))))))))),
        new CompStmt(new AwaitStmt("cnt"), 
        new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
        new CompStmt(new CountdownStmt("cnt"), 
        new PrintStmt(new ValueExp(new IntValue(100)))))))))))))));
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex17.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("17", stmt.toString(), c));
    }
/* Ref int a; Ref int b; int v;
new(a,0); new(b,0);
wh(a,1); wh(b,2);
v=(rh(a)<rh(b))?100:200;
print(v);
v= ((rh(b)-2)>rh(a))?100:200;
print(v); 
The final Out should be {100,200} */
    public static void createExample18() throws MyException, IOException {
        IStmt stmt = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
        new CompStmt(new VarDeclStmt("b", new RefType(new IntType())), 
        new CompStmt(new VarDeclStmt("v", new IntType()), 
        new CompStmt(new NewStmt("a", new ValueExp(new IntValue(0))), 
        new CompStmt(new NewStmt("b", new ValueExp(new IntValue(0))), 
        new CompStmt(new HeapWriteStmt("a", new ValueExp(new IntValue(1))), 
        new CompStmt(new HeapWriteStmt("b", new ValueExp(new IntValue(2))), 
        new CompStmt(new CondAssignStmt("v", new RelationalExp("<", new HeapReadExp(new VarExp("a")), 
        new HeapReadExp(new VarExp("b"))), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), 
        new CompStmt(new PrintStmt(new VarExp("v")), 
        new CompStmt(new CondAssignStmt("v", new RelationalExp(">", new ArithExp('-', new HeapReadExp(new VarExp("b")), new ValueExp(new IntValue(2))), new HeapReadExp(new VarExp("a"))), 
        new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), 
        new PrintStmt(new VarExp("v"))))))))))));
        MyIDict < String, Type > typeEnv = new MyDict<String, Type>();
        stmt.typecheck(typeEnv);
        MyIStack <IStmt> stk = new MyStack<IStmt>();
        MyIDict <String, Value> symtbl = new MyDict <String, Value >();
        MyIList <Value> out = new MyList < Value >();
        MyIFileTable < StringValue, BufferedReader > filetbl = new MyFileTable<StringValue, BufferedReader>(); 
        MyIHeap < Value > heap = new MyHeap<Value>(); 
        MyILatch < Integer > sem = new MyLatch<Integer>(); 
        PrgState PrgState = new PrgState(stk, symtbl, out, filetbl, heap, stmt, sem);
        IRepo r = new Repo(PrgState, "app/src/main/java/files/ex18.out");
        Controller c = new Controller(r);
        tm.addCommand(new RunExample("18", stmt.toString(), c));
    }

    public TextMenu getMenu() {
        return tm;
    }

    public void addExit() {
        tm.addCommand(new ExitCommand("0", "exit"));
    }

    public void createExamples() {
        tm.addCommand(new ExitCommand("0", "exit"));
        try {
            createExample1();
            createExample2();
            createExample3();
            createExample4();
            createExample5();
            createExample6();
            createExample7();
            createExample8();
            createExample9();
            createExample10();
            createExample11();
            createExample12();
            createExample13();
            createExample14();
            createExample15();
            createExample16();
            createExample17();
            createExample18();
        }
        catch (MyException | IOException e) {
            System.out.println(e.getMessage());
        }
        //tm.show();
    }

    public static void main(String args[]) {
        tm.addCommand(new ExitCommand("0", "exit"));
        try {
            createExample1();
            createExample2();
            createExample3();
            createExample4();
            createExample5();
            createExample6();
            createExample7();
            createExample8();
            createExample9();
            createExample10();
            createExample11();
            createExample12();
            createExample13();
            createExample14();
            createExample15();
            createExample16();
            createExample17();
            createExample18();
        }
        catch (MyException | IOException e) {
            System.out.println(e.getMessage());
        }
        tm.show();
    }
    
}
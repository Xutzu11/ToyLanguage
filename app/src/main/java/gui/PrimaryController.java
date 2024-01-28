package gui;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import adt.PrgState;
import controller.Controller;
import exc.MyException;
import exc.RepoStateException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import stmt.IStmt;
import value.StringValue;
import value.Value;
import view.TextMenu;

public class PrimaryController {

    private Controller mainContr;
    private TextMenu runner;
    @FXML private Stage stage;
    @FXML private Button primaryButton;
    @FXML private TextField prgNumber;
    @FXML private TableView < HeapEntry > heapTable;
    @FXML private TableColumn < HeapEntry, String > addressHeap;
    @FXML private TableColumn < HeapEntry, String > valueHeap;
    @FXML private ListView < String > stackList;
    @FXML private ListView < String > outputList; 
    @FXML private ListView < String > prgStateList;
    @FXML private ListView < String > fileTable;
    @FXML private TableView < SymEntry > symTable;
    @FXML private TableColumn < SymEntry, String > variableSym;
    @FXML private TableColumn < SymEntry, String > valueSym;
    @FXML private TableView < LatchEntry > latchTable;
    @FXML private TableColumn < LatchEntry, String > locationLatch;
    @FXML private TableColumn < LatchEntry, String > valueLatch;

    private PrgState selectedState;

    public void setContr(Controller c) {
        mainContr = c;
    }

    public void setMenu(TextMenu t) {
        runner = t;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void updateCurrentProgram() {
        String prg = prgStateList.getSelectionModel().getSelectedItem();
        if (prg == null) return;
        selectedState = mainContr.stateByString(prg);
        updateAll();
    }
 
    public void initialize() {
        heapTable.getColumns().clear();
        addressHeap.setCellValueFactory(new PropertyValueFactory<>("address"));
        valueHeap.setCellValueFactory(new PropertyValueFactory<>("value"));
        heapTable.getColumns().addAll(addressHeap, valueHeap);

        symTable.getColumns().clear();
        variableSym.setCellValueFactory(new PropertyValueFactory<>("variable"));
        valueSym.setCellValueFactory(new PropertyValueFactory<>("value"));
        symTable.getColumns().addAll(variableSym, valueSym);

        latchTable.getColumns().clear();
        locationLatch.setCellValueFactory(new PropertyValueFactory<>("location"));
        valueLatch.setCellValueFactory(new PropertyValueFactory<>("value"));
        latchTable.getColumns().addAll(locationLatch, valueLatch);
    }

    public void getSelection(String stmt) {
        try {
            setContr(runner.runByName(stmt));
            selectedState = mainContr.crtState();
            updateAll();
        }
        catch (MyException e) {
            triggerAlert(e.getMessage());
        }
    }

    public void updateFiles() {
        fileTable.getItems().clear();
        if (mainContr.programEnded()) return;
        Set < StringValue > files = selectedState.getFileTable().getContent().keySet();
        for (StringValue s:files) {
            fileTable.getItems().add(s.toString());
        }
    }

    public void updateHeap() throws RepoStateException {
        heapTable.getItems().clear();
        if (mainContr.programEnded()) return;
        Map < Integer, Value > heap = mainContr.getHeap().getContent();
        for (Map.Entry<Integer, Value > k:heap.entrySet()) {
            heapTable.getItems().add(new HeapEntry(k.getKey(), k.getValue()));
        }
    }

    public void updateSym() throws RepoStateException {
        symTable.getItems().clear();
        if (mainContr.programEnded()) return;
        Map < String, Value > sym = selectedState.getSymTable().getContent();
        for (Map.Entry<String, Value > k:sym.entrySet()) {
            symTable.getItems().add(new SymEntry(k.getKey(), k.getValue()));
        }
    }

    public void updateStack() throws RepoStateException {
        stackList.getItems().clear();
        if (mainContr.programEnded()) return;
        List < IStmt > st = selectedState.getExeStack().getReversed();
        for (IStmt s:st) {
            stackList.getItems().add(s.toString());
        }
    }

    public void updatePrgStates() {
        prgStateList.getItems().clear();
        if (mainContr.programEnded()) return;
        List < PrgState > l = mainContr.getPrgList();
        for (PrgState p:l) {
            prgStateList.getItems().add(p.tableString());
        }
    }

    public void updatePrgNumber() {
        prgNumber.setText("Number of program states: " + Integer.toString(mainContr.getPrgList().size()));
    }

    public void updateOutput() throws MyException {
        outputList.getItems().clear();
        List < Value > l = mainContr.getOutput().getList();
        for (Value v:l) {
            outputList.getItems().addAll(v.toString());
        }
    }

    public void updateLatch() throws MyException {
        latchTable.getItems().clear();
        if (mainContr.programEnded()) return;
        Map < Integer, Integer > latch = mainContr.getLatch().getContent();
        for (Map.Entry<Integer, Integer > k:latch.entrySet()) {
            latchTable.getItems().add(new LatchEntry(k.getKey(), k.getValue()));
        }
    }

    public void triggerAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void updateAll() {
        try { 
            updatePrgNumber();
            updateHeap();
            updateStack();
            updateSym();
            updateFiles();
            updateOutput();
            updatePrgStates();
            updateLatch();
        }
        catch (MyException e) {
            triggerAlert(e.getMessage());
        }
    }

    @FXML
    public void runStep() {
        try {
            mainContr.oneStepRun();
        }
        catch (MyException | IOException | InterruptedException e) {
            triggerAlert(e.getMessage());
        }
        updateAll();
    }

}

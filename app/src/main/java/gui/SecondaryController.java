package gui;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SecondaryController {
    
    @FXML private ListView < String > prgList;
    private PrimaryController prContr;
    @FXML private Stage stage;

    public void setStage(Stage s) {
        stage = s;
    }

    @FXML
    public void setPrimaryController(PrimaryController prContr) {
        this.prContr = prContr;
    }

    @FXML
    public void populateList(List < String > s) {
        prgList.getItems().clear();
        prgList.getItems().addAll(s);
    }

    @FXML
    public void programSelection() {
        String selectedStmt = prgList.getSelectionModel().getSelectedItem();
        if (selectedStmt == null) return;
        prContr.getSelection(selectedStmt);
        stage.close();
    }
}

package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

import exc.MyException;
import interpreter.Interpreter;

/**
 * JavaFX App
 */
public class App extends Application {

    private Interpreter i;


    public void triggerWarning(String s, int exmp) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Error at example " + Integer.toString(exmp));
        alert.setContentText(s);
        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) throws IOException {
        i = new Interpreter();
        i.addExit();
        try {
            i.createExample1();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 1);
        }
        try {
            i.createExample2();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 2);
        }
        try {
            i.createExample3();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 3);
        }
        try {
            i.createExample4();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 4);
        }
        try {
            i.createExample5();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 5);
        }
        try {
            i.createExample6();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 6);
        }
        try {
            i.createExample7();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 7);
        }
        try {
            i.createExample8();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 8);
        }
        try {
            i.createExample9();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 9);
        }
        try {
            i.createExample10();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 10);
        }
        try {
            i.createExample11();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 11);
        }
        try {
            i.createExample12();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 12);
        }
        try {
            i.createExample13();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 13);
        }
        try {
            i.createExample14();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 14);
        }
        try {
            i.createExample15();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 15);
        }
        try {
            i.createExample16();
        }
        catch (MyException e) {
            triggerWarning(e.getMessage(), 16);
        }

        FXMLLoader fxmlLoaderPrim = new FXMLLoader();
        FXMLLoader fxmlLoaderSec = new FXMLLoader();

        fxmlLoaderPrim.setLocation(App.class.getResource("primary.fxml"));
        Parent primaryRoot = fxmlLoaderPrim.load();
        PrimaryController prContr = fxmlLoaderPrim.getController();
        prContr.setMenu(i.getMenu());

        fxmlLoaderSec.setLocation(App.class.getResource("secondary.fxml"));
        Parent secondaryRoot = fxmlLoaderSec.load();
        SecondaryController secContr = fxmlLoaderSec.getController();
        secContr.setPrimaryController(prContr);
        secContr.populateList(i.getMenu().getCommands());
        
        Scene primaryScene = new Scene(primaryRoot, 950, 480);
        Scene secondaryScene = new Scene(secondaryRoot, 640, 480);

        Stage primaryStage = new Stage();
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main window");
        prContr.setStage(primaryStage);
        primaryStage.show();

        Stage secondaryStage = new Stage();
        secondaryStage.setScene(secondaryScene);
        secondaryStage.setTitle("Program selection");
        secContr.setStage(secondaryStage);
        secondaryStage.show();

    }
    public static void main(String[] args) {
        launch();
    }

}
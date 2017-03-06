package Asmt1;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

import java.io.*;
/*
* Joseph Robertson
* 100579703
*
* */
public class Main extends Application {
    private BorderPane layout;
    private TableView<TestFile> table;
    public HashMap<String, Double> spamMap;

    /**
     * runs the program by creating and running display and calling other functions
     * @param primaryStage
     * @throws Exception
     * @throws IOException
     */
    @Override
    public void start(final Stage primaryStage) throws Exception, IOException {
        table = new TableView<>();

        TableColumn<TestFile,String> nameColumn = null;
        nameColumn = new TableColumn<>("name");
        nameColumn.setPrefWidth(300);
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<TestFile,String>("FileName"));

        TableColumn<TestFile,Boolean> spamColumn = null;
        spamColumn = new TableColumn<>("actual class");
        spamColumn.setPrefWidth(150);
        spamColumn.setMinWidth(100);
        spamColumn.setCellValueFactory(new PropertyValueFactory<TestFile,Boolean>("ActualClass"));

        TableColumn<TestFile,Boolean> predictionColumn = null;
        predictionColumn = new TableColumn<>("predicted class");
        predictionColumn.setPrefWidth(150);
        predictionColumn.setMinWidth(100);
        predictionColumn.setCellValueFactory(new PropertyValueFactory<TestFile,Boolean>("PredictedClass"));

        TableColumn<TestFile,String> probColumn = null;
        probColumn = new TableColumn<>("spam probability");
        probColumn.setPrefWidth(150);
        probColumn.setMinWidth(100);
        probColumn.setCellValueFactory(new PropertyValueFactory<TestFile,String>("SpamProbRounded"));

        table.getColumns().add(nameColumn);
        table.getColumns().add(spamColumn);
        table.getColumns().add(predictionColumn);
        table.getColumns().add(probColumn);

        GridPane editArea = new GridPane();
        editArea.setPadding(new Insets(10, 10, 10, 10));
        editArea.setVgap(10);
        editArea.setHgap(10);


        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("choose train file");
        directoryChooser.setInitialDirectory(new File("."));
        File trainDirectory = directoryChooser.showDialog(primaryStage);
        try {
            spamMap = evaluate.train(trainDirectory);
        }
        catch (Exception exc){
            System.err.println("bad\t"+exc);
            System.exit(1);
        }

        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("choose test file");
        directoryChooser.setInitialDirectory(new File("."));
        File testDirectory = directoryChooser.showDialog(primaryStage);
        ObservableList<TestFile> data=null;
        try {
            data=evaluate.test(testDirectory.getPath(), spamMap);
            table.setItems(data);
        }
        catch (Exception exc) {
            System.err.println(exc);
            System.exit(1);
        }
        GridPane output=new GridPane();
        try {
            double spamCount = 0, correct = 0, goodSpam = 0, count = 0;
            String type, realType;

            for (TestFile item : data) {
                type = item.getPredictedClass();
                realType = item.getActualClass();
                if (type.equalsIgnoreCase(realType)) {
                    correct++;
                    if (realType.equalsIgnoreCase("Spam")) goodSpam++;
                }
                if (type.equalsIgnoreCase("Spam")) spamCount++;
                count++;
            }
            output.add(new Label("Acurracy: " + correct / count), 0, 0);
            output.add(new Label("Precision: " + goodSpam / spamCount), 0, 1);

            layout = new BorderPane();
            layout.setCenter(table);
            layout.setBottom(output);

            Scene scene = new Scene(layout, 765, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            System.err.println(e);
            System.err.println(e);
        }
    }

    /**
     * main method, calls launch
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

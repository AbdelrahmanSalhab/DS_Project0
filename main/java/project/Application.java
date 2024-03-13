package project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Application extends javafx.application.Application {

    private Manager manager =  new Manager();
    private MyList<Martyr> names;

    @Override
    public void start(Stage stage){
        fileChooserPane(stage);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
    }

    public void fileChooserPane(Stage stage){

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        StackPane centerPane = new StackPane();
        centerPane.setAlignment(Pos.CENTER);
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setTitle("Open CSV File");
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        File desktopDirectory = new File(desktopPath);
        fileChooser.setInitialDirectory(desktopDirectory);

        Button btOpen = new Button("Open File");
        btOpen.setFont(javafx.scene.text.Font.font(17));

        vbox.getChildren().add(btOpen);
        btOpen.setMinWidth(100);
        btOpen.setMinHeight(50);

        centerPane.getChildren().add(vbox);
        stage.setTitle("Open File");
        stage.setScene(new Scene(centerPane,400,300));

        btOpen.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);

            if(file != null) {
                manager.setFile(file);
                stage.setScene(new Scene(mainPane(),800,500));
                stage.centerOnScreen();
                stage.setTitle("Martyr List");

            }else {
                Label lbError = new Label("File not selected.");
                lbError.setStyle("-fx-font-size: 14; -fx-text-fill: red;");
                vbox.getChildren().add(lbError);
                btOpen.setStyle("-fx-border-color: red; -fx-border-width: 2");

            }
        });
    }

    public BorderPane mainPane(){

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20));

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        VBox mainVBox = new VBox(15);

        Label lbTitle = new Label("Martyr List");
        lbTitle.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 24));
        borderPane.setTop(lbTitle);

        //Buttons
        Button btLoad = new Button("Load");
        btLoad.setMinWidth(50);
        Button btAdd = new Button("Add");
        btAdd.setMinWidth(50);
        Button btDelete = new Button("Delete");
        btDelete.setMinWidth(50);
        Button btFind = new Button("Find");
        btFind.setMinWidth(50);
        Button btSave = new Button("Save");
        btSave.setMinWidth(50);
        Button btStatistics = new Button("Statistics");
        btStatistics.setMinWidth(50);
        Button btClearStatistics = new Button("Clear Stats");
        btClearStatistics.setMinWidth(50);

        //TextFields
        TextField tfAddName = new TextField();
        tfAddName.setPromptText("Full Name");
        TextField tfAddAge = new TextField();
        tfAddAge.setPromptText("Age");
        TextField tfAddLocation = new TextField();
        tfAddLocation.setPromptText("Location");
        TextField tfAddDate = new TextField();
        tfAddDate.setPromptText("Month/Day/Year");
        TextField tfAddGender = new TextField();
        tfAddGender.setPromptText("M/F");
        TextField tfDelete = new TextField();
        tfDelete.setPromptText("Full Name");
        TextField tfFind = new TextField();
        tfFind.setPromptText("Full Name");

        //Labels
        Label lbAdd = new Label("Add Martyr");
        lbAdd.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 16));
        lbAdd.setStyle("-fx-text-fill: purple");

        Label lbName = new Label("Name");
        lbName.setMinWidth(35);
        Label lbAge = new Label("Age");
        lbAge.setMinWidth(50);
        Label lbLocation = new Label("Location");

        Label lbDate = new Label("Date");
        lbDate.setMinWidth(35);
        Label lbGender = new Label("Gender");
        lbGender.setMinWidth(50);

        Label lbDelete = new Label("Delete Martyr");
        lbDelete.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 16));
        lbDelete.setStyle("-fx-text-fill: purple");
        Label lbDeleteName = new Label("Name");

        Label lbFind = new Label("Find Martyr");
        lbFind.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 16));
        lbFind.setStyle("-fx-text-fill: purple");
        Label lbFindName = new Label("Name");

        Label lbOut = new Label("");
        lbOut.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));

        Label lbStats = new Label("");
        lbStats.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));

        //ComboBoxes
        ComboBox<String> cbDate = new ComboBox<>();
        cbDate.getItems().addAll("Year" ,"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
                "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021",
                "2022", "2023");
        cbDate.setPromptText("Year");
        cbDate.setMinWidth(90);
        cbDate.setMaxWidth(90);

        ComboBox<String> cbAge = new ComboBox<>();
        cbAge.getItems().addAll("Age" ,"0-5", "6-11", "12-18", "19-30", "31-40", "41-50", "51-60", "61-80", "81-130", "Unknown");
        cbAge.setPromptText("Age");
        cbAge.setMinWidth(90);
        cbAge.setMaxWidth(90);

        ComboBox<String> cbGender = new ComboBox<>();
        cbGender.getItems().addAll("Gender" ,"M", "F", "N/A");
        cbGender.setPromptText("Gender");
        cbGender.setMaxWidth(90);
        cbGender.setMinWidth(90);

        //HBoxes
        HBox hbAge = new HBox(10);
        hbAge.setAlignment(Pos.CENTER_LEFT);
        hbAge.getChildren().addAll(lbName, tfAddName, lbAge, tfAddAge, lbLocation, tfAddLocation);

        HBox hbDate = new HBox(10);
        hbDate.setAlignment(Pos.CENTER_LEFT);
        hbDate.getChildren().addAll(lbDate, tfAddDate, lbGender, tfAddGender,btAdd);

        HBox hbDelete = new HBox(10);
        hbDelete.setAlignment(Pos.CENTER_LEFT);
        hbDelete.getChildren().addAll(lbDeleteName, tfDelete, btDelete);

        HBox hbFind = new HBox(10);
        hbFind.setAlignment(Pos.CENTER_LEFT);
        hbFind.getChildren().addAll(lbFindName, tfFind, btFind);

        HBox hbLoadSave = new HBox(10);
        hbLoadSave.setAlignment(Pos.CENTER_LEFT);
        hbLoadSave.getChildren().addAll(btLoad, btSave, btStatistics, btClearStatistics);

        HBox hbComboBoxes = new HBox(10);
        hbComboBoxes.setAlignment(Pos.CENTER_LEFT);
        hbComboBoxes.getChildren().addAll(cbAge, cbDate, cbGender);

        mainVBox.getChildren().addAll(lbAdd, hbAge, hbDate, lbDelete, hbDelete, lbFind, hbFind, lbOut, hbLoadSave, hbComboBoxes, lbStats);

        btLoad.setOnAction(event -> {
            if(manager.readFile()) {
                names = manager.getNames();
                lbOut.setText("Loaded");
                lbOut.setStyle("-fx-text-fill: green");
            }else{
                lbOut.setText("Already Loaded");
                lbOut.setStyle("-fx-text-fill: red");
            }
        });

        btAdd.setOnAction(event -> {
            if(tfAddName.getText().isEmpty() || tfAddName.getText().isBlank() || tfAddAge.getText().isEmpty()
            || tfAddAge.getText().isBlank() || tfAddLocation.getText().isEmpty() || tfAddLocation.getText().isBlank()
            || tfAddDate.getText().isEmpty() || tfAddDate.getText().isBlank() || tfAddGender.getText().isEmpty() ||
            tfAddGender.getText().isBlank()){
                lbOut.setText("Some Fields are Empty...");
                lbOut.setStyle("-fx-text-fill: red");
            }else{
                Martyr martyrToAdd;
                String newMartyr = tfAddName.getText() + "," + tfAddAge.getText() + "," + tfAddLocation.getText()
                        + "," + tfAddDate.getText() + "," + tfAddGender.getText();

                martyrToAdd = manager.createMartyrObj(newMartyr);
                if(martyrToAdd == null) {
                    lbOut.setText("Incorrect Input");
                    lbOut.setStyle("-fx-text-fill: red");
                }else {
                    if(names != null) {
                        names.add(martyrToAdd);
                        lbOut.setText("Added " + martyrToAdd.getName());
                        lbOut.setStyle("-fx-text-fill: green");
                    }else{
                        lbOut.setText("File not Loaded");
                        lbOut.setStyle("-fx-text-fill: red");
                    }
                }
            }
        });

        btDelete.setOnAction(event -> {
            if(tfDelete.getText().isEmpty() || tfDelete.getText().isBlank()) {
                lbOut.setText("Empty");
                lbOut.setStyle("-fx-text-fill: red");
            }else{
                Martyr martyrToDelete = new Martyr(tfDelete.getText());
                if(names != null) {
                    if (names.delete(martyrToDelete)) {
                        lbOut.setText("Deleted" + martyrToDelete.getName());
                        lbOut.setStyle("-fx-text-fill: green");
                    }else{
                        lbOut.setText("Not Found");
                        lbOut.setStyle("-fx-text-fill: red");
                    }
                }else{
                    lbOut.setText("File not Loaded");
                    lbOut.setStyle("-fx-text-fill: red");
                }
            }
        });

        btFind.setOnAction(event -> {
            if(tfFind.getText().isEmpty() || tfFind.getText().isBlank()) {
                lbOut.setText("Empty");
                lbOut.setStyle("-fx-text-fill: red");
            }else{
                Martyr martyrToFind = new Martyr(tfFind.getText());
                if (names != null ) {
                    int foundIndex = names.find(martyrToFind);
                    if (foundIndex != -1) {
                        lbOut.setText("Found at " + foundIndex);
                        lbOut.setStyle("-fx-text-fill: green");
                    }else{
                        lbOut.setText("Not Found");
                        lbOut.setStyle("-fx-text-fill: red");
                    }
                }else{
                    lbOut.setText("File not Loaded");
                    lbOut.setStyle("-fx-text-fill: red");
                }

            }
        });

        btSave.setOnAction(event -> {
            if(names != null) {
                if (manager.saveToFile(names)) {
                    lbOut.setText("Saved");
                    lbOut.setStyle("-fx-text-fill: green");
                } else{
                    lbOut.setText("Error");
                    lbOut.setStyle("-fx-text-fill: red");
                }
            }else{
                lbOut.setText("File not Loaded");
                lbOut.setStyle("-fx-text-fill: red");
            }
        });

        btClearStatistics.setOnAction(event -> {
            cbAge.getSelectionModel().clearAndSelect(0);
            cbDate.getSelectionModel().clearAndSelect(0);
            cbGender.getSelectionModel().clearAndSelect(0);
            lbStats.setText("");
        });

        btStatistics.setOnAction(event -> {
            if (names == null) {
                lbOut.setText("File not Loaded");
                lbOut.setStyle("-fx-text-fill: red");
                return;
            }

            int age1 = 0;
            int age2 = 0;
            String age = cbAge.getValue();
            if (age == null || age.equals("Age"))
                age = "Age";
            else if (age.equals("Unknown")) {
                age1 = -1;
                age2 = -1;
            }else {
                String[] ageArray = age.split("-");
                age1 = Integer.parseInt(ageArray[0]);
                age2 = Integer.parseInt(ageArray[1]);
            }

            String date = cbDate.getValue();
            if (date == null)
                date = "Year";
            String gender = cbGender.getValue();
            if(gender == null)
                gender = "Gender";

            char genderChar;
            int count = 0;

            // if all are default values
            if (age.equals("Age") && date.equals("Year") && gender.equals("Gender")) {
                lbStats.setText("Select at least one option");
                lbStats.setStyle("-fx-text-fill: red");
                return;

                // if only Gender is selected
            } else if(age.equals("Age") && date.equals("Year")) {
                genderChar = gender.charAt(0);
                for (int i = 0; i < names.getCount(); i++) {
                    if(names.get(i).getGender() == genderChar)
                        count++;
                }
                lbStats.setText("Number of Martyrs with gender " + gender + " : " + count);
                lbStats.setStyle("-fx-text-fill: green");

                // if only Date is selected
            } else if(age.equals("Age") && gender.equals("Gender")) {
                for (int i = 0; i < names.getCount(); i++) {
                    if(names.get(i).getDateOfMartyrdom().contains(date))
                        count++;
                }
                lbStats.setText("Number of Martyrs in " + date + " : " + count);
                lbStats.setStyle("-fx-text-fill: green");

                // if only Age is selected
            } else if(date.equals("Year") && gender.equals("Gender")) {
                for (int i = 0; i < names.getCount(); i++) {
                    if(names.get(i).getAge() >= age1 && names.get(i).getAge() <= age2)
                        count++;
                    if(names.get(i).getAge() == -1 && age1 == -1 && age2 == -1)
                        count++;
                }
                lbStats.setText("Number of Martyrs with age " + age + " : " + count);
                lbStats.setStyle("-fx-text-fill: green");

                // if gender and Date are selected
            } else if (age.equals("Age")) {
                genderChar = gender.charAt(0);
                for (int i = 0; i < names.getCount(); i++) {
                    if(names.get(i).getDateOfMartyrdom().contains(date) && names.get(i).getGender() == genderChar)
                        count++;
                }
                lbStats.setText("Number of Martyrs in " + date + " and with Gender " + gender + " : " + count);
                lbStats.setStyle("-fx-text-fill: green");

                // if Age and gender are selected
            } else if (date.equals("Year")) {
                genderChar = gender.charAt(0);
                for (int i = 0; i < names.getCount(); i++) {
                    if(names.get(i).getAge() >= age1 && names.get(i).getAge() <= age2 && names.get(i).getGender() == genderChar)
                        count++;
                    if(names.get(i).getAge() == -1 && age1 == -1 && age2 == -1)
                        count++;
                }
                lbStats.setText("Number of Martyrs with age " + age + " and with Gender " + gender + " : " + count);
                lbStats.setStyle("-fx-text-fill: green");

                // if Date and Age are selected
            } else if (gender.equals("Gender")) {
                for (int i = 0; i < names.getCount(); i++) {
                    if(names.get(i).getAge() >= age1 && names.get(i).getAge() <= age2 && names.get(i).getDateOfMartyrdom().contains(date))
                        count++;
                    if(names.get(i).getAge() == -1 && age1 == -1 && age2 == -1)
                        count++;
                }
                lbStats.setText("Number of Martyrs with age " + age + " and with Gender " + gender + " : " + count);
                lbStats.setStyle("-fx-text-fill: green");

                // if all is selected
            } else {
                genderChar = gender.charAt(0);
                for (int i = 0; i < names.getCount(); i++) {
                    if(names.get(i).getAge() >= age1 && names.get(i).getAge() <= age2 && names.get(i).getGender() == genderChar && names.get(i).getDateOfMartyrdom().contains(date))
                        count++;
                    if(names.get(i).getAge() == -1 && age1 == -1 && age2 == -1)
                        count++;
                }
                lbStats.setText("Number of Martyrs in " + date + " , with Gender " + gender + " and Age " + age + " : " + count);
                lbStats.setStyle("-fx-text-fill: green");

            }
        });

        borderPane.setCenter(gridPane);
        gridPane.add(mainVBox, 0, 0);
        return borderPane;
    }


    public static void main(String[] args){ launch(args); }
}
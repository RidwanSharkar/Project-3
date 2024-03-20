package fitnessclub;
import java.io.File;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;

/*-------------------------------------------------------------*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;



/* Demo RadioButton, CheckBox, DatePicker and TableView */


public class StudioManagerController
{
    @FXML
    DatePicker dp_dob;

    @FXML
    private ToggleGroup membership;

    @FXML
    private TextField fnameMemField, lnameMemField, gpassMemField;

    @FXML
    private TextArea output;

    @FXML
    private RadioButton rb_basic, rb_family, rb_premium;

    @FXML
    private Button addNewButton, canMemButton;

    @FXML
    private RadioButton bridgeMemRadButton, edisonMemRadButton, frankMemRadButton, piscaMemRadButton, somerMemRadButton;

    @FXML
    private CheckBox cb_caramel, cb_cream, cb_sugar;

    @FXML
    TableView tbv_contact;

    @FXML
    TableColumn<DataModel, String> col_name, col_phone;

    private MemberList memberList;

    /* The initialize() method will be performed automatically when the application launches. */

    public void initialize() {
        ObservableList list = FXCollections.observableArrayList(
                new DataModel("a", "123"),
                new DataModel("b", "456"),
                new DataModel("c", "789"));
        tbv_contact.setItems(list);
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    @FXML
    void checkSugar(ActionEvent event)
    {
        if (cb_sugar.isSelected())
            cb_caramel.setDisable(true);
        else
            cb_caramel.setDisable(false);
    }

    @FXML
    void displaySelected(ActionEvent event)
    {
        if (rb_basic.isSelected())
            output.appendText("basic\n");
        else if (rb_family.isSelected())
            output.appendText("family\n");
        else
            output.appendText("premium\n");
    }

    @FXML
    void displayDate(ActionEvent event)
    {
        String date = dp_dob.getValue().toString();
        output.appendText(date + "\n");
    }

    private Date handleDatePicker(LocalDate ldate) {
        int month = ldate.getMonthValue();
        int day = ldate.getDayOfMonth();
        int year = ldate.getYear();

        Date rdate = new Date(month, day, year);
        return rdate;
    }

    private Location handleHomeStudioRB() {

        Location homeStudio;

        if (bridgeMemRadButton.isSelected()) {
            homeStudio = Location.BRIDGEWATER;
            //output.appendText("bridgewater\n");
        } else if(edisonMemRadButton.isSelected()) {
            homeStudio = Location.EDISON;
            //output.appendText("edison\n");
        } else if(frankMemRadButton.isSelected()) {
            homeStudio = Location.FRANKLIN;
            //output.appendText("franklin\n");
        } else if(piscaMemRadButton.isSelected()) {
            homeStudio = Location.PISCATAWAY;
            //output.appendText("piscataway\n");
        } else {
            homeStudio = Location.SOMERVILLE;
            //output.appendText("somerville\n");
        }

        return homeStudio;

    }

    private Date handleExprDate() {
        //default for expiration
        Date expiration;
        Date today = Date.getTodaysDate();
        int day = today.getDay();
        int month = today.getMonth();
        int year = today.getYear();

        if (rb_basic.isSelected()) { //basic exp 1 month from today
            if (month > 11) {
                month = 1;
                year += 1;
                expiration = new Date(month, day, year);
            } else {
                month += 1;
                expiration = new Date(month, day, year);
            }

        } else if (rb_family.isSelected()) { //family exp 3 month from today
            switch (month) {
                case 10:
                    month = 1;
                    year += 1;
                case 11:
                    month = 2;
                    year += 1;
                case 12:
                    month = 3;
                    year += 1;
                default:
                    month += 3;
            }
            expiration = new Date(month, day, year);

        } else { //premium expires in a year from today
            year += 1;
            expiration = new Date(month, day, year);
        }

        return expiration;
    }

    private void handleAddMember(ActionEvent event) {

        //Creating a Profile from GUI data
        String firstName = fnameMemField.getText();
        String lastName = lnameMemField.getText();
        LocalDate dpdate = dp_dob.getValue();
        Date dob = handleDatePicker(dpdate);
        Profile memProf = new Profile(firstName, lastName, dob);

        Location homeStudio = handleHomeStudioRB();

        Date expire = new Date (1, 1, 2000);
        Member member;

        if (rb_basic.isSelected()) {
            expire = handleExprDate();
            member = new Basic (memProf, expire, homeStudio);
            gpassMemField.setText("0"); //changes Guest Pass field to 0 for basic members

        } else if (rb_family.isSelected()) {
            expire = handleExprDate();
            member = new Family (memProf, expire, homeStudio);
            gpassMemField.setText("1"); //changes Guest Pass field to 1 for family members
        } else {
            expire = handleExprDate();
            member = new Premium (memProf, expire, homeStudio);
            gpassMemField.setText("3"); //changes Guest Pass field to 3 for premium members
        }

        if (addNewButton.isPressed()) {
            memberList.add(member);
            //output.appendText("Member " + member.toString() + " added");
        }
    }


    /*-------------------------------------------------------------------------------*/


    /*
    private void handleAddMember(ActionEvent event)
    {
        // Get data from GUI controls
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        //... other fields

        // Use model classes to add member
        /*
        Member newMember = new Member(firstName, lastName, /* other da); */
/*
        if (memberList.add(newMember)) {
            // Update GUI accordingly
        } else {
            // Show error message on GUI
        }

    }
    */


}








/*

public class StudioManagerController
{
    @FXML
    private Button addButton, clearButton;

    @FXML
    private TextField int1, int2, sum;

    @FXML
    private TextArea messageArea;

    @FXML

     * Event Handler for the add button
     * @param event

    void add(ActionEvent event) {
        //messageArea.clear(); //clear the TextArea.
        try {
            int result = Integer.parseInt(int1.getText()) + Integer.parseInt(int2.getText());
            sum.setText(String.valueOf(result));
        }
        //Show the error message with a pop-up window.
        catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setHeaderText("Non-numeric data has been entered.");
            alert.setContentText("Please enter an integer.");
            alert.showAndWait();
        }
    }

    @FXML
    void checkInteger1(MouseEvent event) {
        //messageArea.clear();
        try {
            int integer = Integer.parseInt(int1.getText());
        }
        //Show the error message in the TextArea.
        catch (NumberFormatException e) {
            messageArea.appendText("Must enter an integer.\n");
            return;
        }
    }

    @FXML
    void checkInteger2(KeyEvent event) {
        //messageArea.clear();
        if (event.getCode().equals(KeyCode.ENTER)) { //check if the ENTER key is pressed
            try {
                int integer = Integer.parseInt(int1.getText());
            }
            catch (NumberFormatException e) {
                messageArea.appendText("Not an integer.\n");
                return;
            }
        }
    }

    @FXML
    void importFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
        //Scanner scanner = new Scanner(sourceFile);
        //write code to read from the file.
    }

    @FXML
    void exportFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file
        //write code to write to the file.
    }

    @FXML
    void clear(ActionEvent event) {
        int1.clear();
        int2.clear();
        sum.clear();
        messageArea.clear();
    }
}

*/


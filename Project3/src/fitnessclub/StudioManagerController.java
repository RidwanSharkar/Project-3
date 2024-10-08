package fitnessclub;

import java.io.File;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class that handles the backend code of the GUI elements
 * Gathers information from the user inputs in the GUI and uses logic to interpret them
 * into useable information to operate the fitness class functionalities
 * @author Ryan Colling, Ridwan Sharkar
 */
public class StudioManagerController
{
    @FXML
    DatePicker dp_dob, dobClasField;

    @FXML
    private TextField fnameMemField, lnameMemField, gpassMemField, fnameClassField, lnameClasField, gpassRemainField;

    @FXML
    private TextArea output;

    @FXML
    private RadioButton rb_basic, rb_family, rb_premium;

    @FXML
    private Button addNewButton, canMemButton;

    @FXML
    private RadioButton bridgeMemRadButton, edisonMemRadButton, frankMemRadButton, piscaMemRadButton, somerMemRadButton;

    @FXML
    private RadioButton pilatRadButton, spinRadButton, cardioRadButton;

    @FXML
    private RadioButton jennRadButton, kimRadButton, deniRadButton, davRadButton, emmaRadButton;

    @FXML
    private RadioButton bridgeClasRadButton, edisonClasRadButton, frankClasRadButton, piscaClasRadButton, somerClasRadButton;

    private MemberList memberList = new MemberList();

    @FXML
    private TableView<Location> locationTableView;
    @FXML
    private TableColumn<Location, String> cityColumn;
    @FXML
    private TableColumn<Location, String> countyColumn;
    @FXML
    private TableColumn<Location, String> zipCodeColumn;

    /**
     * Writes and formats information to the studio location and class scheule
     * table in the GUI by getting the data from the Location enum and classSchedule.txt
     */
    @FXML
    public void initialize()
    {
        cityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        countyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCounty()));
        zipCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZipCode()));

        ObservableList<Location> locationList = FXCollections.observableArrayList(Location.values());
        locationTableView.setItems(locationList);


        timeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().get("Time")));

        classNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().get("Class Name")));

        instructorColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().get("Instructor")));

        studioLocationColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().get("Studio Location")));
    }

    @FXML
    private Button loadScheduleButton;
    @FXML
    private TableView<Map<String, String>> classScheduleTable;
    @FXML
    private TableColumn<Map<String, String>, String> timeColumn;
    @FXML
    private TableColumn<Map<String, String>, String> classNameColumn;
    @FXML
    private TableColumn<Map<String, String>, String> instructorColumn;
    @FXML
    private TableColumn<Map<String, String>, String> studioLocationColumn;


    /**
     * Loads the scheule by getting information from classSchedule.txt
     * writes that information to the Class Schedule table in GUI
     * @param event The load schedule button being clicked
     */
    @FXML
    private void handleLoadSchedule(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        Stage stage = (Stage) locationTableView.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            loadScheduleFromFile(file);
        }
    }

    /**
     * loads the scheulde from the classScheudle.txt file
     * @param file classScheudle.txt file
     */
    private void loadScheduleFromFile(File file) {
        try {
            List<Map<String, String>> scheduleEntries = new ArrayList<>();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 4) {
                    Map<String, String> entry = new HashMap<>();
                    entry.put("Time", convertTime(parts[2]));
                    entry.put("Class Name", parts[0]);
                    entry.put("Instructor", parts[1]);
                    entry.put("Studio Location", parts[3]);
                    scheduleEntries.add(entry);
                }
            }


            ObservableList<Map<String, String>> observableList = FXCollections.observableArrayList(scheduleEntries);

            classScheduleTable.setItems(observableList);

            output.appendText("Class Schedule loaded.\n");

            scanner.close();
        } catch (FileNotFoundException e) {
            output.appendText("Failed to load the schedule file.\n");
        }
    }

    /**
     * converts the time into useable values for the fitness class logic
     * @param timeIdentifier A string with the value morning, afternoon, or evening
     * @return A string with the time that can be processed by the program
     */
    private String convertTime(String timeIdentifier) {
        switch (timeIdentifier) {
            case "morning": return "9:30";
            case "afternoon": return "14:00";
            case "evening": return "18:30";
            default: return "";
        }
    }


    /**
     * A helper method to process the data from the date picker in the GUI
     * @param ldate the date/information recieved from the GUI input
     * @return the useable date interpreted from the GUI given date
     */
    private Date handleDatePicker(LocalDate ldate) {
        int month = ldate.getMonthValue();
        int day = ldate.getDayOfMonth();
        int year = ldate.getYear();

        Date rdate = new Date(month, day, year);
        return rdate;
    }

    /**
     * Helper method that determines which location is home location for the
     * new user, based on information from the GUI
     * @return the home location of the user
     */
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

    /**
     * Helper method that determines the expiration date of auser wants to
     * new users membership, information comes from the GUI
     * @return the date of expiration for the membership
     */
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

    /**
     * Helper method that determines which instructor a user wants to
     * participate with in a class with information from the GUI
     * @return the desired instructor for the class
     */
    private Instructor handleInstructorSelect () {

        Instructor instructor;

        if(jennRadButton.isSelected()) {
            instructor = Instructor.JENNIFER;
        } else if (kimRadButton.isSelected()) {
            instructor = Instructor.KIM;
        } else if (deniRadButton.isSelected()) {
            instructor = Instructor.DENISE;
        } else if (davRadButton.isSelected()) {
            instructor = Instructor.DAVIS;
        } else {
            instructor = Instructor.EMMA;
        }

        return instructor;
    }

    /**
     * Helper method that determines which class type a user wants to
     * participate in with information from the GUI
     * @return the desired class type for the class
     */
    private Offer handleOfferSelect() {

        Offer offer;

        if(pilatRadButton.isSelected()) {
            offer = Offer.PILATES;
        } else if (spinRadButton.isSelected()) {
            offer = Offer.SPINNING;
        } else  {
            offer = Offer.CARDIO;
        }

        return offer;
    }

    /**
     * Helper method that determines which location a user wants to
     * participate in a class with information from the GUI
     * @return the desired location of the class
     */
    private Location handleClassStudio() {

        Location classStudio;

        if (bridgeClasRadButton.isSelected()) {
            classStudio = Location.BRIDGEWATER;
        } else if (edisonClasRadButton.isSelected()) {
            classStudio = Location.EDISON;
        } else if (frankClasRadButton.isSelected()) {
            classStudio = Location.FRANKLIN;
            //output.appendText("franklin\n");
        } else if (piscaClasRadButton.isSelected()) {
            classStudio = Location.PISCATAWAY;
        } else {
            classStudio = Location.SOMERVILLE;
        }

        return classStudio;

    }

    /**
     * Method that changes the Guest Pass Text Field on the GUI
     * depending on what membership type the user elects
     * @param event The specific radio button being selected
     */
    @FXML
    private void handleGPassCounter(ActionEvent event) {

        if (rb_basic.isSelected()) {
            gpassMemField.setText("0"); //changes Guest Pass field to 0 for basic members
        } else if (rb_family.isSelected()) {
            gpassMemField.setText("1"); //changes Guest Pass field to 1 for family members
        } else {
            gpassMemField.setText("3"); //changes Guest Pass field to 3 for premium members

        }

    }

    /**
     * Adds a member to the member list with the information from the GUI Members tab
     * @param event the Add Member button being pressed
     */
    @FXML
    private void handleAddMember(ActionEvent event) {

        //Creating a Profile from GUI data

        MemberList memberList = new MemberList();

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

        } else if (rb_family.isSelected()) {
            expire = handleExprDate();
            member = new Family (memProf, expire, homeStudio);
        } else {
            expire = handleExprDate();
            member = new Premium (memProf, expire, homeStudio);
        }

        if(memberList.add(member)) {
            output.appendText("Member " + member + " added\n");
        } else {
            output.appendText("Error, invalid member or member is already in list");
        }

    }

    /**
     * Removes a member from the member list with the information from the GUI member tab
     * @param event the Cancel Existing button being pressed
     */
    @FXML
    private void handleCancelExist (ActionEvent event) {

        String firstName = fnameMemField.getText();
        String lastName = lnameMemField.getText();
        LocalDate dpdate = dp_dob.getValue();
        Date dob = handleDatePicker(dpdate);
        Profile memProf = new Profile(firstName, lastName, dob);

        Member remMember = new Member();
        remMember = memberList.containsProfile(memProf);

        if (remMember.getProfile() == null) {
            output.appendText("Error, invalid member or member doesn't have membership");
            return;
        }

        if (memberList.remove(remMember)) {
            output.appendText("Member " + remMember + " removed\n");
        } else {
            output.appendText("Error, invalid member or member doesn't have membership");
        }

    }


    /**
     * Adds a member to a class from the information given on the
     * GUI Class Attendence tab
     */
    @FXML
    private void handleAddClassMem(ActionEvent event) {

        String firstName = fnameClassField.getText();
        String lastName = lnameClasField.getText();
        LocalDate dpdate = dobClasField.getValue();
        Date dob = handleDatePicker(dpdate);
        Profile memProf = new Profile(firstName, lastName, dob);

        Offer offer = handleOfferSelect();
        Instructor instructor = handleInstructorSelect();
        Location classStudio = handleClassStudio();

    }

    /**
     * Removes a member to a class from the information given on the
     * GUI Class Attendence tab
     */
    @FXML
    private void handleRemClassMem(ActionEvent event) {

        String firstName = fnameClassField.getText();
        String lastName = lnameClasField.getText();
        LocalDate dpdate = dobClasField.getValue();
        Date dob = handleDatePicker(dpdate);
        Profile memProf = new Profile(firstName, lastName, dob);

        Offer offer = handleOfferSelect();
        Instructor instructor = handleInstructorSelect();
        Location classStudio = handleClassStudio();

    }

    /**
     * Adds a guest to a class from the information given on the
     * GUI Class Attendence tab
     */
    @FXML
    private void handleAddClassGuest(ActionEvent event) {

        String firstName = fnameClassField.getText();
        String lastName = lnameClasField.getText();
        LocalDate dpdate = dobClasField.getValue();
        Date dob = handleDatePicker(dpdate);
        Profile guessProf = new Profile(firstName, lastName, dob);

        Offer offer = handleOfferSelect();
        Instructor instructor = handleInstructorSelect();
        Location classStudio = handleClassStudio();

    }

    /**
     * Removes a guest to a class from the information given on the
     * GUI Class Attendence tab
     */
    @FXML
    private void handleRemClassGuest(ActionEvent event) {

        String firstName = fnameClassField.getText();
        String lastName = lnameClasField.getText();
        LocalDate dpdate = dobClasField.getValue();
        Date dob = handleDatePicker(dpdate);
        Profile guessProf = new Profile(firstName, lastName, dob);

        Offer offer = handleOfferSelect();
        Instructor instructor = handleInstructorSelect();
        Location classStudio = handleClassStudio();

    }

}


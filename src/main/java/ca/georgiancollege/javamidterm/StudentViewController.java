package ca.georgiancollege.javamidterm;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StudentViewController  {

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> studentNumCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> telephoneCol;

    @FXML
    private TableColumn<Student, String> addressCol;

    @FXML
    private TableColumn<Student, String> provinceCol;

    @FXML
    private TableColumn<Student, Integer> avgGradeCol;

    @FXML
    private TableColumn<Student, String> majorCol;

    @FXML
    private CheckBox ontarioCheckBox;

    @FXML
    private Label numOfStudentsLabel;

    @FXML
    private CheckBox honourRollCheckBox;

    @FXML
    private ComboBox<String> areaCodeComboBox;

    @FXML
    void applyFilter(ActionEvent event) {

    }

    //holds number of students
    String numberOfStudents;

    //hold filters...
    ArrayList<String> filterStack = new ArrayList<>();

    //columns creation
//    TableColumn<Student, Integer> studNumCol = new TableColumn<Student,Integer>("studentNum");
//    TableColumn<Student, String> fnameCol = new TableColumn<Student,String>("First Name");
//    TableColumn<Student, String> lNameCol = new TableColumn<Student,String>("Last Name");
//    TableColumn<Student, String> telephoneCol = new TableColumn<Student,String>("Telephone");
//    //TableColumn<Student, String> addressCol = new TableColumn<Student,String>("Address");
    //TableColumn<Student, String> provinceCol = new TableColumn<Student,String>("Province");
    //TableColumn<Student, Integer> averageCol = new TableColumn<Student,Integer>("Avg Grade");
    //TableColumn<Student, String> majorCol = new TableColumn<Student,String>("Major");




//
    private void applyFilter1(String filterType) throws Exception {

        filterStack.add(filterType);

        updateTable("filter");



    }

    private void undoFilter(String filterType) throws Exception {

        if(filterType.equalsIgnoreCase("Province")){
            filterStack.remove("Province");
            updateTable("filter");

        }
        else if (filterType.equalsIgnoreCase("Average")) {
            filterStack.remove("Average");
            updateTable("filter");

        }

        for(int i =0; i<filterStack.size();i++){
            System.out.println(filterStack.get(i));
        }
//        else{
//            filterStack.remove(filterType);
//            updateTable("filter");
//        }

    }

    private void undoAreaCode() throws Exception {

        for(int i = 0; i<filterStack.size();i++){

            //is an area code so remove
            if(filterStack.get(i).length() == 3){
                filterStack.remove(i);
            }
        }
    }

    //
    private void updateTable(String condition) throws Exception {

        ArrayList<Student> studentData = DataStore.getStudentData();

        if(!tableView.getItems().isEmpty()){
            tableView.getItems().clear();
        }

        if(condition.equalsIgnoreCase("load")){
            for(int i = 0; i<studentData.size(); i++){
                tableView.getItems().add(studentData.get(i));
            }
        }
        else{

            //hold temp data to throw into tableview
            //clone
            ArrayList<Student> listOfStudents = studentData;

            for(int i = 0; i<filterStack.size(); i++){

                ArrayList<Student> tempCopy = new ArrayList<>();

                if (filterStack.get(i).equalsIgnoreCase("Province")) {

                    for(int j = 0; j<listOfStudents.size(); j++) {

                        //
                        if (listOfStudents.get(j).getProvince().equalsIgnoreCase("ON")) {
                            //tableView.getItems().add(studentData.get(i));
                            tempCopy.add(listOfStudents.get(j));
                        }

                    }
                }

                if (filterStack.get(i).equalsIgnoreCase("Average")) {
                    for(int j = 0; j<listOfStudents.size(); j++) {

                        //
                        if (listOfStudents.get(j).getAverage() > 79) {
                            //tableView.getItems().add(studentData.get(i));
                            tempCopy.add(listOfStudents.get(j));
                        }
                    }
                }
                else{
//                    if(filterStack.get(i).equalsIgnoreCase("All")){
//
//                    }

                    for(int j = 0; j<listOfStudents.size(); j++) {
                        if(updateAreaCode(filterStack.get(i),listOfStudents.get(j).getPhoneNumber())){
                            tempCopy.add(listOfStudents.get(j));
                        }
                    }
                }

                //copy temp over to list to filter to create new product.
                listOfStudents = tempCopy;

            }

            //now loop through final filtered list and add all items to tableview...
            for(int i = 0; i<listOfStudents.size(); i++){
                tableView.getItems().add(listOfStudents.get(i));
            }

        }//end of filter update section...

    }

    //
    private boolean updateAreaCode(String condition, String number){

        ArrayList<Student> studentData = DataStore.getStudentData();
        Pattern pattern = Pattern.compile(condition, Pattern.CASE_INSENSITIVE);

        //clear then update...
        //tableView.getItems().clear();
        Matcher matcher = pattern.matcher(number);

        return matcher.find();

    }





    //
    private void getStudents(){

        //
        ObservableList<Student> lister = tableView.getItems();

        //
        String numberOfStudentsTemp = Integer.toString(lister.size());

        //set
        numOfStudentsLabel.setText(numberOfStudentsTemp);

    }

    @FXML
    void initialize() throws Exception {
        areaCodeComboBox.getItems().add("All");
        areaCodeComboBox.getItems().add("705");
        areaCodeComboBox.getItems().add("905");
        areaCodeComboBox.getItems().add("416");
        areaCodeComboBox.getItems().add("519");
        areaCodeComboBox.getItems().add("647");


        //set cell factories to grab class data
        studentNumCol.setCellValueFactory(new PropertyValueFactory<Student,Integer>("studentNumber"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<Student,String>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Student,String>("address"));
        provinceCol.setCellValueFactory(new PropertyValueFactory<Student,String>("province"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<Student,Integer>("average"));
        majorCol.setCellValueFactory(new PropertyValueFactory<Student,String>("major"));

        // load all data...
        updateTable("load");


        //part 2c
        getStudents();


        //part 2d
        ontarioCheckBox.setOnAction(e -> {

            if(ontarioCheckBox.isSelected()){
                //update graph to ON only
                try {
                    applyFilter1("Province");

                    //updateTable("update-ON");
                    getStudents();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
            else{

                //update graph to normal. Load all without any filters...
                try {
                    undoFilter("Province");

                    //updateTable("load");
                    getStudents();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            }

        });

        //part 2E
        honourRollCheckBox.setOnAction(e -> {

            if(honourRollCheckBox.isSelected()){
                try {
                    //updateTable("honour-roll");
                    applyFilter1("Average");
                    getStudents();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            else{

                //update graph to normal. Load all without any filters...
                try {
                    undoFilter("Average");
                    //updateTable("load");
                    getStudents();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }


        });


        //Part 2F
        areaCodeComboBox.setOnAction(e -> {

            //

            if(areaCodeComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")){
                //updateAreaCode("All");
                try {
                    undoAreaCode();
                    //applyFilter1("All");
                    //updateTable("load");

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
            else if (areaCodeComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("705")){
                try {
                    undoAreaCode();
                    applyFilter1("705");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (areaCodeComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("905")) {
                try {
                    undoAreaCode();
                    applyFilter1("905");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }            }
            else if (areaCodeComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("416")) {
                try {
                    undoAreaCode();
                    applyFilter1("416");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
            else if (areaCodeComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("519")) {
                try {
                    undoAreaCode();
                    applyFilter1("519");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (areaCodeComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("647")) {
                try {
                    undoAreaCode();
                    applyFilter1("647");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }            }

            //update amount of items afterwards.
            getStudents();

            //e.toString();
            //areaCodeComboBox.getSelectionModel().getSelectedItem();
        });




    }

    public void applyFilter(javafx.event.ActionEvent event) {
    }
}

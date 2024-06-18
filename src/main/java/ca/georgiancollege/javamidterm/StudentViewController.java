package ca.georgiancollege.javamidterm;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.util.ArrayList;


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

    //columns creation
//    TableColumn<Student, Integer> studNumCol = new TableColumn<Student,Integer>("studentNum");
//    TableColumn<Student, String> fnameCol = new TableColumn<Student,String>("First Name");
//    TableColumn<Student, String> lNameCol = new TableColumn<Student,String>("Last Name");
//    TableColumn<Student, String> telephoneCol = new TableColumn<Student,String>("Telephone");
//    //TableColumn<Student, String> addressCol = new TableColumn<Student,String>("Address");
    //TableColumn<Student, String> provinceCol = new TableColumn<Student,String>("Province");
    //TableColumn<Student, Integer> averageCol = new TableColumn<Student,Integer>("Avg Grade");
    //TableColumn<Student, String> majorCol = new TableColumn<Student,String>("Major");




    @FXML
    private void applyFilter()  {
    }


    private void updateTable(ArrayList<Student> studentData) throws Exception {

        for(int i = 0; i<studentData.size(); i++){
            tableView.getItems().add(studentData.get(i));
        }

    }

    @FXML
    void initialize() throws Exception {
        areaCodeComboBox.getItems().add("All");


        //set cell factories to grab class data
        studentNumCol.setCellValueFactory(new PropertyValueFactory<Student,Integer>("studentNumber"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<Student,String>("phoneNumber"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Student,String>("address"));
        provinceCol.setCellValueFactory(new PropertyValueFactory<Student,String>("province"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<Student,Integer>("average"));
        majorCol.setCellValueFactory(new PropertyValueFactory<Student,String>("major"));

        //
        updateTable(DataStore.getStudentData());

    }
}

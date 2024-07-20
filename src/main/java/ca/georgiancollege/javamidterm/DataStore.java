package ca.georgiancollege.javamidterm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DataStore {




    //hold table data
    private static ArrayList<Student> studentData = new ArrayList<>();

    //private static ObservableList<Student> dataList = FXCollections.observableArrayList();
    //ON filter
    //private static ObservableList<Student> OntarioStudents = new ObservableList<>();

    //holds area code data........
    private static ArrayList<Student> filteredData = new ArrayList<>();


    //hold

    //private constructor
    private DataStore(){

    }

    public static void setFilteredData(ArrayList<Student> data){
        filteredData = data;
    }

    public static ArrayList<Student> getFilteredData(){
        return filteredData;
    }





    public static void setStudentData(ArrayList<Student> data){
        studentData = data;
    }

    public static ArrayList<Student> getStudentData(){
        return studentData;
    }
}

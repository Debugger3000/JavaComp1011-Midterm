package ca.georgiancollege.javamidterm;

import java.util.ArrayList;

public class DataStore {




    //hold table data
    private static ArrayList<Student> studentData = new ArrayList<>();

    //holds area code data........
    //ArrayList<String> areaCode = new ArrayList<String>();


    //hold

    //private constructor
    private DataStore(){

    }






    public static void setStudentData(ArrayList<Student> data){
        studentData = data;
    }

    public static ArrayList<Student> getStudentData(){
        return studentData;
    }
}

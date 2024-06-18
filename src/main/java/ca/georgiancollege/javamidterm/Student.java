package ca.georgiancollege.javamidterm;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student {


    private int studentNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String province;
    private int average;
    private String major;

    private ArrayList<String> provinces = new ArrayList<String>();


    public Student(){

    }

    public Student(int studentNumber, String firstName, String lastName, String phoneNumber, String address, String province, int average, String major){

        //student number validation
        if(studentNumber <= 200034000){
            this.studentNumber = -1;
        }
        else{
            this.studentNumber = studentNumber;
        }

        //first name validation
        if(firstName.length() < 2){
            this.firstName = "void";
        }
        else{
            this.firstName = firstName;
        }

        //last Name validation
        if(lastName.length() < 2){
            this.lastName = "void";
        }
        else{
            this.lastName = lastName;
        }

        //telephone number
        if(phoneNumberValid(phoneNumber)){
            this.phoneNumber = phoneNumber;

            //SortedList<String> areaCodes = new SortedList<String>();
            //ArrayList<String> areaCode = new ArrayList<String>();
            //add area code to list
            //areaCode.add(phoneNumber.substring(0,3));





        }
        else{
            this.phoneNumber = "void";
        }


        //address validation
        if(address.length()  < 7){
            this.address = "void";
        }
        else{
            this.address = address;
        }

        //province validation
        //populate array yeah
        populateArray();

        if(provinces.contains(province)){
            this.province = province;
        }
        else{
            this.province = "void";
        }

        //grade validation
        if(average > -1 && average < 101){
            this.average = average;
        }
        else{
            this.average = -1;
        }

        //major validation
        if(major.length() < 6){
            this.major = "void";
        }
        else{
            this.major = major;
        }
    }



    public void queryForData() throws Exception{

        DBUtility dataBase = new DBUtility();

        String queryStatement = "Select studentNum,firstName,lastName,telephone,homeaddress,province,avgGrade,major from students order by studentNum";

        ArrayList<Student> listOfStudents = dataBase.queryAll(queryStatement);

        DataStore.setStudentData(listOfStudents);

    }










    private boolean phoneNumberValid(String phoneNumber){

        for(int i = 0; i<12; i++){

            if(i == 3 || i == 7){
                if(phoneNumber.charAt(i) == '-'){
                    continue;
                }
                else{
                    return false;
                }
            }
            else{
                if(Character.isDigit(phoneNumber.charAt(i))){
                    continue;
                }
                else{
                    return false;
                }
            }
        }
        return true;

    }

    private void populateArray(){
        provinces.add("AB");
        provinces.add("BC");
        provinces.add("MB");
        provinces.add("NB");
        provinces.add("NL");
        provinces.add("NS");
        provinces.add("NT");
        provinces.add("NU");
        provinces.add("ON");
        provinces.add("PE");
        provinces.add("QC");
        provinces.add("SK");
        provinces.add("YT");
    }


    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

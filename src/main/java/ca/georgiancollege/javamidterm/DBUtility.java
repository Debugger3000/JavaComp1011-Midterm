package ca.georgiancollege.javamidterm;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {


    private final String connectionString = "jdbc:mysql://javadb.cpg08gycq61o.us-east-2.rds.amazonaws.com:3306/midTermSchema";

    private final String username = "admin303";
    private final String password = "Cartier6066";

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;


    public DBUtility() {


        try {
//            connection = DriverManager.getConnection(
//                    connectionString + "/" + "NHL",
//                    username, password);
            connection = DriverManager.getConnection(
                    connectionString,
                    username, password);
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("constructor");

        }
    }


    public ArrayList<Student> queryAll(String sql) throws Exception{
        ArrayList<Student> pointsList = new ArrayList<>();

        try {
            statement = connection.createStatement();

            //boolean result = statement.execute(sql);
            ResultSet q = statement.executeQuery(sql);

            while(q.next()){
                //Create teamclass object to store a row of data for a single team.
                Student queryObject = new Student(q.getInt("studentNum"),q.getString("firstName"),q.getString("lastName"),q.getString("telephone"),q.getString("homeAddress"),q.getString("province"),q.getInt("avgGrade"),q.getString("major"));
                //add query object to array to return
                pointsList.add(queryObject);

            }


        }

        catch(Exception ex)
        {
            System.err.println(ex);
            System.out.println("queryall");
        }

        return pointsList;

    }


}

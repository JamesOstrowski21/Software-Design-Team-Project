import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import javafx.application.Application;

import javax.swing.plaf.nimbus.State;
import javax.xml.crypto.Data;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Database class responsible for query's
 */
public class Database {
    /**
     * String variable returns when database connect fails
     */
    private String word = "fail";

    /**
     * URL of the database
     */
    final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db010";
    /**
     * Query statement: query words5let table
     */
    final String WORD_QUERY = "SELECT word FROM words5let";
    /**
     * Query statement: select all values from results table
     */
    final String RESULT_QUERY = "SELECT * FROM results";
    /**
     * Query statement: Used to insert new client information to results table
     */
    final String UPDATE_RESULT = "INSERT INTO results (user, word, attempt, time, score) VALUES (?,?,?,?,?)";
    /**
     * Query statement: Used to update score of client
     */
    final String UPDATE_QUERY = "UPDATE results SET score = ? WHERE user = ?";
    /**
     * Upper bound for random word generator
     */
    final int MAX = 11430;
    /**
     * Used for random word generator
     */
    private  SecureRandom rand = new SecureRandom();
    /**
     * Connection object used to establish connection to database
     */
    private Connection connection = null;
    /**
     * Statement variable used to create query statements
     */
    private Statement statement = null;
    /**
     * ResultSet object used to parse tables in database
     */
    private ResultSet resultSet = null;

    /**
     * Database constructor: established initial connected to database
     */
    public Database(){
        try{

            connection = DriverManager.getConnection(
                    DATABASE_URL, "swd_group010", "group10" );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    /**
     * Generates new word of day -> pulls from words5let table in database
     * @return new word of day
     */
    public String wordGenerator(){

    try{

        connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group010", "group10" );

        int randomRow =  rand.nextInt(MAX);
        statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE,resultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(WORD_QUERY);
        ResultSetMetaData metaData = resultSet.getMetaData();
        resultSet.absolute(randomRow);
        word = resultSet.getObject(1).toString().toUpperCase();
        disconnectDatabase();

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }

    return word;
}

    /**
     * Parses the results table in database for information pertaining to the requested client
     * @param client name of client used in search
     * @return Array list of column data pertaining to the client
     */
    public ArrayList<String> getResult(String client){
        ArrayList<String> userData = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(
                    DATABASE_URL, "swd_group010", "group10" );

            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(RESULT_QUERY);
           // PreparedStatement prep = connection.prepareStatement(RESULT_QUERY);
            ResultSetMetaData meta = resultSet.getMetaData();
            //prep.setString(1,client);
            resultSet.first();
            while(resultSet.next()){
                if (resultSet.getString(1).equals(client)){
                    for (int i = 1; i <= meta.getColumnCount(); i++){
                        userData.add(resultSet.getString(i));
                    }
                    break;
                }
            }
            disconnectDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return userData;
}

    /**
     * Update the score of current client
     * @param user name of current client
     * @param score score value used to replace old score
     */
    public void updateResults (String user, String score){

        try {
            connection = DriverManager.getConnection(
                    DATABASE_URL, "swd_group010", "group10" );


            PreparedStatement prep = connection.prepareStatement(UPDATE_QUERY);

            prep.setString(1,score);
            prep.setString(2,user);
            prep.executeUpdate();
            disconnectDatabase();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
}

    /**
     * Used to insert new client data into results table
     * @param user name of client
     * @param word last word this client played the game with
     * @param attempt attempt number
     * @param lastPlayed time last played -> when client started
     * @param score running score in tournament
     */
    public void setResults(String user, String word, String attempt, String lastPlayed, String score){
        try {
            connection = DriverManager.getConnection(
                    DATABASE_URL, "swd_group010", "group10" );
            PreparedStatement prep = connection.prepareStatement(UPDATE_RESULT);



            prep.setString(1, user);
            prep.setString(2, word);
            prep.setString(3, attempt);
            prep.setString(4, lastPlayed);
            prep.setString(5, score);


            prep.execute();

            System.out.println("Updated database");
            disconnectDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
}

    /**
     * used to close resultSet, statement, and connection.
     */
    public void disconnectDatabase(){

    try {
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Database disconnected.");
    }
    catch (Exception exception){
        exception.printStackTrace();
    }
}





}

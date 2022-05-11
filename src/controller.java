import javafx.css.converter.FontConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.w3c.dom.Text;
//import sun.jvm.hotspot.debugger.win32.coff.TestDebugInfo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

import static java.awt.Color.GREEN;

/**
 * Controller class for Wordle GUI
 * @author SWD_Team10
 */
public class controller implements Initializable {
    /**
     * Stores current guess amount
     */
    Integer currentGuess = 0;
    /**
     * Stores Current Row
     */
    Integer currentRow =1;
    /**
     * Stores words as array of letters
     */
    ArrayList<ArrayList<TextArea>> words = new ArrayList<>();
    /**
     * Initializes database
     */
    private Database database = new Database();
    /**
     * Stores what is today's word
     */
    private String wordOfDay = database.wordGenerator();
    /**
     * Stores word of the day in an array
     */
    private final char[] wodArr = wordOfDay.toCharArray();
    /**
     * Stores client name
     */
    private String client;
    /**
     * Stores client score
     */
    private Integer clientScore = 0; // will create new one for each client

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private String compare; // string to hold characters of text areas -> compare to word of day
    private TreeMap<Integer,TextArea>  rows = new TreeMap<>(); //Tree map store text areas to display client data. (can just post the leader).

    /**
     * Button for letter A
     */
    @FXML
    private Button A;
    /**
     * Button for letter B
     */
    @FXML
    private Button B;
    /**
     * Button for letter Back
     */
    @FXML
    private Button BACK;
    /**
     * Button for letter C
     */
    @FXML
    private Button C;
    /**
     * Button for letter D
     */
    @FXML
    private Button D;
    /**
     * Button for letter E
     */
    @FXML
    private Button E;
    /**
     * Button for letter ENTER
     */
    @FXML
    private Button ENTER;
    /**
     * Button for letter F
     */
    @FXML
    private Button F;
    /**
     * Button for letter G
     */
    @FXML
    private Button G;
    /**
     * Button for letter H
     */
    @FXML
    private Button H;
    /**
     * Button for letter I
     */
    @FXML
    private Button I;
    /**
     * Button for letter J
     */
    @FXML
    private Button J;
    /**
     * Button for letter K
     */
    @FXML
    private Button K;
    /**
     * Button for letter L
     */
    @FXML
    private Button L;
    /**
     * Text Letter 1
     */
    @FXML
    private TextArea L1;

    @FXML
    private TextArea L2;

    @FXML
    private TextArea L3;

    @FXML
    private TextArea L4;

    @FXML
    private TextArea L5;
    @FXML
    private TextArea w2L1;

    @FXML
    private TextArea w2L2;

    @FXML
    private TextArea w2L3;

    @FXML
    private TextArea w2L4;

    @FXML
    private TextArea w2L5;
    @FXML
    private TextArea w3L1;

    @FXML
    private TextArea w3L2;

    @FXML
    private TextArea w3L3;

    @FXML
    private TextArea w3L4;

    @FXML
    private TextArea w3L5;
    @FXML
    private TextArea w4L1;

    @FXML
    private TextArea w4L2;

    @FXML
    private TextArea w4L3;

    @FXML
    private TextArea w4L4;

    @FXML
    private TextArea w4L5;
    @FXML
    private TextArea w5L1;

    @FXML
    private TextArea w5L2;

    @FXML
    private TextArea w5L3;

    @FXML
    private TextArea w5L4;

    @FXML
    private TextArea w5L5;
    @FXML
    private TextArea w6L1;

    @FXML
    private TextArea w6L2;

    @FXML
    private TextArea w6L3;

    @FXML
    private TextArea w6L4;

    @FXML
    private TextArea w6L5;

    @FXML
    private Button M;

    @FXML
    private Button N;

    @FXML
    private Button O;

    @FXML
    private Button P;

    @FXML
    private Button Q;

    @FXML
    private Button R;

    @FXML
    private Button S;

    @FXML
    private Button T;

    @FXML
    private Button U;

    @FXML
    private Button V;

    @FXML
    private Button W;

    @FXML
    private Button X;

    @FXML
    private Button Y;

    @FXML
    private Button Z;

    @FXML
    private GridPane guesses;

    /**
     * User area box
     */
    @FXML
    private TextArea userArea;

    /**
     * Word Area text
     */
    @FXML
    private TextArea wordArea;

    /**
     * attemp area
     */
    @FXML
    private TextArea attemptArea;

    /**
     * time area
     */
    @FXML
    private TextArea timeArea;

    /**
     * score area
     */
    @FXML
    private TextArea scoreArea;


    /**
     * Sets the words based on which guess attempt
     * @param event Action event
     */
    @FXML
    void onEvent(ActionEvent event) {
        Button temp = (Button) event.getSource();
        if(currentGuess == 0) {
            setWord(L1,L2,L3,L4,L5,temp);
        }else if(currentGuess == 1){
            setWord(w2L1,w2L2,w2L3,w2L4,w2L5,temp);
        }else if(currentGuess == 2){
            setWord(w3L1,w3L2,w3L3,w3L4,w3L5,temp);
        }else if(currentGuess == 3){
            setWord(w4L1,w4L2,w4L3,w4L4,w4L5,temp);
        }else if(currentGuess == 4){
            setWord(w5L1,w5L2,w5L3,w5L4,w5L5,temp);
        }else if(currentGuess == 5){
            setWord(w6L1,w6L2,w6L3,w6L4,w6L5,temp);
        }
    }

    /**
     * Checks guess for correct word/letters within the word
     * Updates Use's score and user information
     * @param event Action event
     */
    @FXML
    void onGo(ActionEvent event){
                   //word's list of textarea objects
        ArrayList<String> clientInfo = new ArrayList<>();
        if(currentGuess == 0) {
            if(!L5.getText().equals("")) {
                ArrayList<TextArea> word = new ArrayList<>();
                word.add(L1);
                word.add(L2);
                word.add(L3);
                word.add(L4);
                word.add(L5);
                compare = setString(word);

                words.add(word);

                int count = 0;
                for (int i = 0; i < word.size(); i++){
                    word.get(i).setEditable(true);
                        if ((wordOfDay.charAt(i) == compare.charAt(i)) && word.get(i).isEditable()) {
                            word.get(i).setStyle("-fx-background-color: GREEN");
                            word.get(i).requestFocus();

                            word.get(i).setEditable(false);
                            count = count+ 1;
                            clientScore++;
                        }else{
                            word.get(i).setStyle("-fx-background-color: RED");
                        }
                }currentGuess++;
                /*
               if (count == 0){
                    //clearRow(0);
                   currentGuess++;

                } else {
                    for (int i = 0; i < word.size(); i++){
                        if (word.get(i).isEditable()){
                            word.get(i).clear();
                        }
                    }
                    currentRow++;
                    currentGuess++;
                }*/
                database.setResults(client, wordOfDay,currentGuess.toString(), Time.valueOf(LocalTime.now()).toString(), clientScore.toString());
                clientInfo = database.getResult(client);
                updateClientInfo(clientInfo);
            }
        }
        else if(currentGuess == 1){
            if(!w2L5.getText().equals("")) {
                ArrayList<TextArea> word = new ArrayList<>();
                word.add(w2L1);
                word.add(w2L2);
                word.add(w2L3);
                word.add(w2L4);
                word.add(w2L5);

                words.add(word);
                compare = setString(word);

                int count = 0;
                for (int i = 0; i < word.size(); i++){
                    word.get(i).setEditable(true);
                    if ((wordOfDay.charAt(i) == compare.charAt(i)) && word.get(i).isEditable()) {
                        word.get(i).setStyle("-fx-background-color: GREEN");
                        word.get(i).requestFocus();

                        word.get(i).setEditable(false);
                        count = count+ 1;
                        clientScore++;
                    }else{
                        word.get(i).setStyle("-fx-background-color: RED");
                    }
                }
                currentGuess++;
                /*
                if (count == 0){
                    //clearRow(2);
                    currentGuess++;
                } else {
                    for (int i = 0; i < word.size(); i++){
                        if (word.get(i).isEditable()){
                            word.get(i).clear();
                        }
                    }
                    currentRow++;
                    currentGuess++;
                }*/
                database.updateResults(client, clientScore.toString());
                clientInfo = database.getResult(client);
                updateClientInfo(clientInfo);
            }
        }else if(currentGuess == 2){
            if(!w3L5.getText().equals("")) {
                ArrayList<TextArea> word = new ArrayList<>();
                word.add(w3L1);
                word.add(w3L2);
                word.add(w3L3);
                word.add(w3L4);
                word.add(w3L5);

                words.add(word);

                compare = setString(word);

                int count = 0;
                for (int i = 0; i < word.size(); i++){
                    word.get(i).setEditable(true);
                    if ((wordOfDay.charAt(i) == compare.charAt(i)) && word.get(i).isEditable()) {
                        word.get(i).setStyle("-fx-background-color: GREEN");
                        word.get(i).requestFocus();

                        word.get(i).setEditable(false);
                        count = count+ 1;
                        clientScore++;
                    }else{
                        word.get(i).setStyle("-fx-background-color: RED");
                    }
                }currentGuess++;
                /*
                if (count == 0){
                    //clearRow(3);
                    currentGuess++;
                } else {
                    for (int i = 0; i < word.size(); i++){
                        if (word.get(i).isEditable()){
                            word.get(i).clear();
                        }
                    }
                    currentRow++;
                    currentGuess++;

                }*/
                database.updateResults(client, clientScore.toString());
                clientInfo = database.getResult(client);
                updateClientInfo(clientInfo);
            }
        }else if(currentGuess == 3){
            if(!w4L5.getText().equals("")) {
                ArrayList<TextArea> word = new ArrayList<>();
                word.add(w4L1);
                word.add(w4L2);
                word.add(w4L3);
                word.add(w4L4);
                word.add(w4L5);

                words.add(word);

                compare = setString(word);

                int count = 0;
                for (int i = 0; i < word.size(); i++){
                    word.get(i).setEditable(true);
                    if ((wordOfDay.charAt(i) == compare.charAt(i)) && word.get(i).isEditable()) {
                        word.get(i).setStyle("-fx-background-color: GREEN");
                        word.get(i).requestFocus();

                        word.get(i).setEditable(false);
                        count = count+ 1;
                        clientScore++;
                    }else{
                        word.get(i).setStyle("-fx-background-color: RED");
                    }
                }currentGuess++;
                /*
                if (count == 0){
                    //clearRow(4);
                    currentGuess++;
                } else {
                    for (int i = 0; i < word.size(); i++){
                        if (word.get(i).isEditable()){
                            word.get(i).clear();
                        }
                    }
                    currentRow++;
                    currentGuess++;

                }*/
                database.updateResults(client, clientScore.toString());
                clientInfo = database.getResult(client);
                updateClientInfo(clientInfo);
            }
        }else if(currentGuess == 4){
            if(!w5L5.getText().equals("")) {
                ArrayList<TextArea> word = new ArrayList<>();
                word.add(w5L1);
                word.add(w5L2);
                word.add(w5L3);
                word.add(w5L4);
                word.add(w5L5);

                words.add(word);

                compare = setString(word);

                int count = 0;
                for (int i = 0; i < word.size(); i++){
                    word.get(i).setEditable(true);
                    if ((wordOfDay.charAt(i) == compare.charAt(i)) && word.get(i).isEditable()) {
                        word.get(i).setStyle("-fx-background-color: GREEN");
                        word.get(i).requestFocus();

                        word.get(i).setEditable(false);
                        count = count+ 1;
                        clientScore++;
                    }else{
                        word.get(i).setStyle("-fx-background-color: RED");
                    }
                }currentGuess++;
                /*
                if (count == 0){
                    //clearRow(5);
                    currentGuess++;
                } else {
                    for (int i = 0; i < word.size(); i++){
                        if (word.get(i).isEditable()){
                            word.get(i).clear();
                        }
                    }
                    currentRow++;
                    currentGuess++;

                }*/
database.updateResults(client, clientScore.toString());
                    clientInfo = database.getResult(client);
                    updateClientInfo(clientInfo);
            }
        }else if(currentGuess == 5){
            if(!w6L5.getText().equals("")) {
                ArrayList<TextArea> word = new ArrayList<>();
                word.add(w6L1);
                word.add(w6L2);
                word.add(w6L3);
                word.add(w6L4);
                word.add(w6L5);

                words.add(word);

                compare = setString(word);

                int count = 0;
                for (int i = 0; i < word.size(); i++){
                    word.get(i).setEditable(true);
                    if ((wordOfDay.charAt(i) == compare.charAt(i)) && word.get(i).isEditable()) {
                        word.get(i).setStyle("-fx-background-color: GREEN");
                        word.get(i).requestFocus();

                        word.get(i).setEditable(false);
                        count = count+ 1;
                        clientScore++;
                    }else{
                        word.get(i).setStyle("-fx-background-color: RED");
                    }
                }currentGuess++;
                /*
                if (count == 0){
                    //clearRow(5);
                    currentGuess++;
                } else {
                    for (int i = 0; i < word.size(); i++){
                        if (word.get(i).isEditable()){
                            word.get(i).clear();
                        }

                    }
                    currentRow++;
                    currentGuess++;

                }*/
                database.updateResults(client, clientScore.toString());
                clientInfo = database.getResult(client);
                updateClientInfo(clientInfo);
            }
        }

    }

    /**
     * Removes the letters from each box
     * @param event ActionEvent
     */
    @FXML
    void onBack(ActionEvent event){
        if(currentGuess == 0) {
            remove(L1, L2, L3, L4, L5);
        }else if(currentGuess == 1){
            remove(w2L1, w2L2, w2L3, w2L4, w2L5);
        }else if(currentGuess == 2){
            remove(w3L1, w3L2, w3L3, w3L4, w3L5);
        }else if(currentGuess == 3){
            remove(w4L1, w4L2, w4L3, w4L4, w4L5);
        }else if(currentGuess == 4){
            remove(w5L1,w5L2,w5L3,w5L4,w5L5);
        }else if(currentGuess == 5){
            remove(w6L1,w6L2,w6L3,w6L4,w6L5);
        }
    }

    /**
     * Sets each textArea back to a empty string/blank
     * @param s1 Text area 1
     * @param s2 Text area 2
     * @param s3 Text area 3
     * @param s4 Text area 4
     * @param s5 Text area 5
     */
    void remove(TextArea s1, TextArea s2, TextArea s3, TextArea s4,TextArea s5){
        if (!s5.getText().equals("")) {
            s5.setText("");
        } else if (!s4.getText().equals("")) {
            s4.setText("");
        } else if (!s3.getText().equals("")) {
            s3.setText("");
        } else if (!s2.getText().equals("")) {
            s2.setText("");
        } else if (!s1.getText().equals("")) {
            s1.setText("");
        }
    }

    /**
     * Sets word withing the boxes, based on which boxes are already filled.
     * Continues in order left to right
     * @param s1 TextArea 1
     * @param s2 TextArea 2
     * @param s3 TextArea 3
     * @param s4 TextArea 4
     * @param s5 TextArea 5
     * @param temp Temporarily stores the letter.
     */
    void setWord(TextArea s1, TextArea s2, TextArea s3, TextArea s4,TextArea s5,Button temp){
        if (s1.getText().equals("")) {
            s1.setText(temp.getId());
        } else if (s2.getText().equals("") && !s1.getText().equals("")) {
            s2.setText(temp.getId());
        } else if (s3.getText().equals("") && !s2.getText().equals("")) {
            s3.setText(temp.getId());
        } else if (s4.getText().equals("") && !s3.getText().equals("")) {
            s4.setText(temp.getId());
        } else if (s5.getText().equals("") && !s4.getText().equals("")) {
            s5.setText(temp.getId());
        }
    }

    /**
     * Updates Client information
     * @param update New information to be displayed
     */
    void updateClientInfo(ArrayList<String> update){
        for (int i = 0; i < update.size(); i++){
            rows.get(i+1).setText(update.get(i));
        }
    }

    /**
     * Removes elements from a row.
     * @param row row index to be cleared.
     */
    void clearRow(int row) // updates treeMap containing specified row
    {
       for (int i = 0; i < 5; i++) {
           words.get(row).get(i).clear();
       }
    }

    /**
     * Sets array of letter to a string
     * @param word word in letters based on boxes
     * @return returns constructed string
     */
    String setString(ArrayList<TextArea> word){ // sets compare to string
       // Object[] rowResult = rows.get(row);
        StringBuilder builder = new StringBuilder("");

        for (int i = 0; i < 5; i++){
            builder.append(word.get(i).getText()); //returns comparator String array
        }
        return builder.toString();
    }


    /**
     * Initializes Necessary data
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rows.put(1,userArea); rows.put(2,wordArea); rows.put(3,attemptArea); rows.put(4,timeArea); rows.put(5,scoreArea);

        ArrayList<String> getFromResults = database.getResult("user");


        System.out.println(wordOfDay);
        System.out.println(getFromResults);
        System.out.println("Input username: ");
        Scanner scn = new Scanner(System.in);
        String user = scn.next();
        client = user;
    }
}


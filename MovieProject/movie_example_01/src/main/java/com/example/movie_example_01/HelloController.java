package com.example.movie_example_01;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {
    @FXML
    private Button signUp_button;

    @FXML
    private Button signUp_close;

    @FXML
    private TextField signUp_email;

    @FXML
    private AnchorPane signUp_form;

    @FXML
    private Button signUp_minimize;

    @FXML
    private PasswordField signUp_password;

    @FXML
    private Hyperlink signUp_signin;

    @FXML
    private TextField signUp_username;

    @FXML
    private Button signin_close;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private Button signin_loginBtn;

    @FXML
    private Button signin_minimize;

    @FXML
    private Hyperlink signin_newaccount;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public boolean validEmail(){

        Pattern pattern = Pattern.compile("[a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");

        Matcher match = pattern.matcher(signUp_email.getText());

        Alert alert;

        if(match.find() && match.group().matches(signUp_email.getText())){

            return true;
        }else{

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email");
            alert.showAndWait();
            return false;
        }
    }

    public void signup(){

        String sql = "INSERT INTO users (email,username,password) VALUES (?,?,?)";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signUp_email.getText());
            prepare.setString(2, signUp_username.getText());
            prepare.setString(3, signUp_password.getText());

            Alert alert;

            if(signUp_email.getText().isEmpty() || signUp_username.getText().isEmpty() || signUp_password.getText().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            }else if(signUp_password.getText().length() < 8){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Password");
                alert.showAndWait();

            }else{

                if(validEmail()){

                    prepare.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully created new account!");
                    alert.showAndWait();

                    signUp_email.setText("");
                    signUp_username.setText("");
                    signUp_password.setText("");

                }

            }

        }catch(Exception e){e.printStackTrace();}
    }

    private double x = 0;
    private double y = 0;

    public void signin(){
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signin_username.getText());
            prepare.setString(2, signin_password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if(signin_username.getText().isEmpty() || signin_password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{

                if(result.next()){

                    getData.username = signin_username.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    signin_loginBtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();
                }else{

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();

                }

            }

        }catch(Exception e){e.printStackTrace();}
    }



    public void switchForm(ActionEvent event){

        if(event.getSource() == signin_newaccount){
            signin_form.setVisible(false);
            signUp_form.setVisible(true);
        }else if(event.getSource() == signUp_signin){
            signin_form.setVisible(true);
            signUp_form.setVisible(false);
        }

    }

    public void signin_close(){
        System.exit(0);
    }

    public void signin_minimize(){
        Stage stage = (Stage)signin_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void signUp_close(){
        System.exit(0);
    }

    public void signUp_minimize(){
        Stage stage = (Stage)signUp_form.getScene().getWindow();
        stage.setIconified(true);
    }
}
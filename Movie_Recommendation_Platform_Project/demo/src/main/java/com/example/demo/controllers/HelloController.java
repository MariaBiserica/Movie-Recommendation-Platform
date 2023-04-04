package com.example.demo.controllers;

import com.example.demo.HelloApplication;
import com.example.demo.roles.Admin;
import com.example.demo.roles.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private String username = "";
    private String password = "";
    private String role = "";

    @FXML
    public Label usernameOutputLabel;
    public Label passwordOutputLabel;
    public Label roleOutputLabel;
    public TextField usernameField;
    public PasswordField passwordField;
    public CheckBox showPassword;
    public Button loginButton;
    public Button registerButton;
    public ComboBox roleComboBox = new ComboBox<>();


    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getRole() { return role; }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list = FXCollections.observableArrayList("ADMIN", "USER");
        roleComboBox.setItems(list);
    }

    @FXML
    public void onUsernameTextFieldChanged(ActionEvent actionEvent) {
        username = this.usernameField.getText();
    }

    public void onPasswordTextFieldChanged(ActionEvent actionEvent) {
        password = this.passwordField.getText();
    }

    public void onPasswordFieldCheckBoxChecked(ActionEvent actionEvent) {
        if (this.showPassword.isSelected()) { this.passwordOutputLabel.setText(this.passwordField.getText()); }
        else { this.passwordOutputLabel.setText(""); }
    }

    public void onSelectRole(ActionEvent actionEvent) {
        role = this.roleComboBox.getSelectionModel().getSelectedItem().toString();
    }

    public void onLoginButtonClicked(ActionEvent actionEvent) throws IOException {
        usernameOutputLabel.setText("");
        passwordOutputLabel.setText("");
        roleOutputLabel.setText("");

        if(role == "ADMIN") {
            Admin admin = new Admin();
            if(admin.searchUsername(username)){
                if(admin.searchPassword(password)){
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminView.fxml"));
                    Stage window = (Stage)this.loginButton.getScene().getWindow();
                    window.setTitle("Admin_Stage");
                    this.loginButton.getScene().setRoot((Parent)fxmlLoader.load());
                }
                else{ passwordOutputLabel.setText("Password doesn't match username!"); }
            }
            else{ usernameOutputLabel.setText("Username not found!"); }
        }
        else if(role == "USER") {
            User user = new User();
            if(user.searchUsername(username)){
                if(user.searchPassword(password)){
                    user.addCurrentUser(username, password);
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserView.fxml"));
                    Stage window = (Stage)this.loginButton.getScene().getWindow();
                    window.setTitle("User_Stage");
                    this.loginButton.getScene().setRoot((Parent)fxmlLoader.load());
                }
                else{ passwordOutputLabel.setText("Password doesn't match username!"); }
            }
            else{ usernameOutputLabel.setText("Username not found!"); }
        }
        else { roleOutputLabel.setText("Please select a role!"); }
    }

    public void onRegisterButtonClicked(ActionEvent actionEvent) throws IOException {
        usernameOutputLabel.setText("");
        passwordOutputLabel.setText("");
        roleOutputLabel.setText("");

        if(role == "ADMIN") {
            Admin admin = new Admin();
            if(!admin.searchUsername(username)){
                if(!admin.searchPassword(password)){
                    admin.addNewAdmin(username, password);
                    registerButton.setDisable(true);
                    usernameOutputLabel.setText("Now login with your new created account!");
                }
                else{ passwordOutputLabel.setText("Password already exists!"); }
            }
            else{ usernameOutputLabel.setText("Username already exists!"); }
        }
        else if(role == "USER") {
            User user = new User();
            if(!user.searchUsername(username)){
                if(!user.searchPassword(password)){
                    user.addNewUser(username, password);
                    registerButton.setDisable(true);
                    usernameOutputLabel.setText("Now login with your new created account!");
                }
                else{ passwordOutputLabel.setText("Password already exists!"); }
            }
            else{ usernameOutputLabel.setText("Username already exists!"); }
        }
        else { roleOutputLabel.setText("Please select a role!"); }
    }
}
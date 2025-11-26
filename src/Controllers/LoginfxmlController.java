/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.*;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LoginfxmlController implements Initializable {

    @FXML
    private ImageView ImageView;
    @FXML
    private TextField userNameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Label usernameErrorLabel;
    @FXML
    private Label passwordErrorLable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }

    @FXML
    private void loginBtnHandle(ActionEvent event) throws IOException {

        usernameErrorLabel.setText("");
        passwordErrorLable.setText("");

        String username = userNameTF.getText();
        String password = passwordTF.getText();
        User user = validateUser(username, password);

        if (user == null) {
            if (username.isEmpty()) {
                usernameErrorLabel.setText("user name is requierd");
            } else if (password.isEmpty()) {
                passwordErrorLable.setText("password is requierd");
            } else {
                passwordErrorLable.setText("invalid user name or passsword");
            }
        } else {
            if (user.getRole().equals("Admin")) {
                Parent AdminStageroot = FXMLLoader.load(getClass().getResource("/View/AdminDashboard.fxml"));
                Scene AdminSC = new Scene(AdminStageroot);
                SetStageData(AdminStage, AdminSC, "ShopIcon.png", "Admin Dashboard Page", 50, 50);
                AdminStage.show();
                LoginStage.hide();

            } else if (user.getRole().equals("Librerian")) {
                Parent LibrerianStageroot = FXMLLoader.load(getClass().getResource("/View/LibrerianDashboard.fxml"));
                Scene librerianSC = new Scene(LibrerianStageroot);
                SetStageData(LibrerianStage, librerianSC, "ShopIcon.png", "Librerian Dashboard Page", 50, 50);
                LibrerianStage.show();
                LoginStage.hide();

            } else if(user.getRole().equals("User")){
                Parent UserStageroot = FXMLLoader.load(getClass().getResource("/View/UserDashboard.fxml"));
                Scene UserSC = new Scene(UserStageroot);
                SetStageData(UserStage, UserSC, "ShopIcon.png", "User Dashboard Page", 50, 50);
                UserStage.show();
                LoginStage.hide();
            }
        }

    }

    @FXML
    private void registerBtnHandle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Register.fxml"));
        Scene registerSC = new Scene(root);
        SceneBuilderLibraryMangmentsSystem.SetStageData(SceneBuilderLibraryMangmentsSystem.registerStage, registerSC, "ShopIcon.png", "Register Page", 500, 200);

        SceneBuilderLibraryMangmentsSystem.registerStage.show();
        SceneBuilderLibraryMangmentsSystem.LoginStage.hide();
    }

    public User validateUser(String usrname, String passwrd) {
        for (User user : SceneBuilderLibraryMangmentsSystem.Users) {
            if (user.getUserName().equals(usrname) && user.getPassword().equals(passwrd)) {
                loginUser =user;
                return user;
            }
        }
        return null;
    }
}

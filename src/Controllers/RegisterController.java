/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static DataBase.UserDatabaseHandler.AddUser;
import Model.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.LoginStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.Users;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RegisterController implements Initializable {

    @FXML
    private ImageView userImageView;
    @FXML
    private Label userImgErrorLabel;
    @FXML
    private TextField fullNameTF;
    @FXML
    private Label fullNameErrorLabel;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private Label roleErrorLabel;
    @FXML
    private TextField userNameTF;
    @FXML
    private Label userNameErrorLabel;
    @FXML
    private TextField passwordTF;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private TextField emailTF;
    @FXML
    private Label emailErrorLabel;
    @FXML
    private TextField phoneTF;
    @FXML
    private Label phoneErrorLabel;
    @FXML
    private Button registerBtn;
    @FXML
    private Button cancleBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        roleComboBox.getItems().addAll("User", "Admin", "librerian");
        roleComboBox.setValue("User");
        roleComboBox.setDisable(true);
    }

    private String imagename = null;
    private Image[] profileImage = {null};

    @FXML
    private void userImageViewCliked(MouseEvent event) {

        FileChooser fch = new FileChooser();

        File file = fch.showOpenDialog(SceneBuilderLibraryMangmentsSystem.registerStage);
        if (file != null) {
            profileImage[0] = new Image(file.toURI().toString());
            userImageView.setImage(profileImage[0]);
            this.imagename = "/Images/" + file.getName();
            try {
                SceneBuilderLibraryMangmentsSystem.saveimage(profileImage[0], file.getName());
            } catch (IOException ex) {
                Logger.getLogger(SceneBuilderLibraryMangmentsSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void registerBtnHandle(ActionEvent event) {

        userNameErrorLabel.setText("");
        fullNameErrorLabel.setText("");
        roleErrorLabel.setText("");
        passwordErrorLabel.setText("");
        emailErrorLabel.setText("");
        phoneErrorLabel.setText("");
        userImgErrorLabel.setText("");

        boolean haserror = false;
        if (fullNameTF.getText().isEmpty()) {
            fullNameErrorLabel.setText("Full name is requierd . ");
            haserror = true;
        }
        if (userNameTF.getText().isEmpty()) {
            userNameErrorLabel.setText("User name is requierd . ");
            haserror = true;
        }
        if (passwordTF.getText().isEmpty()) {
            passwordErrorLabel.setText("Password is requierd . ");
            haserror = true;
        }
        if (emailTF.getText().isEmpty()) {
            emailErrorLabel.setText("Email is requierd . ");
            haserror = true;
        }
        if (phoneTF.getText().isEmpty()) {
            phoneErrorLabel.setText("Phone number is requierd . ");
            haserror = true;
        }
        if (this.imagename == null) {
            userImgErrorLabel.setText("Image is requierd . ");
            haserror = true;
        }
        if (!haserror) {

            boolean isfounduser = SceneBuilderLibraryMangmentsSystem.UserExist(userNameTF.getText(), passwordTF.getText());
            if (!isfounduser) {
                User newuser = new User(fullNameTF.getText(), userNameTF.getText(), passwordTF.getText(), emailTF.getText(), phoneTF.getText(), roleComboBox.getValue().toString(), this.imagename.toString());
                Users.add(newuser);
                AddUser(newuser);
                //------alert-----
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User has been registered");
                alert.showAndWait();

                //----cleare input------
                fullNameTF.clear();
                userNameTF.clear();
                passwordTF.clear();
                emailTF.clear();
                phoneTF.clear();
                this.imagename = null;
                SceneBuilderLibraryMangmentsSystem.ShowMyStage(LoginStage);
            } else {
                userNameErrorLabel.setText("User is already exist . ");
            }
        }

    }

    @FXML
    private void cancleBtnHandle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Loginfxml.fxml"));
        Scene registerSC = new Scene(root);
        SceneBuilderLibraryMangmentsSystem.SetStageData(SceneBuilderLibraryMangmentsSystem.registerStage, registerSC, "ShopIcon.png", "Login Page", 550, 200);
        SceneBuilderLibraryMangmentsSystem.LoginStage.show();
        SceneBuilderLibraryMangmentsSystem.registerStage.hide();

    }

}

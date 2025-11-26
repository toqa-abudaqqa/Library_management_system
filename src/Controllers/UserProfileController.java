/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static DataBase.UserDatabaseHandler.UpdateUser;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.AdminStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.LibrerianStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.UserProfileStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.UserStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.Users;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.loginUser;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.saveimage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class UserProfileController implements Initializable {

    @FXML
    private ImageView userImageView;
    @FXML
    private Label userImgErrorLabel;
    @FXML
    private TextField fullNameTF;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private TextField userNameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private Button updateBtn;
    @FXML
    private Button cancelBtn;
    private String imagename;
    @FXML
    private AnchorPane PaneBackground;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        for (User user : Users) {
            if (user == loginUser) {
                fullNameTF.setText(user.getFullname());
                userNameTF.setText(user.getUserName());
                emailTF.setText(user.getEmail());
                phoneTF.setText(user.getPhone());
                passwordTF.setText(user.getPassword());
                roleComboBox.setValue(user.getRole());
                userImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream(user.getProfileImagepath())));
            }
        }
        if (loginUser.getRole().equals("Admin")) {
            PaneBackground.setStyle("-fx-background-color : #403f82");
        } else if (loginUser.getRole().equals("User")) {
            PaneBackground.setStyle("-fx-background-color : #31a2bb");
        } else if (loginUser.getRole().equals("Librerian")) {
            PaneBackground.setStyle("-fx-background-color : #f19077");
        }

    }

    @FXML
    private void userImageViewCliked(MouseEvent event) {
        FileChooser fch = new FileChooser();
        File file = fch.showOpenDialog(AdminStage);
        Image[] profileImage = {null};
        if (file != null) {
            profileImage[0] = new Image(file.toURI().toString());
            userImageView.setImage(profileImage[0]);
            imagename = "/Images/" + file.getName();
            try {
                saveimage(profileImage[0], file.getName());
            } catch (IOException ex) {
                Logger.getLogger(SceneBuilderLibraryMangmentsSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void updateBtnHandle(ActionEvent event) {
        boolean sameuserfound = false;
        for (User user : Users) {
            if (loginUser.getUserName().equals(userNameTF.getText())) {
                continue;
            }
            if (user.getUserName().equals(userNameTF.getText())) {
                sameuserfound = true;
                break;
            }
        }

        for (User user : Users) {
            if (user == loginUser) {
                if (!sameuserfound) {
                    user.setFullname(fullNameTF.getText());
                    user.setUserName(userNameTF.getText());
                    user.setPassword(passwordTF.getText());
                    user.setPhone(phoneTF.getText());
                    user.setEmail(emailTF.getText());
                    user.setRole(roleComboBox.getValue());

                    Users.set(Users.indexOf(user), user);
                    UpdateUser(user);

                    loginUser.setUserName(user.getUserName());
                    userImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream(user.getProfileImagepath())));

                    UserProfileStage.hide();
                    if (loginUser.getRole().equals("Admin")) {
                        AdminStage.show();
                    } else if (loginUser.getRole().equals("User")) {
                        UserStage.show();
                    } else if (loginUser.getRole().equals("Librerian")) {
                        LibrerianStage.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Username is used");
                    alert.showAndWait();

                }
            }
        }
    }

    @FXML
    private void cancelBtnHandle(ActionEvent event) {
        UserProfileStage.hide();
        if (loginUser.getRole().equals("Admin")) {
            AdminStage.show();
        } else if (loginUser.getRole().equals("User")) {
            UserStage.show();
        } else if (loginUser.getRole().equals("Librerian")) {
            LibrerianStage.show();
        }
    }

}

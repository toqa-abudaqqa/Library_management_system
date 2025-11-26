/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.AdminDashboardController.uersRoleList;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.UserStatisticeStage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class UsersStatisticController implements Initializable {

    @FXML
    private TextField SearchTF;
    @FXML
    private Button ClearBtn;
    @FXML
    private TableView<User> usersTabelView;
    @FXML
    private TableColumn<User, String> fullNameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableColumn<User, ImageView> userImageColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        userImageColumn.setCellValueFactory(cellData -> {
            ImageView userImage = new ImageView(cellData.getValue().getProfileImagepath());
            userImage.setFitWidth(40);
            userImage.setFitHeight(40);
            return new SimpleObjectProperty<>(userImage);
        });

        usersTabelView.setItems(uersRoleList);

    }

    @FXML
    private void Searching(KeyEvent event) {
        String searchTxt = SearchTF.getText().toLowerCase();
        ObservableList<User> filteredList = FXCollections.observableArrayList();
        for (User user : uersRoleList) {
            if (user.getFullname().toLowerCase().contains(searchTxt)) {
                filteredList.add(user);
            }
        }
        usersTabelView.setItems(filteredList);
    }

    @FXML
    private void Clear(ActionEvent event) {
        SearchTF.clear();
        usersTabelView.setItems(uersRoleList);
    }

    @FXML
    private void Logout(MouseEvent event) {
        UserStatisticeStage.hide();
    }

}

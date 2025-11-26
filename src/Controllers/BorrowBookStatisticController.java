/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.LibrerianDashboardController.BookStatusList;
import Model.BorrowBookDetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
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
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.BorrowBookStatisticeStage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class BorrowBookStatisticController implements Initializable {

    @FXML
    private TextField SearchTF;
    @FXML
    private Button ClearBtn;
    @FXML
    private TableView<BorrowBookDetails> BorrowdBoohsTable;
    @FXML
    private TableColumn<BorrowBookDetails, String> UserIDColumn;
    @FXML
    private TableColumn<BorrowBookDetails, String> UserNameColumn;
    @FXML
    private TableColumn<BorrowBookDetails, ImageView> UserImageColumn;
    @FXML
    private TableColumn<BorrowBookDetails, String> BookIdColumn;
    @FXML
    private TableColumn<BorrowBookDetails, String> BookTitleColumn;
    @FXML
    private TableColumn<BorrowBookDetails ,ImageView> BoolImageColumn;
    @FXML
    private TableColumn<BorrowBookDetails, String> BorrowStatusColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        UserImageColumn.setCellValueFactory(cellData -> {
            ImageView userImage = new ImageView(cellData.getValue().getUserImage());
            userImage.setFitWidth(40);
            userImage.setFitHeight(40);
            return new SimpleObjectProperty<>(userImage);

        });
        BookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        BookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        BoolImageColumn.setCellValueFactory(cellData -> {
            ImageView bookImage = new ImageView(cellData.getValue().getBookImage());
            bookImage.setFitWidth(40);
            bookImage.setFitHeight(40);
            return new SimpleObjectProperty<>(bookImage);

        });
        BorrowStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        BorrowdBoohsTable.setItems(BookStatusList);
        
    }    

    @FXML
    private void Searching(KeyEvent event) {
      String searchTxt =  SearchTF.getText().toLowerCase();
      ObservableList<BorrowBookDetails> filteredList = FXCollections.observableArrayList();
      
      for(BorrowBookDetails bbd :BookStatusList){
          if(bbd.getUserName().toLowerCase().contains(searchTxt)){
              filteredList.add(bbd);
          }
      }
BorrowdBoohsTable.setItems(filteredList);
    }

    @FXML
    private void Clear(ActionEvent event) {
        SearchTF.setText("");
        BorrowdBoohsTable.setItems(BookStatusList);
    }

    @FXML
    private void Logout(MouseEvent event) {
        BorrowBookStatisticeStage.hide();
    }
    
}

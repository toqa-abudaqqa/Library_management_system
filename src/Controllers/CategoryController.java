/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static DataBase.CategorysDatabaseHandler.AddCategory;
import Model.Categoryes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.AdminStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.CategoryStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.categorysList;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.categorysList2;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CategoryController implements Initializable {

    @FXML
    private TextField CategoryTF;
    @FXML
    private Button addBtn;
    @FXML
    private Button CancelBtn;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addBtnHandel(ActionEvent event) {
       
        String newcategory = CategoryTF.getText() ;
        categorysList.add(newcategory);
        categorysList2.add(newcategory);
        AddCategory(newcategory);
        AdminStage.show();
        CategoryStage.hide();
    }

    @FXML
    private void cancelBtnHandel(ActionEvent event) {
        SceneBuilderLibraryMangmentsSystem.AdminStage.show();
        SceneBuilderLibraryMangmentsSystem.CategoryStage.hide();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenebuilderlibrarymangmentssystem;

import DataBase.BookDatabaseHandler;
import static DataBase.BorrowDetailsDatabaseHandler.getBorrowDetails;
import static DataBase.CategorysDatabaseHandler.getCategorys;
import DataBase.DatabaseConnection;
import DataBase.UserDatabaseHandler;
import Model.Book;
import Model.BorrowBookDetails;
import Model.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author PC
 */
public class SceneBuilderLibraryMangmentsSystem extends Application {

    public static Stage LoginStage = new Stage();
    public static Stage registerStage = new Stage();
    public static Stage AdminStage = new Stage();
    public static Stage LibrerianStage = new Stage();
    public static Stage UserStage = new Stage();
    public static Stage CategoryStage = new Stage();
    public static Stage UserProfileStage = new Stage();
    public static Stage UserStatisticeStage = new Stage();
     public static Stage BorrowBookStatisticeStage = new Stage();
    public static ObservableList<User> Users = FXCollections.observableArrayList();
    public static ObservableList<Book> Books = FXCollections.observableArrayList();
    public static ObservableList<BorrowBookDetails> BorrowedBooksDetails = FXCollections.observableArrayList();
    public static ObservableList<String> categorysList = FXCollections.observableArrayList();
    public static ObservableList<String> categorysList2 = FXCollections.observableArrayList();
    public static User loginUser;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Loginfxml.fxml"));
        Scene loginSC = new Scene(root);
        categorysList.addAll(getCategorys());
        categorysList.add("All");
        categorysList2.addAll(getCategorys());
        //----------load Data
        Users.setAll(UserDatabaseHandler.getUsersData());
        Books.setAll(BookDatabaseHandler.getBooksData());
        SetStageData(LoginStage, loginSC, "ShopIcon.png", "Login Page", 550, 200);
        LoginStage.show();
        DatabaseConnection.getInstance();
        BorrowedBooksDetails.setAll(getBorrowDetails());

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

    public static void SetStageImageIcon(Stage s, String logo) {
        s.getIcons().add(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream("/Images/" + logo)));

        s.setResizable(false);

    }

    public static void SetStageData(Stage s, Scene sc, String logo, String title, int setx, int sety) {
        s.setScene(sc);
        s.setTitle(title);
        s.setX(setx);
        s.setY(sety);
        SetStageImageIcon(s, logo);

    }

    public static void ShowMyStage(Stage s) {
        SceneBuilderLibraryMangmentsSystem.LoginStage.hide();
        SceneBuilderLibraryMangmentsSystem.registerStage.hide();
        SceneBuilderLibraryMangmentsSystem.AdminStage.hide();
        s.show();
    }

    public static void saveimage(Image image, String name) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String imagesfolderPath = projectPath + "/src/Images";

        File imagesFolder = new File(imagesfolderPath);
        if (!imagesFolder.exists()) {
            imagesFolder.mkdir();
        }

        String fullFilePath = imagesFolder + "/" + name;
        File file = new File(fullFilePath);
        BufferedImage BI = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(BI, "png", file);
    }

    public static boolean UserExist(String username, String password) {
        boolean userfound = false;
        for (User user : SceneBuilderLibraryMangmentsSystem.Users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                userfound = true;
            }
        }
        return userfound;
    }

}

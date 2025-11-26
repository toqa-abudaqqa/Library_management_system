/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static DataBase.BookDatabaseHandler.AddBook;
import static DataBase.BookDatabaseHandler.DeleteBook;
import static DataBase.BookDatabaseHandler.UpdateBook;
import static DataBase.BookDatabaseHandler.getBooksData;
import static DataBase.CategorysDatabaseHandler.getCategorys;
import static DataBase.UserDatabaseHandler.AddUser;
import static DataBase.UserDatabaseHandler.DeleteUser;
import static DataBase.UserDatabaseHandler.UpdateUser;
import static DataBase.UserDatabaseHandler.getAdmins;
import static DataBase.UserDatabaseHandler.getLibrerians;
import static DataBase.UserDatabaseHandler.getUsers;
import static DataBase.UserDatabaseHandler.getUsersData;
import Model.Book;
import Model.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.*;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Label homeLable;
    @FXML
    private Label sidebarUserMangments;
    @FXML
    private Label sidebarBookMangments;
    @FXML
    private AnchorPane APHome;
    @FXML
    private AnchorPane APUserMangments;
    @FXML
    private AnchorPane APBookMangments;
    @FXML
    private ImageView Logoutimage;
    @FXML
    private ImageView adminImageView;
    @FXML
    private Label adminFullName;
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
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private ComboBox<String> filterCombox;
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

    String imagename;
    Image[] profileImage = {null};
    @FXML
    private ImageView bookImageView;
    @FXML
    private Label bookImageErrorLabel;
    @FXML
    private ComboBox<String> categoryCombox;
    @FXML
    private Label categoryLabelError;
    @FXML
    private Label userImgErrorLabel1;
    @FXML
    private ComboBox<String> languageCombox;
    @FXML
    private Label languageLabelError;
    @FXML
    private TextField titleTF;
    @FXML
    private Label titleErrorLabel;
    @FXML
    private TextField autherTF;
    @FXML
    private Label autherErrorLabel;
    @FXML
    private TextField isbnTF;
    @FXML
    private Label isbnErrorLabel;
    @FXML
    private TextField pblishDateTF;
    @FXML
    private Label publishDateErrorLabel;
    @FXML
    private TextField pageCountTF;
    @FXML
    private Label pageCountErrorLabel;
    @FXML
    private TextField copyCountTF;
    @FXML
    private Label copyCountErrorLabel;
    @FXML
    private TextField publisherTF;
    @FXML
    private Label publishErrorLabel;
    @FXML
    private Button bookAddBtn;
    @FXML
    private Button bookUpdateBtn;
    @FXML
    private Button bookDeleteBtn;
    @FXML
    private Button bookCancelBtn;
    @FXML
    private ComboBox<String> categoryFilterCombox;
    @FXML
    private ImageView categoryAddImage;
    @FXML
    private TableView<Book> booksTabelView;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> autherColumn;
    @FXML
    private TableColumn<Book, String> categoryColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> copyCountColumn;
    @FXML
    private TableColumn<Book, String> pageCountColumn;
    @FXML
    private TableColumn<Book, String> langugeColumn;
    @FXML
    private TableColumn<Book, ImageView> bookImageColumn;
    String bookimagename = null;
    Image[] bookImage = {null};
    Image[] selectedUserImage = {null};
    @FXML
    private Label sidebarStatistics;
    @FXML
    private Label sidebarLogout;
    @FXML
    private AnchorPane APStatistics;
    @FXML
    private Label allUsereNum;
    @FXML
    private Label adminsNum;
    @FXML
    private Label libriransNum;
    @FXML
    private Label usersNum;
    public static ObservableList<User> uersRoleList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO\\
        //---------side bar labels style------
        homeLable.getStyleClass().add("sidebar-label");
        sidebarUserMangments.getStyleClass().add("sidebar-label");
        sidebarBookMangments.getStyleClass().add("sidebar-label");
        sidebarStatistics.getStyleClass().add("sidebar-label");
        setSelectedSidebarLabel(homeLable, APHome);

        //--------combo box set data-----
        roleComboBox.getItems().addAll("User", "Admin", "Librerian");

        filterCombox.getItems().addAll("All", "User", "Admin", "Librerian");

        categoryFilterCombox.setItems(categorysList);
        categoryFilterCombox.setValue("All");
        categoryCombox.setItems(categorysList2);

        languageCombox.getItems().addAll("EN", "AR");

        adminImageView.imageProperty().bind(
                Bindings.createObjectBinding(
                        () -> new Image(loginUser.getProfileImagepath()), loginUser.ProfileImagePathProperty()
                ));

        adminFullName.textProperty().bind(loginUser.FullNameProperty());
        usersTabelView.getItems().removeAll(Users);
        usersTabelView.setItems(Users);

        //--------Table view listener--------------
        usersTabelView.getSelectionModel().selectedItemProperty().addListener(e -> {
            User user = usersTabelView.getSelectionModel().getSelectedItem();
            if (user != null) {

                fullNameTF.setText(user.getFullname());
                userNameTF.setText(user.getUserName());
                passwordTF.setText(user.getPassword());
                phoneTF.setText(user.getPhone());
                emailTF.setText(user.getEmail());
                roleComboBox.setValue(user.getRole());
                selectedUserImage[0] = new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream(user.getProfileImagepath()));
                userImageView.setImage(selectedUserImage[0]);
            }

        });

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
        usersTabelView.setItems(Users);

        //-------------Books tabel view-----------
        booksTabelView.getSelectionModel().selectedItemProperty().addListener(e -> {
            Book book = booksTabelView.getSelectionModel().getSelectedItem();
            if (book != null) {
                titleTF.setText(book.getTitle());
                autherTF.setText(book.getAuther());
                isbnTF.setText(book.getIsbn());
                pblishDateTF.setText(book.getPublishDate());
                pageCountTF.setText(String.valueOf(book.getPagecount()));
                copyCountTF.setText(String.valueOf(book.getCopycount()));
                publisherTF.setText(book.getPublisher());
                categoryCombox.setValue(book.getCategory());
                languageCombox.setValue(book.getLanguage());
                bookImage[0] = new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream(book.getProfileImagepath()));
                bookImageView.setImage(bookImage[0]);
            }
        });

        //---------------Books tabel set cell data--------
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        autherColumn.setCellValueFactory(new PropertyValueFactory<>("auther"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        copyCountColumn.setCellValueFactory(new PropertyValueFactory<>("copycount"));
        pageCountColumn.setCellValueFactory(new PropertyValueFactory<>("pagecount"));
        langugeColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        bookImageColumn.setCellValueFactory(cellData -> {
            ImageView bookImage = new ImageView(cellData.getValue().getProfileImagepath());
            bookImage.setFitWidth(40);
            bookImage.setFitHeight(40);
            return new SimpleObjectProperty<>(bookImage);

        });
        booksTabelView.setItems(Books);

        try {
            Parent userStatisticStageroot = FXMLLoader.load(getClass().getResource("/View/UsersStatistic.fxml"));
            Scene CategorySC = new Scene(userStatisticStageroot);
            SetStageData(UserStatisticeStage, CategorySC, "ShopIcon.png", "User Statistic Page", 200, 200);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ShowHomeSidebar(MouseEvent event) {
        setSelectedSidebarLabel(homeLable, APHome);

    }

    @FXML
    private void showSidebarUserMangments(MouseEvent event) {
        setSelectedSidebarLabel(sidebarUserMangments, APUserMangments);
    }

    @FXML
    private void showsidebarBookMangments(MouseEvent event) {
        setSelectedSidebarLabel(sidebarBookMangments, APBookMangments);
    }

    @FXML
    private void showsidebarStatistics(MouseEvent event) {
        setSelectedSidebarLabel(sidebarStatistics, APStatistics);
        allUsereNum.setText(String.valueOf(getUsersData().size()));
        adminsNum.setText(String.valueOf(getAdmins().size()));
        libriransNum.setText(String.valueOf(getLibrerians().size()));
        usersNum.setText(String.valueOf(getUsers().size()));
    }

    public void setSelectedSidebarLabel(Label selectedLabel, AnchorPane selectedAP) {
        homeLable.getStyleClass().remove("selected");
        sidebarUserMangments.getStyleClass().remove("selected");
        sidebarBookMangments.getStyleClass().remove("selected");
        sidebarStatistics.getStyleClass().remove("selected");
        selectedLabel.getStyleClass().add("selected");

//        -------------------------
        APHome.setVisible(false);
        APUserMangments.setVisible(false);
        APBookMangments.setVisible(false);
        APStatistics.setVisible(false);
        selectedAP.setVisible(true);

    }

    @FXML
    private void Logout(MouseEvent event) {
        SceneBuilderLibraryMangmentsSystem.AdminStage.hide();
        SceneBuilderLibraryMangmentsSystem.LoginStage.show();

    }

    @FXML
    private void adminInfoCheck(MouseEvent event) throws IOException {
        Parent userProfileStageroot = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene CategorySC = new Scene(userProfileStageroot);
        SetStageData(UserProfileStage, CategorySC, "ShopIcon.png", "User Profile Page", 200, 200);
        UserProfileStage.show();
        AdminStage.hide();
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
    private void addBtnHandle(ActionEvent event) {

        fullNameErrorLabel.setText("");
        userNameErrorLabel.setText("");
        passwordErrorLabel.setText("");
        emailErrorLabel.setText("");
        phoneErrorLabel.setText("");
        userImgErrorLabel.setText("");
        roleErrorLabel.setText("");
        boolean isfounduser = UserExist(userNameTF.getText(), passwordTF.getText());

        if (!isfounduser) {
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
            if (roleComboBox.getValue() == null) {
                roleErrorLabel.setText("rolr is requierd . ");
                haserror = true;
            }
            if (!haserror) {

                if (!isfounduser) {
                    User newuser = new User(fullNameTF.getText(), userNameTF.getText(), passwordTF.getText(), emailTF.getText(), phoneTF.getText(), roleComboBox.getValue(), this.imagename.toString());
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
                    roleComboBox.setValue(null);
                    userImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream("/Images/adminIcon.png")));

                } else {
                    userNameErrorLabel.setText("User is already exist with this user name & password . ");
                }
            }
        } else {
            userNameErrorLabel.setText("User is already exist with this user name & password . ");
        }

    }

    @FXML
    private void updateBtnHandle(ActionEvent event) {

        User selectedUser = usersTabelView.getSelectionModel().getSelectedItem();

        boolean sameuserAuth = false;
        int userIndex = 0;
        if (selectedUser != null) {
            for (User user : Users) {
                if (user.getUserName().equals(selectedUser.getUserName())) {
                    selectedUser = user;
                    userIndex = Users.indexOf(user);
                    if (user.equals(loginUser)) {
                        sameuserAuth = true;
                    }
                    break;
                }

            }
            boolean sameuserfound = false;
            for (User user : Users) {
                if (selectedUser.getUserName().equals(userNameTF.getText())) {
                    continue;
                }
                if (user.getUserName().equals(userNameTF.getText())) {
                    sameuserfound = true;
                    break;
                }
            }

            if (selectedUser != null) {
                if (!sameuserfound) {

                    selectedUser.setFullname(fullNameTF.getText());
                    selectedUser.setUserName(userNameTF.getText());
                    selectedUser.setPassword(passwordTF.getText());
                    selectedUser.setPhone(phoneTF.getText());
                    selectedUser.setEmail(emailTF.getText());
                    selectedUser.setRole(roleComboBox.getValue());

                    if (this.imagename != null) {
                        selectedUser.setProfileImagepath(selectedUserImage[0].toString().replace("file:", " "));
                    }

                    Users.set(userIndex, selectedUser);
                    UpdateUser(selectedUser);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "User updated");
                    alert.showAndWait();

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Username is used");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "please select user to update");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteBtnHandle(ActionEvent event) {
        User selectedUser = usersTabelView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {

            for (User user : Users) {

                if (user.getUserName().equals(selectedUser.getUserName())) {

                    Users.remove(user);
                    DeleteUser(user);
                    usersTabelView.setItems(null);
                    usersTabelView.setItems(Users);
                    //--------clear-------
                    fullNameTF.clear();
                    userNameTF.clear();
                    passwordTF.clear();
                    emailTF.clear();
                    phoneTF.clear();
                    this.imagename = null;
                    roleComboBox.setValue(null);
                    userImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream("/Images/adminIcon.png")));

                    if (selectedUser.equals(loginUser)) {
                        AdminStage.hide();
                        LoginStage.show();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, " Deleted");
                    alert.showAndWait();
                    break;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "please select user to Delete");
            alert.showAndWait();
        }

    }

    @FXML
    private void cancelBtnHandle(ActionEvent event) {
        fullNameTF.clear();
        userNameTF.clear();
        passwordTF.clear();
        emailTF.clear();
        phoneTF.clear();
        this.imagename = null;
        roleComboBox.setValue(null);
        userImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream("/Images/adminIcon.png")));
    }

    @FXML
    private void filterComboxHandel(ActionEvent event) {
        String selectedrole = filterCombox.getValue();

        if (selectedrole.equals("All")) {
            usersTabelView.setItems(Users);
        } else {
            ObservableList<User> filteredUsers = FXCollections.observableArrayList();
            for (User user : Users) {
                if (user.getRole().equals(selectedrole)) {
                    filteredUsers.add(user);
                }

            }
            usersTabelView.setItems(filteredUsers);
        }

    }

    @FXML
    private void bookImageViewCliked(MouseEvent event) {
        FileChooser fch = new FileChooser();
        File file = fch.showOpenDialog(AdminStage);
        if (file != null) {
            bookImage[0] = new Image(file.toURI().toString());
            bookImageView.setImage(bookImage[0]);
            this.bookimagename = "/Images/" + file.getName();
            try {
                saveimage(bookImage[0], file.getName());
            } catch (IOException ex) {
                Logger.getLogger(SceneBuilderLibraryMangmentsSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            bookImageErrorLabel.setText("image is required");
        }

    }

    @FXML
    private void bookAddBtnHandle(ActionEvent event) {
        bookImageErrorLabel.setText("");
        titleErrorLabel.setText("");
        autherErrorLabel.setText("");
        isbnErrorLabel.setText("");
        categoryLabelError.setText("");
        languageLabelError.setText("");
        pageCountErrorLabel.setText("");
        copyCountErrorLabel.setText("");
        publishErrorLabel.setText("");
        publishDateErrorLabel.setText("");

        boolean haserror = false;
        if (titleTF.getText().isEmpty()) {
            titleErrorLabel.setText("Tltie is requierd");
            haserror = true;
        }
        if (autherTF.getText().isEmpty()) {
            autherErrorLabel.setText("Auther is requierd");
            haserror = true;
        }
        if (isbnTF.getText().isEmpty()) {
            isbnErrorLabel.setText("isbn is requierd");
            haserror = true;
        }
        if (pblishDateTF.getText().isEmpty()) {
            publishDateErrorLabel.setText("publish data is requierd");
            haserror = true;
        }
        if (pageCountTF.getText().isEmpty()) {
            pageCountErrorLabel.setText("page count is requierd");
            haserror = true;
        }
        if (copyCountTF.getText().isEmpty()) {
            copyCountErrorLabel.setText("copy count is requierd");
            haserror = true;
        }
        if (publisherTF.getText().isEmpty()) {
            publishErrorLabel.setText("publiaher is requierd");
            haserror = true;
        }
        if (categoryCombox.getValue() == null) {
            categoryLabelError.setText("category is requierd");
            haserror = true;
        }
        if (languageCombox.getValue() == null) {
            languageLabelError.setText("language is requierd");
            haserror = true;
        }
        if (this.bookimagename == null) {
            bookImageErrorLabel.setText("image is required");

        }
        if (!haserror) {
            boolean isfoundbook = BookExist(isbnTF.getText());
            if (!isfoundbook) {
                Book newBook = new Book(languageCombox.getValue(), publisherTF.getText(), Integer.parseInt(pageCountTF.getText()), pblishDateTF.getText(), Integer.parseInt(copyCountTF.getText()), isbnTF.getText(), categoryCombox.getValue(), autherTF.getText(), titleTF.getText(), this.bookimagename.toString());
                Books.add(newBook);
                AddBook(newBook);
                //------------clear
                pblishDateTF.clear();
                copyCountTF.clear();
                publisherTF.clear();
                pageCountTF.clear();
                isbnTF.clear();
                autherTF.clear();
                titleTF.clear();
                this.bookimagename = null;
                languageCombox.setValue(null);
                categoryCombox.setValue(null);
                bookImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream("/Images/books.png")));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book has been Added");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "this isbn is used");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void bookUpdateBtnHandle(ActionEvent event) {

        Book selectedbook = booksTabelView.getSelectionModel().getSelectedItem();
        int selectedindex = booksTabelView.getSelectionModel().getSelectedIndex();
        if (selectedbook != null) {
            for (Book book : Books) {
                if (book.getIsbn().equals(selectedbook.getIsbn())) {
                    selectedbook = book;
                    break;
                }

            }
            boolean samebookfound = false;
            for (Book book : Books) {
                if (selectedbook.getIsbn().equals(isbnTF.getText())) {
                    continue;
                }
                if (book.getIsbn().equals(isbnTF.getText())) {
                    samebookfound = true;
                    break;
                }
            }
            if (!samebookfound) {
                selectedbook.setAuther(autherTF.getText());
                selectedbook.setTitle(titleTF.getText());
                selectedbook.setCategory(categoryCombox.getValue());
                selectedbook.setLanguage(languageCombox.getValue());
                selectedbook.setCopycount(Integer.parseInt(copyCountTF.getText()));
                selectedbook.setIsbn(isbnTF.getText());
                selectedbook.setPagecount(Integer.parseInt(pageCountTF.getText()));
                selectedbook.setPublishDate(pblishDateTF.getText());
                selectedbook.setPublisher(publisherTF.getText());

                if (this.bookimagename != null) {
                    selectedbook.setProfileImagepath(bookImage[0].toString().replace("file :", ""));
                }

                Books.set(selectedindex, selectedbook);
                UpdateBook(selectedbook);

                booksTabelView.getItems().set(selectedindex, selectedbook);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "book updated");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Isbn is used");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "please select book to update");
            alert.showAndWait();
        }

    }

    @FXML
    private void bookDeleteBtnHandle(ActionEvent event) {

        Book selectedbook = booksTabelView.getSelectionModel().getSelectedItem();
        if (selectedbook != null) {
            for (Book book : Books) {
                if (book.getIsbn().equals(selectedbook.getIsbn())) {
                    Books.remove(book);
                    DeleteBook(book);
                    booksTabelView.setItems(null);
                    booksTabelView.setItems(Books);
                    //--------clear------
                    titleTF.clear();
                    autherTF.clear();
                    isbnTF.clear();
                    pblishDateTF.clear();
                    pageCountTF.clear();
                    publisherTF.clear();
                    copyCountTF.clear();
                    languageCombox.setValue(null);
                    categoryCombox.setValue(null);
                    bookImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream("/Images/books.jpg")));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, " Deleted");
                    alert.showAndWait();
                }

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "please select Book to Delete");
            alert.showAndWait();
        }

    }

    @FXML
    private void bookCancelBtnHandle(ActionEvent event) {
        titleTF.clear();
        autherTF.clear();
        isbnTF.clear();
        pblishDateTF.clear();
        pageCountTF.clear();
        publisherTF.clear();
        copyCountTF.clear();
        languageCombox.setValue(null);
        categoryCombox.setValue(null);
        bookImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream("/Images/books.jpg")));
    }

    @FXML
    private void addCategory(MouseEvent event) throws IOException {
        Parent CategoryStageroot = FXMLLoader.load(getClass().getResource("/View/Category.fxml"));
        Scene CategorySC = new Scene(CategoryStageroot);
        SetStageData(CategoryStage, CategorySC, "ShopIcon.png", "Category dashboard Page", 200, 200);
        SceneBuilderLibraryMangmentsSystem.CategoryStage.show();
        AdminStage.hide();

    }

    @FXML
    private void categoryFilterHandel(ActionEvent event) {

        String selectedrole = categoryFilterCombox.getValue();

        if (selectedrole.equals("All")) {
            booksTabelView.setItems(Books);
        } else {
            ObservableList<Book> filteredBook = FXCollections.observableArrayList();
            for (Book book : Books) {
                if (book.getCategory().equals(selectedrole)) {
                    filteredBook.add(book);
                }
            }
            booksTabelView.setItems(filteredBook);
        }
    }

    public boolean BookExist(String isbn) {
        boolean bookfound = false;
        for (Book book : Books) {
            if (book.getIsbn().equals(isbn)) {
                bookfound = true;
            }
        }
        return bookfound;
    }

    @FXML
    private void showsidebarLogout(MouseEvent event) {
        SceneBuilderLibraryMangmentsSystem.AdminStage.hide();
        SceneBuilderLibraryMangmentsSystem.LoginStage.show();
    }

    @FXML
    private void ShowAllUsers(MouseEvent event) {
        uersRoleList.clear();
        uersRoleList.addAll(getUsersData());
        UserStatisticeStage.show();

    }

    @FXML
    private void ShowAllAdmins(MouseEvent event) {
        uersRoleList.clear();
        uersRoleList.addAll(getAdmins());
        UserStatisticeStage.show();

    }

    @FXML
    private void SowAllLibririans(MouseEvent event) {
        uersRoleList.clear();
        uersRoleList.addAll(getLibrerians());
        UserStatisticeStage.show();

    }

    @FXML
    private void ShowUsers(MouseEvent event) {
        uersRoleList.clear();
        uersRoleList.addAll(getUsers());
        UserStatisticeStage.show();

    }

}

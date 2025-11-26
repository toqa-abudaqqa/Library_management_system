package Controllers;

import static DataBase.BookDatabaseHandler.updateCopyCount;
import static DataBase.BorrowDetailsDatabaseHandler.AddBorrow;
import static DataBase.BorrowDetailsDatabaseHandler.DeleteBorrow;
import static DataBase.BorrowDetailsDatabaseHandler.getBookStatus;
import static DataBase.BorrowDetailsDatabaseHandler.getBorrowDetails;
import static DataBase.BorrowDetailsDatabaseHandler.getUserBooks;
import Model.Book;
import Model.BorrowBookDetails;
import Model.User;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.Books;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.BorrowedBooksDetails;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.LoginStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.SetStageData;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.UserProfileStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.UserStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.Users;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.categorysList2;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.loginUser;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class UserDashboardController implements Initializable {

    @FXML
    private ImageView UserImageView;
    @FXML
    private Label homeLable;
    @FXML
    private AnchorPane APHome;
    @FXML
    private Label userFullName;
    @FXML
    private ImageView logout;
    @FXML
    private ComboBox<String> categoryCombox;
    @FXML
    private ComboBox<String> selectBook;
    @FXML
    private Button searchBtn;
    @FXML
    private ImageView bookImageView;
    @FXML
    private TextField TitleTF;
    @FXML
    private TextField autherTF;
    @FXML
    private TextField isbnTF;
    @FXML
    private TextField publishDataTF;
    @FXML
    private TextField copyCountTF;
    @FXML
    private TextField pageCountTF;
    @FXML
    private TextField publisherTF;
    @FXML
    private TextField categoryTF;
    @FXML
    private Button borrowBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private AnchorPane APBorrowBook;
    @FXML
    private AnchorPane APBorrowedBook;
    @FXML
    private Label sidebarBorrowBook;
    @FXML
    private Label sidebarBorrowedBook;
    public static ObservableList<BorrowBookDetails> UserBooksList = FXCollections.observableArrayList();
    @FXML
    private FlowPane flowPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO\\
        sidebarBorrowBook.getStyleClass().add("sidebar-label");
        sidebarBorrowedBook.getStyleClass().add("sidebar-label");
        borrowBtn.getStyleClass().add("sidebar-labelborrow-btn");
        setSelectedSidebarLabel(homeLable, APHome);

        UserImageView.imageProperty().bind(
                Bindings.createObjectBinding(
                        () -> new Image(loginUser.getProfileImagepath()), loginUser.ProfileImagePathProperty()
                ));
        userFullName.textProperty().bind(loginUser.FullNameProperty());
        categoryCombox.setItems(categorysList2);
    }

    @FXML
    private void userInfoCheck(MouseEvent event) throws IOException {
        Parent userProfileStageroot = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene CategorySC = new Scene(userProfileStageroot);
        SetStageData(UserProfileStage, CategorySC, "ShopIcon.png", "User Profile Page", 200, 200);
        UserProfileStage.show();
        UserStage.hide();
    }

    @FXML
    private void ShowHomeSidebar(MouseEvent event) {
        setSelectedSidebarLabel(homeLable, APHome);

    }

    @FXML
    private void showSidebarBorrowBook(MouseEvent event) {
        setSelectedSidebarLabel(sidebarBorrowBook, APBorrowBook);
    }

    @FXML
    private void showsidebarBorrowedBook(MouseEvent event) {
        setSelectedSidebarLabel(sidebarBorrowedBook, APBorrowedBook);

        UserBooksList.setAll(getUserBooks(loginUser));
        flowPane.getChildren().clear();
        for (BorrowBookDetails bbd : UserBooksList) {

            Image im = new Image(UserDashboardController.class.getResourceAsStream(bbd.getBookImage()));
            ImageView imageView = new ImageView(im);
            imageView.setFitWidth(120);
            imageView.setFitHeight(120);
            Label titleLabel = new Label(bbd.getBookTitle());
            titleLabel.setStyle("-fx-font-weight:bolder;");
            Label deleverDateLabel = new Label("Delever Date : null ");
            deleverDateLabel.setDisable(true);
            Label statusLabel = new Label("Status : " + bbd.getStatus());
            Button btn = new Button();
            if (bbd.getStatus().equalsIgnoreCase("Approved")) {
                btn.setText("Return");
                btn.setOnAction(e -> {
                    UserBooksList.remove(bbd);
                    DeleteBorrow(bbd);
                    for (Book book : Books) {
                        if (book.getId() == bbd.getBookId()) {
                            updateCopyCount(book);
                        }
                    }

                    flowPane.getChildren().clear();
                    for (BorrowBookDetails updateBbd : UserBooksList) {
                        RefreshListviewBorrowData(updateBbd);
                    }

                });
                btn.setStyle("-fx-background-color : green;"
                        + "-fx-text-fill :white;");
            } else if (bbd.getStatus().equalsIgnoreCase("Pending")) {
                btn.setText("Pending");
                btn.setDisable(true);
                btn.setStyle("-fx-background-color : #ab97ae;"
                        + "-fx-text-fill :white;");

            }

            VBox inVb = new VBox(10, titleLabel, deleverDateLabel, statusLabel, btn);
            VBox vb = new VBox(10, imageView, inVb);
            vb.setMinWidth(250);
            vb.setMaxHeight(250);
            inVb.setAlignment(Pos.CENTER);
            vb.setAlignment(Pos.CENTER);
            vb.getStyleClass().add("main_vb");
            vb.setPadding(new Insets(10));
            flowPane.getChildren().add(vb);

        }

    }

    public void setSelectedSidebarLabel(Label selectedLabel, AnchorPane selectedAP) {
        homeLable.getStyleClass().remove("selected");
        sidebarBorrowBook.getStyleClass().remove("selected");
        sidebarBorrowedBook.getStyleClass().remove("selected");
        selectedLabel.getStyleClass().add("selected");
//        -------------------------
        APHome.setVisible(false);
        APBorrowBook.setVisible(false);
        APBorrowedBook.setVisible(false);
        selectedAP.setVisible(true);
    }

    @FXML
    private void Logout(MouseEvent event) {
        UserStage.hide();
        LoginStage.show();
    }

    @FXML
    private void categoryFilter(ActionEvent event) {

        ObservableList<String> bookName = FXCollections.observableArrayList();
        String selectedCategory = categoryCombox.getValue();

        for (Book book : Books) {
            if (book.getCategory().equals(selectedCategory)) {
                bookName.add(book.getTitle());
            }

        }
        selectBook.setItems(bookName);
        if (categoryCombox.getValue() != null) {
            categoryCombox.getStyleClass().add("combox_chosen_Value");
        }
    }

    @FXML
    private void selectBookListener(ActionEvent event) {
        if (selectBook.getValue() != null) {
            selectBook.getStyleClass().add("combox_chosen_Value");
        }
    }
    private Book selectedBook;

    @FXML
    private void searchingBook(ActionEvent event) {
        TitleTF.setText("");
        autherTF.setText("");
        publishDataTF.setText("");
        copyCountTF.setText("");
        pageCountTF.setText("");
        publisherTF.setText("");
        categoryTF.setText("");
        isbnTF.setText("");
        bookImageView.setImage(new Image(UserDashboardController.class.getResourceAsStream("/Images/books.png")));
        borrowBtn.setText("Borrow");
        borrowBtn.getStyleClass().add("btn_Borrow_Borrow");
        borrowBtn.setDisable(false);
        if (selectBook.getValue() != null && categoryCombox.getValue() != null) {

            for (Book book : Books) {
                if (book.getTitle().equals(selectBook.getValue())) {
                    selectedBook = book;
                    TitleTF.setText(book.getTitle());
                    autherTF.setText(book.getAuther());
                    publishDataTF.setText(book.getPublishDate());
                    copyCountTF.setText(String.valueOf(book.getCopycount()));
                    pageCountTF.setText(String.valueOf(book.getPagecount()));
                    publisherTF.setText(book.getPublisher());
                    categoryTF.setText(book.getCategory());
                    isbnTF.setText(book.getIsbn());
                    bookImageView.setImage(new Image(UserDashboardController.class.getResourceAsStream(book.getProfileImagepath())));

                    BorrowBookDetails status = getBookStatus(loginUser.getId(), book.getId());
                    if (status.getStatus().equals(null)) {
                        borrowBtn.setText("Borrow");
                        borrowBtn.getStyleClass().add("btn_Borrow_Borrow");
                        borrowBtn.setDisable(false);
                    } else if (status.getStatus().equals("Pending")) {
                        borrowBtn.setText("Pending");
                        borrowBtn.getStyleClass().add("btn_Borrow_pending");
                        borrowBtn.setDisable(true);
                    } else if (status.getStatus().equals("Approved")) {
                        borrowBtn.setText("Approved");
                        borrowBtn.getStyleClass().add("btn_Borrow_Approved");
                        borrowBtn.setDisable(true);
                    }
                    status = null;

                }
            }
        } else if (categoryCombox.getValue() == null && selectBook.getValue() == null) {

            categoryCombox.getStyleClass().add("combox_unchosenValue");
            selectBook.getStyleClass().add("combox_unchosenValue");

        } else if (categoryCombox.getValue() == null || selectBook.getValue() == null) {

            if (categoryCombox.getValue() == null) {
                categoryCombox.getStyleClass().add("combox_unchosenValue");

            } else if (selectBook.getValue() == null) {
                selectBook.getStyleClass().add("combox_unchosenValue");
            }
        }

    }

    @FXML
    private void Borrowing(ActionEvent event) {
        if (selectBook.getValue() != null && categoryCombox.getValue() != null) {
            BorrowBookDetails newBorrow = new BorrowBookDetails(loginUser.getId(), loginUser.getFullname(), loginUser.getProfileImagepath(), selectedBook.getId(), selectedBook.getTitle(), selectedBook.getProfileImagepath(), "Pending");
            AddBorrow(newBorrow);
            BorrowedBooksDetails.setAll(getBorrowDetails());
            borrowBtn.setText("Pending");
            borrowBtn.setDisable(true);
            borrowBtn.getStyleClass().add("btn_Borrow_pending");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your requist is pending.....");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please make search ....");
            alert.showAndWait();
        }
    }

    @FXML
    private void clearing(ActionEvent event) {
        TitleTF.clear();
        autherTF.clear();
        publishDataTF.clear();
        copyCountTF.clear();
        pageCountTF.clear();
        publisherTF.clear();
        categoryTF.clear();
        isbnTF.clear();
        categoryCombox.setValue(null);
        selectBook.setValue(null);
        bookImageView.setImage(new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream("/Images/books.png")));
        categoryCombox.setStyle("");
        selectBook.setStyle("");

    }

    @FXML
    private void showsidebarLogout(MouseEvent event) {
        UserStage.hide();
        LoginStage.show();
    }

    public static Book BorrowdBook(int id) {
        Book b = new Book();
        for (Book book : Books) {
            if (book.getId() == id) {
                b = book;
            }
        }
        return b;
    }

    public void RefreshListviewBorrowData(BorrowBookDetails bbd) {

        UserBooksList.setAll(getUserBooks(loginUser));
        flowPane.getChildren().clear();

        Image im = new Image(UserDashboardController.class.getResourceAsStream(bbd.getBookImage()));
        ImageView imageView = new ImageView(im);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        Label titleLabel = new Label(bbd.getBookTitle());
        titleLabel.setStyle("-fx-font-weight:bolder;");
        Label deleverDateLabel = new Label("Delever Date : null ");
        deleverDateLabel.setDisable(true);
        Label statusLabel = new Label("Status : " + bbd.getStatus());
        Button btn = new Button();
        if (bbd.getStatus().equalsIgnoreCase("Approved")) {
            btn.setText("Return");
            btn.setOnAction(e -> {
                UserBooksList.remove(bbd);
                DeleteBorrow(bbd);
                flowPane.getChildren().clear();
                for (BorrowBookDetails updateBbd : UserBooksList) {
                    RefreshListviewBorrowData(updateBbd);
                }

            });
            btn.setStyle("-fx-background-color : green;"
                    + "-fx-text-fill :white;");
        } else if (bbd.getStatus().equalsIgnoreCase("Pending")) {
            btn.setText("Pending");
            btn.setDisable(true);
            btn.setStyle("-fx-background-color : #ab97ae;"
                    + "-fx-text-fill :white;");

        }

        VBox inVb = new VBox(10, titleLabel, deleverDateLabel, statusLabel, btn);
        VBox vb = new VBox(10, imageView, inVb);
        vb.setMinWidth(250);
        vb.setMaxHeight(250);
        inVb.setAlignment(Pos.CENTER);
        vb.setAlignment(Pos.CENTER);
        vb.getStyleClass().add("main_vb");
        vb.setPadding(new Insets(10));
        flowPane.getChildren().add(vb);
    }
}

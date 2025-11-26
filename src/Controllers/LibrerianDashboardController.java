/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static DataBase.BookDatabaseHandler.MinCopyCount;
import static DataBase.BookDatabaseHandler.getBooksData;
import static DataBase.BorrowDetailsDatabaseHandler.DeleteBorrow;
import static DataBase.BorrowDetailsDatabaseHandler.UpdateBorrowStatus;
import static DataBase.BorrowDetailsDatabaseHandler.getApprovedBooka;
import static DataBase.BorrowDetailsDatabaseHandler.getBorrowDetails;
import static DataBase.BorrowDetailsDatabaseHandler.getPendingBooks;
import Model.Book;
import Model.BorrowBookDetails;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.Books;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.BorrowBookStatisticeStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.BorrowedBooksDetails;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.SetStageData;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.UserProfileStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.UserStatisticeStage;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.Users;
import static scenebuilderlibrarymangmentssystem.SceneBuilderLibraryMangmentsSystem.loginUser;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LibrerianDashboardController implements Initializable {

    @FXML
    private Label homeLable;
    @FXML
    private Label sidebarUserMangments;
    @FXML
    private AnchorPane APHome;
    @FXML
    private ImageView libImageView;
    @FXML
    private ImageView logout;
    @FXML
    private Label libFullname;
    @FXML
    private ComboBox<String> StatusSelector;
    @FXML
    private TableView<BorrowBookDetails> BookManagmentsTable;
    @FXML
    private TableColumn<BorrowBookDetails, Integer> UserIDColumn;
    @FXML
    private TableColumn<BorrowBookDetails, String> UserNameColumn;
    @FXML
    private TableColumn<BorrowBookDetails, ImageView> UserImageColumn;
    @FXML
    private TableColumn<BorrowBookDetails, Integer> BookIdColumn;
    @FXML
    private TableColumn<BorrowBookDetails, String> BookTitleColumn;
    @FXML
    private TableColumn<BorrowBookDetails, ImageView> BoolImageColumn;
    @FXML
    private TableColumn<BorrowBookDetails, Label> BorrowStatusColumn;
    @FXML
    private TableColumn<BorrowBookDetails, HBox> actionColumn;
    @FXML
    private AnchorPane APBorrowBookManagements;
    @FXML
    private AnchorPane APStatistics;
    @FXML
    private Label sidebarStatistics;
    @FXML
    private Label sidebarLogout;
    @FXML
    private Label allBooksNum;
    @FXML
    private Label BoorowdBooksNum;
    @FXML
    private Label PendingNum;
    @FXML
    private Label ApprovedNum;

    public static ObservableList<BorrowBookDetails> BookStatusList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO\\
BorrowedBooksDetails.setAll(getBorrowDetails());
        //-----------filter status
        StatusSelector.getItems().addAll("All", "Approved", "Pending");
        homeLable.getStyleClass().add("sidebar-label");
        sidebarUserMangments.getStyleClass().add("sidebar-label");
        sidebarStatistics.getStyleClass().add("sidebar-label");
        setSelectedSidebarLabel(homeLable, APHome);

//        for (User user : Users) {
//            if (loginUser == user) {
//                System.out.print("userss here");
        Image im = new Image(SceneBuilderLibraryMangmentsSystem.class.getResourceAsStream(loginUser.getProfileImagepath()));
        libImageView.setImage(im);
        libFullname.setText(loginUser.getFullname());

//            }
//        }
        //----------- tabel columns--------
        BorrowStatusColumn.setCellValueFactory(cellData -> {
            String statusText = cellData.getValue().getStatus();
            Label borrowStatus = new Label(statusText);
            borrowStatus.getStyleClass().removeAll("Pending_BorrowStatus", "Approved_BorrowStatus");
            if (statusText.equals("Pending")) {
                borrowStatus.getStyleClass().add("Pending_BorrowStatus");
            }
            if (statusText.equals("Approved")) {
                borrowStatus.getStyleClass().add("Approved_BorrowStatus");
            }
            return new SimpleObjectProperty<>(borrowStatus);

        });

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

        actionColumn.setCellValueFactory(cellData -> {
            HBox hb = new HBox();
            String statusText = cellData.getValue().getStatus();

            if (statusText.equals("Pending")) {
                Button approveBtn = new Button("Approve");
                Button rejectBtn = new Button("Reject");
                BorrowBookDetails bd = cellData.getValue();
                //  ----------------------
                approveBtn.setOnAction(e -> {
                    int indexbd = BorrowedBooksDetails.indexOf(bd);
                    boolean enughCopy = ChangeBookCopyNumber(bd.getBookId());
                    if (!enughCopy) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "No enugh copy of Books");
                        alert.showAndWait();
                    } else {
                        bd.setStatus("Approved");
                        BorrowedBooksDetails.set(indexbd, bd);
                        UpdateBorrowStatus(bd);

                    }

                });
                rejectBtn.setOnAction(e -> {
                    BorrowedBooksDetails.remove(bd);
                    DeleteBorrow(bd);
                });
                hb.getChildren().addAll(approveBtn, rejectBtn);
            }
            if (statusText.equals("Approved")) {
                Button approvedBtn = new Button("Approved Book");
                approvedBtn.setDisable(true);
                hb.getChildren().add(approvedBtn);
            }
            return new SimpleObjectProperty<>(hb);

        });

        BookManagmentsTable.setItems(BorrowedBooksDetails);

         try {
            Parent BorrowStatisticStageroot = FXMLLoader.load(getClass().getResource("/View/BorrowBookStatistic.fxml"));
            Scene CategorySC = new Scene(BorrowStatisticStageroot);
            SetStageData(BorrowBookStatisticeStage, CategorySC, "ShopIcon.png", "Borrow Book Statistic Page", 200, 200);
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
        setSelectedSidebarLabel(sidebarUserMangments, APBorrowBookManagements);
    }

    @FXML
    private void showsidebarStatistics(MouseEvent event) {
        setSelectedSidebarLabel(sidebarStatistics, APStatistics);
        allBooksNum.setText(String.valueOf(getBooksData().size()));
        BoorowdBooksNum.setText(String.valueOf(getBorrowDetails().size()));
        ApprovedNum.setText(String.valueOf(getApprovedBooka().size()));
        PendingNum.setText(String.valueOf(getPendingBooks().size()));

    }

    public void setSelectedSidebarLabel(Label selectedLabel, AnchorPane selectedAP) {
        homeLable.getStyleClass().remove("selected");
        sidebarUserMangments.getStyleClass().remove("selected");
        sidebarStatistics.getStyleClass().remove("selected");
        selectedLabel.getStyleClass().add("selected");

//        -------------------------
        APHome.setVisible(false);
        APBorrowBookManagements.setVisible(false);
        APStatistics.setVisible(false);
        selectedAP.setVisible(true);

    }

    @FXML
    private void libInfoCheck(MouseEvent event) throws IOException {
         Parent userProfileStageroot = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene CategorySC = new Scene(userProfileStageroot);
        SetStageData(UserProfileStage, CategorySC, "ShopIcon.png", "User Profile Page", 200, 200);
        UserProfileStage.show();
    }

    @FXML
    private void Logout(MouseEvent event) {
        SceneBuilderLibraryMangmentsSystem.LibrerianStage.hide();
        SceneBuilderLibraryMangmentsSystem.LoginStage.show();
    }

    @FXML
    private void FilterStatus(ActionEvent event) {
        String status = StatusSelector.getValue();
        ObservableList<BorrowBookDetails> tempborrowDetails = FXCollections.observableArrayList();
        tempborrowDetails.clear();
        if (status.equals("All")) {
            BookManagmentsTable.setItems(BorrowedBooksDetails);

        } else {
            for (BorrowBookDetails bbd : BorrowedBooksDetails) {
                if (bbd.getStatus().equals(status)) {
                    tempborrowDetails.add(bbd);
                }
            }
            BookManagmentsTable.setItems(tempborrowDetails);
        }
    }

    public boolean ChangeBookCopyNumber(int bookId) {
        boolean enughCopy = false;
        for (Book b : Books) {
            if (b.getId() == bookId) {
                if (b.getCopycount() > 0) {
                    enughCopy = true;
                    b.setCopycount(b.getCopycount() - 1);
                    MinCopyCount(b);
                }
            }
        }
        return enughCopy;
    }

    @FXML
    private void showsidebarLogout(MouseEvent event) {
        SceneBuilderLibraryMangmentsSystem.LibrerianStage.hide();
        SceneBuilderLibraryMangmentsSystem.LoginStage.show();
    }

    @FXML
    private void ShowAllBooks(MouseEvent event) {
//        BookStatusList.clear();
//        BookStatusList.addAll(getBorrowDetails());
    }

    @FXML
    private void ShowAllBorrowdBooks(MouseEvent event) {
        BookStatusList.clear();
        BookStatusList.addAll(getBorrowDetails());
       BorrowBookStatisticeStage.show();
    }

    @FXML
    private void SowAllPending(MouseEvent event) {
        BookStatusList.clear();
        BookStatusList.addAll(getPendingBooks());
        BorrowBookStatisticeStage.show();
    }

    @FXML
    private void ShowAllApproved(MouseEvent event) {
        BookStatusList.clear();
        BookStatusList.addAll(getApprovedBooka());
        BorrowBookStatisticeStage.show();
    }

}

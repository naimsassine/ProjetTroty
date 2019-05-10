package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MechanicMenuPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button IDScoots;
    @FXML
    private Button ManageScoots;
    @FXML
    private Button Queries;
    @FXML
    private Button ManageUsers;
    @FXML
    private Button ManageComplaints;
    @FXML
    private Button BackButton;

    @FXML
    public void IDScootsButtonPressed(ActionEvent event) throws IOException {
        Parent menuIDScoots = FXMLLoader.load(getClass().getResource("../View/IDScootPage.fxml"));
        Scene menuIDScootsscene = new Scene(menuIDScoots);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuIDScootsscene);
        window.show();
    }
    @FXML
    public void BackButtonPressed(ActionEvent event) throws IOException{
        Parent LoginMecanic = FXMLLoader.load(getClass().getResource("../View/MechanicLoginPage.fxml"));
        Scene LoginMecanicScene = new Scene(LoginMecanic);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(LoginMecanicScene);
        window.show();
    }
    @FXML
    public void ManageScootsButtonPressed(ActionEvent event) throws IOException{
        Parent ManageScoot = FXMLLoader.load(getClass().getResource("../View/ManageScootsPage.fxml"));
        Scene ManageScootScene = new Scene(ManageScoot);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ManageScootScene);
        window.show();
    }

    @FXML
    public void ManageUsersButtonPressed(ActionEvent event) throws IOException{
        Parent ManageUserScoot = FXMLLoader.load(getClass().getResource("../View/TechManagesUsersPage.fxml"));
        Scene ManageUserScootScene = new Scene(ManageUserScoot);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ManageUserScootScene);
        window.show();
    }


    @FXML
    public void QueriesButtonPressed(ActionEvent event) throws IOException{
        Parent ManageUserScoot = FXMLLoader.load(getClass().getResource("../View/QueriesPage.fxml"));
        Scene ManageUserScootScene = new Scene(ManageUserScoot);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ManageUserScootScene);
        window.show();
    }



}

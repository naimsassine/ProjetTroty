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
    public void IDScootsButtonPressed(ActionEvent event) throws IOException {
        Parent menuIDScoots = FXMLLoader.load(getClass().getResource("../View/IDScootPage.fxml"));
        Scene menuIDScootsscene = new Scene(menuIDScoots);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuIDScootsscene);
        window.show();
    }
}

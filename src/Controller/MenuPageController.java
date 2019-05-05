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

public class MenuPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AcceuilPageController.pageoption = 1;

    }

    @FXML
    private Button MapsButton;
    @FXML
    private Button TrottButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button PlainteButton;



    @FXML
    public void TrottButtonPressed(ActionEvent event) throws IOException {
        Parent trott = FXMLLoader.load(getClass().getResource("../View/TrotinettesPage.fxml"));
        Scene trottscene = new Scene(trott);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(trottscene);
        window.show();
    }


    @FXML
    public void MapsButtonPressed(){ }

    @FXML
    public void BackButtonPressed(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("../View/Acceuilpage.fxml"));
        Scene acceuilscene = new Scene(login);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(acceuilscene);
        window.show();
    }

    @FXML
    public void pageCharging(ActionEvent event) throws IOException{
        Parent SignupCharger = FXMLLoader.load(getClass().getResource("../View/ChargerSignup.fxml"));
        Scene chargerscene = new Scene(SignupCharger);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(chargerscene);
        window.show();
    }

    @FXML
    public void loadPagePlainte(ActionEvent event) throws IOException{
        Parent plainte = FXMLLoader.load(getClass().getResource("../View/PlaintePage.fxml"));
        Scene plaintescene = new Scene(plainte);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(plaintescene);
        window.show();
    }
}

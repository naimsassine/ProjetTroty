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

public class PlaintePageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button BackButton;
    @FXML
    private Button DoneButton;


    @FXML
    public void BackButtonPressed(ActionEvent event) throws IOException {
        if (AcceuilPageController.pageoption == 1){
            Parent menu = FXMLLoader.load(getClass().getResource("../View/MenuPage.fxml"));
            Scene menuscene = new Scene(menu);

            // Lets get the stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(menuscene);
            window.show();
        }
        else {
            Parent menu = FXMLLoader.load(getClass().getResource("../View/ChargerMenuPage.fxml"));
            Scene menuscene = new Scene(menu);

            // Lets get the stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(menuscene);
            window.show();
        }

    }


}

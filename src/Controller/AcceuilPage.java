package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class AcceuilPage implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button LoginButton;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button SignupButton;




    @FXML
    public void loadsignin(ActionEvent event) throws IOException{
        Parent signin = FXMLLoader.load(getClass().getResource("../Controller/LoginPage.fxml"));
        Scene signinscene = new Scene(signin);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(signinscene);
        window.show();
    }

    @FXML
    public void loadsignup(ActionEvent event) throws IOException{
        Parent signup = FXMLLoader.load(getClass().getResource("../Controller/signuppage.fxml"));
        Scene signupscene = new Scene(signup);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(signupscene);
        window.show();
    }
}

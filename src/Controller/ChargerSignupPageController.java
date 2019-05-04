package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChargerSignupPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button BackButton;
    @FXML
    private TextField UserIdTextfield;
    @FXML
    private TextField FNTextfield;
    @FXML
    private TextField LNTextfield;
    @FXML
    private TextField TNTextfield;
    @FXML
    private TextField CityTextfield;
    @FXML
    private TextField PCTextfield;
    @FXML
    private TextField StreetTextfield;
    @FXML
    private TextField NumberTextfield;



    @FXML
    public void Goback(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("../View/MenuPage.fxml"));
        Scene menuscene = new Scene(menu);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuscene);
        window.show();
    }



    @FXML
    public void finish(ActionEvent event) throws IOException {
        if(!UserIdTextfield.getText().isEmpty() && !FNTextfield.getText().isEmpty() && !LNTextfield.getText().isEmpty()
                && !TNTextfield.getText().isEmpty() && !CityTextfield.getText().isEmpty()
                && !PCTextfield.getText().isEmpty() && !StreetTextfield.getText().isEmpty()
                && !NumberTextfield.getText().isEmpty()){
            Parent menu = FXMLLoader.load(getClass().getResource("../View/MenuPage.fxml"));
            Scene menuscene = new Scene(menu);

            // Lets get the stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(menuscene);
            window.show();


        }
        else{
            System.out.print("plz fill in all the blanks");
        }
    }
}

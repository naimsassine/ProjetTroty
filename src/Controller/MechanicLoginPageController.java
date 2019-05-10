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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ActualUser;
import sample.DBConnect;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MechanicLoginPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button BackButton;
    @FXML
    private Button LoginButton;
    @FXML
    private TextField IDTextField;
    @FXML
    private Text ErrorText;



    @FXML
    public void Goback(ActionEvent event) throws IOException {
        Parent acceuil = FXMLLoader.load(getClass().getResource("../View/Acceuilpage.fxml"));
        Scene acceuilscene = new Scene(acceuil);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(acceuilscene);
        window.show();
    }

    @FXML
    public void LoginButtonPressed(ActionEvent event) throws SQLException, IOException {
        DBConnect connect = new DBConnect();
        String MID = IDTextField.getText();
        Boolean answer = connect.checkTechnicien(MID);
        if(answer){
            Parent menuMecanic = FXMLLoader.load(getClass().getResource("../View/MechanicMenuPage.fxml"));
            Scene menuMecanicscene = new Scene(menuMecanic);

            // Lets get the stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(menuMecanicscene);
            window.show();

            ActualUser user = new ActualUser();
            user.SaveTech(MID);
        }
        else{
            ErrorText.setText("No Mechanic found");
        }
    }



}

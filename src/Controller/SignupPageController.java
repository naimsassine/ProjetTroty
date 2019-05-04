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
import sample.DBConnect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button backbutton;
    @FXML
    private TextField UserIdTextfield;
    @FXML
    private TextField PasswordTextfield;
    @FXML
    private TextField CCTextfield;

    @FXML
    public void Goback(ActionEvent event) throws IOException{
        Parent acceuil = FXMLLoader.load(getClass().getResource("../View/Acceuilpage.fxml"));
        Scene acceuilscene = new Scene(acceuil);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(acceuilscene);
        window.show();
    }
    @FXML
    public void signup(){
        if(!UserIdTextfield.getText().isEmpty() && !PasswordTextfield.getText().isEmpty() &&
                !CCTextfield.getText().isEmpty()){
            DBConnect connect = new DBConnect();
            connect.signupAnonymeUser(UserIdTextfield.getText(),PasswordTextfield.getText(),CCTextfield.getText());
        }
        else{
            System.out.print("Plz fill in all the blanks");
        }

    }

}

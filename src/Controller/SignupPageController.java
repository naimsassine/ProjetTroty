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
    private Text UIDErrorText;
    @FXML
    private Text PasswordErrorText;
    @FXML
    private Text CCErrorText;
    @FXML
    private Text GeneralErrorText;




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
    public void signup(ActionEvent event) throws IOException {
        if(!UserIdTextfield.getText().isEmpty() && !PasswordTextfield.getText().isEmpty() &&
                !CCTextfield.getText().isEmpty()){
            Boolean numeric = true;

            ActualUser user = new ActualUser();
            DBConnect connect = new DBConnect();
            String ID = UserIdTextfield.getText();
            numeric = ID.matches("-?\\d+(\\.\\d+)?");
            if (numeric){
                String Password = PasswordTextfield.getText();
                if (Password.length()<15){
                    String CC = CCTextfield.getText();
                    if (CC.length()<20){
                        Boolean answer = connect.signupAnonymeUser(ID,Password,CC);
                        if(answer){

                            user.saveUser(ID);
                            Parent menu = FXMLLoader.load(getClass().getResource("../View/MenuPage.fxml"));
                            Scene menuscene = new Scene(menu);

                            // Lets get the stage
                            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                            window.setScene(menuscene);
                            window.show();
                        }
                        else {
                            GeneralErrorText.setText("Error with signup");
                        }
                    }
                    else{
                        CCErrorText.setText("length must be < 20");
                    }
                }
                else{
                    PasswordErrorText.setText("length pass must be < 15");
                }

            }
            else {
                UIDErrorText.setText("Must be an int");
            }

        }
        else{
            GeneralErrorText.setText("Please fill in all the blanks");
        }


    }

}

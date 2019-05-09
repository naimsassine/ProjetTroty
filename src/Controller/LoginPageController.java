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

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.ResourceBundle;

public class LoginPageController  implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button BackButton;
    @FXML
    private Button RideButton;
    @FXML
    private TextField IdTextfield;
    @FXML
    private TextField PasswordTextfield;



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
    public void Ride(ActionEvent event) throws IOException, SQLException {
        String PasswordReturned = "";
        String UserId = "";
        UserId = IdTextfield.getText();

        //saving the actual user in a txt file (each time the code is run, the file is overwrite)
        PrintWriter bw = new PrintWriter("src/ActualUser.txt");
        bw.write(UserId);
        bw.close();


        DBConnect connect = new DBConnect();
        PasswordReturned = connect.Login(UserId);
        Boolean answer = connect.checkRecharger(UserId);

        if (PasswordReturned.equals(PasswordTextfield.getText()) && answer){

            Parent menu = FXMLLoader.load(getClass().getResource("../View/ChargerMenuPage.fxml"));
            Scene menuscene = new Scene(menu);

            // Lets get the stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(menuscene);
            window.show();

        }
        else if (PasswordReturned.equals(PasswordTextfield.getText()) && !answer){

           Parent menu1 = FXMLLoader.load(getClass().getResource("../View/MenuPage.fxml"));
            Scene menuscene1 = new Scene(menu1);

            // Lets get the stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(menuscene1);
            window.show();
        }
        else{

            System.out.print("Wrong Password");

        }

    }
}

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

public class IDScootPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private TextField T_IDTextfield;
    @FXML
    private TextField T_ID2Textfield;
    @FXML
    private TextField DateTextfield;
    @FXML
    private TextField ModelTextfield;
    @FXML
    private TextField BatteryTextfield;
    @FXML
    private TextField ComplaintTextfield;
    @FXML
    private TextField DispoTextfield;
    @FXML
    private TextField XPosTextfield;
    @FXML
    private TextField YPosTextfield;
    @FXML
    private Button AddButton;
    @FXML
    private Button RemoveButton;
    @FXML
    private Button BackButton;

    @FXML
    public void RemoveButtonPressed(){
        DBConnect connect = new DBConnect();
        String TID = T_IDTextfield.getText();
        Boolean answer = connect.removeTrotinette(TID);
        if (answer){
            T_IDTextfield.clear();
        }
    }

    @FXML
    public void BackButtonPressed(ActionEvent event) throws IOException {
        Parent menuIDScoots = FXMLLoader.load(getClass().getResource("../View/MechanicMenuPageController.fxml"));
        Scene menuIDScootsscene = new Scene(menuIDScoots);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuIDScootsscene);
        window.show();
    }

    @FXML
    public  void AddButtonPressed(){

    }


}

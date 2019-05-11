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
import sample.DBConnect;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Text ErrorTextRemove;
    @FXML
    private Text ErrorTextAdd;

    @FXML
    public void RemoveButtonPressed() throws SQLException {
        if(!T_IDTextfield.getText().isEmpty()){
            DBConnect connect = new DBConnect();
            String TID = T_IDTextfield.getText();
            Boolean answer1 = connect.checkTrotinette(TID);
            Boolean answer = connect.removeTrotinette(TID);
            if (answer && answer1){
                T_IDTextfield.clear();
                ErrorTextRemove.setText("");
            }
            else if (answer && !answer1){
                ErrorTextRemove.setText("Trott not found");
            }
            else {
                ErrorTextRemove.setText("Error from database");
            }
        }
        else {
            ErrorTextRemove.setText("Please fill in the blanks");
        }
    }

    @FXML
    public void BackButtonPressed(ActionEvent event) throws IOException {
        Parent menuIDScoots = FXMLLoader.load(getClass().getResource("../View/MechanicMenuPage.fxml"));
        Scene menuIDScootsscene = new Scene(menuIDScoots);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuIDScootsscene);
        window.show();
    }

    @FXML
    public  void AddButtonPressed(){

        if(!T_ID2Textfield.getText().isEmpty() && !DateTextfield.getText().isEmpty() && !ModelTextfield.getText().isEmpty()
        && !BatteryTextfield.getText().isEmpty() && !ComplaintTextfield.getText().isEmpty() && !DispoTextfield.getText().isEmpty()
        && !XPosTextfield.getText().isEmpty() && !YPosTextfield.getText().isEmpty()){
            DBConnect connect = new DBConnect();
            String TID = T_ID2Textfield.getText();
            String Date = DateTextfield.getText();
            String Model = ModelTextfield.getText();
            String Battery = BatteryTextfield.getText();
            String Complaint = ComplaintTextfield.getText();
            String Dispo = DispoTextfield.getText();
            String Xpos = XPosTextfield.getText();
            String Ypos = YPosTextfield.getText();
            Boolean answer = connect.addTrotinette(TID,Model,Date,Battery,Complaint,Dispo,Xpos,Ypos);
            if (answer){
                T_ID2Textfield.clear();
                DateTextfield.clear();
                ModelTextfield.clear();
                BatteryTextfield.clear();
                ComplaintTextfield.clear();
                DispoTextfield.clear();
                XPosTextfield.clear();
                YPosTextfield.clear();
                ErrorTextAdd.setText("");
            }
            else {
                ErrorTextAdd.setText("Error from Database");

            }
        }
        else {
            ErrorTextAdd.setText("Please fill in the blanks");
        }

    }


}

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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RachargeScootController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button BackButton;
    @FXML
    private Button DoneButton;
    @FXML
    private Button DoneButtonFinished;
    @FXML
    private TextField IDTextfieldTocharge;
    @FXML
    private TextField BatteryTextfieldTocharge;
    @FXML
    private TextField PosXTocharge;
    @FXML
    private TextField PosYTocharge;
    @FXML
    private TextField IDTextfieldFinished;
    @FXML
    private TextField BatteryTextfieldFinished;
    @FXML
    private TextField PosXTextfieldFinished;
    @FXML
    private TextField PosYTextfieldFinished;
    @FXML
    private Text ErrorCharging;
    @FXML
    private Text ErrorFinished;




    @FXML
    public void GoBack(ActionEvent event) throws IOException {
        Parent acceuil = FXMLLoader.load(getClass().getResource("../View/ChargerMenuPage.fxml"));
        Scene acceuilscene = new Scene(acceuil);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(acceuilscene);
        window.show();
    }

    @FXML
    public void DoneCharger(){
        if(!IDTextfieldTocharge.getText().isEmpty() && !BatteryTextfieldTocharge.getText().isEmpty() &&
                !PosXTocharge.getText().isEmpty() && !PosYTocharge.getText().isEmpty()){

            DBConnect connect = new DBConnect();

            if (IDTextfieldTocharge.getText().matches("-?\\d+(\\.\\d+)?") && BatteryTextfieldTocharge.getText().matches("-?\\d+(\\.\\d+)?")
             && PosXTocharge.getText().matches("-?\\d+(\\.\\d+)?") && PosYTocharge.getText().matches("-?\\d+(\\.\\d+)?")){

                String ID = IDTextfieldTocharge.getText();
                String Battery = BatteryTextfieldTocharge.getText();
                String PosX = PosXTocharge.getText();
                String PosY = PosYTocharge.getText();

                List<String> list = Arrays.asList(new String[]{"0", "1", "2", "3"});
                if(list.contains(Battery)){
                    Boolean answer = connect.chargeScoot(ID,Battery,PosX,PosY);

                    if(answer){
                        IDTextfieldTocharge.clear();
                        BatteryTextfieldTocharge.clear();
                        PosXTocharge.clear();
                        PosYTocharge.clear();
                        ErrorCharging.setText(null);
                    }
                    else {
                        ErrorCharging.setText("Error from Database");
                    }
                }
                else{
                    ErrorCharging.setText("Battery must be from 0 to 3");
                }

            }
            else {
                ErrorCharging.setText("Invalid values types");
            }
        }
        else {
            ErrorCharging.setText("Please fill in all of the blanks");
        }
    }

    @FXML
    public void DoneButttonFinishedPressed(){
            if(!IDTextfieldFinished.getText().isEmpty() && !BatteryTextfieldFinished.getText().isEmpty() &&
                    !PosXTextfieldFinished.getText().isEmpty() && !PosYTextfieldFinished.getText().isEmpty()){

                DBConnect connect = new DBConnect();

                if (IDTextfieldFinished.getText().matches("-?\\d+(\\.\\d+)?") && BatteryTextfieldFinished.getText().matches("-?\\d+(\\.\\d+)?")
                        && PosXTextfieldFinished.getText().matches("-?\\d+(\\.\\d+)?") && PosYTextfieldFinished.getText().matches("-?\\d+(\\.\\d+)?")){

                    String ID = IDTextfieldFinished.getText();
                    String Battery = BatteryTextfieldFinished.getText();
                    String PosX = PosXTextfieldFinished.getText();
                    String PosY = PosYTextfieldFinished.getText();

                    List<String> list = Arrays.asList(new String[]{"0", "1", "2", "3"});
                    if(list.contains(Battery)){
                        Boolean answer = connect.doneChargingScoot(ID,Battery,PosX,PosY);

                        if(answer){
                            IDTextfieldTocharge.clear();
                            BatteryTextfieldTocharge.clear();
                            PosXTocharge.clear();
                            PosYTocharge.clear();
                            ErrorCharging.setText(null);
                        }
                        else {
                            ErrorCharging.setText("Error from Database");
                        }
                    }
                    else{
                        ErrorCharging.setText("Battery must be from 0 to 3");
                    }

                }
                else {
                    ErrorCharging.setText("Invalid values types");
                }
            }
            else {
                ErrorCharging.setText("Please fill in all of the blanks");
            }
    }





}

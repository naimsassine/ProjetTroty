package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DBConnect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageScootsController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AttributeBox.setItems(ChoiceBoxList);
    }


    @FXML
    private Button BackButton;
    @FXML
    private Button DoneButton;
    @FXML
    private TextField TIDTextfield;
    @FXML
    private TextField NewValueTextfield;
    @FXML
    private ChoiceBox AttributeBox;

    ObservableList<String>  ChoiceBoxList = FXCollections.observableArrayList("Mise_en_service", "Model", "Batterie",
            "État");




    @FXML
    public void BackButtonPressed(ActionEvent event) throws IOException {
        Parent MenuMecanic = FXMLLoader.load(getClass().getResource("../View/MechanicMenuPage.fxml"));
        Scene MenuMecanicScene = new Scene(MenuMecanic);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MenuMecanicScene);
        window.show();
    }

    @FXML
    public void DoneButtonPressed(){
        DBConnect connect = new DBConnect();
        String TID = TIDTextfield.getText();
        String Attribute = (String) AttributeBox.getSelectionModel().getSelectedItem();
        String NewValue = NewValueTextfield.getText();
        Boolean answer = connect.modifyTrotinette(TID,Attribute,NewValue);
        if(answer){
            TIDTextfield.clear();
            AttributeBox.setSelectionModel(null);
            NewValueTextfield.clear();
        }
    }
}

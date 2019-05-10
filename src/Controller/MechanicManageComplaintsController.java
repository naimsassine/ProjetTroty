package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.ActualUser;
import sample.DBConnect;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class MechanicManageComplaintsController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RefreshButtonPressed();

    }

    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private TableView ComplaintTable;
    @FXML
    private Button DoneButton;
    @FXML
    private Button RefreshButton;
    @FXML
    private Button BackButton;
    @FXML
    private TextField ScootTextfield;
    @FXML
    private TextField UserTextfield;
    @FXML
    private TextField Datei;
    @FXML
    private TextField Note;
    @FXML
    private Text ErrorText;



    private void setTable(ResultSet rs) throws SQLException {
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            ComplaintTable.getColumns().addAll(col);
        }
    }


    @FXML
    public void RefreshButtonPressed(){

        ComplaintTable.getItems().clear();
        ComplaintTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "(select * from TrottEnAttente)" ;

            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            setTable(rs);


            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            ComplaintTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }



    @FXML
    public void DoneButtonPressed(ActionEvent event) throws IOException {
        if(!ScootTextfield.getText().isEmpty() && !UserTextfield.getText().isEmpty() && !Datei.getText().isEmpty()){
            String note = "null";
            if (Note.getText().isEmpty()){
                 note = "null";
            }
            else {
                 note = Note.getText();
            }
            DBConnect connect = new DBConnect();
            String TID = ScootTextfield.getText();
            String UID = UserTextfield.getText();
            String T_I = Datei.getText();


            if (TID.matches("-?\\d+(\\.\\d+)?") && UID.matches("-?\\d+(\\.\\d+)?")){
                // Il reste a comparer les deux dates ici


                Boolean answer = connect.resolveComplaint(TID,UID,T_I,note);
                if(answer){
                    ScootTextfield.clear();
                    UserTextfield.clear();
                    Datei.clear();
                    Note.clear();
                    ErrorText.setText("");
                }
                else{
                    ErrorText.setText("Error in databse");
                }
            }
            else {
                ErrorText.setText("Error in the type of data");
            }



        }
        else{
            ErrorText.setText("Please fill in all the blanks");
        }
    }

    @FXML
    public void BackButtonPressed(ActionEvent event) throws IOException{
        Parent LoginMecanic = FXMLLoader.load(getClass().getResource("../View/MechanicMenuPage.fxml"));
        Scene LoginMecanicScene = new Scene(LoginMecanic);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(LoginMecanicScene);
        window.show();
    }





}

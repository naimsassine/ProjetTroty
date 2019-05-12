package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.ActualUser;
import sample.DBConnect;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrottPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private TableView TrottTable;
    @FXML
    private Button BackButton;


    @FXML
    public void LocationButtonPressed() {
        TrottTable.getItems().clear();
        TrottTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            //ResultSet
            String SQL= "select case\n" +
                    "         when r.T_f>t2.T_F\n" +
                    "           then r.T_ID\n" +
                    "         else t2.T_ID\n" +
                    "         end as TID,\n" +
                    "       case\n" +
                    "         when r.T_f>t2.T_F\n" +
                    "           then r.Destination_x\n" +
                    "         else   t2.DestinationX\n" +
                    "         end as PositionX,\n" +
                    "       case\n" +
                    "         when r.T_f>t2.T_F\n" +
                    "          then r.Destination_y\n" +
                    "         else   t2.DestinationY\n" +
                    "         end as PositionY\n" +
                    "from Voyage t2 , Recharge r\n" +
                    "where t2.T_ID=r.T_ID and (t2.T_f>=all (select t1.T_f\n" +
                    "  from Voyage t1\n" +
                    "  where t1.T_ID=t2.T_ID) ) and (r.T_f >= all( select r1.T_f\n" +
                    "                                             from Recharge r1\n" +
                    "                                             where r1.T_ID=r.T_ID ))" ;
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
            TrottTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    public void DetailsButtonPressed() {
        TrottTable.getItems().clear();
        TrottTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            //ResultSet
            String SQL = "(select * from Trotinette)";
            /*String SQL= "(select Trotinette.T_ID, t2.DestinationX as PosY, t2.DestinationY as PosX, Trotinette.État, Trotinette.Batterie, Trotinette.Mise_en_service\n" +
                    "from Voyage t2, Trotinette\n" +
                    "where Trotinette.T_ID = t2.T_ID and t2.T_f>=all( select t1.T_f from Voyage t1 where t1.T_ID=t2.T_ID))";*/
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
            TrottTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

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

            TrottTable.getColumns().addAll(col);
        }
    }

    @FXML
    public void GoBack(ActionEvent event) throws IOException, SQLException {
        ActualUser user = new ActualUser();
        DBConnect connect = new DBConnect();
        String UID = user.getUser();
        Boolean answer = connect.checkRecharger(UID);
        if(answer){
            Parent menu = FXMLLoader.load(getClass().getResource("../View/ChargerMenuPage.fxml"));
            Scene menuscene = new Scene(menu);

            // Lets get the stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(menuscene);
            window.show();
        }

        else {
            Parent menu = FXMLLoader.load(getClass().getResource("../View/MenuPage.fxml"));
            Scene menuscene = new Scene(menu);

            // Lets get the stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(menuscene);
            window.show();
        }}


}

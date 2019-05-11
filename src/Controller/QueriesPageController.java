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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.DBConnect;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QueriesPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private TableView QueriesTable;


    @FXML
    public void Goback(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("../View/MechanicMenuPage.fxml"));
        Scene menuscene = new Scene(menu);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuscene);
        window.show();
    }


    @FXML
    public void Querie1ButtonPressed(ActionEvent event) throws IOException {

        QueriesTable.getItems().clear();
        QueriesTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "(select t2.DestinationY ,t2.DestinationX,t2.T_ID,t2.T_f\n" +
                    "                        from Voyage t2\n" +
                    "                        where  t2.T_f>=all\n" +
                    "                        ( select t1.T_f\n" +
                    "                        from Voyage t1\n" +
                    "                        where t1.T_ID=t2.T_ID )) " ;

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
            QueriesTable.setItems(data);
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

            QueriesTable.getColumns().addAll(col);
        }
    }





    @FXML
    public void Querie2ButtonPressed(ActionEvent event) throws IOException {


        QueriesTable.getItems().clear();
        QueriesTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "(select u.Nom, u.Prenom\n" +
                    "from Utilisateur_Recharge u\n" +
                    "where (u.U_ID not in(select  t.U_ID\n" +
                    "                     from Voyage t , Recharge r\n" +
                    "                     where  r.T_ID != t.T_ID and t.U_ID=r.U_ID\n" +
                    "                       group by t.U_ID )))" ;

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
            QueriesTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }



    @FXML
    public void Querie3ButtonPressed(ActionEvent event) throws IOException {


        QueriesTable.getItems().clear();
        QueriesTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "(select z.tr,z.ui\n" +
                    "from (select d.h as tr,SUM(d.g) as ui\n" +
                    "from(select t.T_ID as h,ST_Distance_Sphere(point(t.Source_x,t.Source_y),point(t.DestinationX,t.DestinationY)) as g\n" +
                    "from Voyage t) as d\n" +
                    "group by d.h) as z\n" +
                    "order by z.ui desc\n" +
                    "limit 1)" ;

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
            QueriesTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }



    @FXML
    public void Querie4ButtonPressed(ActionEvent event) throws IOException {


        QueriesTable.getItems().clear();
        QueriesTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "(select r3.T_ID,count(*) AS cnt\n" +
                    "from Reparation r3\n" +
                    "group by r3.T_ID\n" +
                    "HAVING cnt>=10)" ;

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
            QueriesTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }


    @FXML
    public void Querie5ButtonPressed(ActionEvent event) throws IOException {


        QueriesTable.getItems().clear();
        QueriesTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "(select r3.U_ID,count(*) AS cnt,SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(r3.T_f,r3.T_i)))), SUM(case\n" +
                    "                                                                                             when TIME_TO_SEC(TIMEDIFF(r3.T_f,r3.T_i)) > 86400\n" +
                    "                                                                                               then 1+(6.5*(CEILING(TIME_TO_SEC(TIMEDIFF(r3.T_f,r3.T_i)))/3600))\n" +
                    "                                                                                             else 1+(36*(CEILING(TIME_TO_SEC(TIMEDIFF(r3.T_f,r3.T_i)))/86400))\n" +
                    "  end\n" +
                    "  ) As TotalAmount\n" +
                    "from Voyage r3\n" +
                    "group by r3.U_ID\n" +
                    "HAVING cnt>=10)" ;

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
            QueriesTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }



}

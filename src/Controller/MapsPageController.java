package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.DBConnect;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MapsPageController implements Initializable {
    MyBrowser myBrowser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myBrowser= new MyBrowser();

        //this.buildData();

    }
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private TableView MapTable;
    @FXML
    private Button BackButton;
    @FXML
    private AnchorPane pane;


    public void buildData() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from Trotinette";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            //adding id
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param){return new SimpleStringProperty(param.getValue().get(0).toString());}});

            MapTable.getColumns().addAll(col);
            for (int i = 5; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                MapTable.getColumns().addAll(col);
            }

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Colum
                    row.add(rs.getString(i));
                }
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            MapTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    public void GoBack(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("../View/MenuPage.fxml"));
        Scene menuscene = new Scene(menu);

        // Lets get the stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuscene);
        window.show();
    }
    class MyBrowser extends Pane {

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        public MyBrowser() {
            final URL urlGoogleMaps = getClass().getResource("map.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
                @Override
                public void handle(WebEvent<String> e) {
                    System.out.println(e.toString());
                }
            });

            //getChildren().add(webView);
            final TextField city = new TextField("" + "Guntur");
            //final TextField latitude = new TextField("" + 17.387140 * 1.00007);
            //final TextField longitude = new TextField("" + 78.491684 * 1.00007);
            Button update = new Button("Update");
            update.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    //  lat = Double.parseDouble(latitude.getText());
                    //  lon = Double.parseDouble(longitude.getText());
                    String cityname = city.getText();
                    /*System.out.printf("%.2f %.2f%n", lat, lon);*/
                    webEngine.executeScript("getCityname(' " + cityname + " ') ");
                }
            });

            SplitPane toolbar = new SplitPane();
            HBox hb = new HBox();

            hb.setPadding(new Insets(15, 12, 15, 12));
            hb.setSpacing(10);
            hb.getChildren().addAll(city, update);
            toolbar.setOrientation(Orientation.VERTICAL);
            toolbar.setDividerPositions(new double[] {.1});
            toolbar.getItems().addAll(hb,webView);
            hb.getChildren().addAll(toolbar);
            pane.getChildren().add(toolbar);


        }
    }

}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("../View/Acceuilpage.fxml"));
        window.setTitle("Troty");
        window.setScene(new Scene(root, 750, 500));
        window.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
        DBConnect connect = new DBConnect();
        /*connect.insertDataMecaniciens();
        connect.insertDataUtilisateur();
        connect.insertDataUtilisateurRecharge();
        connect.insertDataScooters();
        connect.insertDataReparation();
        connect.insertDataTrips();
        connect.insertDataReloads();*/


    }




}

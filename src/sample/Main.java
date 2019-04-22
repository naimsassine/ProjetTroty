package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../Controller/acceuilpage.fxml"));
        window.setTitle("Troty");
        window.setScene(new Scene(root, 750, 500));
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
        DBConnect connect = new DBConnect();
        connect.getData();

        /*Connection con;
        Statement st;
        ResultSet rs;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/giraffe","root","03331655");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from Employee");

            while(rs.next()){
                System.out.println("lol");
            }
        }
        catch (Exception e){
                System.out.print("Error " + e.getMessage());}*/

    }
}

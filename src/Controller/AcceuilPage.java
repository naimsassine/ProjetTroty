package Controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class AcceuilPage implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button LoginButton;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private StackPane ParentContainer;


    @FXML
    public void loadsignin(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../Controller/LoginPage.fxml"));
        Scene scene = LoginButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        ParentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            ParentContainer.getChildren().remove(AnchorPane);
        });
        timeline.play();
    }
}

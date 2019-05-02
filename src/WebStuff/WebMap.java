package WebStuff;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class WebMap extends Application {

    @Override public void start(Stage stage) {
        // create web engine and view
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        URL urlHello = getClass().getResource("mapfile.html");
        webEngine.load(urlHello.toExternalForm());


        // create scene
        stage.setTitle("Web Map");
        Scene scene = new Scene(webView,1000,700, Color.web("#666970"));
        stage.setScene(scene);
        // show stage
        stage.show();
    }

    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
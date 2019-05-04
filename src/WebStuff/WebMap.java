package WebStuff;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebMap extends Application {

    @Override public void start(Stage stage) {
        // create web engine and view
       /* WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        URL urlHello = getClass().getResource("googlemaps.html");
        webEngine.load(urlHello.toExternalForm());*/


        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();
        String url = "https://static.view.g.imapbuilder.net/?m=71755&s=8c98c92df30535b7b628be2a2f4dc93d";
        engine.load(url);


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
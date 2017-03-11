package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpViewer extends Application {
    
    public static final String DEFAULT_REFERENCE = "reference.html";

    private WebEngine webEngine;
    private WebView webView;
    
    public HelpViewer() {
        this(DEFAULT_REFERENCE);
    }

    public HelpViewer(String resourceFile) {
        webView = new WebView();
        webView.setVisible(true);
        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(getClass().getClassLoader().getResource(resourceFile).toExternalForm());
    }

    public void start(Stage myStage) throws Exception {
        Scene scene = new Scene(webView, 500, 300);
        myStage.setTitle("Commands Reference");
        myStage.setScene(scene);
        myStage.show();
    }
}



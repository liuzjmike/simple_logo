import controller.Workspace;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Workspace workspace = new Workspace(stage);
        workspace.show();
    }

}

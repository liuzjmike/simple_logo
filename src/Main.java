import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        Controller myControlHandler = new Controller();
        myControlHandler.start(arg0);
    }

}

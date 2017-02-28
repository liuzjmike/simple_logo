import controller.ControlHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        ControlHandler myControlHandler = new ControlHandler();
        myControlHandler.start(arg0);
    }

}

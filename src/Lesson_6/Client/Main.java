package Lesson_6.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {


    public static boolean isClosing() {
        return closing;
    }



    static boolean closing = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Lesson_6/Client/sample.fxml"));
        primaryStage.setTitle("chat 2k19");
        Scene scene = new Scene(root, 350, 370);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            if(root.isDisable())
            {
                event.consume();
            }
        });
        primaryStage.setOnCloseRequest(event -> {
            requestExit();
            event.consume();
        });


    }

    private void requestExit() {
        closing = true;
    }


    public static void main(String[] args) {
        launch(args);
    }
}


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setTitle("ParaVie");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();

        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

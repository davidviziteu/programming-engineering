package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/*
--module-path
"D:\Cloud Clients\OneDrive\facultate\sem4\java\javafx-sdk-11.0.2\lib"
--add-modules
javafx.controls,javafx.fxml
 */
public class MainClassUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        var resource = getClass().getResource("ui.fxml");
        if(resource == null){
            System.err.println("failed to load ui.fxml     clean the build and rebuild");
            System.exit(-1);
        }
        try{
            var root = new FXMLLoader(resource);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(root.load()));
            primaryStage.show();
        } catch (IOException e){
            System.err.println("loaded ui.fxml. cannot instatiate parent root.");
            System.err.println("problems in controller.");
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}
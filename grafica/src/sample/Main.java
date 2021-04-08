package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.text.Style;


public class Main extends Application {

    /* Matricea initiala data de echipa de la strazi
    public Integer[][] map={

            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {2, 2, 5, 2, 2, 5, 2, 2, 2, 2},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {2, 2, 5, 2, 2, 5, 2, 2, 2, 2},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0}
    };
    */
    /* A trebuit sa transpunem matricea pentru ca gridpane-ul inverseaza coloanele si linii */
    public Integer[][] map={

            {0, 2, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 2, 0, 0, 0, 0, 0},
            {4, 5, 4, 4, 5, 4, 4, 4, 4, 4},
            {0, 2, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 2, 0, 0, 0, 0, 0},
            {4, 5, 4, 4, 5, 4, 4, 4, 4, 4},
            {0, 2, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 2, 0, 0, 0, 0, 0}

    };
    @Override
    public void start(Stage primaryStage) {

        GridPane gridpane = new GridPane();
        gridpane.setGridLinesVisible(true);
        Image streetBlock = new Image("/resources/StreetBlock.jpg");
        Image streetBlockUp = new Image("/resources/StreetBlockUp.jpg");
        Image grass = new Image("/resources/grass.png");
        Image intersection = new Image("/resources/intersection.jpg");

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 2) {
                    ImageView streetSideways = new ImageView(streetBlock);
                    gridpane.add(streetSideways, i, j);
                } else if (map[i][j] == 4) {
                    ImageView streetUpDown = new ImageView(streetBlockUp);
                    gridpane.add(streetUpDown, i, j);
                } else if (map[i][j] == 5) {
                    ImageView intersectionView = new ImageView(intersection);
                    gridpane.add(intersectionView, i, j);
                }
                else {
                    ImageView grassView = new ImageView(grass);
                    gridpane.add(grassView, i, j);
                }
            }
        Scene scene = new Scene(gridpane, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


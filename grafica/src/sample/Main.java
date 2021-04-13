package sample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
    int x = 0;
    int y = 200;
    @Override
    public void start(Stage primaryStage) {

        GridPane gridpane = new GridPane();
        Image streetBlock = new Image("/resources/StreetBlock.jpg");
        Image streetBlockUp = new Image("/resources/StreetBlockUp.jpg");
        Image grass = new Image("/resources/grass.png");
        Image intersection = new Image("/resources/junction.png");

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

        HBox user = new HBox();
        Label trafficLabel = new Label("Select Traffic:");
        ObservableList<String> trafficTypes = FXCollections.observableArrayList("Low","Medium","High");
        Spinner<String> trafficSpinner = new Spinner<>();
        SpinnerValueFactory<String> values = new SpinnerValueFactory.ListSpinnerValueFactory<>(trafficTypes);
        trafficSpinner.setValueFactory(values);

        //Am creat un dreptunghi pentru a testa cum se misca ceva pe harta (care simuleaza o masina).
        Rectangle car = new Rectangle(40, 20);
        car.setTranslateY(185);
        Button move = new Button("Move");

        move.setOnAction(e->{
            x+=20;
            car.setTranslateX(x);

        });
        user.getChildren().addAll(trafficLabel, trafficSpinner, move);
        user.setAlignment(Pos.CENTER);

        BorderPane map = new BorderPane();
        map.setCenter(gridpane);
        map.setTop(user);
        map.getChildren().add(car);
        Scene scene = new Scene(map, 1000, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


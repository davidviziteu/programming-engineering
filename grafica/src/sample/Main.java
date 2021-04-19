package sample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    String input;
    HBox user = new HBox();
    Button submit = new Button("Submit");
    Label trafficLabel = new Label("Select Traffic:");
    ObservableList<String> trafficTypes = FXCollections.observableArrayList("Low","Medium","High");
    Spinner<String> trafficSpinner = new Spinner<>();

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
    int mouseXstart;
    int mouseYstart;
    int mouseXfinal;
    int mouseYfinal;
    Point2D point;
    Point2D start;
    Point2D finish;
    int countClick = 0;
    List<Point2D> special = new ArrayList<>();
    GridPane gridpane = new GridPane();
    @Override
    public void start(Stage primaryStage) throws InterruptedException {


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

        SpinnerValueFactory<String> values = new SpinnerValueFactory.ListSpinnerValueFactory<>(trafficTypes);
        trafficSpinner.setValueFactory(values);

        Image car = new Image("/resources/specialCarGoingRight.png");
        ImageView carView = new ImageView(car);

        carView.setTranslateY(125);
        Button move = new Button("Move");

        move.setOnAction(e->{
            x+=10;
            carView.setTranslateX(x);

        });


        user.getChildren().addAll(trafficLabel, trafficSpinner, submit, move);
        user.setAlignment(Pos.CENTER);

        BorderPane map = new BorderPane();
        map.setCenter(gridpane);
        map.setTop(user);
        map.getChildren().add(carView);
        Scene scene = new Scene(map, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        getInput();
        getCoordForSpecialCar();
    }

    public String getInput(){

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                input = trafficSpinner.getValue();
                System.out.println(input);
            }
        });
        return input;
    }

    public Point2D getCoordForSpecialCar(){
        gridpane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                point = new Point2D(mouseEvent.getX(), mouseEvent.getY());

            }
        });
        return point;
    }

    public static void main(String[] args) {
        launch(args);

    }
}


package GraphicsModule;

import CarGenerating.Car;
import CityGenerating.CityGenerator;
import CityGenerating.Intersection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Graphics {

    public BorderPane window = new BorderPane();

    //We add what we need for the user inputs
    HBox user;
    Button submit;
    Label trafficLabel;
    ObservableList<String> trafficTypes;
    Spinner<String> trafficSpinner = new Spinner<>();
    String trafficFrequencyInput;
    public static Integer[][] map = {

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
    //We use gridpane for a visual representation of the matrix
    GridPane gridpane;

    public void drawMap() {
        gridpane = new GridPane();
        Image streetBlock = new Image("/GraphicsModule/resources/StreetBlock.jpg");
        Image streetBlockUp = new Image("/GraphicsModule/resources/StreetBlockUp.jpg");
        Image grass = new Image("/GraphicsModule/resources/grass.png");
        Image intersection = new Image("/GraphicsModule/resources/junction.png");

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
                } else {
                    ImageView grassView = new ImageView(grass);
                    gridpane.add(grassView, i, j);
                }
            }
    }

    public void addUserPane() {

        drawMap();
        user = new HBox();
        submit = new Button("Submit");
        trafficLabel = new Label("Select Traffic:");
        trafficTypes = FXCollections.observableArrayList("Low", "Medium", "High");
        SpinnerValueFactory<String> values = new SpinnerValueFactory.ListSpinnerValueFactory<>(trafficTypes);
        trafficSpinner.setValueFactory(values);
        user.getChildren().addAll(trafficLabel, trafficSpinner, submit);
        user.setAlignment(Pos.CENTER);
        window.setCenter(gridpane);
        window.setTop(user);
    }

    public void drawTrafficLights() {
        Intersection intersection;
        for (int i = 0; i < CityGenerator.city.getNrOfIntersections(); i++) {
            intersection = CityGenerator.city.getIntersectionByIndex(i);
            Integer x = intersection.getPosX();
            Integer y = intersection.getPosY();
            Image semafor = new Image("/GraphicsModule/resources/TrafficLightWithTimer.gif");
            ImageView semaforView = new ImageView(semafor);
            if (map[y][x] == 5)
                gridpane.add(semaforView, y, x);
        }
    }

    public String getTrafficFrequency() {

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                trafficFrequencyInput = trafficSpinner.getValue();
            }
        });
        return trafficFrequencyInput;
    }

    public void printStreets() {

        System.out.println("Strada de la coordonatele " + " " + CityGenerator.city.getStreetByIndex(CityGenerator.city.getStreetByCoordonates(4, 4)).getName());

    }

    public void drawCars() {
        for (Car car : CityGenerator.city.getCars()) {
            //daca strada e orizontala
            if (CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getDirection() == 1)
                //daca sensul e normal (de la stanga la dreapta)
                if (car.getDirection() == 1) {
                    Image newCar = new Image("/GraphicsModule/resources/carGoingRight.png");
                    ImageView carToRight = new ImageView(newCar);
                    int X = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX();
                    int Y = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY();
                    //System.out.println("Street: " + (car.getCurrentPosition() + 1) + "          Position: " + car.getDistance() + "Direction" + car.getDirection());
                    //System.out.println(X + " " + Y);
                    gridpane.add(carToRight, Y, X);

            } else { //daca sensul e invers (de la dreapta la stanga)
                    Image newCar = new Image("/GraphicsModule/resources/carGoingLeft.png");
                    ImageView carToLeft = new ImageView(newCar);
                    int X = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX();
                    int Y = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY();
                    //System.out.println("Street: " + (car.getCurrentPosition() + 1) + "          Position: " + car.getDistance() + "Direction" + car.getDirection());
                    //System.out.println(X + " " + Y);
                    gridpane.add(carToLeft, Y, X);
                }
            //daca strada e verticala
            else if (car.getDirection()== 1) {
                //daca masina merge normal (in sus)
                Image newCar = new Image("/GraphicsModule/resources/carGoingUp.png");
                ImageView carToUp = new ImageView(newCar);
                int X = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX();
                int Y = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY();
                //System.out.println("Street: " + (car.getCurrentPosition() + 1) + "          Position: " + car.getDistance() + "Direction" + car.getDirection());
                //System.out.println(X + " " + Y);
                gridpane.add(carToUp, Y, X);
            }
            else { //daca masina merge in sens invers (in jos)
                Image newCar = new Image("/GraphicsModule/resources/carGoingDown.png");
                ImageView carToDown = new ImageView(newCar);
                int X = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX();
                int Y = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY();
                //System.out.println("Street: " + (car.getCurrentPosition() + 1) + "          Position: " + car.getDistance() + "Direction" + car.getDirection());
                //System.out.println(X + " " + Y);
                gridpane.add(carToDown, Y, X);

            }


        }
    }
}

package TestGrafica;

import CarGenerating.Car;
import CityGenerating.CityGenerator;
import CityGenerating.Intersection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;


public class Graphics2 {

    public BorderPane window = new BorderPane();

    //We add what we need for the user inputs
    HBox user;
    Button submit;
    Label trafficLabel;
    ObservableList<String> trafficTypes;
    Spinner<String> trafficSpinner = new Spinner<>();
    static String trafficFrequencyInput;
    public static Integer[][] map = new Integer[10][10];

    //We use gridpane for a visual representation of the matrix
    public GridPane gridpane;
    List<ImageView> carsToDraw = new ArrayList<>();
    List<Integer> firstCoord = new ArrayList<>();
    List<Integer> secondCoord = new ArrayList<>();

    public void transposeMatrix() {
        map = CityGenerator.city.mapPreGenerated;
        Integer[][] transpose = new Integer[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                transpose[i][j] = map[j][i];

        map = transpose;
    }

    public void drawMap() {
        gridpane = new GridPane();
        transposeMatrix();
        Image streetBlock = new Image("file:src/GraphicsModule/resources/StreetBlock.jpg");
        Image streetBlockUp = new Image("file:src/GraphicsModule/resources/StreetBlockUp.jpg");
        Image grass = new Image("file:src/GraphicsModule/resources/grass.png");
        Image intersection = new Image("file:src/GraphicsModule/resources/junction.png");

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
//        user = new HBox();
//        submit = new Button("Submit");
//        trafficLabel = new Label("Select Traffic:");
//        trafficTypes = FXCollections.observableArrayList("Low", "Medium", "High");
//        SpinnerValueFactory<String> values = new SpinnerValueFactory.ListSpinnerValueFactory<>(trafficTypes);
//        trafficSpinner.setValueFactory(values);
//        user.getChildren().addAll(trafficLabel, trafficSpinner, submit);
//        user.setAlignment(Pos.CENTER);
        window.setCenter(gridpane);
        //   window.setTop(user);
    }

    public void drawTrafficLights() {
        Intersection intersection;
        for (int i = 0; i < CityGenerator.city.getNrOfIntersections(); i++) {
            intersection = CityGenerator.city.getIntersectionByIndex(i);
            Integer x = intersection.getPosX();
            Integer y = intersection.getPosY();
            Image semafor = new Image("file:src/GraphicsModule/resources/TrafficLightWithTimer.gif");
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

    public static String getTrafficFrequencyInput() {

        return trafficFrequencyInput;
    }//am facut un getter

    public List<ImageView> drawCars() throws InterruptedException {
        //TODO: ruleaza functia asta intr-un thread javafx

        //TODO: luati in considerare car.getDistance cand o puneti pe strada

        //dam remove ca sa fie actualizata pozitia de fiecare data
        System.out.println("ajung aici");
        System.out.println(carsToDraw);
        System.out.println("aici au fost masinile de desenat");

        for (Car car : CityGenerator.city.getCars()) {
            System.out.println("grafica" + car.getShortestPath());
//            if(car.getDistance() == -1)
//                continue; //probabil tre sa faci si asta
            //daca strada e orizontala
            // System.out.println("si aici");
            //System.out.println(CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX());
            //System.out.println(CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY());

            if (CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getDirection() == 1)
                //daca sensul e normal (de la stanga la dreapta)
                if (car.getDirection() == 1) {
                    System.out.println("si aici2");
                    Image newCar = new Image("file:src/GraphicsModule/resources/carGoingRight.png");
                    ImageView carToRight = new ImageView(newCar);
                    int X = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX();
                    int Y = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY() + car.getDistance();
                    System.out.println(X);
                    System.out.println(Y);
                    carsToDraw.add(carToRight);
                    firstCoord.add(Y);
                    secondCoord.add(X);

                } else { //daca sensul e invers (de la dreapta la stanga)
                    System.out.println("si aici3");

                    Image newCar = new Image("file:src/GraphicsModule/resources/carGoingLeft.png");
                    ImageView carToLeft = new ImageView(newCar);
                    int X = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX();
                    int Y = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY() + car.getDistance();
                    carsToDraw.add(carToLeft);
                    firstCoord.add(Y);
                    secondCoord.add(X);
                    System.out.println(X);
                    System.out.println(Y);
                    System.out.println("am iesit");
                }
                //daca strada e verticala
            else if (car.getDirection() == 1) {
                System.out.println("si aici4");

                //daca masina merge normal (in sus)
                Image newCar = new Image("file:src/GraphicsModule/resources/carGoingUp.png");
                ImageView carToUp = new ImageView(newCar);
                int X = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX() + CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getLength() - car.getDistance();
                int Y = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY();
                carsToDraw.add(carToUp);
                firstCoord.add(Y);
                System.out.println(X);
                System.out.println(Y);
                secondCoord.add(X);
            } else { //daca masina merge in sens invers (in jos)
                System.out.println("si aici5");

                Image newCar = new Image("file:src/GraphicsModule/resources/carGoingDown.png");
                ImageView carToDown = new ImageView(newCar);
                int X = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosX() - CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getLength() + car.getDistance();
                int Y = CityGenerator.city.getStreetByIndex(car.getCurrentPosition()).getPosY();
                carsToDraw.add(carToDown);
                firstCoord.add(Y);
                System.out.println(X);
                System.out.println(Y);
                secondCoord.add(X);
            }
        }

        return carsToDraw;
    }

    public List<Integer> getFirstCoord() {
        return firstCoord;
    }

    public List<Integer> getSecondCoord() {
        return secondCoord;
    }
}
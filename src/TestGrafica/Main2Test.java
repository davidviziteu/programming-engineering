package TestGrafica;

import CityGenerating.CityGenerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main2Test extends Application{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    @Override
    public void start(Stage stage) throws InterruptedException {

        CityGenerator.generateCity();
        Graphics2 ourCity = new Graphics2();
        ourCity.addUserPane();
        //ourCity.addUserPane();
        ourCity.drawTrafficLights();

        Scene scene = new Scene(ourCity.window, 1000, 600);

        Image car = new Image("file:///C:\\Users\\andre\\Documents\\GitHub\\testip\\programming-engineering-thread-test\\src\\GraphicsModule\\resources\\carGoingRight.png");
        ImageView ball = new ImageView(car);
        ball.relocate(5, 5);
        ourCity.window.getChildren().add(ball);
        List<ImageView> cars = new ArrayList(ourCity.drawCars());
        System.out.println("Am luat masinile:");
        System.out.println(cars);
        List<Integer> first = new ArrayList<>(ourCity.getFirstCoord());
        List<Integer> second = new ArrayList<>(ourCity.getSecondCoord());

        System.out.println(first);
        System.out.println(second);
        for (int i = 0; i < cars.size(); i++) {
            System.out.println("aici" + CityGenerator.city.getCars().get(i).getShortestPath());
            cars.get(i).setLayoutX(first.get(i));
            cars.get(i).setLayoutY(second.get(i));
            ourCity.window.getChildren().add(cars.get(i));
            cars.get(i).relocate(cars.get(i).getLayoutX() * 100, cars.get(i).getLayoutY() * 100);
        }
        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {

                    double dx = 8; //Step on x or velocity
                    double dy = 8; //Step on y

                    @Override
                    public void handle(ActionEvent t) {
                        //move the ball
                        for (int i = 0; i < cars.size(); i++) {
                            //System.out.println(CityGenerator.city.getCars().get(i).getShortestPath());
                            if (CityGenerator.city.getStreetByIndex(CityGenerator.city.getCars().get(i).getCurrentPosition()).getDirection() == 1)//orizonatal
                                if (CityGenerator.city.getCars().get(i).getDirection() == 1) //de la stanga la dreapta
                                {
                                    cars.get(i).setLayoutX(cars.get(i).getLayoutX() + dx);
                                    //System.out.println(cars.get(0).getLayoutX());
                                    cars.get(i).setLayoutY(cars.get(i).getLayoutY());
                                }else {//de la dreapta la stanga
                                    cars.get(i).setLayoutX(cars.get(i).getLayoutX() - dx);
                                    cars.get(i).setLayoutY(cars.get(i).getLayoutY());
                                }
                            else if (CityGenerator.city.getCars().get(i).getDirection() == 1) //de sus in jos
                            {
                                cars.get(i).setLayoutX(cars.get(i).getLayoutX());
                                //System.out.println(cars.get(0).getLayoutX());
                                cars.get(i).setLayoutY(cars.get(i).getLayoutY()-dy);
                            }else {//de la
                                cars.get(i).setLayoutX(cars.get(i).getLayoutX());
                                cars.get(i).setLayoutY(cars.get(i).getLayoutY()+dy);
                            }
                        }


                        System.out.println(cars);
                        System.out.println("Aici este valoare trimisa de GetData actualizata: \t");

                        for (int i = 0; i < CityGenerator.city.getCars().size(); i++)
                            System.out.println(ANSI_YELLOW + "Acesta distanta pe strada" + CityGenerator.city.getCars().get(i).getDistance() + "\n Current Position:" + CityGenerator.city.getCars().get(i).getCurrentPosition());


                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void incepe(){
        System.out.println("am inceput");
        launch();
    }

}
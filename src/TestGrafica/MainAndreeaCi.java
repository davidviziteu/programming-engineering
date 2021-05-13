package TestGrafica;

import AnimationLogic.CarAnimator;
import AnimationLogic.Miscellaneous.ConsoleColors;
import AnimationLogic.Miscellaneous.Utilities;
import CityGenerating.City;
import CityGenerating.CityGenerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class MainAndreeaCi extends Application{


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    @Override
    public void start(Stage stage) {

        Graphics2 ourCity = new Graphics2();
        ourCity.addUserPane();
        //ourCity.addUserPane();
        ourCity.drawTrafficLights();
        Scene scene = new Scene(ourCity.window, 900, 900);

        //Pane canvas = new Pane();
        //Scene scene = new Scene(canvas, 900, 900, Color.ALICEBLUE);
        Image car = new Image("file:///C:\\Users\\andre\\OneDrive\\Desktop\\ip-vTest\\programming-engineering\\src\\TestGrafica\\carGoingRight.png");
        ImageView ball = new ImageView(car);

        putFirstCar(ball, ourCity);

        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();

        System.out.println("inainte de prima iteratie");

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000),
                new EventHandler<ActionEvent>() {

                    double dx = 10; //Step on x or velocity
                    double dy = 3; //Step on y

                    @Override
                    public void handle(ActionEvent t) {
                        //move the ball
                        System.out.println(ball.getLayoutX());
                        System.out.println(ball.getLayoutY());
                       // ball.setLayoutX(ball.getLayoutX() + dx);
                       // ball.setLayoutY(ball.getLayoutY());
                       //
                        if(CityGenerator.city.getStreetByIndex(CityGenerator.city.getCars().get(0).getCurrentPosition()).getDirection()==-1){
                            var path = new File("src\\GraphicsModule\\resources\\carGoingUp.png").getAbsolutePath();
                            ball.setImage(new Image("file:///" + path));
//                            ball.setImage(new Image("file:///C:\\Users\\andre\\OneDrive\\Desktop\\ip-try3\\programming-engineering\\src\\GraphicsModule\\resources\\carGoingUp.png"));
                        }
                        else{
                            var path = new File("src\\TestGrafica\\carGoingRight.png").getAbsolutePath();
                            ball.setImage(new Image("file:///" + path));
//                            ball.setImage(new Image("file:///C:\\Users\\andre\\OneDrive\\Desktop\\ip-vTest\\programming-engineering\\src\\TestGrafica\\carGoingRight.png"));

                        }
                        int x, y;
                        try {
                            CarAnimator.rwLock.readLock().lock();
                            x = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(0)).getKey();
                            y = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(0)).getValue();
                        }
                        finally {
                            CarAnimator.rwLock.readLock().unlock();
                        }
                        System.out.println("AICI ESTE VALOAREA COORDONATELOR: " + x + " . " + y);
                        ball.setLayoutX(y*100);
                        ball.setLayoutY(x*100);
                        System.out.println("Aici este valoare trimisa de GetData actualizata: \t" );

                        for(int i=0; i< CityGenerator.city.getCars().size(); i++)
                            System.out.println(
                                    ConsoleColors.YELLOW +
                                            "Aceasta este masina" + CityGenerator.city.getCars().get(i).getID() +
                                            "\tAcesta distanta pe strada" + CityGenerator.city.getCars().get(i).getDistance() +
                                            "\n Current Position:"+CityGenerator.city.getCars().get(i).getCurrentPosition()
                            + ConsoleColors.RESET);
                        System.out.println(ball.getLayoutX() + dx);

                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public void showWindow() throws Exception {
        Stage stage = new Stage();
        start(stage);
    }

    public static void incepe(){
        System.out.println("am inceput");
        launch();
    }
    public static void putFirstCar(ImageView ball, Graphics2 ourCity){
        int x1= CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(0)).getKey();

        int y1 = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(0)).getValue();
        ball.setLayoutX(y1*100);
        ball.setLayoutY(x1*100);
        if(!isVertical()) {
            var path = new File("src\\TestGrafica\\carGoingRight.png").getAbsolutePath();
            ball.setImage(new Image("file:///" + path));
        }
        else {
            var path = new File("src\\GraphicsModule\\resources\\carGoingUp.png").getAbsolutePath();
            ball.setImage(new Image("file:///" + path));
        }
        System.out.println(x1 + "..." + y1);
        ourCity.window.getChildren().add(ball);

    }
    public static boolean isVertical(){
        int ok=1;
        if(CityGenerator.city.getStreetByIndex(CityGenerator.city.getCars().get(0).getCurrentPosition()).getDirection()==-1)
            return true;
        return false;
    }

}
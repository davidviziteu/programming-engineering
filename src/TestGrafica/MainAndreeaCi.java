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
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<ImageView> carView = new ArrayList<>();

        for(int i =0; i <CityGenerator.city.getCars().size(); i++){
            carView.add(new ImageView(car));
        }
        putAllCars(carView, ourCity);

        stage.setTitle("Traffic Simulator");
        stage.setScene(scene);
        stage.show();

        System.out.println("inainte de prima iteratie");

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000),
                new EventHandler<ActionEvent>() {

                    double dx = 10; //Step on x or velocity
                    double dy = 3; //Step on y

                    @Override
                    public void handle(ActionEvent t) {
                        for(int i =0; i< carView.size(); i++){


                        //move the ball
                        System.out.println(carView.get(i).getLayoutX());
                        System.out.println(carView.get(i).getLayoutY());
                       // ball.setLayoutX(ball.getLayoutX() + dx);
                       // ball.setLayoutY(ball.getLayoutY());
                       //
                        if(CityGenerator.city.getCars().get(i).getID() == 1){
                            var path = new File("src\\GraphicsModule\\resources\\specialCarGoingRight.png").getAbsolutePath();
                            carView.get(i).setImage(new Image("file:///" + path));
                        }
                        else
                            if(CityGenerator.city.getStreetByIndex(CityGenerator.city.getCars().get(i).getCurrentPosition()).getDirection()==1){
                            if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                                var path = new File("src\\GraphicsModule\\resources\\carGoingRight.png").getAbsolutePath();
                                carView.get(i).setImage(new Image("file:///" + path));
                            } else {
                                var path = new File("src\\GraphicsModule\\resources\\carGoingLeft.png").getAbsolutePath();
                                carView.get(i).setImage(new Image("file:///" + path));
                            }
//                            ball.setImage(new Image("file:///Users\\andre\\OneDrive\\Desktop\\ip-try3\\programming-engineering\\src\\GraphicsModule\\resources\\carGoingUp.png"));
                        }
                        else if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                            var path = new File("src\\GraphicsModule\\resources\\carGoingDown.png").getAbsolutePath();
                            carView.get(i).setImage(new Image("file:///" + path));
                        } else {
                            var path = new File("src\\GraphicsModule\\resources\\carGoingUp.png").getAbsolutePath();
                            carView.get(i).setImage(new Image("file:///" + path));

//                            ball.setImage(new Image("file:///C:\\Users\\andre\\OneDrive\\Desktop\\ip-vTest\\programming-engineering\\src\\TestGrafica\\carGoingRight.png"));
                        }
                        int x, y;
                        try {
                            CarAnimator.rwLock.readLock().lock();
                            x = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(i)).getKey();
                            y = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(i)).getValue();
                        }
                        finally {
                            CarAnimator.rwLock.readLock().unlock();
                        }
//                        System.out.println("AICI ESTE VALOAREA COORDONATELOR: " + x + " . " + y);
                            carView.get(i).setLayoutX(y*100);
                            carView.get(i).setLayoutY(x*100);
//                        System.out.println("Aici este valoare trimisa de GetData actualizata: \t" );

//                        for(int i=0; i< CityGenerator.city.getCars().size(); i++)
//                            System.out.println(
//                                    ConsoleColors.YELLOW +
//                                            "Aceasta este masina" + CityGenerator.city.getCars().get(i).getID() +
//                                            "\tAcesta distanta pe strada" + CityGenerator.city.getCars().get(i).getDistance() +
//                                            "\n Current Position:"+CityGenerator.city.getCars().get(i).getCurrentPosition()
//                            + ConsoleColors.RESET);
//                        System.out.println(carView.get(i).getLayoutX() + dx);
                        }
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


    public static void putAllCars(ArrayList<ImageView> imageViewArrayList, Graphics2 ourCity){

        for(int i =0; i <imageViewArrayList.size(); i++){

            int x1= CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(i)).getKey();
            int y1 = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(i)).getValue();
            imageViewArrayList.get(i).setLayoutX(y1*100);
            imageViewArrayList.get(i).setLayoutY(x1*100);

            if(CityGenerator.city.getCars().get(i).getID() == 1){
                var path = new File("src\\GraphicsModule\\resources\\specialCarGoingRight.png").getAbsolutePath();
                imageViewArrayList.get(0).setImage(new Image("file:///" + path));
            }else
            if(!isVertical(i)) {

                if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                    var path = new File("src\\GraphicsModule\\resources\\carGoingRight.png").getAbsolutePath();
                    imageViewArrayList.get(i).setImage(new Image("file:///" + path));
                } else {
                    var path = new File("src\\GraphicsModule\\resources\\carGoingLeft.png").getAbsolutePath();
                    imageViewArrayList.get(i).setImage(new Image("file:///" + path));
                }
            }
            else if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                    var path = new File("src\\GraphicsModule\\resources\\carGoingDown.png").getAbsolutePath();
                imageViewArrayList.get(i).setImage(new Image("file:///" + path));
                } else {
                    var path = new File("src\\GraphicsModule\\resources\\carGoingUp.png").getAbsolutePath();
                imageViewArrayList.get(i).setImage(new Image("file:///" + path));
                }

            System.out.println(x1 + "..." + y1);
            ourCity.window.getChildren().add(imageViewArrayList.get(i));
        }
    }
    public static boolean isVertical(int i){
        int ok=1;
        if(CityGenerator.city.getStreetByIndex(CityGenerator.city.getCars().get(i).getCurrentPosition()).getDirection()==-1)
            return true;
        return false;
    }

}
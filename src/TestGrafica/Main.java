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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    @Override
    public void start(Stage stage) {

        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 500, 400, Color.ALICEBLUE);
        Image car = new Image("file:///C:\\Users\\andre\\OneDrive\\Desktop\\ip-vTest\\programming-engineering\\src\\TestGrafica\\carGoingRight.png");
        ImageView ball = new ImageView(car);
        ball.relocate(5, 5);
        canvas.getChildren().add(ball);
        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {

                    double dx = 10; //Step on x or velocity
                    double dy = 3; //Step on y

                    @Override
                    public void handle(ActionEvent t) {
                        //move the ball

                        ball.setLayoutX(ball.getLayoutX() + dx);
                        ball.setLayoutY(ball.getLayoutY());
                        System.out.println("Aici este valoare trimisa de GetData actualizata: \t" );

                        for(int i=0; i< CityGenerator.city.getCars().size(); i++)
                            System.out.println( ANSI_YELLOW + "Acesta distanta pe strada" + CityGenerator.city.getCars().get(i).getDistance() + "\n Current Position:"+CityGenerator.city.getCars().get(i).getCurrentPosition());
                        System.out.println(ball.getLayoutX() + dx);

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
package TestGrafica;

import Alg2.Alg2;
import AnimationLogic.CarAnimator;
import AnimationLogic.CarController;
import AnimationLogic.MasterThread;
import AnimationLogic.Miscellaneous.ConsoleColors;
import AnimationLogic.Miscellaneous.Utilities;
import AnimationLogic.SemaphoreController;
import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.TrafficLights;
import CityGenerating.Street;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainAndreeaCi extends Application {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    static Image semaphoreGreenHorizontalImg = new Image(
            "file:src/GraphicsModule/resources/SemaphoreGreenHorizontal.png");
    static Image semaphoreGreenVerticalImg = new Image("file:src/GraphicsModule/resources/SemaphoreGreenVertical.png");
    static Image semaphoreYellowImg = new Image("file:src/GraphicsModule/resources/SemaphoreYellow.png");

    static ImageView semaphoreIntersection8ImgView = new ImageView(semaphoreGreenHorizontalImg);
    static ImageView semaphoreIntersection9ImgView = new ImageView(semaphoreGreenVerticalImg);
    static ImageView semaphoreIntersection10ImgView = new ImageView(semaphoreGreenVerticalImg);
    static ImageView semaphoreIntersection11ImgView = new ImageView(semaphoreGreenVerticalImg);

    static Integer horizontalSemaphoreIntersection8Id = CityGenerator.city.getStreetByIndex(0).getTrafficLights();
    static Integer verticalSemaphoreIntersection8Id = CityGenerator.city.getStreetByIndex(1).getTrafficLights();

    static Integer horizontalSemaphoreIntersection9Id = CityGenerator.city.getStreetByIndex(2).getTrafficLights();
    static Integer verticalSemaphoreIntersection9Id = CityGenerator.city.getStreetByIndex(3).getTrafficLights();

    static Integer horizontalSemaphoreIntersection10Id = CityGenerator.city.getStreetByIndex(7).getTrafficLights();
    static Integer verticalSemaphoreIntersection10Id = CityGenerator.city.getStreetByIndex(5).getTrafficLights();

    static Integer horizontalSemaphoreIntersection11Id = CityGenerator.city.getStreetByIndex(8).getTrafficLights();
    static Integer verticalSemaphoreIntersection11Id = CityGenerator.city.getStreetByIndex(6).getTrafficLights();
    public static City city;

    @Override
    public void start(Stage stage) {

        Graphics2 ourCity = new Graphics2();
        ourCity.addUserPane();
        // ourCity.addUserPane();
        ourCity.drawTrafficLights();
        Scene scene = new Scene(ourCity.window, 900, 900);

        // Pane canvas = new Pane();
        // Scene scene = new Scene(canvas, 900, 900, Color.ALICEBLUE);
        Image car = new Image(
                "file:///C:\\Users\\andre\\OneDrive\\Desktop\\ip-vTest\\programming-engineering\\src\\TestGrafica\\carGoingRight.png");
        ArrayList<ImageView> carView = new ArrayList<>();

        for (int i = 0; i < CityGenerator.city.getCars().size(); i++) {
            carView.add(new ImageView(car));
        }
        putAllCars(carView, ourCity);

        stage.setTitle("Traffic Simulator");
        stage.setScene(scene);
        stage.show();

        System.out.println("inainte de prima iteratie");

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {

            double dx = 10; // Step on x or velocity
            double dy = 3; // Step on y

            @Override
            public void handle(ActionEvent t) {
                var stateHorizontalSemaphore8 = CityGenerator.city.getTLightsById(horizontalSemaphoreIntersection8Id)
                        .getColor();
                if (stateHorizontalSemaphore8 == TrafficLights.StareSemafor.YellowRed
                        || stateHorizontalSemaphore8 == TrafficLights.StareSemafor.YellowGreen)
                    semaphoreIntersection8ImgView.setImage(semaphoreYellowImg);
                else if (stateHorizontalSemaphore8 == TrafficLights.StareSemafor.Green)
                    semaphoreIntersection8ImgView.setImage(semaphoreGreenHorizontalImg);
                else if (stateHorizontalSemaphore8 == TrafficLights.StareSemafor.Red)
                    semaphoreIntersection8ImgView.setImage(semaphoreGreenVerticalImg);

                var stateHorizontalSemaphore9 = CityGenerator.city.getTLightsById(horizontalSemaphoreIntersection9Id)
                        .getColor();
                if (stateHorizontalSemaphore9 == TrafficLights.StareSemafor.YellowRed
                        || stateHorizontalSemaphore9 == TrafficLights.StareSemafor.YellowGreen)
                    semaphoreIntersection9ImgView.setImage(semaphoreYellowImg);
                else if (stateHorizontalSemaphore9 == TrafficLights.StareSemafor.Green)
                    semaphoreIntersection9ImgView.setImage(semaphoreGreenHorizontalImg);
                else if (stateHorizontalSemaphore9 == TrafficLights.StareSemafor.Red)
                    semaphoreIntersection9ImgView.setImage(semaphoreGreenVerticalImg);

                var stateHorizontalSemaphore10 = CityGenerator.city.getTLightsById(horizontalSemaphoreIntersection10Id)
                        .getColor();
                if (stateHorizontalSemaphore10 == TrafficLights.StareSemafor.YellowRed
                        || stateHorizontalSemaphore10 == TrafficLights.StareSemafor.YellowGreen)
                    semaphoreIntersection10ImgView.setImage(semaphoreYellowImg);
                else if (stateHorizontalSemaphore10 == TrafficLights.StareSemafor.Green)
                    semaphoreIntersection10ImgView.setImage(semaphoreGreenHorizontalImg);
                else if (stateHorizontalSemaphore10 == TrafficLights.StareSemafor.Red)
                    semaphoreIntersection10ImgView.setImage(semaphoreGreenVerticalImg);

                var stateHorizontalSemaphore11 = CityGenerator.city.getTLightsById(horizontalSemaphoreIntersection11Id)
                        .getColor();
                if (stateHorizontalSemaphore11 == TrafficLights.StareSemafor.YellowRed
                        || stateHorizontalSemaphore11 == TrafficLights.StareSemafor.YellowGreen)
                    semaphoreIntersection11ImgView.setImage(semaphoreYellowImg);
                else if (stateHorizontalSemaphore11 == TrafficLights.StareSemafor.Green)
                    semaphoreIntersection11ImgView.setImage(semaphoreGreenHorizontalImg);
                else if (stateHorizontalSemaphore11 == TrafficLights.StareSemafor.Red)
                    semaphoreIntersection11ImgView.setImage(semaphoreGreenVerticalImg);

                // semaphoreIntersection9ImgView.setImage(semaphoreYellowImg);
                // semaphoreIntersection10ImgView.setImage(semaphoreYellowImg);
                // semaphoreIntersection11ImgView.setImage(semaphoreYellowImg);

                for (int i = 0; i < carView.size(); i++) {
                    // move the ball
                    // ball.setLayoutX(ball.getLayoutX() + dx);
                    // ball.setLayoutY(ball.getLayoutY());
                    //
                    if (CityGenerator.city.getCars().get(i).getID() == 1) {

                        if (!isVertical(i)) {

                            if (CityGenerator.city.getCars().get(0).getDirection() == 1) {
                                var path_special = new File("src\\GraphicsModule\\resources\\specialCarGoingRight.png")
                                        .getAbsolutePath();
                                carView.get(0).setImage(new Image("file:///" + path_special));
                            } else {
                                var path_special = new File("src\\GraphicsModule\\resources\\specialCarGoingLeft.png")
                                        .getAbsolutePath();
                                carView.get(0).setImage(new Image("file:///" + path_special));
                            }
                        } else if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                            var path_special = new File("src\\GraphicsModule\\resources\\specialCarGoingDown.png")
                                    .getAbsolutePath();
                            carView.get(0).setImage(new Image("file:///" + path_special));
                        } else {
                            var path_special = new File("src\\GraphicsModule\\resources\\specialCarGoingUp.png")
                                    .getAbsolutePath();
                            carView.get(0).setImage(new Image("file:///" + path_special));
                        }

                    } else if (CityGenerator.city
                            .getStreetByIndex(CityGenerator.city.getCars().get(i).getCurrentPosition())
                            .getDirection() == 1) {
                        if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                            var path = new File("src\\GraphicsModule\\resources\\carGoingRight.png").getAbsolutePath();
                            carView.get(i).setImage(new Image("file:///" + path));
                        } else {
                            var path = new File("src\\GraphicsModule\\resources\\carGoingLeft.png").getAbsolutePath();
                            carView.get(i).setImage(new Image("file:///" + path));
                        }
                        // ball.setImage(new
                        // Image("file:///Users\\andre\\OneDrive\\Desktop\\ip-try3\\programming-engineering\\src\\GraphicsModule\\resources\\carGoingUp.png"));
                    } else if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                        var path = new File("src\\GraphicsModule\\resources\\carGoingDown.png").getAbsolutePath();
                        carView.get(i).setImage(new Image("file:///" + path));
                    } else {
                        var path = new File("src\\GraphicsModule\\resources\\carGoingUp.png").getAbsolutePath();
                        carView.get(i).setImage(new Image("file:///" + path));

                        // ball.setImage(new
                        // Image("file:///C:\\Users\\andre\\OneDrive\\Desktop\\ip-vTest\\programming-engineering\\src\\TestGrafica\\carGoingRight.png"));
                    }
                    int x, y;
                    try {
                        CarAnimator.rwLock.readLock().lock();
                        x = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(i)).getKey();
                        y = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(i)).getValue();
                    } finally {
                        CarAnimator.rwLock.readLock().unlock();
                    }
                    // System.out.println("AICI ESTE VALOAREA COORDONATELOR: " + x + " . " + y);
                    carView.get(i).setLayoutX(y * 100);
                    carView.get(i).setLayoutY(x * 100);
                    // System.out.println("Aici este valoare trimisa de GetData actualizata: \t" );

                    // for(int i=0; i< CityGenerator.city.getCars().size(); i++)
                    // System.out.println(
                    // ConsoleColors.YELLOW +
                    // "Aceasta este masina" + CityGenerator.city.getCars().get(i).getID() +
                    // "\tAcesta distanta pe strada" +
                    // CityGenerator.city.getCars().get(i).getDistance() +
                    // "\n Current
                    // Position:"+CityGenerator.city.getCars().get(i).getCurrentPosition()
                    // + ConsoleColors.RESET);
                    // System.out.println(carView.get(i).getLayoutX() + dx);
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

    public static void incepe() {
        System.out.println("am inceput");
        launch();
    }

    public static void putAllCars(ArrayList<ImageView> imageViewArrayList, Graphics2 ourCity) {

        for (int i = 0; i < imageViewArrayList.size(); i++) {

            int x1 = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(i)).getKey();
            int y1 = CityGenerator.city.getCarCoordinates(CityGenerator.city.getCars().get(i)).getValue();
            imageViewArrayList.get(i).setLayoutX(y1 * 100);
            imageViewArrayList.get(i).setLayoutY(x1 * 100);

            if (CityGenerator.city.getCars().get(i).getID() == 1) {
                // var path = new
                // File("src\\GraphicsModule\\resources\\specialCarGoingRight.png").getAbsolutePath();
                // imageViewArrayList.get(0).setImage(new Image("file:///" + path));

                if (!isVertical(i)) {

                    if (CityGenerator.city.getCars().get(0).getDirection() == 1) {
                        var path_special = new File("src\\GraphicsModule\\resources\\specialCarGoingRight.png")
                                .getAbsolutePath();
                        imageViewArrayList.get(0).setImage(new Image("file:///" + path_special));
                    } else {
                        var path_special = new File("src\\GraphicsModule\\resources\\specialCarGoingLeft.png")
                                .getAbsolutePath();
                        imageViewArrayList.get(0).setImage(new Image("file:///" + path_special));
                    }
                } else if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                    var path_special = new File("src\\GraphicsModule\\resources\\specialCarGoingDown.png")
                            .getAbsolutePath();
                    imageViewArrayList.get(0).setImage(new Image("file:///" + path_special));
                } else {
                    var path_special = new File("src\\GraphicsModule\\resources\\specialCarGoingUp.png")
                            .getAbsolutePath();
                    imageViewArrayList.get(0).setImage(new Image("file:///" + path_special));
                }
            } else if (!isVertical(i)) {

                if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                    var path = new File("src\\GraphicsModule\\resources\\carGoingRight.png").getAbsolutePath();
                    imageViewArrayList.get(i).setImage(new Image("file:///" + path));
                } else {
                    var path = new File("src\\GraphicsModule\\resources\\carGoingLeft.png").getAbsolutePath();
                    imageViewArrayList.get(i).setImage(new Image("file:///" + path));
                }
            } else if (CityGenerator.city.getCars().get(i).getDirection() == 1) {
                var path = new File("src\\GraphicsModule\\resources\\carGoingDown.png").getAbsolutePath();
                imageViewArrayList.get(i).setImage(new Image("file:///" + path));
            } else {
                var path = new File("src\\GraphicsModule\\resources\\carGoingUp.png").getAbsolutePath();
                imageViewArrayList.get(i).setImage(new Image("file:///" + path));
            }
            ourCity.window.getChildren().add(imageViewArrayList.get(i));

        }
        ((GridPane) (ourCity.window.getCenter())).add(semaphoreIntersection8ImgView, 2, 1);
        ((GridPane) (ourCity.window.getCenter())).add(semaphoreIntersection9ImgView, 5, 1);
        ((GridPane) (ourCity.window.getCenter())).add(semaphoreIntersection10ImgView, 2, 4);
        ((GridPane) (ourCity.window.getCenter())).add(semaphoreIntersection11ImgView, 5, 4);
        // Utilities.correctDistanceOfAllCars();
        Utilities.computeShortestPathForAllCars();
        Alg2 geneticAlgorithm = new Alg2(city);
        List<Street> path = geneticAlgorithm.run(city.getCars().get(0).getCurrentPosition(),
                city.getCars().get(0).getFinalPosition());
        List<Integer> intersections = new ArrayList<>();
        for (int i = 1; i < path.size() - 1; i++) {
            intersections.add(geneticAlgorithm.getCommonIntersection(path.get(i), path.get(i + 1)));
        }
        city.getCars().get(0).setShortestPath(intersections);

        Utilities.setAllCarsSpeed(1);
        for (long i = 0; i < 1000000000L; ++i)
            ;

        var carsControllerInstance = CarController.getInstance();
        var carsControllerThread = new Thread(carsControllerInstance);

        var carAnimatorInstance = CarAnimator.getInstance();
        var carAnimatorThread = new Thread(carAnimatorInstance);

        var semaphoreController = SemaphoreController.getInstance();
        var semaphoreControllerThread = new Thread(semaphoreController);

        semaphoreControllerThread.start();
        carsControllerThread.start();
        carAnimatorThread.start();
        MasterThread.followAllCars();
    }

    public static boolean isVertical(int i) {
        int ok = 1;
        if (CityGenerator.city.getStreetByIndex(CityGenerator.city.getCars().get(i).getCurrentPosition())
                .getDirection() == -1)
            return true;
        return false;
    }

}
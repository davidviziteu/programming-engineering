package CityGenerating;
import GraphicsModule.Graphics;
import javafx.application.Application;
import javafx.scene.Scene;
import CarGenerating.Car;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        CityGenerator.generateCity();
        Graphics ourCity = new Graphics();
        ourCity.addUserPane();
        ourCity.drawTrafficLights();
        Scene scene = new Scene(ourCity.window, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        ourCity.drawCars();

    }

    public static void main(String[] args) {
        launch(args);
        CityGenerator.generateCity();
        Street temp;
        for (int i = 0; i < CityGenerator.city.getNrOfStreets(); i++) {
            temp = CityGenerator.city.getStreetByIndex(i);
            System.out.println(temp.toString());
        }
        Intersection temp2;
        for (int i = 0; i < CityGenerator.city.getNrOfIntersections(); i++) {
            temp2 = CityGenerator.city.getIntersectionByIndex(i);
            System.out.println(temp2.toString());
        }

        for (Car temp3 : CityGenerator.city.getCars()) {
            System.out.println("Street: " + temp3.getCurrentPosition() + "          Position: " + temp3.getDistance());
        }

        System.out.println("Strada de la coordonatele 4,7 " + CityGenerator.city.getStreetByIndex(CityGenerator.city.getStreetByCoordonates(4, 7)).getName());

        Scanner scan = new Scanner(System.in);
        Integer index = scan.nextInt();

        Car carTemporar = CityGenerator.city.getStreetByIndex(index).peekQueue(1);
        CityGenerator.city.getStreetByIndex(index).removeCar(1);

        index = scan.nextInt();
        CityGenerator.city.getStreetByIndex(index).addCar(carTemporar, Math.random() < 0.5 ? -1 : 1);
        scan.close();
        for (Car temp3 : CityGenerator.city.getCars()) {
            System.out.println("Street: " + temp3.getCurrentPosition() + "          Position: " + temp3.getDistance());
        }
    }
}

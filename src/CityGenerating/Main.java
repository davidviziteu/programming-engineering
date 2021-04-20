package CityGenerating;

import CarGenerating.Car;
import CarGenerating.CarGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
            System.out.println("Street: " + temp3.getInitialPosition() + "          Position: " + temp3.getDistance());
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
            System.out.println("Street: " + temp3.getInitialPosition() + "          Position: " + temp3.getDistance());
        }
    }
}

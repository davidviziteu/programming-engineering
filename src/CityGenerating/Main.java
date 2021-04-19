package CityGenerating;
import CarGenerating.Car;
import CarGenerating.CarGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        CityGenerator.generateCity();
        Street temp;
        for(int i=0;i<CityGenerator.city.getNrOfStreets();i++){
            temp=CityGenerator.city.getStreetByIndex(i);
            System.out.println(temp.getName());
        }
        Intersection temp2;
        for(int i=0;i<CityGenerator.city.getNrOfIntersections();i++){
            temp2=CityGenerator.city.getIntersectionByIndex(i);
            System.out.println(temp2.getName());
        }
        for (Car temp3 : CityGenerator.city.getCars()){
            System.out.println("Street: " + temp3.getInitialPosition() +  "          Position: "+ temp3.getDistance());
        }
        Scanner scan = new Scanner(System.in);
        Integer index=scan.nextInt();

        Car carTemporar=CityGenerator.city.getStreetByIndex(index).peekQueue();
        CityGenerator.city.getStreetByIndex(index).removeCar();

        index=scan.nextInt();
        CityGenerator.city.getStreetByIndex(index).addCar(carTemporar);
        scan.close();
        for (Car temp3 : CityGenerator.city.getCars()){
            System.out.println("Street: " + temp3.getInitialPosition() +  "          Position: "+ temp3.getDistance());
        }
    }
}

package TestGrafica;

import CarGenerating.Car;
import CityGenerating.City;
import CityGenerating.CityGenerator;

public class TestFunction {
    public static void main(String[] args) {
        //distance 0--> inseamna ca e in itersectie
        //1 pe orizontala -> pe pozitia [distance] de la dreapta la stanga
        //-1 pe orizontala   -> pe pozitia [distance] de la stanga la dreapta
        //-1 pe verticala --> pe pozitia [distance] de sus in jos
        //1 pe verticala --> pe pozitia [distance] de jos in sus
        CityGenerator.generateCity();
        Car c1= new Car();
        c1.setDirection(-1);
        c1.setDistance(1);
        c1.setCurrentPosition(8);
        System.out.println(c1);
       System.out.println(CityGenerator.city.getCarCoordinates(c1).toString());
    }
}

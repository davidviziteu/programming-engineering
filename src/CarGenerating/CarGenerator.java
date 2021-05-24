package CarGenerating;

import GraphicsModule.Graphics;
import UI.UIController;
import UI.UIController.*;
import java.util.ArrayList;
import java.util.Locale;

import static CityGenerating.CityGenerator.city;

public class CarGenerator {
    private String frequency;
    public ArrayList<Car> cars;
    public static int numberOfCars;

    private final double frequencyLow = 0.2;
    private final double frequencyMedium = 0.4;
    private final double frequencyHigh = 0.6;


    public Integer getStreetByIntersection(Integer intersectionIndex){
        switch (intersectionIndex){
            case 0:
                return 1;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 0;
            case 7:
                return 7;
        }
        return -1;
    }

    public void generate(String frequency, Integer specialCarStartPosition, Integer specialCarFinishPosition, Integer direction) {
        Car specialCar = new Car(specialCarStartPosition,specialCarFinishPosition,1);
        specialCar.setDirection(direction);
        cars.add(specialCar);
        city.getStreetByIndex(cars.get(0).getCurrentPosition()).addCar(cars.get(0), cars.get(0).getDirection());

        //in functie de frecventa pe fiecare strada vor fi generate un numar de masini(strict <= capacitatea strazii)
        this.frequency = frequency.toLowerCase();

        if (this.frequency.equals("scăzut")) {
            numberOfCars = (int) (frequencyLow * totalStreetsLength());
//            numberOfCars = 5;
        }else if (this.frequency.equals("moderat")) {
            numberOfCars = (int) (frequencyMedium * totalStreetsLength());
//            numberOfCars = 9;
        } else if (this.frequency.equals("intens")) {
            numberOfCars = (int) (frequencyHigh * totalStreetsLength());
//            numberOfCars = 15;
        } else{
            numberOfCars = 1;
        }

        for (int index = 1; index <= numberOfCars; index++) {
            cars.add(new Car());
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
        }
        numberOfCars++;
    }

    public String getFrequency() {
        return frequency;
    }

    public CarGenerator(String frequency) {
        cars = new ArrayList<Car>();
        this.frequency = frequency;
    }

    public int totalStreetsLength() {
        int sum = 0;
        if (CityGenerating.CityGenerator.city != null) {
            for (int index = 0; index < city.getNrOfStreets(); index++) {
                sum += city.getStreetByIndex(index).getLength();
            }
        }
        return sum;
    }
}
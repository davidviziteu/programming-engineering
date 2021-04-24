package CarGenerating;

import java.util.ArrayList;

import static CityGenerating.CityGenerator.city;

public class CarGenerator {
    private String frequency;
    public ArrayList<Car> cars;
    public int numberOfCars;

    private final double frequencyLow = 0.2;
    private final double frequencyMedium = 0.4;
    private final double frequencyHigh = 0.6;


    public void generate() {
        //in functie de frecventa pe fiecare strada vor fi generate un numar de masini(strict <= capacitatea strazii)

        if (this.frequency == "Low") {
            numberOfCars = (int) (frequencyLow * totalStreetsLength());
        }
        if (this.frequency == "Medium") {
            numberOfCars = (int) (frequencyMedium * totalStreetsLength());
        }
        if (this.frequency == "High") {
            numberOfCars = (int) (frequencyHigh * totalStreetsLength());
        }

        for (int index = 0; index < numberOfCars; index++) {
            cars.add(new Car());
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
        }
    }

    public CarGenerator(String frequency) {
        cars = new ArrayList<Car>();
        this.frequency = frequency;
    }

    private int totalStreetsLength() {
        int sum = 0;

        for (int index = 0; index < city.getNrOfStreets(); index++) {
            sum += city.getStreetByIndex(index).getLength();
        }
        return sum;
    }
}
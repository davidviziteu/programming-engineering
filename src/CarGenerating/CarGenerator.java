package CarGenerating;

import UI.UIController;

import java.io.*;
import java.util.ArrayList;

import static CityGenerating.CityGenerator.city;

public class CarGenerator {
    private String frequency;
    public ArrayList<Car> cars;
    public static int numberOfCars;

    private final double frequencyLow = 0.2;
    private final double frequencyMedium = 0.4;
    private final double frequencyHigh = 0.6;


    public Integer getStreetByIntersection(Integer intersectionIndex) {
        switch (intersectionIndex) {
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

        Car specialCar = new Car(specialCarStartPosition, specialCarFinishPosition, 1, direction);
        specialCar.setID(1);
        cars.add(specialCar);
        city.getStreetByIndex(cars.get(0).getCurrentPosition()).addCar(cars.get(0), cars.get(0).getDirection());

        //in functie de frecventa pe fiecare strada vor fi generate un numar de masini(strict <= capacitatea strazii)
        this.frequency = frequency.toLowerCase();

        if (this.frequency.equals("scăzut")) {
            numberOfCars = (int) (frequencyLow * totalStreetsLength());
        } else if (this.frequency.equals("moderat")) {
            numberOfCars = (int) (frequencyMedium * totalStreetsLength());
        } else if (this.frequency.equals("intens")) {
            numberOfCars = (int) (frequencyHigh * totalStreetsLength());
        } else {
            numberOfCars = 1;
        }
        int index = 1;

        if (UIController.specialTestCase == 1) {
            numberOfCars = 5;
            Car.LastID = 2;

            File file = new File("inputMasiniLow.txt");

            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/CityGenerating/inputMasiniLow.txt"));
                String line;
                while((line = reader.readLine()) != null) {

                    String[] attributes = line.split(" ");
                    cars.add(new Car(Integer.parseInt(attributes[0]),
                            Integer.parseInt(attributes[1]), 1, Integer.parseInt(attributes[2])));
                    city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
                    index++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            cars.add(new Car(9, 7, 1, -1));
//            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
//
//            index++;
//            cars.add(new Car(9, 7, 2, -1));
//            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
//
//            index++;
//
//            cars.add(new Car(6, 4, 1, 1));
//            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
//
//            index++;
//
//            cars.add(new Car(6, 5, 2, 1));
//            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
//
            int index1 = index;
            for (index = index1 + 1; index <= numberOfCars; index++) {
                cars.add(new Car());
                city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
            }
            numberOfCars++;

        } else if (UIController.specialTestCase == 2) {
            cars.add(new Car(2, 7, 1, -1));
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());

            index++;
            cars.add(new Car(2, 7, 2, -1));
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());

            index++;

            cars.add(new Car(5, 4, 1, 1));
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());

            index++;

            cars.add(new Car(5, 5, 2, 1));
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
            //int index=1;

            int index1 = index;
            for (index = index1 + 1; index < numberOfCars; index++) {
                cars.add(new Car());
                city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
            }
            numberOfCars++;
        } else if (UIController.specialTestCase == 3) {
            numberOfCars = 4;
            Car.LastID = 2;
            cars.add(new Car(8, 7, 1, -1));
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());

            index++;
            cars.add(new Car(8, 7, 2, -1));
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());

            index++;

            cars.add(new Car(6, 7, 1, 1));
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());

            index++;

            cars.add(new Car(6, 7, 2, 1));
            city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
            //int index=1;

            int index1 = index;
            for (index = index1 + 1; index <= numberOfCars; index++) {
                cars.add(new Car());
                city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
            }
            numberOfCars++;
        }
        //        else if (UIController.specialTestCase == 4) {}

        else {
            for (index = 1; index <= numberOfCars; index++) {
                cars.add(new Car());
                city.getStreetByIndex(cars.get(index).getCurrentPosition()).addCar(cars.get(index), cars.get(index).getDirection());
            }
            numberOfCars++;
        }
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
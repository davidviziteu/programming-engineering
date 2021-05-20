package CityGenerating;

import CarGenerating.Car;
import CarGenerating.CarGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CityGenerator {
    public static City city;

    public static void generateCity(){
        generateCity("low",0,0, 1);
    }

    public static void generateCity(String frequency, Integer specialCarStartPosition, Integer specialCarFinishPosition, Integer direction) {
        ArrayList<Street> streets = new ArrayList<Street>();
        ArrayList<Intersection> intersections = new ArrayList<Intersection>();
        ArrayList<TrafficLights> trafficLights = new ArrayList<TrafficLights>();
        ArrayList<Car> cars = new ArrayList<Car>();
        intersections.add(new Intersection("Top1", new ArrayList<Integer>(Arrays.asList(1)), 0, 2, true));
        intersections.add(new Intersection("Top2", new ArrayList<Integer>(Arrays.asList(3)), 0, 5, true));
        intersections.add(new Intersection("Right1", new ArrayList<Integer>(Arrays.asList(4)), 1, 9, true));
        intersections.add(new Intersection("Right2", new ArrayList<Integer>(Arrays.asList(9)), 4, 9, true));
        intersections.add(new Intersection("Bottom1", new ArrayList<Integer>(Arrays.asList(10)), 9, 2, true));
        intersections.add(new Intersection("Bottom2", new ArrayList<Integer>(Arrays.asList(11)), 9, 5, true));
        intersections.add(new Intersection("Left1", new ArrayList<Integer>(Arrays.asList(0)), 1, 0, true));
        intersections.add(new Intersection("Left2", new ArrayList<Integer>(Arrays.asList(7)), 4, 0, true));
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/CityGenerating/inputIntersectii.txt"));
            String line = reader.readLine();
            while (line != null) {
                // read next line
                String[] parts = line.split(" ");
                ArrayList<Integer> strazi = new ArrayList<Integer>();
                for (int i = 2; i <= 5; i++) {
                    strazi.add(Integer.parseInt(parts[i]));
                }
                Intersection myIntersection = new Intersection(parts[0], strazi, Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
                intersections.add(myIntersection);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader("src/CityGenerating/inputStrazi.txt"));
            String line = reader.readLine();
            while (line != null) {
                // read next line
                String[] parts = line.split(" ");
                Street myStreet = new Street(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                        Integer.parseInt(parts[6]), Integer.parseInt(parts[7]), Integer.parseInt(parts[8]));
                streets.add(myStreet);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader("src/CityGenerating/inputSemafoare.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(" ");
                TrafficLights myLights = new TrafficLights(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]), 1);
                trafficLights.add(myLights);
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        city = new City(streets, intersections, trafficLights, cars);
        CarGenerator carGenerator = new CarGenerator("Medium");
        carGenerator.generate(frequency,specialCarStartPosition,specialCarFinishPosition,direction);
        System.out.println("[CityGenerator] Lungimea vectorului de masini generat este de: " + carGenerator.cars.size());
        city.setCars(carGenerator.cars);
    }
}

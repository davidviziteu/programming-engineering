package CityGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CityGenerator {
    public static City city;
    CityGenerator(){
        generateCity();
    }
    public static void generateCity(){
        ArrayList<Street> streets= new ArrayList<Streets>();
        ArrayList<Intersection> intersections= new ArrayList<Intersection>();
        ArrayList<TrafficLights> trafficLights= new ArrayList<TrafficLights>();
        ArrayList<Cars> cars= new ArrayList<Cars>();
        intersections.add(new Intersection("Top", new ArrayList<Integer>(Arrays.asList(2,4)),true));
        intersections.add(new Intersection("Right", new ArrayList<Integer>(Arrays.asList(5,10)),true));
        intersections.add(new Intersection("Bottom", new ArrayList<Integer>(Arrays.asList(11,12)),true));
        intersections.add(new Intersection("Left", new ArrayList<Integer>(Arrays.asList(1,8)),true));
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "inputIntersectii.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // read next line
                String[] parts = line.split(" ");
                ArrayList<Integer> strazi = new ArrayList<Integer>();
                for(int i =2; i<= 5; i++){
                    strazi.add(Integer.parseInt(parts[i]));
                    System.out.println(Integer.parseInt(parts[i]));
                }
                System.out.println();
                Intersection myIntersection = new Intersection(parts[0], strazi, Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
                intersections.add(myIntersection);
 
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader("inputStrazi.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // read next line
                String[] parts = line.split(" ");
                Street myStreet = new Street(parts[0], Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                        Integer.parseInt(parts[6]));
                streets.add(myStreet);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        city= new City(streets,intersections,trafficLights,cars);
    }
}

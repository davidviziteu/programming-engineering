package AnimationLogic.Miscellaneous;

import CarGenerating.Car;
import CityGenerating.Pair;
import ShortestPath.ShortestPath2;

import java.util.Queue;

import static CityGenerating.CityGenerator.city;

public class Utilities {
    public static void computeShortestPathForAllCars() {
        for (var st : city.getStreets()) {
            for (var qElement : st.getCars()) {
                var car = qElement.getValue();
                var shPathResult = ShortestPath2.compute(
                        city.getStreets(),
                        st.getIntersectionDestination(),
                        car.getFinalPosition(),
                        city.getNrOfIntersections()
                );
                car.setShortestPath(shPathResult.getSecond());
                car.setShortestPathDistance(shPathResult.getFirst() + car.getDistance());
            }
            for (var qElement : st.getCarsReversed()) {
                var car = qElement.getValue();
                var shPathResult = ShortestPath2.compute(
                        city.getStreets(),
                        st.getIntersectionSource(),
                        qElement.getValue().getFinalPosition(),
                        city.getNrOfIntersections()
                );
                car.setShortestPath(shPathResult.getSecond());
                car.setShortestPathDistance(shPathResult.getFirst() + car.getDistance());
            }
        }
    }

    public static int getIndexOfCarInQueue(Queue<Pair<Integer, Car>> q, Car car) {
        int i = 0;
        for (var pair : q) {
            if (pair.getValue().equals(car))
                return i;
            ++i;
        }
        return -1; //not found
    }

    /**
     * noi avem pt fiecare sens un Queue<Pair<Integer, Car>>
     * car.distance va fi offset ul fata de indexul 0 al cozii din care face parte
     * tre executat doar la inceput
     */
    public static void correctDistanceOfAllCars() {
        for (var st : city.getStreets()) {
            st.getCars().stream().forEachOrdered(pair -> {
                var offset = getIndexOfCarInQueue(st.getCars(), pair.getValue());
                if (offset == -1)
                    System.err.println("car " + pair.getValue().toString() + " not found in " + st.getName() + "'s queue");
                else pair.getValue().setDistance(offset);
            });
            st.getCarsReversed().stream().forEachOrdered(pair -> {
                var offset = getIndexOfCarInQueue(st.getCarsReversed(), pair.getValue());
                if (offset == -1)
                    System.err.println("car " + pair.getValue().toString() + " not found in " + st.getName() + "'s queue");
                else pair.getValue().setDistance(offset);
            });
        }
    }

    /**
     * @return true daca mai exista macar o masina pe harta, false altfel
     */
    public static boolean existsACarOnStreets() {
        for (var st : city.getStreets()) {
            if (!st.getCarsReversed().isEmpty())
                return true;
            if (!st.getCars().isEmpty())
                return true;
        }
        return false;
    }

    public static void setAllCarsSpeed(int squaresPerSecond){
        city.getCars().forEach(car -> car.setSpeed(squaresPerSecond));
    }
}

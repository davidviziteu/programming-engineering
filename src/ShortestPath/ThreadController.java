package ShortestPath;

import CityGenerating.CityGenerator;

public class ThreadController {

    static private void computeShortestPathForAllCars(){
        CityGenerator.generateCity();
        var city = CityGenerator.city;
        for(var st : city.getStreets()){
            for(var qElement : st.getCars()){
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
            for(var qElement : st.getCarsReversed()){
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

    public static void main(String[] args) {
        computeShortestPathForAllCars();
        var carsControllerInstance = new CarController();
        var semaphoreControllerInstance = new SemaphoreController();
        var threadController1 = new Thread(carsControllerInstance);
        var threadController2 = new Thread(semaphoreControllerInstance);
        threadController1.start();
        threadController2.start();
        try {
            threadController1.join();
            threadController2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

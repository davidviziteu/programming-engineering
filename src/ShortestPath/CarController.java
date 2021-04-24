package ShortestPath;

import CarGenerating.Car;
import CityGenerating.Pair;
import CityGenerating.Street;

import java.util.ArrayList;
import java.util.Queue;

import static CityGenerating.CityGenerator.city;

public class CarController extends Thread {

    /**
     * @return true daca mai exista macar o masina pe harta, false altfel
     */
    static boolean existsACarOnStreets() {
        for (var st : city.getStreets()) {
            if (!st.getCarsReversed().isEmpty())
                return true;
            if (!st.getCars().isEmpty())
                return true;
        }
        return false;
    }

    /**
     * vede daca o intersectie este din aia finala
     *
     * @param id
     * @return
     */
    static boolean isFinalIntersection(int id) {
        return id >= 0 && id <= 7;
    }

    /**
     * face peek la prima masina din coada si vede pe ce strada & coada tre sa ajunga si incearca sa o si mute acolo
     *
     * @param streetQueue           coada unui sens. nu prea e relevant care. de pe asta se va scoate prima masina
     * @param currentIntersectionId sensul de mers duce la o intersectie. parametrul asta este acea intersectie
     */
    static void tryMoveFirstCar(Queue<Pair<Integer, Car>> streetQueue, int currentIntersectionId) {
        boolean queueNotFull = true; //temporar, pt ca inca nu stiu cum sa verific daca o coada este plina

        if (streetQueue.peek() == null) return;
        if (isFinalIntersection(currentIntersectionId)) {
            System.out.println("car that has arrived at destination[" + currentIntersectionId + "]:" + streetQueue.peek().getValue());
            city.getCars().remove(streetQueue.remove().getValue());
            return;
        }
        var car = streetQueue.peek().getValue();
        Integer destinationIntersectionId;
        try {
            destinationIntersectionId = car.getShortestPath().get(0);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("ceva nu ie bine, stergem masina " + car);
            city.getCars().remove(car);
            return;
        }
        ArrayList<Street> streets = city.getStreets();
        for (int i = 0, streetsSize = streets.size(); i < streetsSize; i++) {
            Street nextStreet = streets.get(i);
            if (nextStreet.getIntersectionSource() == currentIntersectionId
                    && nextStreet.getIntersectionDestination() == destinationIntersectionId) {
                //daca codul intra pe if ul asta, inseamna ca masina tre sa ajunga pe sensul "normal" al urmatoarei strazi
                //pe care tre sa ajunga pt a ajunge la intersectia destinatie
                if (nextStreet.getCars().size() + 1 <= nextStreet.getLength()) {
                    //sectiune in care s ar putea sa apara bug uri cand legam cu grafica. tre vazut cum facem un lock aici
                    car.getShortestPath().remove(0);
                    car.setCurrentPosition(i);
                    car.setDirection(1);
                    nextStreet.getCars().add(new Pair<>(
                            nextStreet.getCars().size(),
                            car
                    ));
                    streetQueue.remove();
                }
                return;
            }
            if (nextStreet.getIntersectionSource() == destinationIntersectionId
                    && nextStreet.getIntersectionDestination() == currentIntersectionId) {
                //daca codul intra pe if ul asta, inseamna ca masina tre sa ajunga pe sensul "reversed" al urmatoarei strazi
                //pe care tre sa ajunga pt a ajunge la intersectia destinatie
                if (nextStreet.getCarsReversed().size() + 1 <= nextStreet.getLength()) {
                    //sectiune in care s ar putea sa apara bug uri cand legam cu grafica. tre vazut cum facem un lock aici
                    car.getShortestPath().remove(0);
                    car.setCurrentPosition(i);
                    car.setDirection(-1);
                    nextStreet.getCarsReversed().add(new Pair<>(
                            nextStreet.getCarsReversed().size(),
                            car
                    ));
                    streetQueue.remove();
                }
                return;
            }
        }

    }

    public CarController() {
    }

    @Override
    public void run() {
        try {
            System.out.println("start thread");
            while (existsACarOnStreets()) {
                for (var st : city.getStreets()) {
                    tryMoveFirstCar(st.getCars(), st.getIntersectionDestination());
                    tryMoveFirstCar(st.getCarsReversed(), st.getIntersectionSource());
                }
                sleep(0);
            }
            System.out.println("all streets are empty now");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

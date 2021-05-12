package AnimationLogic;

import AnimationLogic.Miscellaneous.Utilities;
import CarGenerating.Car;
import CityGenerating.Pair;
import CityGenerating.Street;
import CityGenerating.TrafficLights;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import static AnimationLogic.Miscellaneous.Utilities.existsACarOnStreets;
import static CityGenerating.CityGenerator.city;

public class CarController extends Thread {

    static private boolean running = false;
    static private CarController instance;
    /**
     * @return true daca thread ul merge. false otherwise
     */
    public static boolean isRunning() {
        return running;
    }

    /**
     * vede daca o intersectie este din aia finala. hardcodat
     */
    static boolean isFinalIntersection(int id) {
        return id >= 0 && id <= 7;
    }

    /**
     * vede daca o masina poate fi mutata unde trebuie (are semaforul verde, a ajuns la intersectie, are loc pe strada etc)
     *
     * @param car          pt a vedea daca masina a ajuns "aproape de intersectie" aka sa nu sara de pe o strada pe alta
     * @param to           coada unei strazi unde masina ar trebui / vrea sa ajunga
     * @param capacity     pt a verifica daca masina mai are loc efectiv pe strada pe care ar trebui / vrea sa ajunga
     * @param intersection intersectia prin care trebuie sa treaca masina pt a ajunge pe destinationStreet.
     *                     cred ca avem nevoie de asta pt atunci cand o sa mearga semafoarele. momentan nu e folosit
     * @return true, false daca toate condiile sunt indeplinite
     */
    static public boolean carCanBeMoved(Car car, Queue<Pair<Integer, Car>> to, int capacity, int intersection) {
        if (to.size() + 1 > capacity) //poate trebuie >= ??
            return false; //masina nu mai are loc pe sensul unde vrea sa se duca

        if (CarAnimator.isRunning()) {
            CarAnimator.rwLock.readLock().lock();
            try {
                if (car.getDistance() != 0)
                    return false; //masina inca nu a ajuns la "capatul" strazii (poate de abia a intrat pe strada)
            } finally {
                CarAnimator.rwLock.readLock().unlock();
            }
        }

        if(SemaphoreController.isRunning()) {
            if (car.getDirection() == 1) {
                var currentStreet = city.getStreetByIndex(car.getCurrentPosition());
                var semaphoreId = currentStreet.getTrafficLights();
                return city.getTLightsById(semaphoreId).getStare() != TrafficLights.StareSemafor.Red
                        || city.getTLightsById(semaphoreId).getStare() != TrafficLights.StareSemafor.YellowRed;
            }
            else {
                var currentStreet = city.getStreetByIndex(car.getCurrentPosition());
                var semaphoreId = currentStreet.getTrafficLightsReversed();
                return city.getTLightsById(semaphoreId).getStare() != TrafficLights.StareSemafor.Red
                        || city.getTLightsById(semaphoreId).getStare() != TrafficLights.StareSemafor.YellowRed;
            }
        }

        return true;
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
            var car = streetQueue.peek().getValue();
            if (CarAnimator.isRunning()) {
                CarAnimator.rwLock.readLock().lock();
                try {
                    if (car.getDistance() != 0)
                        return; //masina nu a ajuns la capatul strazii deci n am ce i face acum
                } finally {
                    CarAnimator.rwLock.readLock().unlock();
                }
            }
            var finishedCar = streetQueue.remove();
            finishedCar.getValue().setDistance(-1);
            System.out.println("car[" + finishedCar.getValue().getID() + "] that has arrived at destination[" + currentIntersectionId + "]: " + finishedCar.getValue());
            return;
        }
        var car = streetQueue.peek().getValue();
        var carId = streetQueue.peek().getKey();
        Integer destinationIntersectionId;
        try {
            destinationIntersectionId = car.getShortestPath().get(0);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.err.println("ceva nu e bine, ignoram masina " + car);
//            city.getCars().remove(car);
            return;
        }
        ArrayList<Street> streets = city.getStreets();
        for (int i = 0, streetsSize = streets.size(); i < streetsSize; i++) {
            Street nextStreet = streets.get(i);
            if (nextStreet.getIntersectionSource() == currentIntersectionId
                    && nextStreet.getIntersectionDestination() == destinationIntersectionId) {
                //daca codul intra pe if ul asta, inseamna ca masina tre sa ajunga pe sensul "normal" al urmatoarei strazi
                //pe care tre sa ajunga pt a ajunge la intersectia destinatie
                if (carCanBeMoved(car, nextStreet.getCars(), nextStreet.getLength(), nextStreet.getIntersectionSource())) {
                    //sectiune in care s ar putea sa apara bug uri cand legam cu grafica. tre vazut cum facem un lock aici
                    CarAnimator.rwLock.writeLock().lock();
                    car.getShortestPath().remove(0);
                    car.setCurrentPosition(i);
                    car.setDirection(1);
                    car.setDistance(nextStreet.getLength());
                    nextStreet.getCars().add(new Pair<>(
                            carId,
                            car
                    ));
                    streetQueue.remove();
                    System.out.println("[CarController.java, line 131] car["+car.getID()+"] now on street " + i + "; applying patch");
                    Utilities.correctCurrentPositionOfAllCars(); //patch
                    CarAnimator.rwLock.writeLock().unlock();
                }
                return;
            }
            if (nextStreet.getIntersectionSource() == destinationIntersectionId
                    && nextStreet.getIntersectionDestination() == currentIntersectionId) {
                //daca codul intra pe if ul asta, inseamna ca masina tre sa ajunga pe sensul "reversed" al urmatoarei strazi
                //pe care tre sa ajunga pt a ajunge la intersectia destinatie
                if (carCanBeMoved(car, nextStreet.getCarsReversed(), nextStreet.getLength(), nextStreet.getIntersectionDestination())) {
//                    if (car.getDistance() == 0 && nextStreet.getCarsReversed().size() + 1 <= nextStreet.getLength()) {
                    //sectiune in care s ar putea sa apara bug uri cand legam cu grafica. tre vazut cum facem un lock aici
                    CarAnimator.rwLock.writeLock().lock();
                    car.getShortestPath().remove(0);
                    car.setCurrentPosition(i);
                    car.setDirection(-1);
                    car.setDistance(nextStreet.getLength());
                    nextStreet.getCarsReversed().add(new Pair<>(
                            carId,
                            car
                    ));
                    streetQueue.remove();
                    System.out.println("[CarController.java, line 154] car["+car.getID()+"] now on street " + i+ "; applying patch");
                    //DECOMENTEAZA LINIA DE MAI JOS PT UN FEL DE PATCH
                    Utilities.correctCurrentPositionOfAllCars(); //patch
                    CarAnimator.rwLock.writeLock().unlock();
                }
                return;
            }
        }

    }

    static synchronized
    public CarController getInstance(){
        if(instance == null)
            instance = new CarController();
        return instance;
    }

    private CarController() {
    }

    @Override
    public void run() {
        synchronized (this) {
            //sper ca merge codul asta
            if (running) {
                System.err.println("deja ai pornit un thread care muta masinile pe harta, nu mai poti porni inca unul");
                return;
            } else running = true;
        }
        try {
            System.out.println("start thread");
            while (existsACarOnStreets()) {
                for (var st : city.getStreets()) {
                    tryMoveFirstCar(st.getCars(), st.getIntersectionDestination());
                    tryMoveFirstCar(st.getCarsReversed(), st.getIntersectionSource());
                }
                sleep(0);
//                System.out.println("iter"); //pune sleep 1000 daca vrei sa afisezi iter
            }
            System.out.println("all streets are empty now");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

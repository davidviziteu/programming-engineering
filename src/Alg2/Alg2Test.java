package Alg2;

import ShortestPath.Tuple;

import static org.junit.jupiter.api.Assertions.*;

import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.Street;

import CarGenerating.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Alg2Test {

    @org.junit.jupiter.api.Test
    void main() {
    }

    @org.junit.jupiter.api.Test
    void run() {
    }

    @org.junit.jupiter.api.Test
    void populate() {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        algorithm.populate();

        assertEquals(Alg2.K_POP_SIZE, algorithm.getPopulation().size());
    }

    @org.junit.jupiter.api.Test
    void fitness() {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        // City generates cars randomly automatically; so we need to remove them for testing with a chosen set of cars
        for (Street street : city.getStreets()) {
            int countCars = street.getCars().size();
            while (countCars-- > 0) {
                street.removeCar(1);
            }
            int countCarsReversed = street.getCarsReversed().size();
            while (countCarsReversed-- > 0) {
                street.removeCar(0);
            }
        }

        // Pick a path
        List<Street> chromosome = new ArrayList<>();
        chromosome.add(city.getStreetByName("Strada1"));
        chromosome.add(city.getStreetByName("Strada6"));
        chromosome.add(city.getStreetByName("Strada9"));
        chromosome.add(city.getStreetByName("Strada12"));


        city.getStreetByIndex(1).addCar(new Car(), 0); // should not influence
        city.getStreetByIndex(1).addCar(new Car(), 1);
        city.getStreetByIndex(3).addCar(new Car(), 0);
        city.getStreetByIndex(3).addCar(new Car(), 0);
        city.getStreetByIndex(7).addCar(new Car(), 1);
        city.getStreetByIndex(8).addCar(new Car(), 1);
        city.getStreetByIndex(9).addCar(new Car(), 1);
        city.getStreetByIndex(9).addCar(new Car(), 0);
        city.getStreetByIndex(10).addCar(new Car(), 0);
        city.getStreetByIndex(11).addCar(new Car(), 1); // should not influence


        final double epsilon = 1e-4;

        double realFit = 0.310689310;
        double testFit = algorithm.fitness(chromosome);
        assertTrue(testFit - epsilon < realFit && testFit + epsilon > realFit);
    }

    @org.junit.jupiter.api.Test
    void selection() {
    }

    @org.junit.jupiter.api.Test
    void mutate() {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        // Pick a path
        List<Street> chromosome = new ArrayList<>();
        chromosome.add(city.getStreetByName("Strada1"));
        chromosome.add(city.getStreetByName("Strada6"));
        chromosome.add(city.getStreetByName("Strada9"));
        chromosome.add(city.getStreetByName("Strada12"));

        List<Street> newChromosome = algorithm.mutate(chromosome);
        for (int index = 1; index < newChromosome.size(); ++index) {
            // check if sequence is properly connected
            assertEquals(newChromosome.get(index - 1).getIntersectionDestination(), newChromosome.get(index).getIntersectionSource());
        }
    }

    @org.junit.jupiter.api.Test
    void createRandomPath() {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        List<Street> newChromosomeSection = algorithm.createRandomPath(city.getStreetByIndex(0), city.getStreetByIndex(3), null);
        for (int index = 1; index < newChromosomeSection.size(); ++index) {
            // check if sequence is properly connected
            assertEquals(newChromosomeSection.get(index - 1).getIntersectionDestination(), newChromosomeSection.get(index).getIntersectionSource());
        }
    }

    @org.junit.jupiter.api.Test
    void crossover() {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        // our two chromosomes
        List<Street> chromosome1 = new ArrayList<>();
        List<Street> chromosome2 = new ArrayList<>();

        // generate the streets from our chromosome
        chromosome1.add(city.getStreetByName("Strada1"));
        chromosome1.add(city.getStreetByName("Strada6"));
        chromosome1.add(city.getStreetByName("Strada9"));
        chromosome1.add(city.getStreetByName("Strada12"));

        chromosome2.add(city.getStreetByName("Strada8"));
        chromosome2.add(city.getStreetByName("Strada9"));
        chromosome2.add(city.getStreetByName("Strada7"));
        chromosome2.add(city.getStreetByName("Strada5"));

        // call crossover
        Tuple<List<Street>, List<Street>> chromosomes = algorithm.crossover(chromosome1, chromosome2);

        // generate the real solution in chromosomes
        chromosome1.clear();
        chromosome2.clear();

        chromosome1.add(city.getStreetByName("Strada1"));
        chromosome1.add(city.getStreetByName("Strada6"));
        chromosome1.add(city.getStreetByName("Strada9"));
        chromosome1.add(city.getStreetByName("Strada7"));
        chromosome1.add(city.getStreetByName("Strada5"));

        chromosome2.add(city.getStreetByName("Strada8"));
        chromosome2.add(city.getStreetByName("Strada9"));
        chromosome2.add(city.getStreetByName("Strada12"));

        // compare our solution with crossover's solution
        assert(chromosome1.size() == chromosomes.getFirst().size() &&
                chromosome2.size() == chromosomes.getSecond().size());

        assert(Arrays.deepEquals(new List[]{chromosome1}, new List[]{chromosomes.getFirst()}) &&
                Arrays.deepEquals(new List[]{chromosome2}, new List[]{chromosomes.getSecond()}));
    }

    @org.junit.jupiter.api.Test
    void findCommonGeneOfTwoChromosomes() {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        // our two chromosomes
        List<Street> chromosome1 = new ArrayList<>();
        List<Street> chromosome2 = new ArrayList<>();

        // generate two chromosomes
        chromosome1.add(city.getStreetByName("Strada1"));
        chromosome1.add(city.getStreetByName("Strada6"));
        chromosome1.add(city.getStreetByName("Strada9"));
        chromosome1.add(city.getStreetByName("Strada12"));

        chromosome2.add(city.getStreetByName("Strada8"));
        chromosome2.add(city.getStreetByName("Strada9"));
        chromosome2.add(city.getStreetByName("Strada7"));
        chromosome2.add(city.getStreetByName("Strada5"));

        // generate the correct pair
        Tuple<Integer, Integer> ourPair = new Tuple<Integer, Integer>();
        ourPair.setFirst(2);
        ourPair.setSecond(1);

        // call the findCommonGeneOfTwoChromosomes function
        Tuple<Integer, Integer> pair = algorithm.findCommonGeneOfTwoChromosomes(chromosome1, chromosome2);

        // compare our solution with findCommonGeneOfTwoChromosomes solution
        assert (pair.getFirst().equals(ourPair.getFirst()) && pair.getSecond().equals(ourPair.getSecond()));
    }
}
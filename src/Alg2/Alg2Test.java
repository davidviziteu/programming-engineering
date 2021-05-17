package Alg2;

import CarGenerating.Car;
import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.Street;
import ShortestPath.Tuple;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class Alg2Test {

    private City city;
    private Alg2 algorithm;

    @BeforeEach
    public void setUp() {
        CityGenerator.generateCity();
        city = CityGenerator.city;
        algorithm = new Alg2(city);
    }

    @org.junit.jupiter.api.Test
    void testRun() {

        List<Street> solution = algorithm.run();
        for (int index = 1; index < solution.size(); ++index) {
            // check if sequence is properly connected
            int leftStart = solution.get(index - 1).getIntersectionSource();
            int leftFinish = solution.get(index - 1).getIntersectionDestination();
            int rightStart = solution.get(index).getIntersectionSource();
            int rightFinish = solution.get(index).getIntersectionDestination();
            assert(leftStart == rightStart || leftStart == rightFinish ||
                    leftFinish == rightStart || leftFinish == rightFinish);
        }
    }

    @org.junit.jupiter.api.Test
    void testPopulatePositive() {

        algorithm.populate();

        assertEquals(Alg2.K_POP_SIZE, algorithm.getPopulation().size());
    }

//    @org.junit.jupiter.api.Test
//    void testPopulateNegative() {
//        CityGenerator.generateCity();
//        City city = null;
//        Alg2 algorithm = new Alg2(city);
//
//        algorithm.populate();
//
//        assertEquals(0, algorithm.getPopulation().size()); // TODO: check values @ populate
//    }

    @org.junit.jupiter.api.Test
    void testFitness() {

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


        city.getStreetByName("Strada1").addCar(new Car(), 0); // should not influence
        city.getStreetByName("Strada1").addCar(new Car(), 1);
        city.getStreetByName("Strada3").addCar(new Car(), 0);
        city.getStreetByName("Strada3").addCar(new Car(), 0);
        city.getStreetByName("Strada7").addCar(new Car(), 1);
        city.getStreetByName("Strada8").addCar(new Car(), 1);
        city.getStreetByName("Strada9").addCar(new Car(), 1);
        city.getStreetByName("Strada9").addCar(new Car(), 0);
        city.getStreetByName("Strada10").addCar(new Car(), 0);
        city.getStreetByName("Strada11").addCar(new Car(), 1); // should not influence


        final double epsilon = 1e-4;

        double realFit = 0.310689310;
        double testFit = algorithm.fitness(chromosome);
        assertTrue(testFit - epsilon < realFit && testFit + epsilon > realFit);
    }

    @org.junit.jupiter.api.Test
    void testSelection() {

        algorithm.populate();
        List<List<Street>> firstPopulation = algorithm.getPopulation();

        List<List<Street>> secondPopulation = algorithm.selection();
        Set<List<Street>> set = new HashSet<>();

        for (List<Street> chromosomeAfterSelection : secondPopulation) {
            assert(firstPopulation.contains(chromosomeAfterSelection));
            if(set.size() < 3) {
                set.add(chromosomeAfterSelection);
            }
        }
        assertEquals(2, set.size());
    }

    @org.junit.jupiter.api.Test
    void testMutate() {

        // Pick a path
        List<Street> chromosome = new ArrayList<>();
        chromosome.add(city.getStreetByName("Strada1"));
        chromosome.add(city.getStreetByName("Strada6"));
        chromosome.add(city.getStreetByName("Strada9"));
        chromosome.add(city.getStreetByName("Strada12"));

        List<Street> newChromosome = algorithm.mutate(chromosome);
        for (int index = 1; index < newChromosome.size(); ++index) {
            // check if sequence is properly connected
            int leftStart = newChromosome.get(index - 1).getIntersectionSource();
            int leftFinish = newChromosome.get(index - 1).getIntersectionDestination();
            int rightStart = newChromosome.get(index).getIntersectionSource();
            int rightFinish = newChromosome.get(index).getIntersectionDestination();
            assert(leftStart == rightStart || leftStart == rightFinish ||
                    leftFinish == rightStart || leftFinish == rightFinish);
        }
    }

    @org.junit.jupiter.api.Test
    void testCreateRandomPath() {

        List<Street> newChromosomeSection = algorithm.createRandomPath(
                city.getIntersectionByIndex(city.getStreetByName("Strada1").getIntersectionDestination()),
                city.getIntersectionByIndex(city.getStreetByName("Strada12").getIntersectionSource()),
                null);
        if (newChromosomeSection != null) {
            for (int index = 1; index < newChromosomeSection.size(); ++index) {
                // check if sequence is properly connected
                int leftStart = newChromosomeSection.get(index - 1).getIntersectionSource();
                int leftFinish = newChromosomeSection.get(index - 1).getIntersectionDestination();
                int rightStart = newChromosomeSection.get(index).getIntersectionSource();
                int rightFinish = newChromosomeSection.get(index).getIntersectionDestination();
                assert(leftStart == rightStart || leftStart == rightFinish ||
                        leftFinish == rightStart || leftFinish == rightFinish);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void testCrossover() {

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
    void testCrossoverNegative() {

        // our two chromosomes
        List<Street> chromosome1 = new ArrayList<>();
        List<Street> chromosome2 = new ArrayList<>();

        chromosome1.add(city.getStreetByName("Strada5"));
        chromosome1.add(city.getStreetByName("Strada7"));
        chromosome1.add(city.getStreetByName("Strada12"));

        chromosome2.add(city.getStreetByName("Strada1"));
        chromosome2.add(city.getStreetByName("Strada6"));
        chromosome2.add(city.getStreetByName("Strada9"));
        chromosome2.add(city.getStreetByName("Strada10"));

        Tuple<List<Street>, List<Street>> chromosomes = algorithm.crossover(chromosome1, chromosome2);

        assert(chromosomes.getFirst().equals(chromosome1) && chromosomes.getSecond().equals(chromosome2));
    }

    @org.junit.jupiter.api.Test
    void testFindCommonGeneOfTwoChromosomes() {

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

    @org.junit.jupiter.api.Test
    void testFindCommonGeneOfTwoChromosomesNegative() {

        // our two chromosomes
        List<Street> chromosome1 = new ArrayList<>();
        List<Street> chromosome2 = new ArrayList<>();

        chromosome1.add(city.getStreetByName("Strada5"));
        chromosome1.add(city.getStreetByName("Strada7"));
        chromosome1.add(city.getStreetByName("Strada12"));

        chromosome2.add(city.getStreetByName("Strada1"));
        chromosome2.add(city.getStreetByName("Strada6"));
        chromosome2.add(city.getStreetByName("Strada9"));
        chromosome2.add(city.getStreetByName("Strada10"));

        Tuple<Integer, Integer> pair = algorithm.findCommonGeneOfTwoChromosomes(chromosome1, chromosome2);

        assert(pair == null);
    }

    @org.junit.jupiter.api.Test
    void testGetCommonStreetNegative() {
        assertNull(algorithm.getCommonStreet(0, 1));
    }
}
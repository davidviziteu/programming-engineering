package Alg2;

import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.Intersection;
import CityGenerating.Street;
import ShortestPath.*;

import java.util.*;


public class Alg2 {
    // TODO: what is car size
    private static final int K_CAR_SIZE = 10;
    private static final int K_POP_SIZE = 20;
    private static final int K_GENERATION_NUMBER = 100;
    private static final double K_MUTATION_CHANCE = 0.01;

    private final City city;
    private List<List<Street>> population;

    public Alg2(City city) {
        this.city = city;
    }

    public static void main(String[] args) {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        for (int i = 0; i < K_POP_SIZE; i++) {
            List<Integer> ids = ShortestPath2.compute(algorithm.city.getStreets(),
                    0, 3, algorithm.city.getStreets().size()).getSecond();

            List<Street> chromosome = new ArrayList<>();
            for (Integer id : ids) {
                chromosome.add(city.getStreetByIndex(id));
            }

            algorithm.population.add(chromosome);
        }

        int count = 0;
        while (count < K_GENERATION_NUMBER) {
            algorithm.population = algorithm.selection();

            for (int i = 0; i < algorithm.population.size(); i++) {
                Random random = new Random(System.currentTimeMillis());

                double randNum = random.nextDouble();

                if (randNum < K_MUTATION_CHANCE) {
                    algorithm.population.set(i, algorithm.mutate(algorithm.population.get(i)));
                }
            }

            for (int i = 0; i < algorithm.population.size(); i = i + 2) {
                Tuple<List<Street>, List<Street>> newPair = algorithm.crossover(algorithm.population.get(i),
                        algorithm.population.get(i + 1));
                algorithm.population.set(i, newPair.getFirst());
                algorithm.population.set(i + 1, newPair.getSecond());
            }
            count++;
        }
    }

    public double fitness(List<Street> chromosome, City city) {
        List<Street> streets = city.getStreets();
        int countTrafficLights = streets.size();
        int[] capacity = new int[countTrafficLights];
        int[] load = new int[countTrafficLights];

//        pentru fiecare intersectie:
//        calculeaza gradul de incarcare
//        (grad incarcare: suma numarului de masini care intra/asteapta la intrarea in intersectie
//                => procentaj din capacitatea maxima al strazilor)
        for (Street street : streets) {
            // TODO: add getCapacity, return street capacity considering "average" car size
            capacity[street.getIntersectionDestination()] += street.getLength() / K_CAR_SIZE;
//            capacity[street.getIntersectionSource()] += street.getLength() / K_CAR_SIZE;

            load[street.getIntersectionDestination()] += street.getCars().size();
//            load[street.getIntersectionSource()] += street.getCars().size();
        }

        float[] loadDensity = new float[countTrafficLights];
        for (int i = 0; i < countTrafficLights; ++i) {
            loadDensity[i] = ((float) load[i]) / ((float) capacity[i]);
        }

//        fitness:
//        pentru o secventa de strazi, vedem cu cat ar creste masina user "incarcarea" intersectiilor
//        adica cat creste prin procentaj
//
//        cu cat aceasta crestere de incarcare este mai MARE, cu atat mai bine!
//                (daca creste mult, inseamna ca erau putin incarcate inainte)
//
//        mergem pe presupunerea ca intersectiile incarcate se mentin incarcate (si deci, incete)
//        iar strazile putin incarcate, raman putin incarcate (si deci, mai rapide)
        float fitness = 0.0f;
        for (Street gene : chromosome) {
            int id = gene.getIntersectionDestination();
            fitness += (((float) (load[id] + 1)) / ((float) (capacity[id]))) - loadDensity[id];
        }
        return fitness;
    }

    public List<List<Street>> selection() {
        // k = 10; j = 5;
        int maxNumberOfSelectionChromosomes = 9;
        int numberOfBestChromosomes = 4;
        Set<Integer> indexSet = new HashSet<>();

        for (int k = 0; k < maxNumberOfSelectionChromosomes; k++) {
            Random random = new Random();
            indexSet.add(random.nextInt(population.size()));
        }

        List<Integer> indexList = new ArrayList<>(indexSet);

        indexList.sort(Comparator.comparingDouble(index -> fitness(population.get(index), city)));

        List<List<Street>> secondPopulation = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = numberOfBestChromosomes; j >= 0; j--) {
                secondPopulation.add(population.get(j));
            }
        }

        return secondPopulation;
    }

    public List<Street> mutate(List<Street> chromosome) {
        Random random = new Random(System.currentTimeMillis());

        int firstIndex, secondIndex;
        do {
            firstIndex = random.nextInt(chromosome.size());
            secondIndex = random.nextInt(chromosome.size());
        } while (firstIndex + 1 >= secondIndex); // ignore adjacent streets

        List<Street> newChromosome = new ArrayList<>();

        List<Street> visitedStreets = new ArrayList<>();
        visitedStreets.addAll(chromosome.subList(0, firstIndex));
        visitedStreets.addAll(chromosome.subList(secondIndex, chromosome.size() - 1));

        newChromosome.addAll(chromosome.subList(0, firstIndex));

        newChromosome.addAll(createRandomPath(
                chromosome.get(firstIndex),
                chromosome.get(secondIndex),
                visitedStreets
        ));

        newChromosome.addAll(chromosome.subList(secondIndex, chromosome.size() - 1));

        return newChromosome;
    }

    public List<Street> createRandomPath(Street street1, Street street2, List<Street> visitedStreets) {
        Intersection start = city.getIntersectionByIndex(street1.getIntersectionDestination());
        Intersection finish = city.getIntersectionByIndex(street2.getIntersectionSource());


        Stack<Street> stackStreets = new Stack<>();
        Stack<Intersection> stackIntersections = new Stack<>();
        Stack<Integer> stackIndex = new Stack<>();
        stackIntersections.push(start);
        stackIndex.push(0);

        while (stackIntersections.size() > 0) {
            Intersection intersection = stackIntersections.peek();
            int index = stackIndex.peek();

            List<Integer> idStreets = intersection.getStreets();
            for (; index < idStreets.size(); ++index) {
                Street street = city.getStreetByIndex(idStreets.get(index));

                if (!visitedStreets.contains(street)) {
                    stackIndex.push(0);
                    stackIntersections.push(city.getIntersectionByIndex(street.getIntersectionDestination()));
                    stackStreets.push(street);

                    if (city.getIntersectionByIndex(street.getIntersectionDestination()) == finish) {
                        return (List<Street>) new ArrayList(stackStreets);
                    }
                    break;
                }
            }

            if (index >= idStreets.size()) {
                stackIntersections.pop();
                stackIndex.pop();
            }
        }

        return null;
    }

    public Tuple<List<Street>, List<Street>> crossover(List<Street> parent1, List<Street> parent2) {
        List<Street> chromosome1 = new ArrayList<>();
        List<Street> chromosome2 = new ArrayList<>();

        Tuple<Integer, Integer> pair;

        pair = findCommonNodeOfTwoChromosomes(parent1, parent2);

        // add left side of chromosome1 to chromosome1
        for (int k = 0; k < pair.getFirst(); k++) {
            chromosome1.add(parent1.get(k));
        }

        // add right side of chromosome2 to chromosome1
        for (int k = pair.getSecond() + 1; k < parent2.size(); k++) {
            chromosome1.add(parent2.get(k));
        }

        // add left side of chromosome2 to chromosome2
        for (int k = 0; k < pair.getSecond(); k++) {
            chromosome2.add(parent2.get(k));
        }

        // add right side of chromosome1 to chromosome2
        for (int k = pair.getFirst() + 1; k < parent1.size(); k++) {
            chromosome2.add(parent1.get(k));
        }

        return new Tuple<>(chromosome1, chromosome2);
    }

    public Tuple<Integer, Integer> findCommonNodeOfTwoChromosomes(List<Street> chromosome1, List<Street> chromosome2) {
        // random common vertex of 2 chromosomes
        List<Street> commonStreets = new ArrayList<>();
        Tuple<Integer, Integer> pair = new Tuple<Integer, Integer>();
        // get the common streets of our 2 chromosomes
        for (Street street1 : chromosome1) {
            for (Street street2 : chromosome2) {
                if (street1 == street2) {
                    commonStreets.add(street1);
                }
            }
        }

        // get random street from common streets
        Random rand = new Random(System.currentTimeMillis());
        int n = rand.nextInt(commonStreets.size());

        // set pair of index from our chromosomes
        for (int i = 0; i < chromosome1.size(); i++) {
            if (chromosome1.get(i) == commonStreets.get(n)) {
                pair.setFirst(i);
            }
        }
        for (int i = 0; i < chromosome2.size(); i++) {
            if (chromosome2.get(i) == commonStreets.get(n)) {
                pair.setSecond(i);
            }
        }
        return pair;
    }

    // 5 common node
    // 2 position of chromosome i
    // 4 position of chromosome ++i

    // chromosome = [1,2,3,5,4,8,6,7]

}

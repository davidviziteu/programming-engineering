package Alg2;

import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.Intersection;
import CityGenerating.Street;
import ShortestPath.ShortestPath2;
import ShortestPath.Tuple;

import java.util.*;


public class Alg2 {
    // TODO: what is car size
    public static final int K_CAR_SIZE = 1;
    public static final int K_POP_SIZE = 20;
    public static final int K_GENERATION_NUMBER = 100;
    public static final double K_MUTATION_CHANCE = 0.01;

    private final City city;
    private List<List<Street>> population;

    public Alg2(City city) {
        this.city = city;
    }

    public static void main(String[] args) {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        algorithm.run();
    }

    public void run() {
        populate();
        // selection
        List<Street> bestChromosome = population.get(0);
        double bestFitness = fitness(bestChromosome);
        for (List<Street> chromosome : population) {
            double currentFitness = fitness(chromosome);
            if (currentFitness > bestFitness) {
                bestChromosome = chromosome;
                bestFitness = currentFitness;
            }
        }

        int count = 0;
        while (count < K_GENERATION_NUMBER) {
            population = selection();

            // mutation
            for (int i = 0; i < population.size(); i++) {
                Random random = new Random(System.currentTimeMillis());

                double randNum = random.nextDouble();

//                if (randNum < K_MUTATION_CHANCE) {
                   population.set(i, mutate(population.get(i)));
//                }
            }
            // cross-over
            for (int i = 0; i < population.size() - 1; i = i + 2) {
                Tuple<List<Street>, List<Street>> newPair = crossover(population.get(i),
                        population.get(i + 1));
                population.set(i, newPair.getFirst());
                population.set(i + 1, newPair.getSecond());
            }

            for (List<Street> chromosome : population) {
                double currentFitness = fitness(chromosome);
                if (currentFitness > bestFitness) {
                    bestChromosome = chromosome;
                    bestFitness = currentFitness;
                }
            }
            count++;
        }

        System.out.println("The solution is: ");
        for (Street street : bestChromosome) {
            System.out.println(street);
        }
    }

    public void populate() {
        population = new ArrayList<>();
        for (int i = 0; i < K_POP_SIZE; i++) {
            List<Street> chromosome = null;

            if (i % 2 == 0) {
                if (city != null) {
                    chromosome = new ArrayList<>();
                    List<Integer> ids = ShortestPath2.compute(city.getStreets(),
                            0, 3, city.getStreets().size()).getSecond();

                    for (Integer id : ids) {
                        chromosome.add(city.getStreetByIndex(id));
                    }
                }
            } else {
                if (city != null) {
                    chromosome = createRandomPath(city.getIntersectionByIndex(0), city.getIntersectionByIndex(3), null);
                }
            }

            if (chromosome != null) {
                population.add(chromosome);
            }
        }
    }

    public List<List<Street>> getPopulation() {
        return population;
    }

    public double fitness(List<Street> chromosome) {
        // TODO: optimise; don't compute these arrays every time
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
            capacity[street.getIntersectionSource()] += street.getLength() / K_CAR_SIZE;

            load[street.getIntersectionDestination()] += street.getCars().size();
            load[street.getIntersectionSource()] += street.getCarsReversed().size();
        }

        double[] loadDensity = new double[countTrafficLights];
        for (int i = 0; i < countTrafficLights; ++i) {
            loadDensity[i] = ((double) load[i]) / ((double) capacity[i]);
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
        double fitness = 0.0;
//      ignore last street (we check intersection at the end of the street, so map exit intersection should be ignored)
        for (int index = 0; index < chromosome.size() - 1; ++index) {
            Street gene = chromosome.get(index);
            int id = gene.getIntersectionDestination();
            fitness += (((double) (load[id] + 1)) / ((double) (capacity[id]))) - loadDensity[id];
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

        indexList.sort(Comparator.comparingDouble(index -> fitness(population.get(index))));

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

        List<Street> visitedStreets = new ArrayList<>();
        visitedStreets.addAll(chromosome.subList(0, firstIndex));
        visitedStreets.addAll(chromosome.subList(secondIndex, chromosome.size() - 1));

        List<Street> newChromosome = new ArrayList<>(chromosome.subList(0, firstIndex));


        // TODO: order is messed up; streets have source-destination order fixed; "which way is forward?"
        // NOTE: check "connection point" of genes (10 - 10, 01 - 10, 01 - 01, 10 - 01;
        // where 1 is connection point and 0 is another intersection)
        List<Street> newPath = createRandomPath(
                city.getIntersectionByIndex(chromosome.get(firstIndex).getIntersectionDestination()),
                city.getIntersectionByIndex(chromosome.get(secondIndex).getIntersectionSource()),
                visitedStreets
        );

        if (newPath != null) {
            newChromosome.addAll(newPath);
        }

        newChromosome.addAll(chromosome.subList(secondIndex, chromosome.size() - 1));

        return newChromosome;
    }

    public List<Street> createRandomPath(Intersection start, Intersection finish, List<Street> visitedStreets) {
//        Intersection start = city.getIntersectionByIndex(street1.getIntersectionDestination());
//        Intersection finish = city.getIntersectionByIndex(street2.getIntersectionSource());

        if (visitedStreets == null) {
            visitedStreets = new ArrayList<>();
        }

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

                int nextIntersection = street.getIntersectionDestination();
                if (city.getIntersectionByIndex(nextIntersection).equals(intersection)) {
                    nextIntersection = street.getIntersectionSource();
                }

                if (!visitedStreets.contains(street)) {
                    stackIndex.pop();
                    stackIndex.push(index+1);

                    visitedStreets.add(street);

                    stackIndex.push(0);
                    stackIntersections.push(city.getIntersectionByIndex(nextIntersection));
                    stackStreets.push(street);

                    if (city.getIntersectionByIndex(street.getIntersectionDestination()) == finish) {
                        return (List<Street>) new ArrayList<>(stackStreets);
                    }
                    break;
                }
            }

            if (index >= idStreets.size()) {
                if (visitedStreets.size() > 0) {
                    visitedStreets.remove(visitedStreets.size()-1);
                }
                stackIntersections.pop();
                stackIndex.pop();
                if (stackStreets.size() > 0) {
                    stackStreets.pop();
                }
            }
        }

        return null;
    }

    public Tuple<List<Street>, List<Street>> crossover(List<Street> parent1, List<Street> parent2) {
        List<Street> chromosome1 = new ArrayList<>();
        List<Street> chromosome2 = new ArrayList<>();

        Tuple<Integer, Integer> pair;

        pair = findCommonGeneOfTwoChromosomes(parent1, parent2);

        if(pair == null) {
            return null;
        }

        // add left side of chromosome1 to chromosome1
        for (int k = 0; k <= pair.getFirst(); k++) {
            chromosome1.add(parent1.get(k));
        }

        // add right side of chromosome2 to chromosome1
        for (int k = pair.getSecond() + 1; k < parent2.size(); k++) {
            chromosome1.add(parent2.get(k));
        }

        // add left side of chromosome2 to chromosome2
        for (int k = 0; k <= pair.getSecond(); k++) {
            chromosome2.add(parent2.get(k));
        }

        // add right side of chromosome1 to chromosome2
        for (int k = pair.getFirst() + 1; k < parent1.size(); k++) {
            chromosome2.add(parent1.get(k));
        }

        return new Tuple<>(chromosome1, chromosome2);
    }

    public Tuple<Integer, Integer> findCommonGeneOfTwoChromosomes(List<Street> chromosome1, List<Street> chromosome2) {
        // random common vertex of 2 chromosomes
        List<Street> commonStreets = new ArrayList<>();
        Tuple<Integer, Integer> pair = new Tuple<Integer, Integer>();

        // get the common streets of our 2 chromosomes
        for (Street street1 : chromosome1) {
            for (Street street2 : chromosome2) {
                if (street1.getIntersectionDestination().equals(street2.getIntersectionDestination()) &&
                        street1.getIntersectionSource().equals(street2.getIntersectionSource())) {
                    commonStreets.add(street1);
                }
            }
        }

        // get random street from common streets
        Random rand = new Random(System.currentTimeMillis());
        if (commonStreets.size() == 0) {
            return null;
        }
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

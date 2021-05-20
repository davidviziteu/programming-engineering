package Alg2;

import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.Intersection;
import CityGenerating.Street;
import ShortestPath.ShortestPath2;
import ShortestPath.Tuple;

import java.util.*;


public class Alg2 {
    public static final int K_POP_SIZE = 20;
    public static final int K_GENERATION_NUMBER = 100;
    public static final double K_MUTATION_CHANCE = 0.01;
    public static final int K_ITERATIONS = 100;

    public static final int K_START_INTERSECTION = 11; // not final
    public static final int K_FINAL_INTERSECTION = 8; // not final

    private final City city;
    private List<List<Street>> population;

    public Alg2(City city) {
        this.city = city;
    }

    public static void main(String[] args) {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);
        algorithm.run(K_START_INTERSECTION ,K_FINAL_INTERSECTION);
    }

    public List<Street> run(int Source, int Destination) {
        int countIteration = 0;
        List<Street> bestChromosome = new ArrayList<>();
        while (countIteration < K_ITERATIONS) {
            populate(Source, Destination);
            // selection
            bestChromosome = population.get(0);
            double bestFitness = fitness(bestChromosome);
            for (List<Street> chromosome : population) {
                double currentFitness = fitness(chromosome);
                if (currentFitness > bestFitness) {
                    bestChromosome = chromosome;
                    bestFitness = currentFitness;
                }
            }

            Random random = new Random(System.currentTimeMillis());
            int countGeneration = 0;
            while (countGeneration < K_GENERATION_NUMBER) {
                population = selection();

                // mutation
//                System.out.println("[Alg2] mutation: " + count.toString());
                for (int i = 0; i < population.size(); i++) {
                    double randNum = random.nextDouble();

//                System.out.println("[Alg2]before " + population.get(i));
//                System.out.println("[Alg2] " + i);
                    if (randNum < K_MUTATION_CHANCE) {
                        population.set(i, mutate(population.get(i)));
                    }
//                System.out.println("[Alg2]after " + population.get(i));
                }
                // cross-over
//                System.out.println("[Alg2] Cross-over");
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

                countGeneration++;

                //break;
            }
            countIteration++;
        }

        System.out.println("[Alg2] The solution is for intersection " + K_START_INTERSECTION + " to intersection " + K_FINAL_INTERSECTION);
        for (Street street : bestChromosome) {
            System.out.println(street);
        }

        return bestChromosome;
    }

    public void populate(int idStart, int idFinish) {

        population = new ArrayList<>();
        for (int i = 0; i < K_POP_SIZE; i++) {
            List<Street> chromosome = null;

            if (i % 2 == 0) {
                if (city != null) {
                    chromosome = new ArrayList<>();
                    List<Integer> ids = ShortestPath2.compute(
                            city.getStreets(),
                            idStart,
                            idFinish,
                            city.getNrOfIntersections()).getSecond();

                    chromosome.add(getCommonStreet(idStart, ids.get(0)));

                    for (int index = 0; index < ids.size() - 1; ++index) {
                        chromosome.add(getCommonStreet(ids.get(index), ids.get(index + 1)));
                    }
                }
            } else {
                if (city != null) {
                    chromosome = createRandomPath(idStart, idFinish, null);
                }
            }

            if (chromosome != null) {
                population.add(chromosome);

//                if (testSequenceInvalid(chromosome)) {
//                    System.out.println("[populate]: " + chromosome);
//                    System.exit(-1);
//                }
            } else {
                --i;
            }
        }
    }

    public List<List<Street>> getPopulation() {
        return population;
    }

    public double computeTime(List<Street> chromosome) {
        // TODO: compute an optimum formula (distance & speed & time of
        //  semaphores of the intersections through which the car goes through)
        double time = (chromosome.size() - 1) * 5;
        int distance = 0;

        for (Street street : chromosome) {
            distance += street.getLength();
        }
        // coefficients:
        // distance / maxDistance %
        // intersectionCount / maxIntersectionCount %
        // distance + intersectionCount * 5
        // intersectionLoadDensity %
        return time + distance;
    }

    public double computeLoadDensity(List<Street> chromosome) {
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
            capacity[street.getIntersectionDestination()] += street.getLength();
            capacity[street.getIntersectionSource()] += street.getLength();

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
        double density = 0.0;
//      ignore last street (we check intersection at the end of the street, so map exit intersection should be ignored)
        for (int index = 0; index < chromosome.size() - 1; ++index) {
            Integer id = getCommonIntersection(chromosome.get(index), chromosome.get(index + 1));
            if (id == null) {
                System.out.println("God is dead");
            } else {
                density += (((double) (load[id] + 1)) / ((double) (capacity[id]))) - loadDensity[id];
            }
        }
        return density;
    }

    public double fitness(List<Street> chromosome) {
        double loadDensity = computeLoadDensity(chromosome);
        double time = computeTime(chromosome);
//        System.out.println("[Alg2] " + loadDensity + " " + time);
        // TODO: adjust coefficients
        return 1.0 / loadDensity + 22.0 / time;
    }

    public List<List<Street>> selection() {
        // k = 10; j = 5;
        int maxNumberOfSelectionChromosomes = 9;
        int numberOfBestChromosomes = 4;
        Set<Integer> indexSet = new HashSet<>();

        Random random = new Random(System.currentTimeMillis());
        for (int k = 0; k < maxNumberOfSelectionChromosomes; k++) {
            indexSet.add(random.nextInt(population.size()));
        }

        List<Integer> indexList = new ArrayList<>(indexSet);

        indexList.sort(Comparator.comparingDouble(index -> fitness(population.get(index))));

        List<List<Street>> secondPopulation = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = numberOfBestChromosomes - 1; j >= 0; j--) {
                secondPopulation.add(population.get(j));
            }
        }

        return secondPopulation;
    }

    public List<Street> mutate(List<Street> chromosome) {
        // TODO: change to 1, select 1st street source and 2nd street destination
        // this means the same street can be chosen (it's source and destination)
        // and it can be replaced by a whole path
        if (chromosome.size() <= 2) {
            return chromosome;
        }

        Random random = new Random(System.currentTimeMillis());

        int firstIndex, secondIndex;
        do {
            firstIndex = random.nextInt(chromosome.size());
            secondIndex = random.nextInt(chromosome.size());
        } while (firstIndex + 1 >= secondIndex); // ignore adjacent streets

        List<Street> visitedStreets = new ArrayList<>();
        visitedStreets.addAll(chromosome.subList(0, firstIndex + 1));
        visitedStreets.addAll(chromosome.subList(secondIndex, chromosome.size()));

        List<Street> newChromosome = new ArrayList<>(chromosome.subList(0, firstIndex + 1));

        Integer intersectionStart;
        if (city.getIntersectionByIndex(chromosome.get(firstIndex).getIntersectionDestination()).isCanPark() ||
                city.getIntersectionByIndex(chromosome.get(firstIndex).getIntersectionSource()).isCanPark()) {
            if (city.getIntersectionByIndex(chromosome.get(firstIndex).getIntersectionDestination()).isCanPark()) {
                intersectionStart = chromosome.get(firstIndex).getIntersectionSource();
            } else {
                intersectionStart = chromosome.get(firstIndex).getIntersectionDestination();
            }
        } else {
            // assume sequence is correct
            intersectionStart = getCommonIntersection(chromosome.get(firstIndex), chromosome.get(firstIndex + 1));
        }

        Integer intersectionEnd;
        if (city.getIntersectionByIndex(chromosome.get(secondIndex).getIntersectionDestination()).isCanPark() ||
                city.getIntersectionByIndex(chromosome.get(secondIndex).getIntersectionSource()).isCanPark()) {
            if (city.getIntersectionByIndex(chromosome.get(secondIndex).getIntersectionDestination()).isCanPark()) {
                intersectionEnd = chromosome.get(secondIndex).getIntersectionSource();
            } else {
                intersectionEnd = chromosome.get(secondIndex).getIntersectionDestination();
            }
        } else {
            // assume sequence is correct
            intersectionEnd = getCommonIntersection(chromosome.get(secondIndex), chromosome.get(secondIndex - 1));
        }

        List<Street> newPath = null;
        if (intersectionStart != null && intersectionEnd != null) {
            // NOTE: check "connection point" of genes (10 - 10, 01 - 10, 01 - 01, 10 - 01;
            // where 1 is connection point and 0 is another intersection)
            newPath = createRandomPath(
                    intersectionStart,
                    intersectionEnd,
                    visitedStreets
            );
        }

        if (newPath != null) {
            newChromosome.addAll(newPath);
        } else {
            newChromosome.addAll(chromosome.subList(firstIndex + 1, chromosome.size()));
        }

        newChromosome.addAll(chromosome.subList(secondIndex, chromosome.size()));


//        if (testSequenceInvalid(newChromosome)) {
//            System.out.println("[mutate]: (before) " + chromosome);
//
//            System.out.println("(first)  " + chromosome.subList(0, firstIndex + 1));
//            System.out.println("(middle) " + newPath);
//            System.out.println("(last)   " + chromosome.subList(secondIndex, chromosome.size()));
//
//            System.out.println("[mutate]: (after) " + newChromosome);
//            System.exit(-1);
//        }

        return newChromosome;
    }

    public List<Street> createRandomPath(int idIntersectionStart, int idIntersectionFinish, List<Street> visitedStreets) {
        if (visitedStreets == null) {
            visitedStreets = new ArrayList<>();
        }

        Stack<Street> stackStreets = new Stack<>();
        Stack<Integer> stackIntersections = new Stack<>();
        Stack<Integer> stackIndex = new Stack<>();
        Stack<List<Integer>> stackShuffle = new Stack<>();

        stackIntersections.push(idIntersectionStart);
        stackIndex.push(0);

        Random random = new Random(System.currentTimeMillis());
        while (stackIntersections.size() > 0) {
            Intersection intersection = city.getIntersectionByIndex(stackIntersections.peek());
            int index = stackIndex.peek();

            List<Integer> idStreets;
            if (index == 0) {
                idStreets = intersection.getStreets();
                java.util.Collections.shuffle(idStreets, random);
                stackShuffle.push(idStreets);
            } else {
                idStreets = stackShuffle.peek();
            }

            for (; index < idStreets.size(); ++index) {
                Street street = city.getStreetByIndex(idStreets.get(index));

                int nextIntersection;
                if (!street.getIntersectionDestination().equals(stackIntersections.peek())) {
                    nextIntersection = street.getIntersectionDestination();
                } else {
                    nextIntersection = street.getIntersectionSource();
                }

                if (!visitedStreets.contains(street)) {
                    stackIndex.pop();
                    stackIndex.push(index + 1);

                    visitedStreets.add(street);

                    stackIndex.push(0);
                    stackIntersections.push(nextIntersection);
                    stackStreets.push(street);

                    if (nextIntersection == idIntersectionFinish) {
                        return new ArrayList<>(stackStreets);
                    }
                    break;
                }
            }

            if (index >= idStreets.size()) {
                if (visitedStreets.size() > 0) {
                    visitedStreets.remove(visitedStreets.size() - 1);
                }
                stackIntersections.pop();
                stackIndex.pop();
                if (stackStreets.size() > 0) {
                    stackStreets.pop();
                }
                if (stackShuffle.size() > 0) {
                    stackShuffle.pop();
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


        if (pair == null) {
            return new Tuple<>(parent1, parent2);
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


//        if (testSequenceInvalid(chromosome1)) {
//            System.out.println("[populate]: (1) " + chromosome1);
//            System.exit(-1);
//        }


//        if (testSequenceInvalid(chromosome2)) {
//            System.out.println("[crossover]: (2) " + chromosome2);
//            System.exit(-1);
//        }

        return new Tuple<>(chromosome1, chromosome2);
    }

    public Tuple<Integer, Integer> findCommonGeneOfTwoChromosomes(List<Street> chromosome1, List<Street> chromosome2) {
        // random common vertex of 2 chromosomes
        List<Street> commonStreets = new ArrayList<>();
        Tuple<Integer, Integer> pair = new Tuple<Integer, Integer>();

        // get the common streets of our 2 chromosomes
        for (int indexStreet1 = 1; indexStreet1 < chromosome1.size() - 1; ++indexStreet1) {
            for (int indexStreet2 = 1; indexStreet2 < chromosome2.size() - 1; ++indexStreet2) {
                if (chromosome1.get(indexStreet1).equals(chromosome2.get(indexStreet2))) {
                    int nextIntersection1 = getCommonIntersection(chromosome1.get(indexStreet1), chromosome1.get(indexStreet1 + 1));
                    int nextIntersection2 = getCommonIntersection(chromosome2.get(indexStreet2), chromosome2.get(indexStreet2 + 1));

                    if (nextIntersection1 == nextIntersection2) {
                        commonStreets.add(chromosome1.get(indexStreet1));
                    }
                }
            }
        }

        if (commonStreets.size() == 0) {
            return null;
        }

        // get random street from common streets
        Random rand = new Random(System.currentTimeMillis());
        int n = rand.nextInt(commonStreets.size());

        // set pair of index from our chromosomes
        for (int i = 1; i < chromosome1.size() - 1; i++) {
            if (chromosome1.get(i) == commonStreets.get(n)) {
                pair.setFirst(i);
            }
        }

        for (int i = 1; i < chromosome2.size() - 1; i++) {
            if (chromosome2.get(i) == commonStreets.get(n)) {
                pair.setSecond(i);
            }
        }
        return pair;
    }

    public Street getCommonStreet(Integer idIntersection1, Integer idIntersection2) {
        for (Integer street1 : city.getIntersectionByIndex(idIntersection1).getStreets()) {
            for (Integer street2 : city.getIntersectionByIndex(idIntersection2).getStreets()) {
                if (street1.equals(street2)) {
                    return city.getStreetByIndex(street1);
                }
            }
        }

        return null;
    }

    public Integer getCommonIntersection(Street street1, Street street2) {
        if (street1.getIntersectionDestination().equals(street2.getIntersectionDestination())) {
            return street1.getIntersectionDestination();
        } else if (street1.getIntersectionDestination().equals(street2.getIntersectionSource())) {
            return street1.getIntersectionDestination();
        } else if (street1.getIntersectionSource().equals(street2.getIntersectionDestination())) {
            return street1.getIntersectionSource();
        } else if (street1.getIntersectionSource().equals(street2.getIntersectionSource())) {
            return street1.getIntersectionSource();
        }

        return null;
    }

//    private boolean testSequenceInvalid(List<Street> sequence) {
//        for (int index = 0; index < sequence.size() - 1; ++index) {
//            // check if sequence is properly connected
//            Integer intersection = getCommonIntersection(sequence.get(index), sequence.get(index + 1));
//            if (intersection == null) {
//                return true;
//            }
//        }
//        return false;
//    }

    // 5 common node
    // 2 position of chromosome i
    // 4 position of chromosome ++i

    // chromosome = [1,2,3,5,4,8,6,7]
}

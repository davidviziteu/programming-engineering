package TestGrafica;

import Alg2.Alg2;
import AnimationLogic.CarAnimator;
import AnimationLogic.CarController;
import AnimationLogic.MasterThread;
import AnimationLogic.Miscellaneous.Utilities;
import AnimationLogic.SemaphoreController;
import CityGenerating.CityGenerator;
import CityGenerating.Street;
import UI.UIController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class United {


    public static void main(String[] args) {
        if(args.length == 0)
            CityGenerator.generateCity("scăzut", 5, 4, 1);
        else
            CityGenerator.generateCity(args[1], Integer.parseInt(args[2].replace("Strada", ""))-1, Integer.parseInt(args[3]), Integer.parseInt(args[4]));

        Alg2 geneticAlgorithm = new Alg2(CityGenerator.city);
        List<Street> path = geneticAlgorithm.run(CityGenerator.city.getCars().get(0).getCurrentPosition(),
                CityGenerator.city.getCars().get(0).getFinalPosition());
        List<Integer> intersections = new ArrayList<>();
        for (int i = 1; i < path.size() - 1; i++) {
            intersections.add(geneticAlgorithm.getCommonIntersection(path.get(i), path.get(i + 1)));
        }
        CityGenerator.city.getCars().get(0).setShortestPath(intersections);

        try {
            if (args.length > 0 && args[0].equals("full app")) {
                var secondWindow = new MainAndreeaCi();
                secondWindow.showWindow();
            }
            else
                MainAndreeaCi.incepe();
            // NU DA JOIN LA THREADS, SE BLOCHEAZA cand vreau sa fac a2a fereastra
            // LASA GARBAGE COLLECTOR UL SA SE OCUPE DE ELE
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//https://www.programmersought.com/article/43064649273/
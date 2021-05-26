package TestGrafica;

import CarGenerating.Car;
import CityGenerating.CityGenerator;
import CityGenerating.Intersection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * genereaza harta (strazile si iarba din jurul lor)
 */
public class Graphics2 {

    public BorderPane window = new BorderPane();

    //We add what we need for the user inputs
    HBox user;
    Button submit;
    Label trafficLabel;
    ObservableList<String> trafficTypes;
    Spinner<String> trafficSpinner = new Spinner<>();
    static String trafficFrequencyInput;
    public static Integer[][] map = new Integer[10][10];

    //We use gridpane for a visual representation of the matrix
    public GridPane gridpane;
    List<ImageView> carsToDraw = new ArrayList<>();
    List<Integer> firstCoord = new ArrayList<>();
    List<Integer> secondCoord = new ArrayList<>();

    public void transposeMatrix() {
        map = CityGenerator.city.mapPreGenerated;
        Integer[][] transpose = new Integer[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                transpose[i][j] = map[j][i];

        map = transpose;
    }

    public void drawMap() {
        gridpane = new GridPane();
        transposeMatrix();
        Image streetBlock = new Image("file:src/GraphicsModule/resources/StreetBlock.jpg");
        Image streetBlockUp = new Image("file:src/GraphicsModule/resources/StreetBlockUp.jpg");
        Image grass = new Image("file:src/GraphicsModule/resources/grass.png");
        Image intersection = new Image("file:src/GraphicsModule/resources/junction.png");

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 2) {
                    ImageView streetSideways = new ImageView(streetBlock);
                    gridpane.add(streetSideways, i, j);
                } else if (map[i][j] == 4) {
                    ImageView streetUpDown = new ImageView(streetBlockUp);
                    gridpane.add(streetUpDown, i, j);
                } else if (map[i][j] == 5) {
                    ImageView intersectionView = new ImageView(intersection);
                    gridpane.add(intersectionView, i, j);
                } else {
                    ImageView grassView = new ImageView(grass);
                    gridpane.add(grassView, i, j);
                }
            }
    }

    public void addUserPane() {
        drawMap();
        window.setCenter(gridpane);
    }
}
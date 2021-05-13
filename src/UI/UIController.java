package UI;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import CityGenerating.CityGenerator;
import CityGenerating.Street;
import TestGrafica.United;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class UIController implements Initializable {

    @FXML
    public Canvas drawingCanvas;
    @FXML
    public ImageView imageView;
    @FXML
    public Button startButton;
    @FXML
    public Button exitButton;
    @FXML
    public Label distanceLabel;
    @FXML
    public Label intitialPositionLabel;
    @FXML
    public Label carSpeedLabel;
    @FXML
    public Label finalIntersectionLabel;
    @FXML
    public Label carSpeedLabelDetails;
    @FXML
    public AnchorPane clickArea;
    @FXML
    public ChoiceBox<String> initialPositionChoiceBox;
    @FXML
    public Spinner<Integer> streetDistanceSpinner;
    @FXML
    public ChoiceBox<Integer> finalPositionChoiceBox;
    @FXML
    public Spinner<Integer> choiceBoxSpeed;
    @FXML
    public CheckBox carOnReversed;
    //NU SCHIMBA ASTEA CA NU O SA MAI PORNEASCA JAVAFX


    public void startButtonOnAction(ActionEvent actionEvent) throws Exception {
        if (finalPositionChoiceBox.getValue() == null && initialPositionChoiceBox.getValue() == null) {
            finalIntersectionLabel.setTextFill(Color.YELLOW);
            intitialPositionLabel.setTextFill(Color.YELLOW);
            return;
        }
        if (finalPositionChoiceBox.getValue() == null) {
            finalIntersectionLabel.setTextFill(Color.YELLOW);
            return;
        }
        if (initialPositionChoiceBox.getValue() == null) {
            intitialPositionLabel.setTextFill(Color.YELLOW);
            return;
        }

        var initialPosition = getIndexOfStreet(initialPositionChoiceBox.getValue());
        var initialDistance = streetDistanceSpinner.getValue();
        var finalPosition = finalPositionChoiceBox.getValue();
        var isReversed = carOnReversed.isSelected() ? 1 : -1;
        //TODO: add final car

        String[] args = new String[1];
        args[0] = "full app";
        United.main(args); //https://stackoverflow.com/questions/26674498/how-to-open-two-javafx-windows
    }

    public void exitButtonOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    private int getIndexOfStreet(String stName) throws Exception {
        ArrayList<Street> streets = CityGenerator.city.getStreets();
        for (int i = 0; i < streets.size(); i++) {
            Street st = streets.get(i);
            if (st.getName().equals(stName))
                return i;
        }
        throw new Exception("street " + stName + " not found");
    }

    public void updateDistanceBox(ActionEvent actionEvent) {
        intitialPositionLabel.setTextFill(Color.WHITE);
        streetDistanceSpinner.setVisible(true);
        distanceLabel.setVisible(true);
        var chosenStreet = CityGenerator.city.getStreetByName(initialPositionChoiceBox.getValue());
        //poate fi + 1 aici si sa
        var spinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, chosenStreet.getLength(), 1);
        streetDistanceSpinner.setValueFactory(spinnerFactory);
    }


    public void updateDistance(ActionEvent actionEvent) {
    }

    public void updateFinalPosition(ActionEvent actionEvent) {
    }

    private void displayMap() {
        var path = new File("src\\UI\\mapPreview.png").getAbsolutePath();
        imageView.setImage(new Image("file:///" + path));
//        imageView.fitWidthProperty().bind(imageView.widthProperty());
//        imageView.fitHeightProperty().bind(imageView.heightProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        streetDistanceSpinner.setVisible(false);
        distanceLabel.setVisible(false);
        displayMap();

        SpinnerValueFactory<Integer> sizeValueSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        choiceBoxSpeed.setValueFactory(sizeValueSpinner);

        CityGenerator.generateCity();
        CityGenerator.city.getStreets().forEach(street -> {
            initialPositionChoiceBox.getItems().add(street.getName());
        });

        for (int i = 0; i <= 7; ++i)
            finalPositionChoiceBox.getItems().add(i);
    }
}

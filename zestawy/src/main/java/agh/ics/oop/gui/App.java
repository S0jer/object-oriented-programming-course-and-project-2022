package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.mapobjects.WorldMapElement;
import agh.ics.oop.model.worldmap.AbstractWorldMap;
import agh.ics.oop.model.worldmap.GrassField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

public class App extends Application implements PositionChangeObserver {

    private AbstractWorldMap map;
    private GridPane grid;
    private ThreadSimulationEngine engine;
    private int moveDelay;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        try {
            this.grid = new GridPane();
            this.grid.setGridLinesVisible(true);
            map = new GrassField(10);
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
            this.engine = new ThreadSimulationEngine(map, positions);
            this.engine.addObserver(this);
            this.moveDelay = 1000;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        initializeInterface();
        initializeMap();
        addToMap();

        Scene scene = new Scene(this.grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initializeInterface() {
        TextField textField = new TextField();
        Button startButton = new Button("Start");
        startButton.setDefaultButton(true);
        startButton.setOnAction(event -> {
            List<String> dir = List.of(textField.getText().split(""));
            List<MoveDirection> directions = new OptionsParser().parse(dir);
            this.engine.setDirections(directions);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });
        HBox hBox = new HBox(startButton, textField);
        this.grid.add(hBox, 0, 0, 4, 1);
        GridPane.setHalignment(hBox, HPos.CENTER);
        this.grid.getColumnConstraints().add(new ColumnConstraints(50));
        this.grid.getRowConstraints().add(new RowConstraints(50));
    }

    private void initializeMap() {
        List<Vector2d> borders = this.map.getBorders();
        Vector2d lowerBorder = borders.get(0);
        Vector2d upperBorder = borders.get(1);
        Integer minX = lowerBorder.getX();
        int minY = lowerBorder.getY();
        int maxX = upperBorder.getX();
        Integer maxY = upperBorder.getY();

        Label yx = new Label("y/x");
        this.grid.add(yx, 0, 1, 1, 1);
        GridPane.setHalignment(yx, HPos.CENTER);

        this.grid.getColumnConstraints().add(new ColumnConstraints(45));
        this.grid.getRowConstraints().add(new RowConstraints(45));
        for (Integer i = minX; i <= maxX; i++) {
            Label label = new Label(i.toString());
            this.grid.add(label, i - minX + 1, 1, 1, 1);
            this.grid.getColumnConstraints().add(new ColumnConstraints(45));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (Integer i = maxY; i >= minY; i--) {
            Label label = new Label(i.toString());
            this.grid.add(label, 0, maxY - i + 2, 1, 1);
            this.grid.getRowConstraints().add(new RowConstraints(45));
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    private void addToMap() {
        List<Vector2d> borders = this.map.getBorders();
        Vector2d lowerBorder = borders.get(0);
        Vector2d upperBorder = borders.get(1);
        int minX = lowerBorder.getX();
        int minY = lowerBorder.getY();
        int maxX = upperBorder.getX();
        int maxY = upperBorder.getY();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Vector2d pos = new Vector2d(x, y);
                if (this.map.isOccupied(pos)) {
                    WorldMapElement worldMapElement = this.map.objectAt(pos);
                    try {
                        this.grid.add(new GuiElementBox(worldMapElement).getvBox(), x - minX + 1, maxY + 2 - y, 1, 1);
                    } catch (ImageNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            this.grid.getChildren().retainAll(grid.getChildren().get(0));
            initializeInterface();
            initializeMap();
            addToMap();
        });
    }
}

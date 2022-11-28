package agh.ics.oop.gui;

import agh.ics.oop.Engine;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.AbstractWorldMap;
import agh.ics.oop.model.worldmap.GrassField;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

public class App extends Application {

    AbstractWorldMap map;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        try {
            map = new GrassField(10);
            List<String> args = new ArrayList<>(Arrays.asList("f", "r", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f"));
            List<MoveDirection> directions = new OptionsParser().parse(args);
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
            Engine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        initializeMap(grid, map);
        addToMap(grid, map);
        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeMap(GridPane grid, AbstractWorldMap map) {
        List<Vector2d> borders = map.getBorders();
        Vector2d lowerBorder = borders.get(0);
        Vector2d upperBorder = borders.get(1);
        Integer minX = lowerBorder.x;
        Integer minY = lowerBorder.y;
        Integer maxX = upperBorder.x;
        Integer maxY = upperBorder.y;

        Label yx = new Label("y/x");
        grid.add(yx, 0, 0, 1, 1);
        GridPane.setHalignment(yx, HPos.CENTER);

        grid.getColumnConstraints().add(new ColumnConstraints(25));
        grid.getRowConstraints().add(new RowConstraints(25));
        for (Integer i = minX; i <= maxX; i++) {
            Label label = new Label(i.toString());
            grid.add(label, i - minX + 1, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(25));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (Integer i = maxY; i >= minY; i--) {
            Label label = new Label(i.toString());
            grid.add(label, 0, maxY - i + 1, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(25));
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }

    private void addToMap(GridPane grid, AbstractWorldMap map) {
        List<Vector2d> borders = map.getBorders();
        Vector2d lowerBorder = borders.get(0);
        Vector2d upperBorder = borders.get(1);
        int minX = lowerBorder.x;
        int minY = lowerBorder.y;
        int maxX = upperBorder.x;
        int maxY = upperBorder.y;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Vector2d pos = new Vector2d(x, y);
                if (map.isOccupied(pos)) {
                    Object object = map.objectAt(pos);
                    Label label = new Label(object.toString());
                    grid.add(label, x - minX + 1, maxY + 1 - y, 1, 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }
    }
}

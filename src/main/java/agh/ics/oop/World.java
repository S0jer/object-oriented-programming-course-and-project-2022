package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) {
        List<String> movesForAnimals = new ArrayList<>(Arrays.asList("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"));
        List<MoveDirection> directions = new OptionsParser().parse(movesForAnimals);
        WorldMap map = new RectangularMap(10, 5);
        List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        Engine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }
}

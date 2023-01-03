package agh.ics.oop;

import agh.ics.oop.model.mapobjects.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements Engine {

    private final List<MoveDirection> animalsMoves;
    private final WorldMap worldMap;
    private final List<Vector2d> animalsInitationalPosition;
    private List<Animal> animalList;

    public SimulationEngine(List<MoveDirection> animalsMoves, WorldMap worldMap, List<Vector2d> animalsInitationalPosition) {
        this.animalsMoves = animalsMoves;
        this.worldMap = worldMap;
        this.animalsInitationalPosition = animalsInitationalPosition;
        addToMap();
    }

    private void addToMap() {
        this.animalList = new ArrayList<>();
        for (Vector2d vector2d : this.animalsInitationalPosition) {
            Animal animal = new Animal(this.worldMap, vector2d);
            this.animalList.add(animal);
            this.worldMap.place(animal);
        }
    }

    @Override
    public void run() {
        int i = 0;
        System.out.println(this.worldMap);
        for (MoveDirection moveDirection : this.animalsMoves) {
            this.animalList.get(i % this.animalList.size()).moveAnimal(moveDirection);
            i++;
            System.out.println(this.worldMap);
        }
    }
}

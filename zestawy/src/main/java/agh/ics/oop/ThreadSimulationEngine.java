package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.mapobjects.Animal;
import agh.ics.oop.model.worldmap.WorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSimulationEngine implements Engine, Runnable {
    private List<MoveDirection> directions;
    private final WorldMap map;
    private final List<PositionChangeObserver> observers = new ArrayList<>();
    private final int moveDelay;

    private final List<Animal> animalsFromMap = new ArrayList<>();

    public ThreadSimulationEngine(WorldMap map, List<Vector2d> initialPositions) {
        this.directions = new ArrayList<>();
        this.map = map;
        this.moveDelay = 1000;

        for (Vector2d pos : initialPositions) {
            Animal animal = new Animal(map, pos);
            this.animalsFromMap.add(animal);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        Logger logger = Logger.getLogger(ThreadSimulationEngine.class.getName());
        logger.setLevel(Level.INFO);
        int numberOfAnimals = this.animalsFromMap.size();
        int i = 0;
        String mapToString = map.toString();
        logger.log(Level.INFO, mapToString);
        for (MoveDirection move : directions) {
            Vector2d oldPosition = this.animalsFromMap.get(i % numberOfAnimals).getPosition();
            this.animalsFromMap.get(i % numberOfAnimals).moveAnimal(move);
            Vector2d newPosition = this.animalsFromMap.get(i % numberOfAnimals).getPosition();
            positionChanged(oldPosition, newPosition);
            i++;
            mapToString = map.toString();
            logger.log(Level.INFO, mapToString);
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }

    public void addObserver(PositionChangeObserver observer) {
        observers.add(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        observers.forEach(e -> e.positionChanged(oldPosition, newPosition));
    }
}

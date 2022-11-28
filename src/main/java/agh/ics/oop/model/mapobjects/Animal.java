package agh.ics.oop.model.mapobjects;

import agh.ics.oop.PositionChangeObserver;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.WorldMap;

import java.util.HashSet;
import java.util.Set;

public class Animal extends WorldMapElement {

    private final WorldMap worldMap;
    private MapDirection animalDirection = MapDirection.NORTH;

    private Vector2d animalPosition;

    private final Set<PositionChangeObserver> observers = new HashSet<>();

    private void setPosition(Vector2d nextPosition) {
        Vector2d oldPosition = this.animalPosition;
        this.animalPosition = nextPosition;
        positionChanged(oldPosition, nextPosition);
    }

    public Vector2d getPosition() {
        return animalPosition;
    }

    public MapDirection getAnimalDirection() {
        return this.animalDirection;
    }

    public Animal(WorldMap map) {
        super();
        this.animalPosition = new Vector2d(2, 2);
        this.worldMap = map;
    }

    public Animal(WorldMap map, Vector2d initialPosition) {
        super();
        this.animalPosition = initialPosition;
        this.worldMap = map;
    }

    public Animal(WorldMap map, MapDirection initialDirection, Vector2d initialPosition) {
        super();
        this.worldMap = map;
        this.animalDirection = initialDirection;
        this.animalPosition = initialPosition;
    }

    public void moveAnimal(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> {
                Vector2d nextAnimalPosition = this.getPosition().add(this.animalDirection.toUnitVector());
                if (worldMap.canMoveTo(nextAnimalPosition)) {
                    this.setPosition(nextAnimalPosition);
                }
            }
            case BACKWARD -> {
                Vector2d nextAnimalPosition = this.getPosition().subtract(this.animalDirection.toUnitVector());
                if (worldMap.canMoveTo(nextAnimalPosition)) {
                    this.setPosition(nextAnimalPosition);
                }
            }
            case LEFT -> animalDirection = animalDirection.previous();
            case RIGHT -> animalDirection = animalDirection.next();
        }

    }

    public boolean isAt(Vector2d position) {
        return this.getPosition().equals(position);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.observers.forEach(o -> o.positionChanged(oldPosition, newPosition));
    }

    @Override
    public void addObserver(PositionChangeObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(PositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public String toString() {
        return animalDirection.toString();
    }
}

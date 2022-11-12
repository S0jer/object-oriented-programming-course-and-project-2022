package agh.ics.oop.model.mapobjects;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.WorldMap;

public class Animal extends WorldMapElement {

    private final WorldMap worldMap;
    private MapDirection animalDirection = MapDirection.NORTH;

    public MapDirection getAnimalDirection() {
        return this.animalDirection;
    }

    public Animal(WorldMap map) {
        super(new Vector2d(2, 2));
        this.worldMap = map;
    }

    public Animal(WorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.worldMap = map;
    }

    public Animal(WorldMap map, MapDirection initialDirection, Vector2d initialPosition) {
        super(initialPosition);
        this.worldMap = map;
        this.animalDirection = initialDirection;
    }

    public void moveAnimal(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> {
                Vector2d nextAnimalPosition = super.getPosition().add(this.animalDirection.toUnitVector());
                if (worldMap.canMoveTo(nextAnimalPosition)) {
                    updateMapPosition(nextAnimalPosition);
                    Vector2d previousPosition = this.getPosition();
                    super.setPosition(nextAnimalPosition);
                    move(this, previousPosition);
                }
            }
            case BACKWARD -> {
                Vector2d nextAnimalPosition = super.getPosition().subtract(this.animalDirection.toUnitVector());
                if (worldMap.canMoveTo(nextAnimalPosition)) {
                    updateMapPosition(nextAnimalPosition);
                    Vector2d previousPosition = this.getPosition();
                    super.setPosition(nextAnimalPosition);
                    move(this, previousPosition);
                }
            }
            case LEFT -> animalDirection = animalDirection.previous();
            case RIGHT -> animalDirection = animalDirection.next();
        }

    }

    private void updateMapPosition(Vector2d nextAnimalPosition) {
        if (worldMap.isOccupied(nextAnimalPosition)) {
            WorldMapElement worldMapElement = worldMap.objectAt(nextAnimalPosition);
            worldMap.objectAt(nextAnimalPosition).move(worldMapElement, nextAnimalPosition);
        }
    }

    @Override
    public void move(WorldMapElement worldMapElement, Vector2d previousPosition) {
        if (worldMap.canMoveTo(this.getPosition())) {
            worldMap.place(this);
            worldMap.remove(previousPosition);
        }
    }

    @Override
    public String toString() {
        return animalDirection.toString();
    }
}

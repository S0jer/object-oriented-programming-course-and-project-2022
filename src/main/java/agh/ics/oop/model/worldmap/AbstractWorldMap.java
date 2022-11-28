package agh.ics.oop.model.worldmap;

import agh.ics.oop.PositionChangeObserver;
import agh.ics.oop.model.mapobjects.Animal;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.mapobjects.WorldMapElement;

import java.util.*;

public abstract class AbstractWorldMap implements PositionChangeObserver, WorldMap {

    private final Map<Vector2d, WorldMapElement> worldMap = new HashMap<>();

    private Vector2d lowerBorder = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    private Vector2d upperBorder = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(WorldMapElement worldMapElement) {
        if (this.canMoveTo(worldMapElement.getPosition())) {
            worldMapElement.addObserver(this);
            worldMap.put(worldMapElement.getPosition(), worldMapElement);
            return true;
        } else {
            throw new IllegalArgumentException("Can not place WorldMapObject at " + worldMapElement.getPosition());
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    @Override
    public WorldMapElement objectAt(Vector2d position) {
        return worldMap.get(position);
    }

    public void remove(Vector2d removePosition) {
        worldMap.remove(removePosition);
    }


    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        List<Vector2d> borders = getBorders();
        this.lowerBorder = borders.get(0);
        this.upperBorder = borders.get(1);
        return mapVisualizer.draw(this.lowerBorder, this.upperBorder);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        WorldMapElement worldMapElement = worldMap.get(oldPosition);
        worldMap.remove(oldPosition);
        worldMap.put(newPosition, worldMapElement);
    }

    protected WorldMapElement get(Vector2d position) {
        return this.objectAt(position);
    }
}

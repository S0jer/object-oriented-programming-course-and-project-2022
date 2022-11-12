package agh.ics.oop.model.worldmap;

import agh.ics.oop.model.mapobjects.Animal;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.mapobjects.WorldMapElement;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {


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
            worldMap.put(worldMapElement.getPosition(), worldMapElement);
            return true;
        }
        return false;
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
        updateBorders();
        return mapVisualizer.draw(this.lowerBorder, this.upperBorder);
    }

    private void updateBorders() {
        worldMap.keySet().forEach(vector2d -> {
            this.lowerBorder = this.lowerBorder.lowerLeft(vector2d);
            this.upperBorder = this.upperBorder.upperRight(vector2d);
        });
    }
    @Override
    public void placeGrass(Integer grass) {
    }
}

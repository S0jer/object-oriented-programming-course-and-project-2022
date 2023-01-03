package agh.ics.oop.model.worldmap;


import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.mapobjects.WorldMapElement;

import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d mapLowerBorder;

    private final Vector2d mapUpperBorder;

    public RectangularMap(Vector2d mapLowerBorder, Vector2d mapUpperBorder) {
        super();
        this.mapLowerBorder = mapLowerBorder;
        this.mapUpperBorder = mapUpperBorder;
    }

    @Override
    public List<Vector2d> getBorders() {
        return List.of(mapLowerBorder, mapUpperBorder);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(mapLowerBorder) && position.precedes(mapUpperBorder)) {
            return super.canMoveTo(position);
        }
        return false;
    }

    @Override
    public boolean place(WorldMapElement worldMapElement) {
        if (worldMapElement.getPosition().follows(mapLowerBorder) && worldMapElement.getPosition().precedes(mapUpperBorder)) {
            return super.place(worldMapElement);
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (position.follows(mapLowerBorder) && position.precedes(mapUpperBorder)) {
            return super.isOccupied(position);
        }
        return false;
    }

    @Override
    public WorldMapElement objectAt(Vector2d position) {
        if (position.follows(mapLowerBorder) && position.precedes(mapUpperBorder)) {
            return super.objectAt(position);
        }
        return null;
    }
}

package agh.ics.oop.model.mapobjects;


import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.WorldMap;

public class Grass extends WorldMapElement {

    private final WorldMap worldMap;

    private final Vector2d grassPosition;

    public Grass(WorldMap worldMap, Vector2d grassPosition) {
        super();
        this.worldMap = worldMap;
        this.grassPosition = grassPosition;
        this.worldMap.place(this);
    }

    public Vector2d getPosition() {
        return grassPosition;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return this.getPosition().equals(position);
    }

    @Override
    public String getViewName() {
        return "grass.png";
    }

    @Override
    public String toString() {
        return "*";
    }
}

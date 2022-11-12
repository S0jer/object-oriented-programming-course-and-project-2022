package agh.ics.oop.model.mapobjects;


import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.WorldMap;

public class Grass extends WorldMapElement {

    private final WorldMap worldMap;

    public Grass(WorldMap worldMap, Vector2d grassPosition) {
        super(grassPosition);
        this.worldMap = worldMap;
        this.worldMap.place(this);
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public void move(WorldMapElement grass, Vector2d previousPosition) {
        worldMap.placeGrass(1);
    }
}

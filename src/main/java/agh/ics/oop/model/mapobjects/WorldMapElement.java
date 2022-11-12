package agh.ics.oop.model.mapobjects;

import agh.ics.oop.model.Vector2d;

public abstract class WorldMapElement implements MapElement {

    private Vector2d mapObjectPosition;

    protected WorldMapElement(Vector2d mapObjectPosition) {
        this.mapObjectPosition = mapObjectPosition;
    }


    public Vector2d getPosition() {
        return this.mapObjectPosition;
    }

    public void move(WorldMapElement worldMapElement, Vector2d previousPosition) {

    }

    public boolean isAt(Vector2d position) {
        return this.getPosition().equals(position);
    }

    protected void setPosition(Vector2d nextPosition) {
        this.mapObjectPosition = nextPosition;
    }
}

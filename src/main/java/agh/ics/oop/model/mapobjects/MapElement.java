package agh.ics.oop.model.mapobjects;

import agh.ics.oop.model.Vector2d;

public interface MapElement {
    void move(WorldMapElement worldMapElement, Vector2d previousPosition);

    boolean isAt(Vector2d position);
}

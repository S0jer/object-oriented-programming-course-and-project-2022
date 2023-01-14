package agh.ics.oop.model.mapobjects;

import agh.ics.oop.PositionChangeObserver;
import agh.ics.oop.model.Vector2d;

public interface MapElement {
    boolean isAt(Vector2d position);

    Vector2d getPosition();

    void addObserver(PositionChangeObserver positionChangeObserver);

    String getViewName();

    void removeObserver(PositionChangeObserver positionChangeObserver);
}

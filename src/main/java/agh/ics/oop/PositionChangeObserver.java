package agh.ics.oop;

import agh.ics.oop.model.Vector2d;

public interface PositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}

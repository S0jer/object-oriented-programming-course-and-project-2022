package agh.ics.oop.model.worldmap;

import agh.ics.oop.PositionChangeObserver;
import agh.ics.oop.model.Vector2d;


import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements PositionChangeObserver {

    private final SortedSet<Vector2d> objectsVertically = new TreeSet<>((o1, o2) -> {
        if (o2.getY() > o1.getY() || (o1.getY() == o2.getY() && o1.getX() < o2.getX())) {
            return -1;

        } else if (o1.getX() == o2.getX() && o1.getY() == o2.getY()) {
            return 0;
        } else {
            return 1;
        }
    });

    private final SortedSet<Vector2d> objectsHorizontally = new TreeSet<>((o1, o2) -> {
        if (o2.getX() > o1.getX() || (o1.getX() == o2.getX() && o1.getY() < o2.getY())) {
            return -1;
        } else if (o1.getX() == o2.getX() && o1.getY() == o2.getY()) {
            return 0;
        } else {
            return 1;
        }
    });

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeVector(oldPosition);
        addVector(newPosition);
    }

    private void addVector(Vector2d vector2d) {
        this.objectsHorizontally.add(vector2d);
        this.objectsVertically.add(vector2d);
    }

    private void removeVector(Vector2d vector2d) {
        this.objectsHorizontally.remove(vector2d);
        this.objectsVertically.remove(vector2d);
    }

    public List<Vector2d> getBoundaries() {
        return List.of(new Vector2d(objectsHorizontally.first().getX(), objectsVertically.first().getY()), new Vector2d(objectsHorizontally.last().getX(), objectsVertically.last().getY()));
    }
}

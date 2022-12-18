package agh.ics.oop.model.worldmap;


import agh.ics.oop.PositionChangeObserver;
import agh.ics.oop.model.mapobjects.Grass;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.mapobjects.WorldMapElement;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class GrassField extends AbstractWorldMap {
    private final Integer grassCount;

    protected final MapBoundary mapBoundary = new MapBoundary();
    private final Integer upperGrassBorder;

    private final Set<PositionChangeObserver> observers = new HashSet<>();

    private static final Random random = new Random();

    public GrassField(Integer grassCount) {
        this.grassCount = grassCount;
        this.upperGrassBorder = (int) Math.sqrt(this.grassCount * 10.0);
        placeGrass(this.grassCount);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        WorldMapElement elementFromNewPosition = super.get(newPosition);
        super.positionChanged(oldPosition, newPosition);
        if (elementFromNewPosition instanceof Grass) {
            this.placeGrass(1);
        }
    }

    private void placeGrass(Integer grassToPlace) {
        int grassPlaced = 0;
        while (grassPlaced != grassToPlace) {
            int x = random.nextInt(this.upperGrassBorder);
            int y = random.nextInt(this.upperGrassBorder);
            Vector2d grassPosition = new Vector2d(x, y);
            if (!isOccupied(grassPosition)) {
                Grass grass = new Grass(this, grassPosition);
                informObservers(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE), grassPosition);
                this.place(grass);
                grassPlaced += 1;
            }
        }
    }

    @Override
    public boolean place(WorldMapElement worldMapElement) {
        worldMapElement.addObserver(mapBoundary);
        this.addObserver(mapBoundary);
        return super.place(worldMapElement);
    }

    private void informObservers(Vector2d oldPosition, Vector2d newPosition) {
        this.observers.forEach(o -> o.positionChanged(oldPosition, newPosition));
    }

    @Override
    public List<Vector2d> getBorders() {
        return mapBoundary.getBoundaries();
    }

    public void addObserver(PositionChangeObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(PositionChangeObserver observer) {
        this.observers.remove(observer);
    }

}

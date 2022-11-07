package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {

    private final Map<Vector2d, Animal> animalMap;
    private final Integer width;
    private final Integer height;
    private final Vector2d lowerBorder;
    private final Vector2d upperBorder;

    public RectangularMap(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.lowerBorder = new Vector2d(0, 0);
        this.upperBorder = new Vector2d(width, height);
        animalMap = new HashMap<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(lowerBorder) && position.precedes(upperBorder)) {
            return !(this.objectAt(position) instanceof Animal);
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (animal.getAnimalPosition().follows(lowerBorder)
                && animal.getAnimalPosition().precedes(upperBorder)
                && this.canMoveTo(animal.getAnimalPosition())) {
            animalMap.put(animal.getAnimalPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (position.follows(lowerBorder) && position.precedes(upperBorder)) {
            return this.objectAt(position) instanceof Animal;
        }
        return false;
    }

    @Override
    public void move(Animal animal, Vector2d toPosition) {
        if (this.canMoveTo(toPosition)) {
            animalMap.put(toPosition, animal);
            animalMap.put(animal.getAnimalPosition(), null);
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
    }
}

package agh.ics.oop;

public class Animal {

    private WorldMap animalMap;
    private MapDirection animalDirection = MapDirection.NORTH;

    private Vector2d animalPosition = new Vector2d(2, 2);

    public MapDirection getAnimalDirection() {
        return this.animalDirection;
    }

    public Vector2d getAnimalPosition() {
        return this.animalPosition;
    }

    public Animal(WorldMap map) {
        this.animalMap = map;
    }

    public Animal(WorldMap map, Vector2d initialPosition) {
        this.animalMap = map;
        this.animalPosition = initialPosition;
    }

    public Animal(MapDirection initialDirection, Vector2d initialPosition) {
        this.animalDirection = initialDirection;
        this.animalPosition = initialPosition;

    }

    public boolean isAt(Vector2d position) {
        return this.animalPosition.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> {
                Vector2d nextAnimalPosition = animalPosition.add(this.animalDirection.toUnitVector());
                if (animalMap.canMoveTo(nextAnimalPosition)) {
                    animalMap.move(this, nextAnimalPosition);
                    animalPosition = nextAnimalPosition;
//                    nextAnimalPosition.follows(lowerBorder) && nextAnimalPosition.precedes(upperBorder)
                }
            }
            case BACKWARD -> {
                Vector2d nextAnimalPosition = animalPosition.subtract(this.animalDirection.toUnitVector());
                if (animalMap.canMoveTo(nextAnimalPosition)) {
                    animalMap.move(this, nextAnimalPosition);
                    animalPosition = nextAnimalPosition;
                }
            }
            case LEFT -> animalDirection = animalDirection.previous();
            case RIGHT -> animalDirection = animalDirection.next();
        }

    }

    @Override
    public String toString() {
        return animalDirection.toString();
    }
}

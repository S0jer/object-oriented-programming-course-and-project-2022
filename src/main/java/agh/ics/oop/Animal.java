package agh.ics.oop;

public class Animal {

    private MapDirection animalDirection = MapDirection.NORTH;

    private Vector2d animalPosition = new Vector2d(2, 2);

    private final Vector2d upperBorder = new Vector2d(4, 4);

    private final Vector2d lowerBorder = new Vector2d(0, 0);

    public void setAnimalDirection(MapDirection animalDirection) {
        this.animalDirection = animalDirection;
    }

    public void setAnimalPosition(Vector2d animalPosition) {
        this.animalPosition = animalPosition;
    }

    public MapDirection getAnimalDirection() {
        return this.animalDirection;
    }

    public Vector2d getAnimalPosition() {
        return this.animalPosition;
    }

    public Animal() {
        // TODO document why this constructor is empty
    }

    public boolean isAt(Vector2d position) {
        return this.animalPosition.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> {
                if (animalPosition.add(this.animalDirection.toUnitVector()).follows(lowerBorder)
                        && animalPosition.add(this.animalDirection.toUnitVector()).precedes(upperBorder)) {
                    animalPosition = animalPosition.add(this.animalDirection.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (animalPosition.subtract(this.animalDirection.toUnitVector()).follows(lowerBorder)
                        && animalPosition.subtract(this.animalDirection.toUnitVector()).precedes(upperBorder)) {
                    animalPosition = animalPosition.subtract(this.animalDirection.toUnitVector());
                }
            }
            case LEFT -> animalDirection = animalDirection.previous();
            case RIGHT -> animalDirection = animalDirection.next();
        }

    }

    @Override
    public String toString() {
        return animalDirection.toString()
                + " | "
                + animalPosition.toString();
    }
}

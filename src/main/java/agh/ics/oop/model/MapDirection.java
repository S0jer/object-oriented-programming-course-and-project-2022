package agh.ics.oop.model;


public enum MapDirection {
    NORTH("N", new Vector2d(0, 1)),
    EAST("E", new Vector2d(1, 0)),
    SOUTH("S", new Vector2d(0, -1)),
    WEST("W", new Vector2d(-1, 0));

    private final String directionName;
    private final Vector2d unitVector;

    MapDirection(String directionName, Vector2d unitVector) {
        this.directionName = directionName;
        this.unitVector = unitVector;
    }

    public MapDirection next() {
        return MapDirection.values()[(this.ordinal() + 1) % 4];
    }

    public MapDirection previous() {
        return this.ordinal() - 1 >= 0 ? MapDirection.values()[(this.ordinal() - 1) % 4] : MapDirection.values()[3];
    }


    public Vector2d toUnitVector() {
        return this.unitVector;
    }

    @Override
    public String toString() {
        return this.directionName;
    }
}

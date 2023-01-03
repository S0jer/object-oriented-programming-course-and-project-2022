package agh.ics.oop.model;


public enum MapDirection {
    NORTH("N", new Vector2d(0, 1), "up.png"),
    EAST("E", new Vector2d(1, 0), "right.png"),
    SOUTH("S", new Vector2d(0, -1), "down.png"),
    WEST("W", new Vector2d(-1, 0), "left.png");

    private final String directionName;
    private final Vector2d unitVector;

    private final String viewName;

    MapDirection(String directionName, Vector2d unitVector, String viewName) {
        this.directionName = directionName;
        this.unitVector = unitVector;
        this.viewName = viewName;
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

    public String getViewName() {
        return this.viewName;
    }

    @Override
    public String toString() {
        return this.directionName;
    }
}

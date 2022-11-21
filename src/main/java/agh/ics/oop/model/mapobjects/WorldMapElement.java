package agh.ics.oop.model.mapobjects;

import agh.ics.oop.PositionChangeObserver;

public abstract class WorldMapElement implements MapElement {

    @Override
    public void addObserver(PositionChangeObserver positionChangeObserver) {

    }

    @Override
    public void removeObserver(PositionChangeObserver positionChangeObserver) {

    }
}

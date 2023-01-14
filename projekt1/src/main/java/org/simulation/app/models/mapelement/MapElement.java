package org.simulation.app.models.mapelement;

import org.simulation.app.models.mapelement.elementcharacteristics.Vector2d;

public interface MapElement { // I...
    Vector2d getPosition();

    String getPathToImage();
}

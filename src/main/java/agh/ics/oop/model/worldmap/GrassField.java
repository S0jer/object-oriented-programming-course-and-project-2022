package agh.ics.oop.model.worldmap;


import agh.ics.oop.model.mapobjects.Grass;
import agh.ics.oop.model.Vector2d;

import java.util.Random;


public class GrassField extends AbstractWorldMap {
    private final Integer grassCount;
    private final Integer upperGrassBorder;

    private static final Random random = new Random();

    public GrassField(Integer grassCount) {
        this.grassCount = grassCount;
        this.upperGrassBorder = (int) Math.sqrt(this.grassCount * 10.0);
        placeGrass(this.grassCount);
    }

    @Override
    public void placeGrass(Integer grassToPlace) {
        int grassPlaced = 0;
        while (grassPlaced != grassToPlace) {
            int x = random.nextInt(this.upperGrassBorder);
            int y = random.nextInt(this.upperGrassBorder);
            Vector2d grassPosition = new Vector2d(x, y);
            if (!isOccupied(grassPosition)) {
                Grass grass = new Grass(this, grassPosition);
                super.place(grass);
                grassPlaced += 1;
            }
        }
    }
}

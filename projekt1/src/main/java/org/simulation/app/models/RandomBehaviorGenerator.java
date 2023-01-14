package org.simulation.app.models;

import java.util.Random;

public class RandomBehaviorGenerator {
    private static final Random generator = new Random();

    public int behaviorGenerator() {
        return generator.nextInt(10);
    }

    public int numberToGenerator(int value) {  // niezrozumiała nazwa
        return generator.nextInt(value);
    }

    public int numberExceptGenerator(int bottomRange, int upperRangeBottom, int upperRangeUpper) {  // jw.; upperRangeUpper?
        int chosenRange = numberToGenerator(2);
        int chosenValue;
        if (chosenRange == 0) {
            chosenValue = numberToGenerator(bottomRange);
        } else {
            chosenValue = numberToGenerator(upperRangeUpper - upperRangeBottom) + upperRangeBottom;
        }
        return chosenValue;
    }
}

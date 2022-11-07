package agh.ics.oop;

import java.util.Arrays;

public enum MoveDirection {
    FORWARD("forward", "f"),
    BACKWARD("backward", "b"),
    RIGHT("right", "r"),
    LEFT("left", "l");

    private final String nameOfValueOne;
    private final String nameOfValueTwo;


    MoveDirection(String nameOfValueOne, String nameOfValueTwo) {
        this.nameOfValueOne = nameOfValueOne;
        this.nameOfValueTwo = nameOfValueTwo;
    }

    static MoveDirection fromString(String fromString) {
        return Arrays.stream(MoveDirection.values()).filter(x -> x.nameOfValueOne.equals(fromString) || x.nameOfValueTwo.equals(fromString)).findAny().orElse(null);
    }
}

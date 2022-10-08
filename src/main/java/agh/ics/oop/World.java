package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class World {
    public static void main(String[] args) {
        System.out.print("Start");
        String[] p = {"f", "f", "r", "l"};
        List<Direction> path = changeType(p); // or args to run from terminal
        run(path);
        properPrint("Stop");
    }

    static List<Direction> changeType(String[] path) {
        return Arrays.stream(path).map(World::getType).filter(Objects::nonNull).toList();
    }

    private static Direction getType(String arg) {
        return switch (arg) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "l" -> Direction.LEFT;
            case "r" -> Direction.RIGHT;
            default -> null;
        };
    }

    static void run(List<Direction> path) {
        path.forEach(instruction -> {
            switch (instruction) {
                case FORWARD -> properPrint("Zwierzak idzie do przodu");
                case BACKWARD -> properPrint("Zwierzak idzie do tylu");
                case LEFT -> properPrint("Zwierzak skręca w lewo");
                case RIGHT -> properPrint("Zwierzak skręca w prawo");
            }
        });
    }

    static void properPrint(String message) {
        String prefix = ",";
        System.out.print(prefix + message);
    }
}

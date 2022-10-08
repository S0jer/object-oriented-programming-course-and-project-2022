package agh.ics.oop;

import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.print("Start");
        String[] p = {"f", "f"};
        List<Direction> path = changeType(p);
        run(path);
        System.out.print("Koniec");
    }

    static List<Direction> changeType(String[] path) {
        return Arrays.stream(path).map(World::getType).toList();
    }

    private static Direction getType(String arg) {
        return switch (arg) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "l" -> Direction.LEFT;
            case "r" -> Direction.RIGHT;
            default -> Direction.DEFAULT;
        };
    }

    static void run(List<Direction> path) {
        path.forEach((instruction) -> {
            System.out.print(",");
            switch (instruction) {
                case FORWARD -> System.out.print("Forward");
                case BACKWARD -> System.out.print("Backward");
                case LEFT -> System.out.print("Left");
                case RIGHT -> System.out.print("Right");
            }
        });
        System.out.print(",");
    }
}

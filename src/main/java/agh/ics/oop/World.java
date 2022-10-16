package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class World {
    public static void main(String[] args) {

//        System.out.print("Start");
//        String[] p = {"f", "f", "r", "l"};
//        List<MoveDirection> path = changeType(p); // or args to run from terminal
//        run(path);
//        properPrint("Stop");

        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));


    }

    static List<MoveDirection> changeType(String[] path) {
        return Arrays.stream(path).map(World::getType).filter(Objects::nonNull).toList();
    }

    private static MoveDirection getType(String arg) {
        return switch (arg) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            case "r" -> MoveDirection.RIGHT;
            default -> null;
        };
    }

    static void run(List<MoveDirection> path) {
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

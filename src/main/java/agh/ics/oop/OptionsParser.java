package agh.ics.oop;


import java.util.List;
import java.util.Objects;

public class OptionsParser {

    List<MoveDirection> parse(List<String> pathToConvert) {
        return pathToConvert.stream().map(OptionsParser::getType).filter(Objects::nonNull).toList();
    }

    private static MoveDirection getType(String arg) {
        return switch (arg) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "l", "left" -> MoveDirection.LEFT;
            case "r", "right" -> MoveDirection.RIGHT;
            default -> null;
        };
    }
}

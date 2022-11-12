package agh.ics.oop;


import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.Objects;

public class OptionsParser {

    List<MoveDirection> parse(List<String> pathToConvert) {
        return pathToConvert.stream().map(OptionsParser::getType).filter(Objects::nonNull).toList();
    }

    private static MoveDirection getType(String arg) {
        return MoveDirection.fromString(arg);
    }
}

package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


import static agh.ics.oop.model.MoveDirection.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class OptionsParserTest {

    @ParameterizedTest
    @MethodSource("properParseArgumentsProvider")
    void shouldParseTest(List<String> toConvertList, List<MoveDirection> expectedDirectionsList) {
        //When
        OptionsParser optionsParser = new OptionsParser();
        List<MoveDirection> convertedList = optionsParser.parse(toConvertList);

        //Then
        assertThat(convertedList).isEqualTo(expectedDirectionsList);
    }

    static Stream<Arguments> properParseArgumentsProvider() {
        return Stream.of(
                arguments(new ArrayList<>(Arrays.asList("f", "forward", "backward", "left")), new ArrayList<>(Arrays.asList(FORWARD, FORWARD, BACKWARD, LEFT))),
                arguments(new ArrayList<>(Arrays.asList("f", "f")), new ArrayList<>(Arrays.asList(FORWARD, FORWARD))),
                arguments(new ArrayList<>(Arrays.asList("b", "b", "b", "b", "b", "left", "b", "b", "b", "b", "b")),
                        new ArrayList<>(Arrays.asList(BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD, LEFT, BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD))),
                arguments(new ArrayList<>(Arrays.asList("f", "f", "f", "f", "f", "f", "f", "f", "f", "f")),
                        new ArrayList<>(Arrays.asList(FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD))),
                arguments(new ArrayList<>(Arrays.asList("r", "r", "f", "l", "f", "f", "f", "l", "f", "f", "r", "b")),
                        new ArrayList<>(Arrays.asList(RIGHT, RIGHT, FORWARD, LEFT, FORWARD, FORWARD, FORWARD, LEFT, FORWARD, FORWARD, RIGHT, BACKWARD)))
        );
    }

    @ParameterizedTest
    @MethodSource("corruptedParseArgumentsProvider")
    void shouldNotParseTest(List<String> toConvertList, List<MoveDirection> expectedDirectionsList) {
        //When
        OptionsParser optionsParser = new OptionsParser();
        String expectedMessage = "Illegal move specification";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            optionsParser.parse(toConvertList);
        });

        String actualMessage = exception.getMessage();

        //Then
        assertThat(actualMessage).contains(expectedMessage);
    }

    static Stream<Arguments> corruptedParseArgumentsProvider() {
        return Stream.of(
                arguments(new ArrayList<>(Arrays.asList("f", "a", "forward", "backward", "leftt")), new ArrayList<>(Arrays.asList(FORWARD, FORWARD, BACKWARD))),
                arguments(new ArrayList<>(Arrays.asList("f", "a", "f", "prawo")), new ArrayList<>(Arrays.asList(FORWARD, FORWARD))),
                arguments(new ArrayList<>(Arrays.asList("b", "b", "t", "b", "b", "b", "lefo", "b", "b", "b", "b", "b")),
                        new ArrayList<>(Arrays.asList(BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD))),
                arguments(new ArrayList<>(Arrays.asList("f", "f", "f", "f", "k", "f", "f", "x", "f", "f", "f", "f", "for", "tyl")),
                        new ArrayList<>(Arrays.asList(FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD))),
                arguments(new ArrayList<>(Arrays.asList("r", "r", "f", "l", "F", "f", "f", "f", "l", "g", "f", "f", "r", "b", "fr")),
                        new ArrayList<>(Arrays.asList(RIGHT, RIGHT, FORWARD, LEFT, FORWARD, FORWARD, FORWARD, LEFT, FORWARD, FORWARD, RIGHT, BACKWARD)))
        );
    }


}
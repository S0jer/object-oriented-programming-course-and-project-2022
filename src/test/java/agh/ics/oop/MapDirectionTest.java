package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static agh.ics.oop.model.MapDirection.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MapDirectionTest {

    @ParameterizedTest
    @MethodSource("nextVerificationArgumentsProvider")
    void shouldVerifyNext(MapDirection startDirection, MapDirection expectedDirection) {
        //When
        MapDirection result = startDirection.next();

        //Then
        assertThat(result).isEqualTo(expectedDirection);
    }

    static Stream<Arguments> nextVerificationArgumentsProvider() {
        return Stream.of(
                arguments(NORTH, EAST),
                arguments(EAST, SOUTH),
                arguments(SOUTH, WEST),
                arguments(WEST, NORTH)
        );
    }

    @ParameterizedTest
    @MethodSource("previousVerificationArgumentsProvider")
    void previous(MapDirection startDirection, MapDirection expectedDirection) {
        //When
        MapDirection result = startDirection.previous();

        //Then
        assertThat(result).isEqualTo(expectedDirection);
    }

    static Stream<Arguments> previousVerificationArgumentsProvider() {
        return Stream.of(
                arguments(NORTH, WEST),
                arguments(EAST, NORTH),
                arguments(SOUTH, EAST),
                arguments(WEST, SOUTH)
        );
    }
}
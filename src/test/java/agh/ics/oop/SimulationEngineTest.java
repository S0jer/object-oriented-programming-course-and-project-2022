package agh.ics.oop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SimulationEngineTest {

    @ParameterizedTest
    @MethodSource("borderAndMovesArgumentsProvider")
    void shouldTestMovesOfFewAnimals(WorldMap map, List<MoveDirection> moves, List<Vector2d> animals, List<Vector2d> expectedPositions) {
        //when
        Engine engine = new SimulationEngine(moves, map, animals);
        engine.run();

        //then
        for (Vector2d expectedPosition : expectedPositions) {
            Object object = map.objectAt(expectedPosition);
            assertThat(object).isNotNull();
        }
    }

    static Stream<Arguments> borderAndMovesArgumentsProvider() {
        List<String> movesAround = new ArrayList<>(Arrays.asList("r", "r", "f", "l", "f", "f", "f", "l", "f", "f", "r", "b"));
        List<String> movesForAnimals = new ArrayList<>(Arrays.asList("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"));
        OptionsParser optionsParser = new OptionsParser();
        List<MoveDirection> moveDirectionsAround = optionsParser.parse(movesAround);
        List<MoveDirection> moveDirectionsTwo = optionsParser.parse(movesForAnimals);
        List<Vector2d> animals = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));

        return Stream.of(
                arguments(new RectangularMap(10, 5), moveDirectionsTwo, animals, new ArrayList<>(Arrays.asList(new Vector2d(2, 0), new Vector2d(3, 5)))),
                arguments(new RectangularMap(10, 5), moveDirectionsAround, animals, new ArrayList<>(Arrays.asList(new Vector2d(6, 2), new Vector2d(3, 5)))));
    }

}
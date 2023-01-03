package agh.ics.oop;

import agh.ics.oop.model.mapobjects.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.WorldMap;
import agh.ics.oop.model.worldmap.RectangularMap;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AnimalTest {


    @ParameterizedTest
    @MethodSource("isAtTestProvider")
    void isAtTest(Animal givenAnimal, Vector2d positionToCheck, boolean expectedResult) {
        //When
        boolean result = givenAnimal.isAt(positionToCheck);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> isAtTestProvider() {
        WorldMap worldMap = new RectangularMap(new Vector2d(0, 0), new Vector2d(4, 4));
        return Stream.of(
                arguments(new Animal(worldMap), new Vector2d(2, 2), true),
                arguments(new Animal(worldMap), new Vector2d(2, -1), false),
                arguments(new Animal(worldMap), new Vector2d(-2, 2), false),
                arguments(new Animal(worldMap), new Vector2d(-21, 17), false)
        );
    }

    @ParameterizedTest
    @MethodSource("borderAndMovesArgumentsProvider")
    void shouldTestBordersAndProperMoves(Animal animal, List<MoveDirection> path, Vector2d endPosition, MapDirection endOrientation, boolean check) {
        //When
        Animal afterMovesAnimal = doPath(animal, path);
        boolean result = (afterMovesAnimal.isAt(endPosition) && afterMovesAnimal.getAnimalDirection().equals(endOrientation));

        //Then
        assertThat(result).isEqualTo(check);
        assertTrue(Objects.deepEquals(result, check));
    }

    static Stream<Arguments> borderAndMovesArgumentsProvider() {
        List<String> movesBackward = new ArrayList<>(Arrays.asList("b", "b", "b", "b", "b", "b", "b", "b", "b", "b"));
        List<String> movesForward = new ArrayList<>(Arrays.asList("f", "f", "f", "f", "f", "f", "f", "f", "f", "f"));
        List<String> movesAround = new ArrayList<>(Arrays.asList("r", "r", "f", "l", "f", "f", "f", "l", "f", "f", "r", "b"));
        OptionsParser optionsParser = new OptionsParser();

        return Stream.of(
                arguments(AnimalCreator(2, 2, MapDirection.NORTH), optionsParser.parse(movesForward), new Vector2d(2, 4), MapDirection.NORTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.EAST), optionsParser.parse(movesForward), new Vector2d(4, 2), MapDirection.EAST, true),
                arguments(AnimalCreator(2, 2, MapDirection.SOUTH), optionsParser.parse(movesForward), new Vector2d(2, 0), MapDirection.SOUTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.WEST), optionsParser.parse(movesForward), new Vector2d(0, 2), MapDirection.WEST, true),

                arguments(AnimalCreator(2, 2, MapDirection.NORTH), optionsParser.parse(movesBackward), new Vector2d(2, 0), MapDirection.NORTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.EAST), optionsParser.parse(movesBackward), new Vector2d(0, 2), MapDirection.EAST, true),
                arguments(AnimalCreator(2, 2, MapDirection.SOUTH), optionsParser.parse(movesBackward), new Vector2d(2, 4), MapDirection.SOUTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.WEST), optionsParser.parse(movesBackward), new Vector2d(4, 2), MapDirection.WEST, true),

                arguments(AnimalCreator(1, 1, MapDirection.NORTH), optionsParser.parse(movesAround), new Vector2d(3, 2), MapDirection.EAST, true),
                arguments(AnimalCreator(1, 3, MapDirection.EAST), optionsParser.parse(movesAround), new Vector2d(2, 1), MapDirection.SOUTH, true),
                arguments(AnimalCreator(4, 4, MapDirection.WEST), optionsParser.parse(movesAround), new Vector2d(2, 3), MapDirection.NORTH, true),
                arguments(AnimalCreator(0, 0, MapDirection.WEST), optionsParser.parse(movesAround), new Vector2d(0, 2), MapDirection.NORTH, true),
                arguments(AnimalCreator(0, 4, MapDirection.WEST), optionsParser.parse(movesAround), new Vector2d(0, 3), MapDirection.NORTH, true),
                arguments(AnimalCreator(4, 0, MapDirection.SOUTH), optionsParser.parse(movesAround), new Vector2d(2, 0), MapDirection.WEST, true)
        );
    }

    static Animal doPath(Animal animal, List<MoveDirection> path) {
        for (MoveDirection doMove : path) {
            animal.moveAnimal(doMove);
        }
        return animal;
    }

    static Animal AnimalCreator(int x, int y, MapDirection direction) {
        return new Animal(new RectangularMap(new Vector2d(0, 0), new Vector2d(4, 4)), direction, new Vector2d(x, y));
    }
}
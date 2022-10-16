package agh.ics.oop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Vector2dTest {

    @ParameterizedTest
    @MethodSource("precedesVerificationArgumentsProvider")
    void shouldVerifyPrecedes(Vector2d startVector, Vector2d toCompareVector, boolean expectedResult) {
        //When
        boolean result = startVector.precedes(toCompareVector);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("followsVerificationArgumentsProvider")
    void shouldVerifyFollows(Vector2d startVector, Vector2d toCompareVector, boolean expectedResult) {
        //When
        boolean result = startVector.follows(toCompareVector);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("upperRightVerificationArgumentsProvider")
    void shouldVerifyUpperRight(Vector2d vector1, Vector2d vector2, Vector2d expectedVector) {
        //When
        Vector2d upperRightResult = vector1.upperRight(vector2);

        //Then
        assertThat(upperRightResult.x).isEqualTo(expectedVector.x);
        assertThat(upperRightResult.y).isEqualTo(expectedVector.y);
    }

    @ParameterizedTest
    @MethodSource("lowerLeftVerificationArgumentsProvider")
    void shouldVerifyLowerLeft(Vector2d vector1, Vector2d vector2, Vector2d expectedVector) {
        //When
        Vector2d lowerLeftResult = vector1.lowerLeft(vector2);

        //Then
        assertThat(lowerLeftResult.x).isEqualTo(expectedVector.x);
        assertThat(lowerLeftResult.y).isEqualTo(expectedVector.y);
    }

    @ParameterizedTest
    @MethodSource("addVerificationArgumentsProvider")
    void shouldVerifyAdd(Vector2d mainVector, Vector2d toAddVector, Vector2d expectedVector) {
        //When
        Vector2d addResult = mainVector.add(toAddVector);

        //Then
        assertThat(addResult.x).isEqualTo(expectedVector.x);
        assertThat(addResult.y).isEqualTo(expectedVector.y);
    }

    @ParameterizedTest
    @MethodSource("subtractVerificationArgumentsProvider")
    void shouldVerifySubtract(Vector2d mainVector, Vector2d toSubtractVector, Vector2d expectedVector) {
        //When
        Vector2d subtractResult = mainVector.subtract(toSubtractVector);

        //Then
        assertThat(subtractResult.x).isEqualTo(expectedVector.x);
        assertThat(subtractResult.y).isEqualTo(expectedVector.y);
    }

    @ParameterizedTest
    @MethodSource("oppositeVerificationArgumentsProvider")
    void shouldVerifyOpposite(Vector2d toConvertVector, Vector2d expectedVector) {
        //When
        Vector2d convertedVector = toConvertVector.opposite();

        //Then
        assertThat(convertedVector.x).isEqualTo(expectedVector.x);
        assertThat(convertedVector.y).isEqualTo(expectedVector.y);
    }

    @ParameterizedTest
    @MethodSource("equalsVerificationArgumentsProvider")
    void shouldVerifyEquals(Vector2d mainVector, Object toCompareVector, boolean expectedResult) {
        //When
        boolean equalsResult = mainVector.equals(toCompareVector);

        //Then
        assertThat(equalsResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("toStringVerificationArgumentsProvider")
    void shouldVerifyToString(Vector2d toPrintVector, String expectedPrint) {
        //When
        String stringOfVector = toPrintVector.toString();

        //Then
        assertThat(stringOfVector).isEqualTo(expectedPrint);
    }


    static Stream<Arguments> precedesVerificationArgumentsProvider() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 1), true),
                arguments(new Vector2d(-5, -1), new Vector2d(-11, 2), false),
                arguments(new Vector2d(1, 1), new Vector2d(11, -2), false),
                arguments(new Vector2d(35, 70), new Vector2d(10, 25), false),
                arguments(new Vector2d(21, -37), new Vector2d(201, -12), true)
        );
    }

    static Stream<Arguments> followsVerificationArgumentsProvider() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 1), true),
                arguments(new Vector2d(1, 1), new Vector2d(-11, 2), false),
                arguments(new Vector2d(35, 70), new Vector2d(102, 25), false),
                arguments(new Vector2d(21, 37), new Vector2d(201, 122), false),
                arguments(new Vector2d(17, 27), new Vector2d(13, 22), true)
        );
    }

    static Stream<Arguments> upperRightVerificationArgumentsProvider() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 1)),
                arguments(new Vector2d(11, -2), new Vector2d(-11, 2), new Vector2d(11, 2)),
                arguments(new Vector2d(-11, 2), new Vector2d(11, -2), new Vector2d(11, 2)),
                arguments(new Vector2d(-35, -70), new Vector2d(-25, -51), new Vector2d(-25, -51))
        );
    }

    static Stream<Arguments> lowerLeftVerificationArgumentsProvider() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 1)),
                arguments(new Vector2d(11, -2), new Vector2d(-11, 2), new Vector2d(-11, -2)),
                arguments(new Vector2d(-11, 2), new Vector2d(11, -2), new Vector2d(-11, -2)),
                arguments(new Vector2d(-35, -70), new Vector2d(-25, -51), new Vector2d(-35, -70))
        );
    }

    static Stream<Arguments> addVerificationArgumentsProvider() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(2, 2)),
                arguments(new Vector2d(11, -2), new Vector2d(-11, 2), new Vector2d(0, 0)),
                arguments(new Vector2d(-11, 2), new Vector2d(11, -2), new Vector2d(0, 0)),
                arguments(new Vector2d(-35, -70), new Vector2d(-25, -51), new Vector2d(-60, -121))
        );
    }

    static Stream<Arguments> subtractVerificationArgumentsProvider() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(0, 0)),
                arguments(new Vector2d(11, -2), new Vector2d(-11, 2), new Vector2d(22, -4)),
                arguments(new Vector2d(-11, 2), new Vector2d(11, -2), new Vector2d(-22, 4)),
                arguments(new Vector2d(-35, -70), new Vector2d(-25, -51), new Vector2d(-10, -19))
        );
    }

    static Stream<Arguments> oppositeVerificationArgumentsProvider() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(-1, -1)),
                arguments(new Vector2d(11, -2), new Vector2d(-11, 2)),
                arguments(new Vector2d(-11, 2), new Vector2d(11, -2)),
                arguments(new Vector2d(-35, -70), new Vector2d(35, 70))
        );
    }

    static Stream<Arguments> equalsVerificationArgumentsProvider() {
        Vector2d sameVector = new Vector2d(21, 37);
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(-1, -1), false),
                arguments(new Vector2d(11, -2), new Vector2d(-11, 2), false),
                arguments(new Vector2d(-11, 2), new Vector2d(11, -2), false),
                arguments(new Vector2d(-11, 2), new Object(), false),
                arguments(new Vector2d(-11, 2), null, false),
                arguments(sameVector, sameVector, true),
                arguments(new Vector2d(-35, -70), new Vector2d(-35, -70), true)
        );
    }

    static Stream<Arguments> toStringVerificationArgumentsProvider() {
        return Stream.of(
                arguments(new Vector2d(1, 1), "(1,1)"),
                arguments(new Vector2d(11, -2), "(11,-2)"),
                arguments(new Vector2d(-11, 22), "(-11,22)"),
                arguments(new Vector2d(-35, -70), "(-35,-70)")
        );
    }

}
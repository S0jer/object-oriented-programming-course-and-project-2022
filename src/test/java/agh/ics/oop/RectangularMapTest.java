package agh.ics.oop;

import agh.ics.oop.model.mapobjects.Animal;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.WorldMap;
import agh.ics.oop.model.worldmap.RectangularMap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangularMapTest {

    @Test
    void shouldBeAbleToMoveAnimalInEmptyMap() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));


        //when
        boolean result = map.canMoveTo(new Vector2d(1, 1));

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotBeAbleToMoveAnimalOccupiedPosition() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));
        map.place(new Animal(map, new Vector2d(1, 2)));

        //when
        boolean result = map.canMoveTo(new Vector2d(1, 2));

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldBeAbleToPlaceAnimalOnFreePosition() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));
        Animal animal = new Animal(map, new Vector2d(1, 1));

        //when
        boolean result = map.place(animal);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotBeAbleToPlaceAnimal() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));
        Animal animal = new Animal(map, new Vector2d(1, 1));
        String expectedMessage = "Can not place WorldMapObject at (1,1)";

        //when
        map.place(animal);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            map.place(animal);
        });

        String actualMessage = exception.getMessage();

        //Then
        assertThat(actualMessage).contains(expectedMessage);
    }

    @Test
    void shouldReturnThatPositionIsOccupied() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));
        Vector2d animalPosition = new Vector2d(1, 1);
        Animal animal = new Animal(map, animalPosition);

        //when
        map.place(animal);
        boolean result = map.isOccupied(animalPosition);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnThatPositionIsNotOccupied() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));
        Vector2d positionToCheck = new Vector2d(1, 1);

        //when
        boolean result = map.isOccupied(positionToCheck);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnNullFromEmptyPlace() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));

        //when
        Object object = map.objectAt(new Vector2d(1, 1));

        //then
        assertThat(object).isNull();
    }

    @Test
    void shouldReturnAnimalFromOccupiedPlace() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));
        Vector2d animalPosition = new Vector2d(1, 1);
        Animal animal = new Animal(map, animalPosition);

        //when
        map.place(animal);
        Object object = map.objectAt(animalPosition);

        //then
        assertThat(object).isEqualTo(animal);
    }

    @Test
    void shouldNotBeAbleToMoveBehindBorders() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));

        //when
        boolean result = map.canMoveTo(new Vector2d(10, 10));

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldNotBeAbleToPlaceBehindBorders() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));
        Animal animal = new Animal(map, new Vector2d(10, 10));

        //when
        boolean result = map.place(animal);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldGetNullIfBehindBorders() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));

        //when
        Object result = map.objectAt(new Vector2d(10, 10));

        //then
        assertThat(result).isNull();
    }

    @Test
    void shouldReturnFalseWhenIndexOutsideOfMap() {
        //given
        WorldMap map = new RectangularMap(new Vector2d(0, 0), new Vector2d(5, 5));

        //when
        boolean result = map.isOccupied(new Vector2d(-1, -1));

        //then
        assertThat(result).isFalse();
    }

}
package agh.ics.oop;

import agh.ics.oop.model.mapobjects.Animal;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.worldmap.GrassField;
import agh.ics.oop.model.worldmap.WorldMap;
import agh.ics.oop.model.worldmap.RectangularMap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RectangularMapTest {

    @Test
    void shouldBeAbleToMoveAnimalInEmptyMap() {
        //given
        WorldMap map = new RectangularMap();

        //when
        boolean result = map.canMoveTo(new Vector2d(1, 1));

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotBeAbleToMoveAnimalOccupiedPosition() {
        //given
        WorldMap map = new RectangularMap();
        map.place(new Animal(map, new Vector2d(1, 2)));

        //when
        boolean result = map.canMoveTo(new Vector2d(1, 2));

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldBeAbleToPlaceAnimalOnFreePosition() {
        //given
        WorldMap map = new RectangularMap();
        Animal animal = new Animal(map, new Vector2d(1, 1));

        //when
        boolean result = map.place(animal);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotBeAbleToPlaceAnimal() {
        //given
        WorldMap map = new RectangularMap();
        Animal animal = new Animal(map, new Vector2d(1, 1));

        //when
        map.place(animal);
        boolean result = map.place(animal);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnThatPositionIsOccupied() {
        //given
        WorldMap map = new RectangularMap();
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
        WorldMap map = new RectangularMap();
        Vector2d positionToCheck = new Vector2d(1, 1);

        //when
        boolean result = map.isOccupied(positionToCheck);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnNullFromEmptyPlace() {
        //given
        WorldMap map = new RectangularMap();

        //when
        Object object = map.objectAt(new Vector2d(1, 1));

        //then
        assertThat(object).isNull();
    }

    @Test
    void shouldReturnAnimalFromOccupiedPlace() {
        //given
        WorldMap map = new RectangularMap();
        Vector2d animalPosition = new Vector2d(1, 1);
        Animal animal = new Animal(map, animalPosition);

        //when
        map.place(animal);
        Object object = map.objectAt(animalPosition);

        //then
        assertThat(object).isEqualTo(animal);
    }


    @Test
    void shouldGetNullIfBehindBorders() {
        //given
        WorldMap map = new RectangularMap();

        //when
        Object result = map.objectAt(new Vector2d(10, 10));

        //then
        assertThat(result).isNull();
    }

    @Test
    void shouldReturnFalseWhenIndexOutsideOfMap() {
        //given
        WorldMap map = new RectangularMap();

        //when
        boolean result = map.isOccupied(new Vector2d(-1, -1));

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldGrassIsRemoved() {
        //given
        WorldMap map = new GrassField(0);
        Vector2d animalPosition = new Vector2d(1, 1);
        Animal animal = new Animal(map, animalPosition);
        map.place(animal);

        //when
        map.remove(animalPosition);

        //then
        assertThat(map.objectAt(animalPosition)).isNull();
    }
}
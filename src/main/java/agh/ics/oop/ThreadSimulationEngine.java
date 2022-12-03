package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.mapobjects.Animal;
import agh.ics.oop.model.worldmap.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class ThreadSimulationEngine implements Engine, Runnable {
    private List<MoveDirection> directions;
    private final WorldMap map;
    private final List<PositionChangeObserver> observers = new ArrayList<>();
    private final int moveDelay;

    public ThreadSimulationEngine(List<MoveDirection> directions, WorldMap map, List<Vector2d> initialPositions) {
        this.directions = directions;
        this.map = map;
        this.moveDelay = 1000;

        for (Vector2d pos : initialPositions) {
            Animal animal = new Animal(map, pos);
            map.place(animal);
        }
    }

    public ThreadSimulationEngine(WorldMap map, List<Vector2d> initialPositions) {
        this.directions = new ArrayList<>();
        this.map = map;
        this.moveDelay = 1000;

        for (Vector2d pos : initialPositions) {
            Animal animal = new Animal(map, pos);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        List<Animal> animals = putAnimalsOnMap();
        int numberOfAnimals = animals.size();
        int i = 0;
        System.out.println(map);
        for (MoveDirection move: directions){
            Vector2d oldPosition = animals.get(i%numberOfAnimals).getPosition();
            animals.get(i%numberOfAnimals).moveAnimal(move);
            Vector2d newPosition = animals.get(i%numberOfAnimals).getPosition();
            positionChanged(oldPosition, newPosition);
            i++;
            System.out.println(map);
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Animal> putAnimalsOnMap (){
        List<Animal> animals = new ArrayList<>();
        List<Vector2d> borders = this.map.getBorders();
        Vector2d lowerBorder = borders.get(0);
        Vector2d upperBorder = borders.get(1);
        int minX = lowerBorder.getX();
        int minY = lowerBorder.getY();
        int maxX = upperBorder.getX();
        int maxY = upperBorder.getY();

        for (int x = minX;x <= maxX; x++){
            for (int y = minY; y <= maxY; y++){
                Vector2d pos = new Vector2d(x,y);
                if (map.isOccupied(pos) && map.objectAt(pos) instanceof Animal animal){
                    animals.add(animal);
                }
            }
        }
        return animals;
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }

    public void addObserver(PositionChangeObserver observer) {
        observers.add(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        observers.forEach(e -> e.positionChanged(oldPosition, newPosition));
    }
}

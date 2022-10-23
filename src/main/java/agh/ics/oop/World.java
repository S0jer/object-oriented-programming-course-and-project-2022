package agh.ics.oop;

import java.util.List;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal);
    }



    static void run(List<MoveDirection> path) {
        path.forEach(instruction -> {
            switch (instruction) {
                case FORWARD -> properPrint("Zwierzak idzie do przodu");
                case BACKWARD -> properPrint("Zwierzak idzie do tylu");
                case LEFT -> properPrint("Zwierzak skręca w lewo");
                case RIGHT -> properPrint("Zwierzak skręca w prawo");
            }
        });
    }

    static void properPrint(String message) {
        String prefix = ",";
        System.out.print(prefix + message);
    }
}

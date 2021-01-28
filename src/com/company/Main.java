package com.company;

import java.util.concurrent.TimeUnit;

public class Main {

    private static World _world;

    public static void main(String[] args) throws InterruptedException {
        createInitialWorldState(args[0], args[1], args[2]);
        RunWorldUntilExit();
    }

    private static void createInitialWorldState(String worldInputAtStart, String worldWidth, String worldHeight) {
        _world = new DefaultWorld(worldInputAtStart, worldHeight, worldWidth);
    }

    private static void RunWorldUntilExit() throws InterruptedException {
        while (true) {
            clearScreen();
            System.out.println(_world.getWorldState());
            _world.updateWorld();
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}

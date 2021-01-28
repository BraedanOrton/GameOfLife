package com.company;

public interface World {
    String getWorldState();

    void updateWorld() throws InterruptedException;
}

package com.company;

import java.util.ArrayList;

public class DefaultWorld implements World {
    private int _worldWidth;
    private int _worldHeight;
    private DefaultCell[][] _cell;


    public DefaultWorld(String worldInputAtStart, String worldHeight, String worldWidth) {

        _worldWidth = Integer.parseInt(worldWidth);
        _worldHeight = Integer.parseInt(worldHeight);
        _cell = new DefaultCell[_worldWidth][_worldHeight];

        initializeCells(worldInputAtStart);
    }

    private void initializeCells(String worldInputAtStart) {
        int inputIndex = 0;
        for (int height = 0; height < _worldHeight; height++) {
            for (int width = 0; width < _worldWidth; width++) {
                _cell[width][height] = new DefaultCell(worldInputAtStart.substring(inputIndex, ++inputIndex));
            }
        }
    }


    @Override
    public String getWorldState() {
        String printableWorldState = "";
        for (int height = 0; height < _worldHeight; height++) {
            for (int width = 0; width < _worldWidth; width++) {
                printableWorldState = printableWorldState + " " + _cell[width][height].toString();
            }
            printableWorldState = printableWorldState + "\n";
        }

        return printableWorldState;
    }


    @Override
    public void updateWorld() throws InterruptedException {

        DefaultCell[][] newWorld = new DefaultCell[_worldWidth][_worldHeight];
        ArrayList<Cell> neighbors;

        for (int width = 0; width < _worldWidth; width++) {
            for (int height = 0; height < _worldHeight; height++) {
                neighbors = getCellNeighbors(width, height);
                newWorld[width][height] = _cell[width][height].growDieOrContinue(neighbors);
            }
        }
        _cell = newWorld;
    }

    private ArrayList<Cell> getCellNeighbors(int width, int height) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        //left
        if (width != 0) {
            neighbors.add(_cell[width - 1][height]);
        }
        //right
        if (width != _worldWidth-1) {
            neighbors.add(_cell[width + 1][height]);
        }
        //down
        if (height != 0) {
            neighbors.add(_cell[width][height - 1]);
        }
        //up
        if (height != _worldHeight - 1) {
            neighbors.add(_cell[width][height + 1]);
        }
        //down left
        if (height != 0 && width != 0) {
            neighbors.add(_cell[width - 1][height - 1]);
        }
        //up right
        if (height != _worldHeight - 1 && width != _worldWidth - 1) {
            neighbors.add(_cell[width + 1][height + 1]);
        }
        //bottom right
        if (height != 0 && width != _worldWidth - 1) {
            neighbors.add(_cell[width + 1][height - 1]);
        }
        //up left
        if (height != _worldHeight - 1 && width != 0) {
            neighbors.add(_cell[width - 1][height + 1]);
        }
        return neighbors;
    }
}

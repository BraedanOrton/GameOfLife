package com.company;

import java.util.ArrayList;

public class DefaultCell implements Cell {
    private static final String DEAD = ".";
    private static final String ALIVE = "O";
    private boolean _alive;

    public DefaultCell(String cellState) {
        if (cellState.equals(ALIVE)) {
            _alive = true;
        } else if (cellState.equals(DEAD)) {
            _alive = false;
        }
    }

    public String toString() {
        if (_alive) {
            return "O";
        } else {
            return ".";
        }
    }

    public boolean isAlive() {
        return _alive;
    }

    public DefaultCell growDieOrContinue(ArrayList<Cell> neighbors) {
        int livingNeighbors = getLivingNeighbors(neighbors);

        if (_alive) {
            if (livingNeighbors == 2 || livingNeighbors == 3) {
                return new DefaultCell(ALIVE);
            } else {
                return new DefaultCell(DEAD);
            }
        } else if (livingNeighbors == 3) {
            return new DefaultCell(ALIVE);
        } else {
            return new DefaultCell(DEAD);
        }
    }

    private int getLivingNeighbors(ArrayList<Cell> neighbors) {
        int livingNeighbors = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.isAlive()) {
                livingNeighbors++;
            }
        }
        return livingNeighbors;
    }

}

package com.awei.tank;

import java.util.Random;

public enum Dir {
    L, U, R, D;

    private static Random random = new Random();

    public static Dir randomDir() {
        return values()[random.nextInt(values().length)];
    }
}

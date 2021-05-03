package com.awei.tank.strategy;

import com.awei.tank.Player;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Player p);
}

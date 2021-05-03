package com.awei.tank.strategy;

import com.awei.tank.*;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Player p) {
        Dir[] values = Dir.values();
        int bx = p.getX() + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = p.getY() + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        for (int i = 0; i < values.length; i++) {
            TankFrame.INSTANCE.add(new Bullet(bx, by, values[i], p.getGroup()));
        }
    }
}

package com.awei.tank.chainofresponsebility;

import com.awei.tank.AbstractGameObject;
import com.awei.tank.Bullet;
import com.awei.tank.ResourceMgr;
import com.awei.tank.Tank;

import java.awt.*;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Tank) {
            Bullet b = (Bullet) go1;
            Tank t = (Tank) go2;
            if (!b.isLive() || !t.isLive()) return false;

            if (b.getGroup() == t.getGroup()) return true;

            Rectangle rectTank = t.getRect();
            if (b.getRect().intersects(rectTank)) {
                b.die();
                t.die();
                return false;
            }
        } else if (go1 instanceof Tank && go2 instanceof Bullet) {
            return collide(go2, go1);
        }
        return true;
    }
}

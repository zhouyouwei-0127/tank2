package com.awei.tank.chainofresponsebility;

import com.awei.tank.AbstractGameObject;

public interface Collider {
    boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}

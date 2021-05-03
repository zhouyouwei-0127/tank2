package com.awei.tank.chainofresponsebility;

import com.awei.tank.AbstractGameObject;

import java.io.Serializable;

public interface Collider extends Serializable {
    boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}

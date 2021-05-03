package com.awei.tank;

import lombok.Data;

import java.awt.*;

@Data
public class Explode extends AbstractGameObject {
    
    private int x, y;
    private boolean live = true;
    private int width, height;
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[1].getHeight();
    }

    public void paint(Graphics g) {
        if (!live) {
            return;
        }
        g.drawImage(ResourceMgr.explodes[step], x, y, null);
        step++;
        if (step >= ResourceMgr.explodes.length) {
            die();
        }
    }
    
    private void die() {
        this.live = false;
    }
}

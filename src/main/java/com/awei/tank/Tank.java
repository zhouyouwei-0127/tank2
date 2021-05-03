package com.awei.tank;

import lombok.Data;

import java.awt.*;
import java.util.Random;

@Data
public class Tank extends AbstractGameObject {

    private int x;
    private int y;
    private static final int SPEED = 5;
    private Dir dir;
    private Group group;
    private boolean bL, bU, bR, bD;
    private boolean moving = true;
    private boolean live = true;
    private Random random = new Random();
    private int oldX, oldY;
    private int width, height;
    private Rectangle rect;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        this.width = ResourceMgr.goodTankU.getWidth();
        this.height = ResourceMgr.goodTankU.getHeight();
        rect = new Rectangle(x, y, width, height);
    }

    public void paint(Graphics g) {
        if (!live) {
            return;
        }
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
        rect.x = x;
        rect.y = y;

        if (random.nextInt(100) > 90) {
            fire();
        }
    }
    
    private void fire() {
        int bx = x + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = y + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.getGm().add(new Bullet(bx, by, dir, group));
    }

    private void move() {
        if (!moving) {
            return;
        }
        
        oldX = x;
        oldY = y;
        
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }

        boundsCheck();
        
        if (random.nextInt(100) > 90) {
            randomDir();
        }
    }

    private void randomDir() {
        dir = Dir.randomDir();
    }
    
    private void boundsCheck() {
        if (x < 0 || y < 30 || x + width > TankFrame.GAME_WIDTH || y + height > TankFrame.GAME_HEIGHT) {
            this.back();
        }
    }

    public void back() {
        x = oldX;
        y = oldY;
    }
    
    public void die() {
        live = false;
        TankFrame.INSTANCE.getGm().add(new Explode(x, y));
    }

    public Rectangle getRect() {
        return rect;
    }
}

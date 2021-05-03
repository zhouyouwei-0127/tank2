package com.awei.tank;

import lombok.Data;

import java.awt.*;

@Data
public class Bullet extends AbstractGameObject {
    
    private int x, y;
    private int w = ResourceMgr.bulletU.getWidth();
    private int h = ResourceMgr.bulletU.getHeight();
    private static final int SPEED = 6;
    private Dir dir;
    private Group group;
    private boolean live = true;
    private Rectangle rect;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect = new Rectangle(x, y, w, h);
    }

    public void paint(Graphics g) {
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();

        rect.x = this.x;
        rect.y = this.y;
        
        //子弹的rect
        /*Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.setColor(c);*/
    }

    private void move() {
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
        
        //边界检测
        boundsCheck();
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }
    
    public Rectangle getRect() {
        return rect;
    }

    public void die() {
        this.setLive(false);
    }
    
}

package com.awei.tank;

import com.awei.tank.chainofresponsebility.ColliderChain;

import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements Serializable {
    private Player myTank;
    private List<AbstractGameObject> objects;
    private ColliderChain chain = new ColliderChain();

    public GameModel() {
        initGameObjects();
    }

    private void initGameObjects() {
        myTank = new Player(100,100, Dir.R, Group.GOOD);
        objects = new ArrayList<>();

        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            this.add(new Tank(100 + i * 70, 400, Dir.D, Group.BAD));
        }

        /*this.add(new Wall(300, 200, 400, 50));*/
    }

    public void add(AbstractGameObject go) {
        objects.add(go);
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("objects：" + objects.size(), 10, 50);
        g.setColor(color);
        myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            if (!objects.get(i).isLive()) {
                objects.remove(i);
                break;
            }
        }
        
        for (int i = 0; i < objects.size(); i++) {
            
            AbstractGameObject go1 = objects.get(i);
            for (int j = 0; j < objects.size(); j++) {
                AbstractGameObject go2 = objects.get(j);
                chain.collide(go1, go2);
            }
            if (objects.get(i).isLive()) {
                objects.get(i).paint(g);
            }
        }
    }

    Player getMyTank() {
        return myTank;
    }
    
}

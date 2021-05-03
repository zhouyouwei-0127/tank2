package com.awei.tank;

import com.awei.tank.chainofresponsebility.ColliderChain;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 1200, GAME_HEIGHT = 900; //窗口的宽高
    private Player myTank;
    private List<AbstractGameObject> objects;
    private ColliderChain chain = new ColliderChain();
    

    private TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.addKeyListener(new TankKeyListener());

        initGameObjects();
    }

    

    private void initGameObjects() {
        myTank = new Player(100,50, Dir.R, Group.GOOD);
        objects = new ArrayList<>();

        Integer initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            this.add(new Tank(200 + i * 50, 400, Dir.D, Group.BAD));
        }

        this.add(new Wall(300, 200, 400, 50));
    }

    public void add(AbstractGameObject go) {
        objects.add(go);
    }

    @Override
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

    //取消闪烁--把东西先画在一张图片上，然后把图片画到屏幕上
    //repaint方法会先调用update方法，再调用paint方法
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }
    
    private class TankKeyListener extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}

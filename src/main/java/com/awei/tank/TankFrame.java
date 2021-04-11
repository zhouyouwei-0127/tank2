package com.awei.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 1200, GAME_HEIGHT = 900; //窗口的宽高
    private Player myTank;
    private List<Tank> tanks;
    public List<Bullet> bullets;
    public List<Explode> explodes;

    private TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.addKeyListener(new TankKeyListener());

        initGameObjects();
    }

    private void initGameObjects() {
        myTank = new Player(100,50, Dir.R, Group.GOOD);
        bullets = new ArrayList<>();
        tanks = new ArrayList<>();
        explodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tanks.add(new Tank(100 + i * 50, 200, Dir.D, Group.BAD));
        }
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullet：" + bullets.size(), 10, 50);
        g.drawString("tanks：" + tanks.size(), 10, 70);
        g.drawString("explodes：" + explodes.size(), 10, 90);
        g.setColor(color);
        myTank.paint(g);
        
        for (int i = 0; i < tanks.size(); i++) {
            if (!tanks.get(i).isLive()) {
                tanks.remove(i);
            } else {
                tanks.get(i).paint(g);
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collidesWithTank(tanks.get(j));
            }
            
            if (!bullets.get(i).isLive()) {
                bullets.remove(i);
            } else {
                bullets.get(i).paint(g);
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            if (!explodes.get(i).isLive()) {
                explodes.remove(i);
            } else {
                explodes.get(i).paint(g);
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

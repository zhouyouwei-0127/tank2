package com.awei.tank;

import lombok.Data;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Data
public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 1200, GAME_HEIGHT = 900; //窗口的宽高
    private GameModel gm = new GameModel();

    private TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.addKeyListener(new TankKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
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
            gm.getMyTank().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gm.getMyTank().keyReleased(e);
        }
    }
}

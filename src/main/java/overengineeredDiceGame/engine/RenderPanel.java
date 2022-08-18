package overengineeredDiceGame.engine;

import overengineeredDiceGame.logic.GameLogic;
import overengineeredDiceGame.logic.SpriteInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RenderPanel extends JPanel implements Runnable {

    private final double fps;
    private final int spriteScaling = 6;
    volatile Thread gameThread;
    private final GameLogic gameLogic;

    public RenderPanel(int width, int height, double fps, GameLogic gameLogic) {
        this.fps = fps;
        this.gameLogic = gameLogic;

        setSize(width, height);
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        addMouseListener(new ButtonHandler(gameLogic));

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ArrayList<SpriteInfo> renderInfo = gameLogic.getRenderInfo();

        g2.setColor(null);
        for (SpriteInfo spriteInfo : renderInfo) {
            BufferedImage sprite = SpriteLoader.getSpriteImage(spriteInfo.getSpriteName());
            g2.drawImage(sprite, spriteInfo.getPosX(), spriteInfo.getPosY(), sprite.getWidth() * spriteScaling, sprite.getHeight() * spriteScaling, this);
        }

        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1_000_000;

                if (remainingTime < 0) {
                    System.out.println("Rendering took longer than expected. Took " + Math.sqrt(Math.pow(remainingTime, 2)) + "ms.");
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

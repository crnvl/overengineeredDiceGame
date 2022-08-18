package overengineeredDiceGame;

import overengineeredDiceGame.engine.RenderPanel;
import overengineeredDiceGame.engine.SpriteLoader;
import overengineeredDiceGame.logic.GameLogic;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Loading Sprites...");
        SpriteLoader.preloadSprites();

        System.out.println("Creating game window...");
        JFrame frame = new JFrame();
        int scale = Integer.parseInt(args[0]);
        frame.setSize(160 * scale, 144 * scale);
        frame.setTitle("Overengineered Dice Game (wtf am I doing)");
        frame.setResizable(false);

        GameLogic gameLogic = new GameLogic();
        RenderPanel panel = new RenderPanel(frame.getWidth(), frame.getHeight(), 5, gameLogic);

        frame.add(panel);
        frame.setVisible(true);
    }
}

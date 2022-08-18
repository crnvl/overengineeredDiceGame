package overengineeredDiceGame.engine;

import overengineeredDiceGame.logic.GameLogic;
import overengineeredDiceGame.logic.SpriteInfo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonHandler implements MouseListener {

    private final GameLogic gameLogic;

    public ButtonHandler(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < gameLogic.getRenderInfo().size(); i++) {
            SpriteInfo element = gameLogic.getRenderInfo().get(i);

            if (e.getX() > element.getPosX() && e.getX() < element.getPosX() + 16 * 6 && e.getY() > element.getPosY() && e.getY() < element.getPosY() + 16 * 6) {
                switch (element.getSpriteName()) {
                    case "lock_open":
                        gameLogic.toggleLock(i - 5);
                        break;
                    case "cup":
                        gameLogic.roll();
                        break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

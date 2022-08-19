package overengineeredDiceGame.logic;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private final ArrayList<SpriteInfo> renderInfo;
    private final boolean[] locked;
    private int turns;
    private boolean print = true;

    public GameLogic() {
        renderInfo = new ArrayList<>();
        locked = new boolean[5];
        turns = 3;
        for (int i = 0; i < 12; i++) {
            renderInfo.add(i, new SpriteInfo("fallback", 0, 0));
        }

        renderInfo.set(10, new SpriteInfo("counter_" + turns, 25 + (16 * 5 * 6), 16));
        renderInfo.set(11, new SpriteInfo("cup", 25 + (16 * 5 * 6), 16 * 6));
    }

    public ArrayList<SpriteInfo> getRenderInfo() {
        return renderInfo;
    }

    public void roll() {
        if (turns < 1) {
            checkDices();
            return;
        }

        for (int i = 0; i < locked.length; i++) {
            if (locked[i]) {
                renderInfo.set(i, new SpriteInfo(renderInfo.get(i).getSpriteName(), 15 + (16 * i * 6), 16));
                renderInfo.set(i + 5, new SpriteInfo("lock_closed", 15 + (16 * i * 6), 16 * 6));
            } else {
                int number = new Random().nextInt(6) + 1;
                renderInfo.set(i, new SpriteInfo(Integer.toString(number), 15 + (16 * i * 6), 16));
                renderInfo.set(i + 5, new SpriteInfo("lock_open", 15 + (16 * i * 6), 16 * 6));
            }
        }
        turns--;
        renderInfo.set(10, new SpriteInfo("counter_" + turns, 15 + (16 * 5 * 6), 16));
    }

    public void toggleLock(int dice) {
        if (locked[dice])
            return;

        locked[dice] = true;
        renderInfo.set(dice + 5, new SpriteInfo("lock_closed", 15 + (16 * dice * 6), 16 * 6));
    }

    public void endGame() {
        checkDices();
    }

    private void checkDices() {
        if (!print)
            return;

        int[] countNumbers = new int[6];
        for (int i = 0; i < 5; i++) {
            countNumbers[Integer.parseInt(renderInfo.get(i).getSpriteName()) - 1] += 1;
        }

        int row = 1;
        for (int i = 0; i < countNumbers.length; i++) {
            if (countNumbers[i] > 1) {
                row++;
                for (int j = 0; j < countNumbers[i]; j++) {
                    renderInfo.add(new SpriteInfo(Integer.toString(i + 1), 15 + (16 * j * 6), 16 + (16 * row * 6)));
                }
            }
        }

        int streak = 1;
        int streetStart = 0;
        for (int i = 0; i < countNumbers.length; i++) {
            if (i > 0 && countNumbers[i] > 0 && countNumbers[i - 1] > 0)
                streak++;
            else
                streetStart = i;
        }

        if (streak > 3) {
            row++;
            for (int i = streetStart; i < streak; i++) {
                renderInfo.add(new SpriteInfo(Integer.toString(i + 1), 15 + (16 * i * 6), 16 + (16 * row * 6)));
            }
        }
        print = false;
    }
}

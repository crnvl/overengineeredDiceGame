package overengineeredDiceGame.logic;

import java.util.Random;

public class Wuerfel {
    private int augenzahl;
    public Wuerfel() {
        augenzahl = 0;
    }

    public void wuerfeln() {
        augenzahl = new Random().nextInt(6) + 1;
    }

    public int gibAugenzahl() {
        return augenzahl;
    }
}
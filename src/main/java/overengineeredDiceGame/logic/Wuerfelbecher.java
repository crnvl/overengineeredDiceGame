package overengineeredDiceGame.logic;

import java.util.Arrays;

public class Wuerfelbecher {
    private int[] augenzahlen;

    private Wuerfel w1;
    private Wuerfel w2;
    private Wuerfel w3;
    private Wuerfel w4;
    private Wuerfel w5;

    public Wuerfelbecher(int[] augenzahlen) {
        this.augenzahlen = augenzahlen;
        w1 = new Wuerfel();
        w2 = new Wuerfel();
        w3 = new Wuerfel();
        w4 = new Wuerfel();
        w5 = new Wuerfel();
    }

    public int[] gibAugenzahlen() {
        return augenzahlen;
    }

    public int gibAugenzahl(int index) {
        return augenzahlen[index];
    }

    public int gibAugensumme() {
        int sum = 0;
        for (int i = 0; i < augenzahlen.length; i++) {
            sum += augenzahlen[i];
        }
        return sum;
    }

    public void schuetteln() {
        w1.wuerfeln();
        augenzahlen[0] = w1.gibAugenzahl();
        w2.wuerfeln();
        augenzahlen[1] = w2.gibAugenzahl();
        w3.wuerfeln();
        augenzahlen[2] = w3.gibAugenzahl();
        w4.wuerfeln();
        augenzahlen[3] = w4.gibAugenzahl();
        w5.wuerfeln();
        augenzahlen[4] = w5.gibAugenzahl();
    }

    public boolean wuerfelSindDreierPasch() {
        for (int zahl = 1; zahl <= 6; zahl++) {
            int anzahl = 0;
            for (int i = 0; i < 5; i++) {
                if(augenzahlen[i] == zahl)
                    anzahl++;
            }

            if (anzahl == 3)
                return true;
        }
        return false;
    }

    public boolean wuerfelSindViererPasch() {
        for (int zahl = 1; zahl <= 6; zahl++) {
            int anzahl = 0;
            for (int i = 0; i < 5; i++) {
                if(augenzahlen[i] == zahl)
                    anzahl++;
            }

            if (anzahl == 4)
                return true;
        }
        return false;
    }

    public boolean wuerfelSindKniffel() {
        for (int zahl = 1; zahl <= 6; zahl++) {
            int anzahl = 0;
            for (int i = 0; i < 5; i++) {
                if(augenzahlen[i] == zahl)
                    anzahl++;
            }

            if (anzahl == 5)
                return true;
        }
        return false;
    }

    public boolean wuerfelSindKleineStrasse() {
        int[] kleineStrasse = new int[]{1, 2, 3, 4, 1};


    }

    public void sortiereWuerfel() {
        for (int i = 0; i < augenzahlen.length - 1; i++) {
            for (int j = 0; j < augenzahlen.length - 1; j++) {
                if(augenzahlen[i] > augenzahlen[i + 1]) {
                    int temp = augenzahlen[i];
                    augenzahlen[i] = augenzahlen[i + 1];
                    augenzahlen[i + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Wuerfelbecher w = new Wuerfelbecher(new int[5]);
        System.out.println(w.wuerfelSindKleineStrasse());;
    }
}

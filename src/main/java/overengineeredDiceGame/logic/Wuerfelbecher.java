package overengineeredDiceGame.logic;

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
        for (int j : augenzahlen) {
            sum += j;
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
                if (augenzahlen[i] == zahl)
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
                if (augenzahlen[i] == zahl)
                    anzahl++;
            }

            if (anzahl == 4)
                return true;
        }
        return false;
    }

    public boolean wuerfelSindKniffel() {
        for (int i = 0; i < augenzahlen.length - 1; i++) {
            if (augenzahlen[i + 1] != augenzahlen[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean wuerfelSindKleineStrasse() {
        sortiereWuerfel();
        int streak = 0;
        for (int i = 0; i < augenzahlen.length - 1; i++) {
            if (augenzahlen[i + 1] != augenzahlen[i] + 1) {
                streak = 0;
            }else {
                streak++;
            }
            if(streak == 3)
                return true;
        }
        return false;
    }

    public boolean wuerfelSindGrosseStrasse() {
        sortiereWuerfel();
        int streak = 0;
        for (int i = 0; i < augenzahlen.length - 1; i++) {
            if (augenzahlen[i + 1] != augenzahlen[i] + 1) {
                streak = 0;
            }else {
                streak++;
            }
            if(streak == 4)
                return true;
        }
        return false;
    }

    public boolean wuerfelSindFullHouse() {
        int[] count = new int[6];
        for (int j : augenzahlen) {
            count[j - 1] += 1;
        }

        boolean three = false, two = false;
        for (int j : count) {
            if (j == 3)
                three = true;
            if (j == 2)
                two = true;
        }

        return (three && two);
    }

    public void sortiereWuerfel() {
        for (int i = 0; i < augenzahlen.length - 1; i++) {
            for (int j = 0; j < augenzahlen.length - 1; j++) {
                if (augenzahlen[i] > augenzahlen[i + 1]) {
                    int temp = augenzahlen[i];
                    augenzahlen[i] = augenzahlen[i + 1];
                    augenzahlen[i + 1] = temp;
                }
            }
        }
    }
}

package overengineeredDiceGame.logic;

public record SpriteInfo(String spriteName, int posX, int posY) {
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getSpriteName() {
        return spriteName;
    }
}

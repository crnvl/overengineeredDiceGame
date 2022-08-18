package overengineeredDiceGame.engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class SpriteLoader {

    public static HashMap<String, byte[]> sprites;

    public static void preloadSprites() throws IOException {
        sprites = new HashMap<>();

        long preCalc = new Date().getTime();
        File assets = new File("./src/main/java/overengineeredDiceGame/engine/assets/");

        for (int i = 0; i < Objects.requireNonNull(assets.listFiles()).length; i++) {
            File sprite = Objects.requireNonNull(assets.listFiles())[i];
            sprites.put(sprite.getName().replace(".png", ""), spriteTo2DArray(sprite));
        }
        System.out.println("Finished preloading in " + (new Date().getTime() - preCalc) + "ms.");
    }

    private static byte[] spriteTo2DArray(File sprite) throws IOException {
        System.out.println("Loading " + sprite.getName());
        return Files.readAllBytes(sprite.toPath());
    }

    public static BufferedImage getSpriteImage(String spriteName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(SpriteLoader.sprites.get(spriteName)));
        } catch (IOException e) {
            try {
                img = ImageIO.read(new ByteArrayInputStream(SpriteLoader.sprites.get("fallback")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return img;
    }
}

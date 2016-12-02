package me.checco.game.graphics.spritesheet;

/**
 * Created by Checco on 01/12/2016.
 */
public class NotValidSpriteSheet extends Exception {
    public NotValidSpriteSheet() {
        super();
    }

    public NotValidSpriteSheet(String message) {
        super(message);
    }
}

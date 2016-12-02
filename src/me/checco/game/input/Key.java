package me.checco.game.input;

/**
 * Created by Checco on 02/12/2016.
 */
public class Key {

    public boolean isPressed() {
        return pressed;
    }

    private boolean pressed = false;

    public void toggle(boolean isPressed){
        pressed = isPressed;
    }

}

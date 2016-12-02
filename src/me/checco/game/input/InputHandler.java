package me.checco.game.input;

import me.checco.game.GameBasic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Created by Checco on 30/11/2016.
 */
public class InputHandler implements KeyListener {

    public InputHandler(GameBasic game){
        game.addKeyListener(this);
    }

    private HashMap<KeyCode,Key> keys = new HashMap<>();

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(),true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(),false);
    }

    private void toggleKey(int keyCode, boolean isPressed){

    }

    public boolean isKeyDown(KeyCode k){
        return keys.get(k).isPressed();
    }

    private void loadKeyCodes(){
        //TODO
    }
}

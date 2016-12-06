package me.checco.game.input;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.checco.game.GameBasic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Checco on 30/11/2016.
 */
public class InputHandler implements KeyListener {

    public InputHandler(GameBasic game){
        game.addKeyListener(this);
        System.out.println("Loaded keys");
        loadKeyCodes();
    }

    private HashMap<KeyCode,Key> keys = new HashMap<>();

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        toggleKey(e.getKeyCode(),true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(),false);
    }

    private void toggleKey(int keyCode, boolean isPressed){
        if(keys.containsKey(KeyCode.getKeyCode(keyCode))) {
            keys.get(KeyCode.getKeyCode(keyCode)).toggle(isPressed);
        }
    }

    public boolean isKeyDown(KeyCode k){
        if(keys.containsKey(k)) {
            return keys.get(k).isPressed();
        }else{
            return false;
        }
    }

    private void loadKeyCodes(){
        File ch = new File(getClass().getResource("/config/keys.json").getPath());
        try {
            String json = String.join("", Files.readAllLines(Paths.get(ch.toURI())));
            JsonArray chiavi = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement chiave:chiavi){
                String keyCode = chiave.getAsJsonObject().get("keyCode").getAsString();
                keys.put(KeyCode.valueOf(keyCode),new Key());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

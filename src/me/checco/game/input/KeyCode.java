package me.checco.game.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Checco on 02/12/2016.
 */

public enum KeyCode {
    UP(38),DOWN(40),LEFT(37),RIGHT(39);

    private int eventKeyCode;

    private static Map<Integer, KeyCode> map = new HashMap<Integer, KeyCode>();

    static {
        for (KeyCode legEnum : KeyCode.values()) {
            map.put(legEnum.eventKeyCode, legEnum);
        }
    }


    private KeyCode(final int val){
        this.eventKeyCode = val;
    }

    public static KeyCode getKeyCode(int code){
        return map.get(code);
    }
}

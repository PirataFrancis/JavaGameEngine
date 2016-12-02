package me.checco.game.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Checco on 02/12/2016.
 */

public enum KeyCode {
    UP(1),DOWN(0),LEFT(0),RIGHT(0);

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
}

package me.checco.game;

import me.checco.game.graphics.spritesheet.SpriteSheetOptions;

/**
 * Created by Checco on 01/12/2016.
 */
public class ProvaGame {

    private static GameBasic gioco;

    public static void main(String[] args){

        gioco = new GameBasic();
        gioco.start();

    }
}

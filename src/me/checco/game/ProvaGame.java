package me.checco.game;

import me.checco.game.graphics.spritesheet.SpriteSheetOptions;

/**
 * Created by Checco on 01/12/2016.
 */
public class ProvaGame {

    private static GameBasic gioco;
    private static GameOptions settings = new GameOptions();

    public static void main(String[] args){

        settings.setgWidth(320);
        settings.setgHeight(settings.getgWidth()/12*9);
        settings.setgScale(3);
        settings.setgName("Figo");

        gioco = new GameBasic(settings);
        gioco.start();
    }
}

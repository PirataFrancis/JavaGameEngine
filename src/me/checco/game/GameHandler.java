package me.checco.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.checco.game.entities.Entity;
import me.checco.game.graphics.spritesheet.SpriteSheet;
import me.checco.game.graphics.spritesheet.SpriteSheetOptions;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Checco on 01/12/2016.
 */
public class GameHandler {


    private GameBasic game;
    private HashMap<String,SpriteSheet> gSpriteSheets = new HashMap<>();

    public GameHandler(GameBasic game) {
        this.game = game;
    }

    public void tick(){

    }

    public void render(Graphics g){

    }


    public HashMap<String, SpriteSheet> getgSpriteSheets() {
        return gSpriteSheets;
    }

    public void loadAllSpriteSheet(SpriteSheetOptions ssOptions){
        File allFile = null;
        File[] listfile;

        try {
            allFile = new File(SpriteSheet.class.getResource(ssOptions.getSsPath()).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        FileFilter accept = pathname -> pathname.getName().contains(".png");
        if (allFile != null) {
            listfile = allFile.listFiles(accept);
            for (File aListfile : listfile) {
                gSpriteSheets.put(aListfile.getName().substring(0,aListfile.getName().lastIndexOf(".")),new SpriteSheet(ssOptions.getSsPath()+"/"+aListfile.getName(),ssOptions));
            }
        }
    }
}

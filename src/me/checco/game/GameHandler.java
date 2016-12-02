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
    private HashMap<String,Entity> gEntities = new HashMap<>();
    private HashMap<String,SpriteSheet> gSpriteSheets = new HashMap<>();

    public GameHandler(GameBasic game) {
        this.game = game;
    }

    public void tick(){
        for (String key: gEntities.keySet()) {
            gEntities.get(key).tick();
        }
    }

    public void render(Graphics g){
        for (String key: gEntities.keySet()) {
            gEntities.get(key).render(g);
        }
    }



    public void addEntity(Entity object){
        this.gEntities.put(object.eName,object);
    }

    public void addEntities(HashMap<String,Entity>  adder){
        this.gEntities.putAll(adder);
    }

    public void removeEntity(String object){
        this.gEntities.remove(object);
    }

    public HashMap<String, SpriteSheet> getgSpriteSheets() {
        return gSpriteSheets;
    }

    public void loadAllEntities(){
        File allFile = null;
        File[] listfile;

        try {
            allFile = new File(SpriteSheet.class.getResource(game.getOptions().getgEntityPath()).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        FileFilter accept = pathname -> pathname.getName().contains(".json");
        if (allFile != null) {
            listfile = allFile.listFiles(accept);
            for (File aListfile : listfile) {
                String json = "";
                try {
                    json = String.join("", Files.readAllLines(Paths.get(aListfile.toURI())));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                JsonArray all = new JsonParser().parse(json).getAsJsonArray();
                for(int i = 0;i<all.size();i++){
                    JsonObject ob = all.get(i).getAsJsonObject();
                    JsonObject ssSet = ob.getAsJsonObject("eSpriteSheet");

                    SpriteSheet sp = game.getgHandler()
                            .getgSpriteSheets()
                            .get(ssSet.get("name").getAsString())
                            .getSubSpriteSheet(ssSet.get("xOffset").getAsInt(),ssSet.get("yOffset").getAsInt(),ssSet.get("xMuch").getAsInt(),ssSet.get("yMuch").getAsInt());

                    Entity en = new Entity(ob.get("eName").getAsString(),ob.get("eX").getAsDouble(),ob.get("eY").getAsDouble(),game,sp);
                    gEntities.put(en.eName,en);
                }
            }
        }
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

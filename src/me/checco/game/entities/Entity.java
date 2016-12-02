package me.checco.game.entities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jdk.nashorn.internal.parser.JSONParser;
import me.checco.game.GameBasic;
import me.checco.game.graphics.spritesheet.SpriteSheet;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Checco on 01/12/2016.
 */
public class Entity  {

    @SerializedName("eX")
    @Expose
    private double eX;

    @SerializedName("eY")
    @Expose
    private double eY;

    @SerializedName("eSpriteSheet")
    @Expose
    private SpriteSheet eSpriteSheet;

    @SerializedName("eName")
    @Expose
    public String eName;

    protected GameBasic game;

    public Entity(String name, double x, double y, GameBasic game, SpriteSheet spriteSheet){
        this.eX = x;
        this.eY = y;
        this.eSpriteSheet = spriteSheet;
        this.eName = name;
        this.game = game;
    }

    public void tick(){
        eX++;
    }

    public void render(Graphics g){
        g.drawImage(eSpriteSheet.getSprite(1,1,eSpriteSheet.getSsOptions().getSsSpriteH(),eSpriteSheet.getSsOptions().getSsSpriteH()),(int)eX,(int)eY,null);
    }

    public static ArrayList<Entity> getAllEntities(String path, GameBasic game){
        ArrayList<Entity> result = new ArrayList<>();
        File allFile = null;
        File[] listfile;

        try {
            allFile = new File(SpriteSheet.class.getResource(path).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        FileFilter accept = pathname -> pathname.getName().contains(".json");
        if (allFile != null) {
            listfile = allFile.listFiles(accept);
            for (File aListfile : listfile) {
                String json = "";
                try {
                    json = String.join("",Files.readAllLines(Paths.get(aListfile.toURI())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JsonObject ob = new JsonParser().parse(json).getAsJsonObject();
                JsonObject ssSet = new JsonParser().parse(json).getAsJsonObject().getAsJsonObject("eSpriteSheet");
                SpriteSheet sp = game.getgHandler()
                        .getgSpriteSheets().get(ssSet.get("name").getAsString())
                        .getSubSpriteSheet(ssSet.get("xOffset").getAsInt(),ssSet.get("yOffset").getAsInt(),ssSet.get("xMuch").getAsInt(),ssSet.get("yMuch").getAsInt());
                Entity en = new Entity(ob.get("eName").getAsString(),ob.get("eX").getAsDouble(),ob.get("eY").getAsDouble(),game,sp);
                result.add(en);
            }
        }
        System.out.println("Loaded SpriteSheets :"+result);
        return result;
    }
}

package me.checco.game.areas;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.util.Pair;
import me.checco.game.GameBasic;
import me.checco.game.entities.Entity;
import me.checco.game.graphics.spritesheet.SpriteSheet;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Francis on 02/12/2016.
 */
public class Map extends Scene {

    private int width;
    private int height;
    private GameBasic game;
    private String entityFile;

    private HashMap<String,Entity> gEntities = new HashMap<>();

    private HashMap<Pair<Integer,Integer>,Entity> mScreen = new HashMap<>();


    public Map(int width, int height, GameBasic game, String entityFile) {
        this.width = width;
        this.height = height;
        this.game = game;
        this.entityFile = entityFile;
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

                    Entity en = new Entity(ob.get("eName").getAsString(),ob.get("eX").getAsInt(),ob.get("eY").getAsInt(),game,sp);
                    gEntities.put(en.eName,en);
                }
            }
        }
    }

}

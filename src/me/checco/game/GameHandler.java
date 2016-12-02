package me.checco.game;

import me.checco.game.entities.Entity;
import me.checco.game.graphics.spritesheet.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Checco on 01/12/2016.
 */
public class GameHandler {

    private ArrayList<Entity> gEntities = new ArrayList<>();
    private HashMap<String,SpriteSheet> gSpriteSheets = new HashMap<>();


    public void tick(){
        for(Entity entity : gEntities){
            entity.tick();
        }
    }

    public void render(Graphics g){
        for(Entity entity : gEntities){
            entity.render(g);
        }
    }

    public void addEntity(Entity object){
        this.gEntities.add(object);
    }

    public void addEntities(ArrayList<Entity> adder){
        this.gEntities.addAll(adder);
    }

    public void removeEntity(Entity object){
        this.gEntities.remove(object);
    }

    public HashMap<String, SpriteSheet> getgSpriteSheets() {
        return gSpriteSheets;
    }
}

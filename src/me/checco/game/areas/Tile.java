package me.checco.game.areas;

import me.checco.game.entities.Entity;
import me.checco.game.graphics.spritesheet.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Francis on 02/12/2016.
 */

public class Tile {

    private ArrayList<Entity> tObjectOnTile;

    public ArrayList<Entity> gettObjectOnTile() {
        return tObjectOnTile;
    }

    public void settObjectOnTile(ArrayList<Entity> tObjectOnTile) {
        this.tObjectOnTile = tObjectOnTile;
    }

    private void tick(){

    }

    private void render(Graphics g){
        for (int i = 0; i<4;i++){
            tObjectOnTile.get(i).render(g);
        }
    }
}

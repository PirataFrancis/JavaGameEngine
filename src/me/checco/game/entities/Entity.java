package me.checco.game.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.checco.game.GameBasic;
import me.checco.game.graphics.spritesheet.SpriteSheet;
import me.checco.game.input.KeyCode;

import java.awt.*;

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
        if(game.getgInputHandler().isKeyDown(KeyCode.DOWN)){
            eY++;
        }
    }

    public void render(Graphics g){
        g.drawImage(eSpriteSheet.getSprite(1,1,eSpriteSheet.getSsOptions().getSsSpriteH(),eSpriteSheet.getSsOptions().getSsSpriteH()),(int)eX,(int)eY,null);
    }


}

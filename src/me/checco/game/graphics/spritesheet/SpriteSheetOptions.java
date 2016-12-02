package me.checco.game.graphics.spritesheet;

/**
 * Created by Checco on 01/12/2016.
 */
public class SpriteSheetOptions {

    private int ssWidth = 200;
    private int ssHeight = 200;
    private String ssPath = "/spritesheet";
    private int ssSpriteW = 8;
    private int ssSpriteH = 8;

    public SpriteSheetOptions() {
    }

    public SpriteSheetOptions(int ssWidth, int ssHeight, int ssSpriteW, int ssSpriteH) {
        this.ssWidth = ssWidth;
        this.ssHeight = ssHeight;
        this.ssSpriteW = ssSpriteW;
        this.ssSpriteH = ssSpriteH;
    }

    public int getSsHeight() {
        return ssHeight;
    }

    public void setSsHeight(int ssHeight) {
        this.ssHeight = ssHeight;
    }

    public int getSsWidth() {
        return ssWidth;
    }

    public void setSsWidth(int ssWidth) {
        this.ssWidth = ssWidth;
    }

    public String getSsPath() {
        return ssPath;
    }

    public void setSsPath(String ssPath) {
        this.ssPath = ssPath;
    }

    public int getSsSpriteW() {
        return ssSpriteW;
    }

    public void setSsSpriteW(int ssSpriteW) {
        this.ssSpriteW = ssSpriteW;
    }

    public int getSsSpriteH() {
        return ssSpriteH;
    }

    public void setSsSpriteH(int ssSpriteH) {
        this.ssSpriteH = ssSpriteH;
    }
}

package me.checco.game;

/**
 * Created by Checco on 01/12/2016.
 */
public class GameOptions {

    private int gWidth = 160;
    private int gHeight = gWidth/12*9;
    private int gScale = 3;
    private String gName = "Game";
    private String gEntityPath = "/entities";


    public GameOptions() {

    }

    public GameOptions(int gWidth, int gHeight, String gName) {
        this.gWidth = gWidth;
        this.gHeight = gHeight;
        this.gName = gName;
    }

    public GameOptions(int gWidth, int gHeight, int gScale, String gName) {
        this.gWidth = gWidth;
        this.gHeight = gHeight;
        this.gScale = gScale;
        this.gName = gName;
    }

    public GameOptions(String gEntityPath, String gName, int gHeight, int gWidth) {
        this.gEntityPath = gEntityPath;
        this.gName = gName;
        this.gHeight = gHeight;
        this.gWidth = gWidth;
    }

    public void setgWidth(int gWidth) {
        this.gWidth = gWidth;
    }

    public void setgHeight(int gHeight) {
        this.gHeight = gHeight;
    }

    public void setgScale(int gScale) {
        this.gScale = gScale;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public int getgWidth() {
        return gWidth;
    }

    public int getgHeight() {
        return gHeight;
    }

    public int getgScale() {
        return gScale;
    }

    public String getgName() {
        return gName;
    }

    public String getgEntityPath() {
        return gEntityPath;
    }

    public void setgEntityPath(String gEntityPath) {
        this.gEntityPath = gEntityPath;
    }
}

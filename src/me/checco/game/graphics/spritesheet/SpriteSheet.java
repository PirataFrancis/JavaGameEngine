package me.checco.game.graphics.spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Created by Checco on 30/11/2016.
 */
public class SpriteSheet {

    private SpriteSheetOptions ssOptions;
    private String ssFileName;
    private BufferedImage ssImage;

    public SpriteSheet(String filename,SpriteSheetOptions spriteSheetOptions){
        this.ssFileName = filename;
        this.ssOptions = spriteSheetOptions;
        try {
            ssImage = ImageIO.read(getClass().getResource(ssFileName));
            if(ssImage.getHeight()!=ssOptions.getSsHeight() || ssImage.getWidth()!=ssOptions.getSsWidth()){
                throw new NotValidSpriteSheet("Malformed Sprite Sheet "+filename);
            }
        } catch (IOException | NotValidSpriteSheet e) {
            e.printStackTrace();
        }
    }

    public SpriteSheet(BufferedImage image,SpriteSheetOptions spriteSheetOptions){
        this.ssImage = image;
        this.ssOptions = spriteSheetOptions;
        try {
            if(ssImage.getHeight()!=ssOptions.getSsHeight() || ssImage.getWidth()!=ssOptions.getSsWidth()){
                throw new NotValidSpriteSheet("Malformed Sprite Sheet");
            }
        } catch (NotValidSpriteSheet e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int col,int row, int width,int height){
        BufferedImage img = ssImage.getSubimage((col-1)*ssOptions.getSsSpriteW(),(row-1)*ssOptions.getSsSpriteH(),width,height);
        return img;
    }

    public SpriteSheet getSubSpriteSheet(int xOffset,int yOffset,int xMuch,int yMuch){
        SpriteSheetOptions op = this.ssOptions;
        op.setSsSpriteW(xMuch*ssOptions.getSsWidth());
        op.setSsSpriteH(yMuch*ssOptions.getSsHeight());
        return new SpriteSheet(this.getSprite(xOffset,yOffset,xMuch*ssOptions.getSsWidth(),yMuch*ssOptions.getSsHeight()),op);
    }

    public static HashMap<String,SpriteSheet> getAllSS(SpriteSheetOptions ssOptions){
        HashMap<String,SpriteSheet> result = new HashMap<>();
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
                result.put(aListfile.getName().substring(0,aListfile.getName().lastIndexOf(".")),new SpriteSheet(ssOptions.getSsPath()+"/"+aListfile.getName(),ssOptions));
            }
        }
        System.out.println("Loaded SpriteSheets :"+result);
        return result;
    }

    public BufferedImage getSsImage() {
        return ssImage;
    }

    public void setSsImage(BufferedImage ssImage) {
        this.ssImage = ssImage;
    }

    public SpriteSheetOptions getSsOptions() {
        return ssOptions;
    }

    public void setSsOptions(SpriteSheetOptions ssOptions) {
        this.ssOptions = ssOptions;
    }
}

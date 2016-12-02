package me.checco.game;

import me.checco.game.entities.Entity;
import me.checco.game.graphics.spritesheet.SpriteSheet;
import me.checco.game.graphics.spritesheet.SpriteSheetOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

/**
 * Created by Checco on 30/11/2016.
 */
public class GameBasic extends Canvas implements Runnable {

    private JFrame gFrame;
    private Thread mainThread;
    private boolean running = false;
    private GameOptions gOptions;

    private SpriteSheetOptions gSpriteSheetsOptions = new SpriteSheetOptions();
    private GameHandler gHandler;

    public GameBasic(GameOptions gOptions) {
        this.gOptions = gOptions;
        this.gHandler = new GameHandler();
        archSetup();
    }

    public void setSSConfiguration(SpriteSheetOptions spriteSheetOptions){
        this.gSpriteSheetsOptions = spriteSheetOptions;
    }

    public synchronized void start() {
        if(running)return;
        running = true;

        mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void stop() {
        if(!running)return;
        running = false;
        try {
            mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double nofTicks = 60.0;
        double ns = 1000000000/nofTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        init();
        long now = 0;
        while (running){
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta>=1){
                updates++;
                tick();
                delta--;
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frames++;
            render();

            if(System.currentTimeMillis()-timer>=1000){
                timer+=1000;
                //System.out.println(updates+ " "+frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        gHandler.tick();
    }

    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0,0,getWidth(),getHeight());
        //// DRAW
        gHandler.render(g);
        /////
        g.dispose();
        bs.show();
    }

    private void archSetup(){
        setMaximumSize(new Dimension(
                gOptions.getgWidth()*gOptions.getgScale(),
                gOptions.getgHeight()*gOptions.getgScale()));
        setMaximumSize(new Dimension(
                gOptions.getgWidth()*gOptions.getgScale(),
                gOptions.getgHeight()*gOptions.getgScale()));
        setPreferredSize(new Dimension(
                gOptions.getgWidth()*gOptions.getgScale(),
                gOptions.getgHeight()*gOptions.getgScale()));

        gFrame = new JFrame(gOptions.getgName());
        gFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gFrame.setLayout(new BorderLayout());
        gFrame.add(this,BorderLayout.CENTER);
        gFrame.pack();
        gFrame.setResizable(false);
        gFrame.setLocationRelativeTo(null);
        gFrame.setVisible(true);
    }

    private void init(){
        setAllResources();
        setAllEntity();
    }

    private void setAllEntity() {
        gHandler.addEntities(Entity.getAllEntities(gOptions.getgEntityPath(),this));
    }

    private void setAllResources() {
        gHandler.getgSpriteSheets().putAll(SpriteSheet.getAllSS(gSpriteSheetsOptions));
    }

    public GameHandler getgHandler() {
        return gHandler;
    }
}

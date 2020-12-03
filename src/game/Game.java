package game;

import display.Display;
import graphics.ImageLoader;
import graphics.SpriteSheet;
import graphics.Assets;

import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
    private Display display;

    public int width, height;
    public String title;
    private Thread thread;
    private boolean running = false; // for the game loop

    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage testImage;
    private SpriteSheet sheet;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        ImageLoader imgLoader = new ImageLoader();
    }

    private void tick() {

    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            // first time the canvas is created it won't have any buffer
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        // clear screen
        g.clearRect(0, 0, width, height);

        // draw here

        g.drawImage(Assets.mario1, 10, 10, null);

        // end drawing

        // now show the drawing
        bs.show();
        g.dispose();

    }

    public void run() {
        // need to have this method
        // this method is called whenever we start up our thread

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps; // as 1B nanoseconds in a second
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;

            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("Ticks and frames " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;

        thread = new Thread(this);
        thread.start(); // will call the run method
    }

    public synchronized void stop() {
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
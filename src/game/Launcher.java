package game;

import game.Game;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Tile Game", 630, 400);

        game.start();
    }
}
package game;

import engine.GameEngine;
import engine.IGameLogic;
 
public class Main {

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;
	
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new Game();
            GameEngine gameEng = new GameEngine("GAME", WIDTH, HEIGHT, vSync, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}
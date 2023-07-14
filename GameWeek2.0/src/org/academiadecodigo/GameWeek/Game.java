package org.academiadecodigo.GameWeek;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.IOException;

public class Game implements KeyboardHandler {

    private Screen screen;

    private Enemies enemies;

    private Picture collisionImage;
    private Picture winImage;
    private Ship ship;

    private boolean gameOver;
    private boolean restart;

    public Game() throws IOException {

        Menu menu = new Menu();
        menu.show();
        menu.waitForEnterKey();

        Screen screen = new Screen();
        Enemies enemies = new Enemies();

        gameOver = false;
        restart = false;

        collisionImage = new Picture(10, 10, "gameover.png");
        winImage = new Picture(10,10, "gamewin.png");

        enemies.createEnemies();

        Ship ship = new Ship(enemies);

        enemies.moveEnemies();

        Keyboard keyboard = new Keyboard(this);
        registerKeys(keyboard);

        if (enemies.isAtBase()) {
            screen.getPicture().delete();
            menu.getMenuBackground().delete();
            screen.setPicture(collisionImage);
            collisionImage.draw();

        }

        if (enemies.allDestroyed() && !enemies.isAtBase()) {
            screen.getPicture().delete();
            menu.getMenuBackground().delete();
            screen.setPicture(winImage);
            winImage.draw();
        }
    }

    public void showImage(Picture image) {
        image.draw();
    }

    public void start() throws IOException {


        while (!restart) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // restart();
    }

    private void registerKeys(Keyboard keyboard) {

        KeyboardEvent enterPress = new KeyboardEvent();
        enterPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        enterPress.setKey(KeyboardEvent.KEY_ENTER);
        keyboard.addEventListener(enterPress);
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        int keyCode = event.getKey();

        if (gameOver && keyCode == KeyboardEvent.KEY_ENTER) {
            restart = true;
        }
    }


    @Override
    public void keyReleased(KeyboardEvent event) {
        // Handle key-released events
    }

}


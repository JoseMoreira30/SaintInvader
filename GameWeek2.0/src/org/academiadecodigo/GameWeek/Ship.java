package org.academiadecodigo.GameWeek;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;



public class Ship implements KeyboardHandler {

    private Picture ship;
    private Position pos;
    private Sound leftSound;
    private Sound rightSound;
    private Sound spaceSound;
    private Sound backgroundMusic;
    private Enemies enemies;
    private boolean shipIsDestroyed;


    public Ship(Enemies enemies) {
        ship = new Picture(260, 700, "duck.png");

        this.enemies = enemies;
        pos = new Position(ship.getX(), ship.getY());
       // ship.setColor(Color.BLUE);
        ship.draw();
        keyboardInit();
        backgroundMusic = new Sound("/backgroundmusic.wav");
        backgroundMusic.play();

        spaceSound = new Sound("/pew pew.wav");
        rightSound = new Sound("/ewn.wav");
        leftSound = new Sound("/ewn.wav");


    }

    public void keyboardInit () {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent rightPress = new KeyboardEvent();
        rightPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        rightPress.setKey(KeyboardEvent.KEY_RIGHT);

        KeyboardEvent leftPress = new KeyboardEvent();
        leftPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        leftPress.setKey(KeyboardEvent.KEY_LEFT);

        KeyboardEvent spacePress = new KeyboardEvent();
        spacePress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        spacePress.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(rightPress);
        keyboard.addEventListener(leftPress);
        keyboard.addEventListener(spacePress);
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (enemies.isAtBase()) {
            return;
        }

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:

                if (ship.getX() < 480) {
                    ship.translate(10, 0);
                    rightSound.play();
                }
                break;

            case KeyboardEvent.KEY_LEFT:

                if (ship.getX() > 20) {
                    ship.translate(-10, 0);
                    leftSound.play();
                }
                break;

            case KeyboardEvent.KEY_SPACE:

                shootBullet();
                spaceSound.play();
                break;

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private void shootBullet() {
        Bullet bullet = new Bullet(ship.getX() + ship.getWidth() / 2, ship.getY(), enemies);
        new Thread(bullet::fire).start();
        System.out.println("PEW PEW");

    }

    public Picture getShip() {
        return ship;
    }
}



package org.academiadecodigo.GameWeek;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Menu implements KeyboardHandler {

    private Rectangle menuScreen;
    private int PADDING = 10;
    private int width = 500;
    private int length = 800;

    private Rectangle playButton;
    private int buttonWidth = 200;
    private int buttonLength = 100;
    private int buttonX = PADDING + width / 2 - buttonWidth / 2;
    private int buttonY = PADDING + length / 2 - buttonLength / 2;

    private boolean shouldStartGame;
    private boolean keyPressed;
    private Rectangle background;
    private Picture menuBackground;
    public Menu() {

        shouldStartGame = false;
        keyPressed = false;
        background = new Rectangle(10, 10, 500, 800);
        menuBackground = new Picture(10, 10, "/FUNDO_MENU.png");
        clear();


        Keyboard keyboard = new Keyboard(this);
        registerEnterKey(keyboard);
    }

    public void show() {
        background.setColor(Color.BLACK);
        background.fill();

        menuBackground.draw();

        Canvas.getInstance().repaint();
    }

    public boolean shouldStartGame() {
        return shouldStartGame;
    }

    private void registerEnterKey(Keyboard keyboard) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_ENTER);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);
    }

    public void clear(){
        if(shouldStartGame == true){
            background.delete();
            menuBackground.delete();
        }
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        int keyCode = event.getKey();

        if (keyCode == KeyboardEvent.KEY_ENTER) {
            shouldStartGame = true;
            keyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        // Handle key-released events
    }

    public void waitForEnterKey() {
        while (!keyPressed) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Picture getMenuBackground() {
        return menuBackground;
    }
}

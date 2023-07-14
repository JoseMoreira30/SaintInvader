package org.academiadecodigo.GameWeek;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Enemy {

    private Picture picture;
    private boolean isDestroyed;

    public Enemy(int enemyX, int enemyY) {

        picture = new Picture(enemyX, enemyY, "mac2.png");


    }

    public Picture getPicture() {
        return picture;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void destroy() {
        isDestroyed = true;
    }
}

package org.academiadecodigo.GameWeek;

import org.academiadecodigo.simplegraphics.pictures.Picture;

class Bullet {
    private static final int BULLET_SPEED = 10;
    private Picture bullet;
    private Enemies enemies;
    private boolean bulletIsDestroyed;

    public Bullet(int x, int y, Enemies enemies) {

        bullet = new Picture(x, y, "sardinha.png");

        this.enemies = enemies;

        // bullet = new Rectangle(x, y, 5, 10);
        // bullet.setColor(Color.RED);
        bullet.draw();
    }

    public void fire() {
        while (bullet.getY() > 0) {
            bullet.translate(0, -BULLET_SPEED);
            //System.out.println(bullet.getY());
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            checkBulletHit();

        }
        bullet.delete();
    }

    private void checkBulletHit() {
        for (int i = 0; i < enemies.getEnemies().length; i++) {

            int enemyPosX = enemies.getEnemies()[i].getPicture().getX();
            int enemyPosY = enemies.getEnemies()[i].getPicture().getY();

            int bulletPosX = bullet.getX();
            int bulletPosY = bullet.getY();

            if (!enemies.getEnemies()[i].isDestroyed() && !bulletIsDestroyed &&
                    enemyPosX <= bulletPosX && bulletPosX <= enemyPosX + enemies.getEnemySize()
                    && enemyPosY <= bulletPosY && enemyPosY + enemies.getEnemySize() >= bulletPosY) {

                System.out.println("kabawowooooom");

                bulletHits(bullet, enemies.getEnemies()[i]);
            }
        }
    }

    private void bulletHits(Picture bullet, Enemy enemy) {
        bullet.delete();
        bulletIsDestroyed = true;
        enemy.getPicture().delete();
        enemy.destroy();
    }
}


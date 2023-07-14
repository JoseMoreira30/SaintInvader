package org.academiadecodigo.GameWeek;


public class Enemies {

    private final int enemySize = 20;
    private final int gap = 20;
    private final int numEnemiesPerRow = 10;
    private final int numRows = 3;
    private Enemy[] enemies;
    boolean atBase = false;


    public Enemies() {
        enemies = new Enemy[numEnemiesPerRow * numRows];
        //createEnemies();
        //moveEnemies();
    }

    public void createEnemies() {

        System.out.println(Thread.currentThread().getName());

        int initialX = 75;
        int y = 75;

        for (int row = 0; row < numRows; row++) {
            for (int i = 0; i < numEnemiesPerRow; i++) {

                int x = initialX + i * (enemySize + gap);
                int index = row * numEnemiesPerRow + i;

                int enemyX = x;
                int enemyY = y + (enemySize + gap) * row;

                enemies[index] = new Enemy(enemyX, enemyY);

                enemies[index].getPicture().translate(0, 35 * row);
                enemies[index].getPicture().draw();
            }
        }
    }

    public void moveEnemies() {

        while (!atBase && !allDestroyed()) {

            for (int row = 0; row < numRows; row++) {
                for (int i = 0; i < numEnemiesPerRow; i++) {
                    int index = row * numEnemiesPerRow + i;
                    if (enemies[index].getPicture().getY() < 680) {
                        enemies[index].getPicture().translate(0, 10);
                    }
                    if (enemies[index].getPicture().getY() >= 680) {
                        atBase = true;
                    }
                }
            }

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public int getEnemySize() {
        return enemySize;
    }

    public boolean isAtBase() {
        return atBase;
    }

    public boolean allDestroyed() {

        int counter = 0;

        for (int row = 0; row < numRows; row++) {
            for (int i = 0; i < numEnemiesPerRow; i++) {

                int index = row * numEnemiesPerRow + i;

                if (enemies[index].isDestroyed() == true) {

                    counter++;
                }
            }
        }

        if (counter == enemies.length) {

            return true;
        }
        return false;
    }
}

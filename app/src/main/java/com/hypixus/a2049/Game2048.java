package com.hypixus.a2049;

// No imports! This is pure Java i spent quite a bit of time on.

public class Game2048 {
    //game[x][y]
    private int[][] game;

    public Game2048() {
        initGame();
    }

    /**
     * Initialize a new game of 2048. This function can be called multiple times during object's lifetime.
     */
    public void initGame() {
        game = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                game[i][j] = 0;
            }
        }
        game[getRandomNumber(0, 4)][getRandomNumber(0, 4)] = 2;
        int x = getRandomNumber(0, 4);
        int y = getRandomNumber(0, 4);
        while (game[x][y] != 0) {
            x = getRandomNumber(0, 4);
            y = getRandomNumber(0, 4);
        }
        game[x][y] = 2;
    }

    /**
     * Move the field in given direction.
     *
     * @param direction direction in which user swiped.
     */
    public void makeMove(SwipeDirection direction) {
        int[][] currentField = game;
        switch (direction) {
            case Up:
                makeMoveUp(currentField);
                break;
            case Down:
                makeMoveDown(currentField);
                break;
            case Left:
                makeMoveLeft(currentField);
                break;
            case Right:
                makeMoveRight(currentField);
                break;
            default:
                break;
        }
        game = currentField;
        addRandomTwo();
    }

    /**
     * Return current state of the game.
     *
     * @return Current state of the game.
     */
    public int[][] getGame() {
        return game;
    }

    //region Movements

    //Im certain those can be made into one function, but frankly I don't want to risk it not working.

    /**
     * Returns field after making a move upwards.
     *
     * @param gameField the state of field which you want to move.
     */
    private void makeMoveUp(int[][] gameField) {
        int[] columnZero = {gameField[0][3], gameField[0][2], gameField[0][1], gameField[0][0]};
        int[] columnOne = {gameField[1][3], gameField[1][2], gameField[1][1], gameField[1][0]};
        int[] columnTwo = {gameField[2][3], gameField[2][2], gameField[2][1], gameField[2][0]};
        int[] columnThree = {gameField[3][3], gameField[3][2], gameField[3][1], gameField[3][0]};
        moveTilesInLine(columnZero);
        moveTilesInLine(columnOne);
        moveTilesInLine(columnTwo);
        moveTilesInLine(columnThree);
        for (int i = 0; i < 4; i++) {
            gameField[0][i] = columnZero[3 - i];
            gameField[1][i] = columnOne[3 - i];
            gameField[2][i] = columnTwo[3 - i];
            gameField[3][i] = columnThree[3 - i];
        }
    }

    /**
     * Returns field after making a move downwards.
     *
     * @param gameField the state of field which you want to move.
     */
    private void makeMoveDown(int[][] gameField) {
        for (int i = 0; i < 4; i++) {
            gameField[i] = moveTilesInLine(gameField[i]);
        }
    }

    /**
     * Returns field after making a move to the left.
     *
     * @param gameField the state of field which you want to move.
     */
    private void makeMoveLeft(int[][] gameField) {
        int[] rowZero = {gameField[3][0], gameField[2][0], gameField[1][0], gameField[0][0]};
        int[] rowOne = {gameField[3][1], gameField[2][1], gameField[1][1], gameField[0][1]};
        int[] rowTwo = {gameField[3][2], gameField[2][2], gameField[1][2], gameField[0][2]};
        int[] rowThree = {gameField[3][3], gameField[2][3], gameField[1][3], gameField[0][3]};
        moveTilesInLine(rowZero);
        moveTilesInLine(rowOne);
        moveTilesInLine(rowTwo);
        moveTilesInLine(rowThree);
        for (int i = 0; i < 4; i++) {
            gameField[i][0] = rowZero[3 - i];
            gameField[i][1] = rowOne[3 - i];
            gameField[i][2] = rowTwo[3 - i];
            gameField[i][3] = rowThree[3 - i];
        }
    }

    /**
     * Returns field after making a move to the right.
     *
     * @param gameField the state of field which you want to move.
     */
    private void makeMoveRight(int[][] gameField) {
        int[] rowZero = {gameField[0][0], gameField[1][0], gameField[2][0], gameField[3][0]};
        int[] rowOne = {gameField[0][1], gameField[1][1], gameField[2][1], gameField[3][1]};
        int[] rowTwo = {gameField[0][2], gameField[1][2], gameField[2][2], gameField[3][2]};
        int[] rowThree = {gameField[0][3], gameField[1][3], gameField[2][3], gameField[3][3]};
        moveTilesInLine(rowZero);
        moveTilesInLine(rowOne);
        moveTilesInLine(rowTwo);
        moveTilesInLine(rowThree);
        for (int i = 0; i < 4; i++) {
            gameField[i][0] = rowZero[i];
            gameField[i][1] = rowOne[i];
            gameField[i][2] = rowTwo[i];
            gameField[i][3] = rowThree[i];
        }
    }
    //endregion

    /**
     * Moves a given line of tiles by adding them and moving across empty spaces.
     *
     * @param line A line of tiles to move around.
     * @return Fused and moved tiles.
     */
    private int[] moveTilesInLine(int[] line) {
        //move tiles together first.
        if (line[3] == 0) {
            line[3] = line[2];
            line[2] = line[1];
            line[1] = line[0];
            line[0] = 0;
        }
        if (line[2] == 0) {
            line[2] = line[1];
            line[1] = line[0];
            line[0] = 0;
        }
        if (line[1] == 0) {
            line[1] = line[0];
            line[0] = 0;
        }

        // add tiles that have the same value and are next to each other.
        if (line[3] == line[2] && line[3] != 0) {
            line[3] *= 2;
            line[2] = 0;
        }
        if (line[2] == line[1] && line[2] != 0) {
            line[2] *= 2;
            line[1] = 0;
        }
        if (line[1] == line[0] && line[1] != 0) {
            line[1] *= 2;
            line[0] = 0;
        }

        //move tiles together after merging once more.
        if (line[3] == 0) {
            line[3] = line[2];
            line[2] = line[1];
            line[1] = line[0];
            line[0] = 0;
        }
        if (line[2] == 0) {
            line[2] = line[1];
            line[1] = line[0];
            line[0] = 0;
        }
        if (line[1] == 0) {
            line[1] = line[0];
            line[0] = 0;
        }
        return line;
    }

    /**
     * Adds a 2 in random empty place on the field.
     */
    private void addRandomTwo() {
        boolean noFreeSpace = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (game[i][j] == 0) {
                    noFreeSpace = false;
                    break;
                }
            }
        }
        // if theres no space, check if game lost. Yes = restart, No = just don't add 2.
        if (noFreeSpace) {
            if (checkGameOver()) {
                initGame();
            }
            return;
        }
        // there is free space. Add a two somewhere.
        int x, y;
        while (true) {
            x = getRandomNumber(0, 4);
            y = getRandomNumber(0, 4);
            if (game[x][y] == 0) {
                game[x][y] = 2;
                break;
            }
        }
    }

    /**
     * Checks the game for losing condition - inability to make any more moves.
     *
     * @return True if game is over, false if a move is still possible.
     */
    private boolean checkGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int center = game[i][j];

                int left = i > 1 ? game[i - 1][j] : -1;
                int right = i < 3 ? game[i + 1][j] : -1;
                int up = j > 1 ? game[i][j - 1] : -1;
                int down = j < 3 ? game[i][j + 1] : -1;

                if (center == left || center == right || center == up || center == down || center == 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * Returns a random number from given range.
     *
     * @param min Minimum value that can be returned.
     * @param max Maximum value that can be returned.
     * @return Randomized integer fitting in range provided.
     */
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
package com.hypixus.a2049;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Array holding instances of every button.
    // not doing it separately so updating them can be easily done with for() loop.
    Button[][] buttons = new Button[4][4];
    // That's it. Entire game logic is hidden in that class.
    Game2048 mainGame = new Game2048();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0][0] = findViewById(R.id.button1);
        buttons[1][0] = findViewById(R.id.button2);
        buttons[2][0] = findViewById(R.id.button3);
        buttons[3][0] = findViewById(R.id.button4);
        buttons[0][1] = findViewById(R.id.button5);
        buttons[1][1] = findViewById(R.id.button6);
        buttons[2][1] = findViewById(R.id.button7);
        buttons[3][1] = findViewById(R.id.button8);
        buttons[0][2] = findViewById(R.id.button9);
        buttons[1][2] = findViewById(R.id.button10);
        buttons[2][2] = findViewById(R.id.button11);
        buttons[3][2] = findViewById(R.id.button12);
        buttons[0][3] = findViewById(R.id.button13);
        buttons[1][3] = findViewById(R.id.button14);
        buttons[2][3] = findViewById(R.id.button15);
        buttons[3][3] = findViewById(R.id.button16);
        updateTextOnTiles();
        updateTileColors();
    }

    public void updateTextOnTiles() {
        int[][] state = mainGame.getGame();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setText(String.valueOf(state[i][j]));
            }
        }
    }

    private void updateTileColors() {
        int[][] state = mainGame.getGame();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].getBackground().setColorFilter(getTileColor(state[i][j]), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    /**
     * Returns tile color corresponding to value of it.
     * @param value Number on the tile.
     * @return Correct color of a tile.
     */
    private int getTileColor(int value) {
        switch (value) {
            case 0:
                return Color.DKGRAY;
            case 2:
                return Color.parseColor("#eee6db");
            case 4:
                return Color.parseColor("#ece0c8");
            case 8:
                return Color.parseColor("#efb27c");
            case 16:
                return Color.parseColor("#f39768");
            case 32:
                return Color.parseColor("#f37d63");
            case 64:
                return Color.parseColor("#f46042");
            case 128:
                return Color.parseColor("#eacf76");
            case 256:
                return Color.parseColor("#edcb67");
            case 512:
                return Color.parseColor("#ecc85a");
            case 1024:
                return Color.parseColor("#e7c257");
            case 2048:
                return Color.parseColor("#e8be4e");
            default:
                return Color.parseColor("#e8be4f");
        }
    }

    //region Button.onClick()

    public void buttonUpClicked(View v) {
        mainGame.makeMove(SwipeDirection.Up);
        updateTextOnTiles();
        updateTileColors();
    }

    public void buttonDownClicked(View v) {
        mainGame.makeMove(SwipeDirection.Down);
        updateTextOnTiles();
        updateTileColors();
    }

    public void buttonLeftClicked(View v) {
        mainGame.makeMove(SwipeDirection.Left);
        updateTextOnTiles();
        updateTileColors();
    }

    public void buttonRightClicked(View v) {
        mainGame.makeMove(SwipeDirection.Right);
        updateTextOnTiles();
        updateTileColors();
    }

    //endregion
}
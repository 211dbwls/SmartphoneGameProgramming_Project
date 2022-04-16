package com.example.jellyking.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jellyking.R;
import com.example.jellyking.framework.GameView;
import com.example.jellyking.game.MainGame;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        GameView.view.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameView.view.resumeGame();
    }

    @Override
    protected void onDestroy() {
        GameView.view = null;
        MainGame.clear();

        super.onDestroy();
    }
}
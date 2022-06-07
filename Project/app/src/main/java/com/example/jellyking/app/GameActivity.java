package com.example.jellyking.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jellyking.framework.view.GameView;
import com.example.jellyking.game.MainScene;
import com.example.jellyking.game.Scene;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new GameView(this, null));

        // MainScene game = MainScene.get();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
        MainScene.clear();

        super.onDestroy();
    }
}

package com.example.jellyking.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jellyking.R;
import com.example.jellyking.framework.GameView;
import com.example.jellyking.game.MainGame;

public class StageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onBtnStage1(View view) {
        MainGame.getInstance().stageNum = 1;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onBtnStage2(View view) {
        MainGame.getInstance().stageNum = 2;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onBtnStage3(View view) {
        MainGame.getInstance().stageNum = 3;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onBtnStage4(View view) {
        MainGame.getInstance().stageNum = 4;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onBtnStage5(View view) {
        MainGame.getInstance().stageNum = 5;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}

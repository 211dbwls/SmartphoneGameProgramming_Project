package com.example.jellyking.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jellyking.R;
import com.example.jellyking.game.MainScene;

public class StageActivity extends AppCompatActivity {
    Button stage2Button;
    Button stage3Button;
    Button stage4Button;
    Button stage5Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        stage2Button = findViewById(R.id.stage2);
        stage3Button = findViewById(R.id.stage3);
        stage4Button = findViewById(R.id.stage4);
        stage5Button = findViewById(R.id.stage5);

        if(MainScene.get().stage1Clear == true) {
            stage2Button.setBackgroundResource(R.mipmap.hud2);
            stage2Button.setEnabled(true);
        }
        if(MainScene.get().stage2Clear == true){
            stage3Button.setBackgroundResource(R.mipmap.hud3);
            stage3Button.setEnabled(true);
        }
        if(MainScene.get().stage3Clear == true) {
            stage4Button.setBackgroundResource(R.mipmap.hud4);
            stage4Button.setEnabled(true);
        }
        if(MainScene.get().stage4Clear == true) {
            stage5Button.setBackgroundResource(R.mipmap.hud5);
            stage5Button.setEnabled(true);
        }
    }

    public void onBtnStage1(View view) {
        MainScene.get().stageNum = 1;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onBtnStage2(View view) {
        MainScene.get().stageNum = 2;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onBtnStage3(View view) {
        MainScene.get().stageNum = 3;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onBtnStage4(View view) {
        MainScene.get().stageNum = 4;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onBtnStage5(View view) {
        MainScene.get().stageNum = 5;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
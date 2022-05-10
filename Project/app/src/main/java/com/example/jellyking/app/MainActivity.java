package com.example.jellyking.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jellyking.R;
import com.example.jellyking.framework.GameView;
import com.example.jellyking.game.MainGame;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onBtnStart(View view) {
        Intent intent = new Intent(this, StageActivity.class);
        startActivity(intent);
    }
}
package com.example.task_07;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG =  MainActivity.class.getSimpleName();

    protected static final int[] IMG_TEXT = new int[] {
            R.mipmap.pic1,
            R.mipmap.pic2,
            R.mipmap.pic3,
            R.mipmap.pic4,
            R.mipmap.pic5
    };

    protected int page;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        page = pref.getInt("page", 1);

        setPage(page);
    }

    public void onBtnPrev(View view) {
        Log.d(TAG, "Prev Button Press");
        setPage(page-1);
    }

    public void onBtnNext(View view) {
        Log.d(TAG, "Next Button Press");
        setPage(page+1);
    }

    private void setPage(int newPage) {
        ImageButton prevButton = findViewById(R.id.prevButton);
        ImageButton nextButton = findViewById(R.id.nextButton);

        if(newPage <= 1) {
            prevButton.setEnabled(false);
            nextButton.setEnabled(true);
        }
        else if(newPage >= IMG_TEXT.length) {
            prevButton.setEnabled(true);
            nextButton.setEnabled(false);
        }
        else {
            prevButton.setEnabled(true);
            nextButton.setEnabled(true);
        }

        page = newPage;
        String text =  page + " / " + IMG_TEXT.length;
        TextView tv =  findViewById(R.id.pageText);
        tv.setText(text);

        ImageView iv =  findViewById(R.id.whaleImage);
        iv.setImageResource(IMG_TEXT[page-1]);

        editor.putInt("page", page);
        editor.apply();
    }
}
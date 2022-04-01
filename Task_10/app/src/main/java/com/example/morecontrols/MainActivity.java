package com.example.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    CheckBox cb;
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        cb = findViewById(R.id.checkbox);
        et = findViewById(R.id.nameEdit);
        tv = findViewById(R.id.outputTextView);

        et.addTextChangedListener(editWatcher);
    }

    TextWatcher editWatcher = new TextWatcher() {    // TextWatcher 세번째 방법
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {  // 입력하기 전에 호출
            Log.v(TAG, "before");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {  // 변화가 있을 때 호출
            Log.d(TAG, "text change: " + charSequence);
            tv.setText("Text Length: " + charSequence.length());
        }

        @Override
        public void afterTextChanged(Editable editable) {  // 입력 끝난 후 호출
            Log.v(TAG, "after");
        }
    };

    public void onCheckBox(View view) {
        CheckBox cb = (CheckBox) view;
        Log.d(TAG, "Checked: " + cb.isChecked());
    }

    public void onBtnDoIt(View view) {
        Log.d(TAG, "onBtnDoIt(), Checked: " + cb.isChecked());

        String text = et.getText().toString();
        tv.setText(text);
    }

}
package com.example.jellyking.game;

import static android.os.Build.VERSION_CODES.R;

import android.util.Log;
import com.example.jellyking.R;
import com.example.jellyking.framework.object.Button;
import com.example.jellyking.framework.object.Sprite;
import com.example.jellyking.framework.res.Metrics;

public class PausedScene extends Scene {
    private static PausedScene singleton;
    public static PausedScene get() {
        if (singleton == null) {
            singleton = new PausedScene();
            singleton.init();
        }
        return singleton;
    }

    public enum Layer {
        ui, touchUi, COUNT;
    }

    @Override
    public void init() {
        super.init();

        initLayers(Layer.COUNT.ordinal());

        add(Layer.ui.ordinal(), new Sprite(
                Metrics.width / 2, Metrics.height / 2,
                Metrics.width, Metrics.height,
                com.example.jellyking.R.mipmap.trans_50p));

        float btn_x = size(1.5f);
        float btn_y = size(8.75f);
        float btn_w = size(0.7f);
        float btn_h = size(0.7f);

        add(Layer.touchUi.ordinal(), new Button(Metrics.width - size(2.7f), Metrics.height - btn_y, btn_w, btn_h,
                com.example.jellyking.R.mipmap.pause_button, com.example.jellyking.R.mipmap.pause_button, new Button.Callback()
        {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.released) {
                    Scene.popScene();
                }
                return true;
            }
        }));

        add(Layer.touchUi.ordinal(), new Button(Metrics.width - size(1.5f), Metrics.height - btn_y, btn_w, btn_h,
                com.example.jellyking.R.mipmap.resume_button, com.example.jellyking.R.mipmap.resume_button, new Button.Callback()
        {
            @Override
            public boolean onTouch(Button.Action action) {
                finish();
                return true;
            }
        }));
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touchUi.ordinal();
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public boolean handleBackKey() {
        Scene.popScene();
        return true;
    }
}

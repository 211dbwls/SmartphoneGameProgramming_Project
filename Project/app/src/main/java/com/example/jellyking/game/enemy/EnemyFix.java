package com.example.jellyking.game.enemy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.example.jellyking.R;
import com.example.jellyking.framework.BitmapPool;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.GameView;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;
import com.example.jellyking.game.MainGame;

public class EnemyFix extends Sprite implements BoxCollidable {
    private static final String TAG = EnemyFix.class.getSimpleName();

    protected RectF boundingBox = new RectF();  // boundingBox

    private float elapsedTimeForChangeImg;
    private float changeImgInterval = 1.0f / 2;

    public EnemyFix(float x, float y) {
        super(x, y, R.dimen.fix_enemy_width, R.dimen.fix_enemy_height, R.mipmap.enemy_fix_1);

        changeImgInterval = Metrics.floatValue(R.dimen.enemy_change_interval);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        elapsedTimeForChangeImg += frameTime;
        if(elapsedTimeForChangeImg >= changeImgInterval) {
            bitmap = BitmapPool.get(R.mipmap.enemy_fix_2);
            elapsedTimeForChangeImg -= changeImgInterval;
        }
        else {
            bitmap = BitmapPool.get(R.mipmap.enemy_fix_1);
        }

        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.fix_enemy_width);
        float heightRadius = Metrics.size(R.dimen.fix_enemy_height);
        boundingBox.set(x - widthRadius, y - heightRadius, x + widthRadius, y + heightRadius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    @Override
    public RectF getBoundingRectHead() {
        return null;
    }

    @Override
    public RectF getBoundingRectFoot() {
        return null;
    }

    @Override
    public RectF getBoundingRectLeft() {
        return null;
    }

    @Override
    public RectF getBoundingRectRight() {
        return null;
    }
}

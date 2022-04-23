package com.example.jellyking.game.enemy;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.R;
import com.example.jellyking.framework.BoxCollidable;
import com.example.jellyking.framework.Metrics;
import com.example.jellyking.framework.Sprite;

public class EnemyFix extends Sprite implements BoxCollidable {
    protected RectF boundingBox = new RectF();  // boundingBox

    public EnemyFix(float x, float y) {
        super(x, y, R.dimen.fix_enemy_width, R.dimen.fix_enemy_height, R.mipmap.enemy_fix_1);
        // super(x, y, R.dimen.block_radius, R.mipmap.enemy_fix_1);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void update() {
        /* boundingBox */
        float widthRadius = Metrics.size(R.dimen.fix_enemy_width);
        float heightRadius = Metrics.size(R.dimen.fix_enemy_height);
        boundingBox.set(x - widthRadius, y - heightRadius, x + widthRadius, y + heightRadius);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }
}

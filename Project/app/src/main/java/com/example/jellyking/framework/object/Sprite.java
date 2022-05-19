package com.example.jellyking.framework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.jellyking.framework.interfaces.GameObject;
import com.example.jellyking.framework.res.BitmapPool;
import com.example.jellyking.framework.res.Metrics;

public class Sprite implements GameObject {
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, radius, width, height;

    public Sprite(float x, float y, int radiusDimenResId, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenResId);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmapResId);
    }

    public Sprite(float x, float y, float w, float h, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = w / 2;
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
        bitmap = BitmapPool.get(bitmapResId);
    }

    public Sprite(float x, float y, int widthRadiusDimenResId, int heightRadiusDimenResId, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.width = Metrics.size(widthRadiusDimenResId);
        this.height = Metrics.size(heightRadiusDimenResId);
        dstRect.set(x - width, y - height, x + width, y + height);
        bitmap = BitmapPool.get(bitmapResId);
    }

    public void setDstRectWithRadius() {
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void setDstRect(float width, float height) {
        dstRect.set(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}

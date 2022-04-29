package com.example.jellyking.framework;

import android.graphics.RectF;

public interface BoxCollidable {
    public RectF getBoundingRect();
    public RectF getBoundingRectHead();
    public RectF getBoundingRectFoot();
    public RectF getBoundingRectLeft();
    public RectF getBoundingRectRight();
}

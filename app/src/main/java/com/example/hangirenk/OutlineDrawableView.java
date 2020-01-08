package com.example.hangirenk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.util.TypedValue;
import android.view.View;

// orta alanı çizme sınıfı
public class OutlineDrawableView extends View {
    // şekilleri tanımla
    private ShapeDrawable outline1;
    private ShapeDrawable outline2;
    private ShapeDrawable crosshair;
    // kalınlık yükseklik ve radius değişkenleri
    private int stroke = 2;
    private int radius;
    private int width_dp;

    // ana sınıf
    public OutlineDrawableView(Context context, int radius) {
        super(context);
        // radius ata
        this.radius = radius;
        width_dp = radius * 6;

        // kalınlığı belirle
        stroke = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, stroke, getResources().getDisplayMetrics());

        // 1. alanı çiz
        outline1 = new ShapeDrawable(new RectShape());
        Paint paint1 = outline1.getPaint();
        paint1.setColor(0x55000000);
        paint1.setStrokeWidth(stroke);
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.STROKE);

        // 2. alanı çiz
        outline2 = new ShapeDrawable(new RectShape());
        Paint paint2 = outline2.getPaint();
        paint2.setColor(0x55ffffff);
        paint2.setStrokeWidth(stroke);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);

        // çarpıyı çiz
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(width_dp, width_dp);
        path.moveTo(0, width_dp);
        path.lineTo(width_dp, 0);
        crosshair = new ShapeDrawable(new PathShape(path, width_dp, width_dp));
        Paint paint3 = crosshair.getPaint();
        paint3.setColor(0xbb000000);
        paint3.setStrokeWidth(Math.max(stroke/2, 1));
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.STROKE);
    }

    // çizmek için
    protected void onDraw(Canvas canvas) {
        outline1.draw(canvas);
        outline2.draw(canvas);
        crosshair.draw(canvas);
    }

    // (x,y) yi tam ortala
    public void move(int x, int y) {
        outline1.setBounds(x - stroke - width_dp / 2, y - stroke - width_dp / 2, x + stroke + width_dp / 2, y + stroke + width_dp / 2);
        outline2.setBounds(x - width_dp / 2, y - width_dp / 2, x + width_dp / 2, y + width_dp / 2);
        crosshair.setBounds(x - radius, y - radius, x + radius, y + radius);
    }

}


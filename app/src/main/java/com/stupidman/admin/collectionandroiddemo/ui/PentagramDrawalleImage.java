package com.stupidman.admin.collectionandroiddemo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by admin on 2015/5/28.
 * 实现显示五角星图片
 */
public class PentagramDrawalleImage extends Drawable {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Path mPath;

    public PentagramDrawalleImage(Bitmap bitmap) {
        this.mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setShader(bitmapShader);
    }

    /**
     * 设置边界
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mPath = new Path();
        mPath.moveTo(27, 360);
        mPath.lineTo(54, 360);
        mPath.lineTo(70, 392);
        mPath.lineTo(40, 420);
        mPath.lineTo(10, 392);
        mPath.close();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }
}

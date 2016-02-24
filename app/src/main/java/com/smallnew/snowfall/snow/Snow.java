package com.smallnew.snowfall.snow;

/**
 * Created by WangRuiPeng on 2016/2/22.
 */

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;

public class Snow {
    private float rXPer;
    private float rYPer;
    private float rZPer;
    public Bitmap bitmap;
    private int topRange;
    private int maxRotate;
    private int leftOffSet;
    public int fallTime;
    private Camera camera;
    public int left;
    public float rotateX;
    public float rotateY;
    public float rotateZ;
    public Bitmap snowBmp;
    public int top;

    //(Math.random() * this.width), topRange = 110, maxRotate ==20, leftOffSet = 10,
    public Snow(int left, int topRange, int paramInt3, int leftOffSet, Bitmap paramBitmap, Camera paramCamera) {
        this.bitmap = paramBitmap;
        this.camera = paramCamera;
        this.topRange = topRange;//110
        this.maxRotate = paramInt3;//20
        this.leftOffSet = leftOffSet;//10
        this.left = left;
        reset();
    }

    public void fall() {
        this.top = ((int) (this.top + (0.125D + Math.random() * this.topRange)));
        int i = 0;
        if (Math.random() - 0.5D >= 0.0D)
            i = 1;
        else
            i = -1;

        this.left += i * this.leftOffSet;
        rotate();
        this.camera.save();
        Matrix localMatrix = new Matrix();
        if ((this.rotateX == 90.0F) || (this.rotateX == 270.0F))
            this.rotateX = (5.0F + this.rotateX);
        this.camera.rotateX(this.rotateX);
        if ((this.rotateY == 90.0F) || (this.rotateY == 270.0F))
            this.rotateY = (5.0F + this.rotateY);
        this.camera.rotateY(this.rotateY);
        this.camera.rotateZ(this.rotateZ);
        this.camera.getMatrix(localMatrix);
        this.camera.restore();
        localMatrix.preTranslate(this.bitmap.getWidth() >> 1, this.bitmap.getHeight() >> 1);
        try {
            this.snowBmp = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), localMatrix, true);
        } catch (IllegalArgumentException localIllegalArgumentException) {
            this.snowBmp = Bitmap.createBitmap(this.bitmap, 0, 0, 1, 1);
        }

    }

    public void reset() {
        this.fallTime = 0;
        this.rotateX = ((float) (360.0D * Math.random()));
        this.rotateY = ((float) (360.0D * Math.random()));
        this.rotateZ = ((float) (360.0D * Math.random()));
        this.top = (-25 + (int) (50.0D * Math.random()));
        this.rXPer = ((float) Math.random());
        this.rYPer = ((float) Math.random());
        this.rZPer = ((float) Math.random());
    }

    public void rotate2() {
        int i = 0;
        if (-30 + this.maxRotate >= 0) {
            i = 1;
            if (this.maxRotate == 30)
                return;
        }
        for (int j = 30 - i * ((-30 + this.maxRotate) * (-30 + this.maxRotate)) / 100; ; j = 30) {
            this.rotateX += j * this.rXPer;
            this.rotateY += j * this.rYPer;
            this.rotateZ += j * this.rZPer;
            i = -1;
            break;
        }
    }


    public void rotate() {
        int v0;
        if (-30 + this.maxRotate < 0) {
            v0 = -1;//v1
        }
        v0 = 1;
        if (this.maxRotate == 30) {
            v0 = 30;
        } else {
            v0 = 30 - v0 * ((-30 + this.maxRotate) * (-30 + this.maxRotate)) / 100;
        }

        this.rotateX += v0 * this.rXPer;
        this.rotateY += v0 * this.rYPer;
        this.rotateZ += v0 * this.rZPer;
    }

}
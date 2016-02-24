package com.smallnew.snowfall.snow;

/**
 * Created by WangRuiPeng on 2016/2/22.
 */

import android.graphics.Bitmap;
import android.graphics.Camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SnowFactory {
    private int width;
    private int height;
    private int snowCount;
    private Bitmap bitmap;
    private int cacheCount;
    private Camera camera;
    private int times;
    private int allCount = 0;
    public List products;
    public List repository;

    public SnowFactory(int cacheCount, int snowCount, int width, int height, Bitmap paramBitmap, int times) {
        this.bitmap = paramBitmap;
        this.width = width;
        this.height = height;
        this.snowCount = snowCount;//30
        this.cacheCount = cacheCount;//8
        this.camera = new Camera();
        this.products = new CopyOnWriteArrayList();
        this.repository = new ArrayList();
        this.times = times;//1
    }

    public void fallSnow(boolean paramBoolean) {
        int cached = 0;
        boolean tmp = false;

        Snow localSnow2 = null;
        if (paramBoolean && ((this.times <= 0) || (this.allCount < this.times * this.snowCount))) {//times=1
            while (this.cacheCount > cached) {

                if (this.repository.isEmpty() || tmp) {
                    if (!tmp) {
                        localSnow2 = new Snow((int) (Math.random() * this.width), 200, 15, 20, this.bitmap, this.camera);
                    } else {
                        tmp = false;
                    }
                    if (this.products.size() < this.snowCount) {
                        this.allCount = (1 + this.allCount);
                        this.repository.remove(localSnow2);
                        this.products.add(0, localSnow2);
                        cached++;
                    } else {
                        break;
                    }
                } else {
                    localSnow2 = (Snow) repository.get(0);
                    localSnow2.reset();
                    tmp = true;
                }
            }

        }

        Iterator iterator = products.iterator();
        while (iterator.hasNext()) {
            Snow localSnow1 = (Snow) iterator.next();
            localSnow1.fall();
            if ((localSnow1.left < 0) || (localSnow1.left > this.width) || (localSnow1.top > this.height)) {
                this.products.remove(localSnow1);
                this.repository.add(localSnow1);
            }
        }


    }

    public void recycle() {
        Iterator localIterator1 = this.products.iterator();
        while (localIterator1.hasNext()) {
            ((Snow) localIterator1.next()).snowBmp.recycle();
        }
        this.products.clear();

        Iterator localIterator2 = this.repository.iterator();
        while (localIterator2.hasNext()) {
            ((Snow) localIterator2.next()).snowBmp.recycle();
        }
        this.repository.clear();

    }
}
package com.ase.team22.ihealthcare.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by chait on 26/04/2017.
 */

public class ImageRequestHelper {
    public static Bitmap getBitmapImage(String imageUrl){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}

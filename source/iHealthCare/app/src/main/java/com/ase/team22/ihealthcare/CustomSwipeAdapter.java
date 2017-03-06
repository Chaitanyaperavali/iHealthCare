package com.ase.team22.ihealthcare;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.res.Configuration;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

/**
 * Created by Sindhu on 2/17/2017.
 */

public class CustomSwipeAdapter extends PagerAdapter {
    private int[] image_resources = {R.drawable.health,R.drawable.doc_appoint,R.drawable.docnearby,R.drawable.care1};
    private Context ctx;
    private LayoutInflater layoutInflater;
    private int imageWidth;
    private int imageHeight;
    ImageView imageView;
    public CustomSwipeAdapter(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(ImageView)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.custom_view_pager, container, false);
        imageView = (ImageView) item_view.findViewById(R.id.image);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                imageWidth = imageView.getWidth();
                imageHeight = imageView.getHeight();

            }
        });
        if(Resources.getSystem().getConfiguration().orientation == ORIENTATION_LANDSCAPE){
            imageWidth=830;
            imageHeight = 400;
        }
        else
        {
            imageHeight=720;
            imageWidth=500;
        }
        Bitmap image = BitmapFactory.decodeResource(imageView.getResources(),image_resources[position]);
        Bitmap resized = Bitmap.createScaledBitmap(image,imageWidth ,imageHeight, true);
        imageView.setImageBitmap(resized);
        container.addView(item_view);
        return item_view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

}

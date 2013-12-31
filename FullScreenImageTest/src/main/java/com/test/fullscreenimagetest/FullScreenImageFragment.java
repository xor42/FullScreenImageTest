package com.test.fullscreenimagetest;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by tnu on 10.10.13.
 */
public class FullScreenImageFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = FullScreenImageFragment.class.getSimpleName();
    private static final float RATIO_ADJUSTMENT = 0.5625f;
    private Drawable mImageDrawable;

    public FullScreenImageFragment(Drawable imageDrawable) {
        mImageDrawable = imageDrawable;
    }


    public static FullScreenImageFragment newInstance(Drawable drawable) {
        return new FullScreenImageFragment(drawable);
    }


/*
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
*/
    public Canvas getResizedBitmap(Bitmap bitmap, int newHeight, int newWidth) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        return canvas;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.screen_full_image, container, false);
        root.setOnClickListener(this);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        float ratio = (float) mImageDrawable.getIntrinsicHeight() / (float)mImageDrawable.getIntrinsicWidth();

        Toast.makeText(getActivity(), "ratio: " + ratio, Toast.LENGTH_SHORT).show();

        AspectRatioImageView imageView = (AspectRatioImageView) root.findViewById(R.id.img_full);
        imageView.setOnClickListener(this);
        imageView.setAspectRatio(ratio);
        imageView.setImageDrawable(mImageDrawable);


        return root;
    }

    @Override
    public void onClick(View v) {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}

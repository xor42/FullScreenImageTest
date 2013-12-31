package com.test.fullscreenimagetest;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class FullScreenImageTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.full_screen_image_test, menu);
        return true;
    }

    public void onImageClick(View v) {
        getFragmentManager().beginTransaction().add(android.R.id.content, FullScreenImageFragment.newInstance(
                ((ImageView) v).getDrawable()), FullScreenImageFragment.TAG).addToBackStack(null).commit();
    }
    
}

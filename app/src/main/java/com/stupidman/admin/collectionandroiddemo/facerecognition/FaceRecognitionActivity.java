package com.stupidman.admin.collectionandroiddemo.facerecognition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.stupidman.admin.collectionandroiddemo.R;
import com.stupidman.admin.collectionandroiddemo.ui.CircleDrawableImage;

public class FaceRecognitionActivity extends ActionBarActivity {

    private ImageView ivFacePic;
    private TextView tvFaceCount;
    private  ImageView ivFaceChoose;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition);

        init();
    }

    private void init() {

        ivFacePic = (ImageView) findViewById(R.id.iv_face_pic);
        tvFaceCount = (TextView) findViewById(R.id.tv_face_count);
        ivFaceChoose = (ImageView) findViewById(R.id.iv_choose_pic);

        ViewTreeObserver vto = ivFaceChoose.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivFaceChoose.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                width = Math.min(ivFaceChoose.getWidth() - 30, ivFaceChoose.getHeight() - 30);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.outWidth = width;
                options.outHeight = width;
                Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.search, options);
                ivFaceChoose.setImageDrawable(new CircleDrawableImage(mBitmap));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_face_recognition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

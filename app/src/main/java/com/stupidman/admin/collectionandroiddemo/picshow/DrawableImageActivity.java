package com.stupidman.admin.collectionandroiddemo.picshow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.stupidman.admin.collectionandroiddemo.R;
import com.stupidman.admin.collectionandroiddemo.ui.CircleDrawableImage;
import com.stupidman.admin.collectionandroiddemo.ui.PentagramDrawalleImage;
import com.stupidman.admin.collectionandroiddemo.ui.RoundDrawalleImage;
import com.stupidman.admin.collectionandroiddemo.ui.SectorDrawalleImage;

public class DrawableImageActivity extends Activity {

    private ImageView ivRoundPic;
    private ImageView ivCirclePic;
    private ImageView ivSectorPic;
    private ImageView ivPentagramPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_image);

        init();
    }

    private void init() {
        ivRoundPic = (ImageView) findViewById(R.id.iv_round_pic);
        ivCirclePic = (ImageView) findViewById(R.id.iv_circle_pic);
        ivSectorPic = (ImageView) findViewById(R.id.iv_sector_pic);
        ivPentagramPic = (ImageView) findViewById(R.id.iv_pentagram_pic);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
        ivRoundPic.setImageDrawable(new RoundDrawalleImage(bitmap));
        ivCirclePic.setImageDrawable(new CircleDrawableImage(bitmap));
        ivSectorPic.setImageDrawable(new SectorDrawalleImage(bitmap));
        ivPentagramPic.setImageDrawable(new PentagramDrawalleImage(bitmap));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawable_image, menu);
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

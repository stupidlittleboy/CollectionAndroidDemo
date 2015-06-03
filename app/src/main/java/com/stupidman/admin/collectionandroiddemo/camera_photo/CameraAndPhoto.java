package com.stupidman.admin.collectionandroiddemo.camera_photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.stupidman.admin.collectionandroiddemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CameraAndPhoto extends ActionBarActivity implements View.OnClickListener {

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private Button btnTackPhoto;
    private Button btnGetPic;
    private ImageView ivCameraPhoto;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_and_photo);

        init();
    }

    private void init() {
        btnTackPhoto = (Button) findViewById(R.id.btn_tack_photo);
        btnGetPic = (Button) findViewById(R.id.btn_get_pic);
        ivCameraPhoto = (ImageView) findViewById(R.id.iv_camera_photo);

        btnTackPhoto.setOnClickListener(this);
        btnGetPic.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera_and_photo, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tack_photo:
                File outputImage = null;
                try {
                    outputImage  = new File(Environment.getExternalStorageDirectory(), "outpub_image.jpg");
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                break;

            case R.id.btn_get_pic:
                    //创建File对象用于存储照片
                    File pic = new File(Environment.getExternalStorageDirectory(), "pic.jpg");
                try {
                    if (pic.exists()) {
                        pic.delete();
                    }
                    pic.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(pic);
                Intent intent1 = new Intent("android.intent.action.GET_CONTENT");
                intent1.setType("image/*");
                intent1.putExtra("crop", true);
                intent1.putExtra("scale", true);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent1, CROP_PHOTO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;

            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        ivCameraPhoto.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }
    }
}

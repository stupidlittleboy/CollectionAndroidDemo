package com.stupidman.admin.collectionandroiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.stupidman.admin.collectionandroiddemo.camera_photo.CameraAndPhoto;
import com.stupidman.admin.collectionandroiddemo.game2048.Game2048Activity;
import com.stupidman.admin.collectionandroiddemo.litepal.option.UserActivity;
import com.stupidman.admin.collectionandroiddemo.picshow.DrawableImageActivity;
import com.stupidman.admin.collectionandroiddemo.share.thirldapplogindemo.ThirldMainActivity;
import com.stupidman.admin.collectionandroiddemo.ui.MyFlowLayout;


public class MainDemoActivity extends Activity {

    private MyFlowLayout myFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo);

        init();

        addChildTo(myFlowLayout);
    }

    private void init() {
        myFlowLayout = (MyFlowLayout) findViewById(R.id.flow_layout_group);
    }

    private void addChildTo(MyFlowLayout myFlowLayout) {
        /*//测试流式布局
        for (int i = 0; i < 35; i++){
            Button btn = new Button(this);
            btn.setText("我是按钮" + i);
            final int j = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainDemoActivity.this, "按钮" + j + "被点击", Toast.LENGTH_SHORT).show();
                }
            });
            myFlowLayout.addView(btn);
        }*/
        //Litepal使用，实现对SQLite数据库的CRUD操作
        Button btnLitapalAct = null;
        createButton(btnLitapalAct, "操作本地数据库-Litepal", UserActivity.class, myFlowLayout);

        //Drawalbe展示图片
        Button btnDrawableImage = null;
        createButton(btnDrawableImage, "Drawable图片", DrawableImageActivity.class, myFlowLayout);

        //第三方登录分享
        Button btnShareOthers = null;
        createButton(btnShareOthers, "第三方登录分享", ThirldMainActivity.class, myFlowLayout);

        //2048游戏
        Button btnGame2048 = null;
        createButton(btnGame2048, "2048游戏", Game2048Activity.class, myFlowLayout);

        //拍照
        Button btnCamera = null;
        createButton(btnCamera, "拍照", CameraAndPhoto.class, myFlowLayout);

       /* Button btnCamera1 = null;
        createButton(btnCamera1, "拍照", CameraAndPhoto.class, myFlowLayout);*/

        //传感器
        Button btnSensor = null;
        createButton(btnSensor, "传感器", Game2048Activity.class, myFlowLayout);
    }

    private void createButton(Button btnName, String text, final Class clazz, MyFlowLayout myFlowLayout) {
        btnName = new Button(this);
        btnName.setText(text);
        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDemoActivity.this, clazz);
                startActivity(intent);
            }
        });
        myFlowLayout.addView(btnName);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_demo, menu);
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

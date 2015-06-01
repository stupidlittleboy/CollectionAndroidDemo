package com.stupidman.admin.collectionandroiddemo.share.thirldapplogindemo;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.stupidman.admin.collectionandroiddemo.R;

import cn.sharesdk.framework.TitleLayout;

public class LoginActivity extends Activity implements OnClickListener {

    private TitleLayout llTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        llTitle = (TitleLayout) findViewById(R.id.llTitle);
        llTitle.getBtnBack().setOnClickListener(this);
        llTitle.getTvTitle().setText("用户资料");

        TextView tvJson = (TextView) findViewById(R.id.tvJson);
        tvJson.setText(getIntent().getStringExtra("userId"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.equals(llTitle.getBtnBack())) {
            finish();
        }
    }
}

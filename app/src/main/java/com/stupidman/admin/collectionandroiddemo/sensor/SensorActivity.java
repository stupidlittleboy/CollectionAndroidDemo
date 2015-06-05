package com.stupidman.admin.collectionandroiddemo.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.stupidman.admin.collectionandroiddemo.R;

public class SensorActivity extends ActionBarActivity {

    private TextView tvSensorLight;
    private SensorManager sensorManager;
    private SensorEventListener lightListener;
    private SensorEventListener gravityListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sernor);

        init();

        lightSensor();

        gravitySensor();
    }

    private void gravitySensor() {
        Sensor gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravityListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //加速度可能为负值，所以要取其绝对值
                float xValue = Math.abs(event.values[0]);
                float yValue = Math.abs(event.values[1]);
                float zValue = Math.abs(event.values[2]);

                if (xValue > 15 || yValue >15 || zValue >15) {
                    //认为用户摇动了手机，触发摇一摇动作
                    Toast.makeText(SensorActivity.this, "摇了一摇", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(gravityListener, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void lightSensor() {

        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        lightListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //event[0]的值是当前光照强度
                float value = event.values[0];
                tvSensorLight.setText("当前光照强度为：" + value + "lx");
                Log.e("光照数组值为：", event.toString());
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(lightListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void init() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tvSensorLight = (TextView) findViewById(R.id.tv_sensor_light);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(lightListener);
            sensorManager.unregisterListener(gravityListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sernor, menu);
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

package com.stupidman.admin.collectionandroiddemo.game2048;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.stupidman.admin.collectionandroiddemo.R;

public class Game2048Configuration extends ActionBarActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinnerGameLevel;
    private Spinner spinnerGameGoal;
    private Button btnGameBack;
    private Button btnGAmeDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2048_configuration);

        init();
    }

    private void init() {
        spinnerGameLevel = (Spinner) findViewById(R.id.spinner_game_level);
        spinnerGameGoal = (Spinner) findViewById(R.id.spinner_game_goal);
        btnGameBack = (Button) findViewById(R.id.btn_game2048_back);
        btnGAmeDone = (Button) findViewById(R.id.btn_game2048_done);

        spinnerGameLevel.setOnItemSelectedListener(this);
        spinnerGameGoal.setOnItemSelectedListener(this);
        btnGameBack.setOnClickListener(this);
        btnGAmeDone.setOnClickListener(this);
    }

    private void saveConfig() {
        SharedPreferences sp = getSharedPreferences("game2048Configuration", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("game_line", Integer.parseInt(spinnerGameLevel.getSelectedItem().toString()));
        editor.putInt("game_goal", Integer.parseInt(spinnerGameGoal.getSelectedItem().toString()));
        editor.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_game2048_back:
                this.finish();
                break;

            case R.id.btn_game2048_done:
                saveConfig();
                setResult(RESULT_OK);
                this.finish();
                break;
            default:
                break;
        }
    }
}

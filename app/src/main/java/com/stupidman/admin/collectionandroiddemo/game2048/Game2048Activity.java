package com.stupidman.admin.collectionandroiddemo.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.stupidman.admin.collectionandroiddemo.R;

/**
 * Created by admin on 2015/6/1.
 */
public class Game2048Activity extends Activity {

    private static TextView tvScore;
    private static Game2048Activity mainActivity;
    private int score = 0;

    public Game2048Activity() {
        mainActivity = this;
    }

    /**
     * 提供静态方法调用Game2048Activity中的方法
     *
     * @return
     */
    public static Game2048Activity getMainActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2048);

        tvScore = (TextView) findViewById(R.id.tv_score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_2048, menu);
        return true;
    }

    /**
     * 分数清零
     */
    public void clearScore() {
        score = 0;
        showScore();
    }

    /**
     * 显示分数
     */
    public void showScore() {
        tvScore.setText(score + "");
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }


}

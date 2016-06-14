package com.dhisat.naveen.airtennisgame;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity  {
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getWindowManager().getDefaultDisplay();
        SCREEN_WIDTH= display.getWidth();  // deprecated
        SCREEN_HEIGHT = display.getHeight();  //
        setContentView(new GameView(this));
    }
}

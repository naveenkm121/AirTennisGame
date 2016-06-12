package com.dhisat.naveen.airtennisgame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LaunchActivity extends Activity{

    private TextView play_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        play_tv = (TextView)findViewById(R.id.playBtn);
        play_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaunchActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
    }
}

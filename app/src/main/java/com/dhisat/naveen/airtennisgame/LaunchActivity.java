package com.dhisat.naveen.airtennisgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhisat.naveen.airtennisgame.constants.AppConstants;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;

public class LaunchActivity extends Activity{

    private TextView play_tv;
    private TextView level_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        play_tv = (TextView)findViewById(R.id.playBtn);
        level_tv = (TextView)findViewById(R.id.game_level);
        play_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaunchActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
        level_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showGameLevelDialog();
            }
        });
    }



    private void showGameLevelDialog() {
        String names[] ={"Easy","Medium","Hard"};
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_level_dailog);
        dialog.setCanceledOnTouchOutside(true);
        TextView header = (TextView) dialog.findViewById(R.id.header_dialog);
        TextView cancel_btn = (TextView)dialog.findViewById(R.id.cancel_btn);
        ListView dialog_ListView = (ListView) dialog.findViewById(R.id.list);
        header.setText("Select Level");
        cancel_btn.setText("Cancel");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        dialog_ListView.setAdapter(adapter);

        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                DebugHandler.Log("position"+parent.getItemAtPosition(position).toString());
                String levelType=parent.getItemAtPosition(position).toString();
                if(levelType.equals("Easy"))
                {
                    AppConstants.BallSpeed=6;
                    AppConstants.BotBatSpeed=3;

                }else if(levelType.equals("Medium"))
                {
                    AppConstants.BallSpeed=8;
                    AppConstants.BotBatSpeed=5;
                }else if(levelType.equals("Hard"))
                {
                    AppConstants.BallSpeed=10;
                    AppConstants.BotBatSpeed=7;
                }
                dialog.dismiss();
            }});


    dialog.show();
    }
}

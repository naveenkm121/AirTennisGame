package com.dhisat.naveen.airtennisgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhisat.naveen.airtennisgame.constants.AppConstants;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;
import com.dhisat.naveen.airtennisgame.presenter.SharedCommon;

public class LaunchActivity extends Activity{

    private ImageView play_imv;
    private ImageView level_imv;
    private ImageView speaker_on_imv;
    private ImageView speaker_off_imv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        play_imv = (ImageView)findViewById(R.id.playBtn);
        level_imv = (ImageView)findViewById(R.id.game_level);
        speaker_off_imv = (ImageView)findViewById(R.id.speaker_off);
        speaker_on_imv = (ImageView)findViewById(R.id.speaker_on);

//        if(SharedCommon.getSpeakerState(LaunchActivity.this).equals("On"))
//        {
//            speaker_off_imv.setVisibility(View.GONE);
//            speaker_on_imv.setVisibility(View.VISIBLE);
//        }else{
//            speaker_on_imv.setVisibility(View.GONE);
//            speaker_off_imv.setVisibility(View.VISIBLE);
//        }
        play_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  stopPlaying();
                Intent intent = new Intent(LaunchActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
        level_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showGameLevelDialog();
            }
        });

        speaker_off_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speaker_off_imv.setVisibility(View.GONE);
                speaker_on_imv.setVisibility(View.VISIBLE);
                SharedCommon.setSpeakerState(LaunchActivity.this,"Off");

            }
        });
        speaker_on_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speaker_on_imv.setVisibility(View.GONE);
                speaker_off_imv.setVisibility(View.VISIBLE);
                SharedCommon.setSpeakerState(LaunchActivity.this,"On");

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
                    AppConstants.BallSpeed=7;
                    AppConstants.BotBatSpeed=3;
                    AppConstants.PlayerBatSpeed=5;

                }else if(levelType.equals("Medium"))
                {
                    AppConstants.BallSpeed=10;
                    AppConstants.BotBatSpeed=5;
                    AppConstants.PlayerBatSpeed=7;
                }else if(levelType.equals("Hard"))
                {
                    AppConstants.BallSpeed=12;
                    AppConstants.BotBatSpeed=6;
                    AppConstants.PlayerBatSpeed=10;
                }
                dialog.dismiss();
            }});


    dialog.show();
    }
}

package com.dhisat.naveen.airtennisgame;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.dhisat.naveen.airtennisgame.constants.AppConstants;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;
import com.dhisat.naveen.airtennisgame.presenter.SharedCommon;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



public class LaunchActivity extends Activity{

    private ImageView play_imv;
    private ImageView level_imv;
    private ImageView speaker_on_imv;
    private ImageView speaker_off_imv;
    private ImageView game_score_imv;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        play_imv = (ImageView)findViewById(R.id.playBtn);
        level_imv = (ImageView)findViewById(R.id.game_level);
        game_score_imv = (ImageView)findViewById(R.id.game_score);
        speaker_off_imv = (ImageView)findViewById(R.id.speaker_off);
        speaker_on_imv = (ImageView)findViewById(R.id.speaker_on);


        try {
            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("12B047CF6C1D6F232725AEB8D0A7C44F")
                    .build();
            mAdView.loadAd(adRequest);
        }catch (Exception e)
        {
            DebugHandler.LogException(e);
        }
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
                finish();
            }
        });
        level_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showGameLevelDialog();
            }
        });
        game_score_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScoreDialog();
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
        try {
            String names[] = {"Easy", "Medium", "Hard"};
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.game_level_dailog);
            dialog.setCanceledOnTouchOutside(true);
            TextView header = (TextView) dialog.findViewById(R.id.header_dialog);
            ListView dialog_ListView = (ListView) dialog.findViewById(R.id.list);
            header.setText("Select Level");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
            dialog_ListView.setAdapter(adapter);

            dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    DebugHandler.Log("position" + parent.getItemAtPosition(position).toString());
                    String levelType = parent.getItemAtPosition(position).toString();
                    if (levelType.equals("Easy")) {
                        AppConstants.BallSpeed = 8;
                        AppConstants.BotBatSpeed = 3;
                        AppConstants.PlayerBatSpeed = 5;

                    } else if (levelType.equals("Medium")) {
                        AppConstants.BallSpeed = 9;
                        AppConstants.BotBatSpeed = 5;
                        AppConstants.PlayerBatSpeed = 7;
                    } else if (levelType.equals("Hard")) {
                        AppConstants.BallSpeed = 11;
                        AppConstants.BotBatSpeed = 6;
                        AppConstants.PlayerBatSpeed = 10;
                    }
                    dialog.dismiss();
                }
            });


            dialog.show();
        }catch (Exception e)
        {
            DebugHandler.LogException(e);
        }
    }

    private void showScoreDialog()
    {
        try
        {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.game_score_dailog);
            dialog.setCanceledOnTouchOutside(true);
            Button dismissBtn = (Button)dialog.findViewById(R.id.dismiss_btn);
            final TextView playedScoreTv = (TextView) dialog.findViewById(R.id.played_score);
            final TextView wonScoreTv = (TextView) dialog.findViewById(R.id.won_score);
            final TextView lostScoreTv = (TextView) dialog.findViewById(R.id.lost_score);
            final TextView easyTagTv = (TextView) dialog.findViewById(R.id.easy_tag);
            final TextView mediumTagTv = (TextView) dialog.findViewById(R.id.medium_tag);
            final TextView hardTagTv = (TextView) dialog.findViewById(R.id.hard_tag);
            easyTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.white));
            easyTagTv.setBackgroundResource(R.drawable.selected_button_style);

            easyTagTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    easyTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.white));
                    easyTagTv.setBackgroundResource(R.drawable.selected_button_style);
                    mediumTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.black));
                    mediumTagTv.setBackgroundResource(R.drawable.unselected_button);
                    hardTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.black));
                    hardTagTv.setBackgroundResource(R.drawable.unselected_button);
                    playedScoreTv.setText(5+"");
                    wonScoreTv.setText(4+"");
                    lostScoreTv.setText(1+"");
                }
            });
            mediumTagTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediumTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.white));
                    mediumTagTv.setBackgroundResource(R.drawable.selected_button_style);
                    hardTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.black));
                    hardTagTv.setBackgroundResource(R.drawable.unselected_button);
                    easyTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.black));
                    easyTagTv.setBackgroundResource(R.drawable.unselected_button);
                    playedScoreTv.setText(10+"");
                    wonScoreTv.setText(7+"");
                    lostScoreTv.setText(6+"");
                }
            });
            hardTagTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hardTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.white));
                    hardTagTv.setBackgroundResource(R.drawable.selected_button_style);
                    mediumTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.black));
                    mediumTagTv.setBackgroundResource(R.drawable.unselected_button);
                    easyTagTv.setTextColor(LaunchActivity.this.getResources().getColor(R.color.black));
                    easyTagTv.setBackgroundResource(R.drawable.unselected_button);
                    playedScoreTv.setText(30+"");
                    wonScoreTv.setText(17+"");
                    lostScoreTv.setText(12+"");
                }
            });

            dismissBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }catch (Exception e)
        {
            DebugHandler.LogException(e);
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}

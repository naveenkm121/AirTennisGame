package com.dhisat.naveen.airtennisgame.gamecomponants;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

import com.dhisat.naveen.airtennisgame.GameActivity;
import com.dhisat.naveen.airtennisgame.GameState;
import com.dhisat.naveen.airtennisgame.R;
import com.dhisat.naveen.airtennisgame.constants.AppConstants;
import com.dhisat.naveen.airtennisgame.constants.ObjectDimensions;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;
import com.dhisat.naveen.airtennisgame.presenter.SharedCommon;

/**
 * Created by naveen on 11/6/16.
 */
public class Ball {
    public int xPosition;
    public int yPosition;
    public int xSpeed= AppConstants.BallSpeed;
    public int ySpeed=AppConstants.BallSpeed;
    public int botScore;
    public   int myScore;
    private MediaPlayer mediaPlayer;
    private Context context;

    public Ball(Context context,int xPosition, int yPosition)
    {
        this.context=context;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        botScore=0;
        myScore=0;
        mediaPlayer = MediaPlayer.create(context,R.raw.miss_ball);
    }

    public void update() {

        xPosition += xSpeed;
        yPosition += ySpeed;

        if (yPosition > (GameActivity.SCREEN_HEIGHT-ObjectDimensions.ScreenPadding-ObjectDimensions.BallRadius) || yPosition < (ObjectDimensions.ScreenYPosition+ObjectDimensions.BallRadius)) {
            if(yPosition > (GameActivity.SCREEN_HEIGHT-ObjectDimensions.ScreenPadding-ObjectDimensions.BallRadius))
            {
                if(SharedCommon.getSpeakerState(context).equals("On")) {
                    mediaPlayer.start();
                }
                botScore++;
            }
            if(yPosition < (ObjectDimensions.ScreenYPosition+ObjectDimensions.BallRadius))
            {
                if(SharedCommon.getSpeakerState(context).equals("On")) {
                    mediaPlayer.start();
                }
                myScore++;
            }
            xPosition = GameActivity.SCREEN_WIDTH/2;
            yPosition = GameActivity.SCREEN_HEIGHT/2;


        }
        if (xPosition > (GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding-ObjectDimensions.BallRadius) || xPosition < (ObjectDimensions.ScreenXPosition+ObjectDimensions.BallRadius)) {
            xSpeed *= -1;
        }
    }

    public void draw(Canvas canvas,Paint paint){
        if(canvas!=null)
        {
           // canvas.drawRect(new Rect(xPosition, yPosition, xPosition + ballRadius, yPosition + ballRadius), paint);
            canvas.drawCircle(xPosition,yPosition, ObjectDimensions.BallRadius,paint);
        }
    }
}

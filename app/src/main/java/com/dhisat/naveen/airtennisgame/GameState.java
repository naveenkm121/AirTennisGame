package com.dhisat.naveen.airtennisgame;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.animation.PathInterpolator;
import android.widget.Toast;

import com.dhisat.naveen.airtennisgame.constants.AppConstants;
import com.dhisat.naveen.airtennisgame.constants.ObjectDimensions;
import com.dhisat.naveen.airtennisgame.gamecomponants.Ball;
import com.dhisat.naveen.airtennisgame.gamecomponants.Player;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;
import com.dhisat.naveen.airtennisgame.presenter.SharedCommon;

/**
 * Created by naveen on 11/6/16.
 */
public class GameState {
    private Background bg;
    private Ball  ball;
    private Player botPlayer;
    private Player myPlayer;
    private Context context;
    private int botSpeed = AppConstants.BotBatSpeed;
    public int playerScore;
    public int botScore;
    public MediaPlayer mediaPlayer;



    private int botXposition=(GameActivity.SCREEN_WIDTH/2)-(ObjectDimensions.BatWidth/2);
    private int myPlayerXposition = (GameActivity.SCREEN_WIDTH/2)-(ObjectDimensions.BatWidth/2);
    private int myPlayerYposition = GameActivity.SCREEN_HEIGHT-ObjectDimensions.PlayerYPadding;

    public GameState(Context context) {
        this.context = context;
        bg = new Background();
        ball = new Ball(context,GameActivity.SCREEN_WIDTH/2,GameActivity.SCREEN_HEIGHT/2);
        botPlayer = new Player(botXposition,ObjectDimensions.PlayerYPadding);
        myPlayer = new Player(myPlayerXposition,myPlayerYposition);
        mediaPlayer = MediaPlayer.create(context,R.raw.ball_hitting_sound);
    }


    public void draw(Canvas canvas) {
        try {
            Paint paint = new Paint();
            bg.draw(canvas,context);
            if(playerScore == ObjectDimensions.TargetScore || botScore==ObjectDimensions.TargetScore)
            {

            }else{
                updateScore(canvas);
            }
            paint.setColor(context.getResources().getColor(R.color.light_yellow));
            ball.draw(canvas,paint);
            paint.setColor(context.getResources().getColor(R.color.light_green));
            botPlayer.draw(canvas,paint);
            paint.setColor(context.getResources().getColor(R.color.red));
            myPlayer.draw(canvas,paint);

        }catch (Exception e)
        {
            DebugHandler.LogException(e);
        }

    }
    public void update()
    {
        try
        {
            ball.update();
            if(ball.yPosition < GameActivity.SCREEN_HEIGHT/2 && ball.xPosition <GameActivity.SCREEN_WIDTH/2)
            {
                botPlayer.xPosition=botPlayer.xPosition-botSpeed;
                if(botPlayer.xPosition<ObjectDimensions.ScreenXPosition)
                {
                    botPlayer.xPosition =ObjectDimensions.ScreenXPosition+botSpeed;
                }
            }
            if(ball.yPosition < GameActivity.SCREEN_HEIGHT/2 && ball.xPosition >GameActivity.SCREEN_WIDTH/2)
            {
                botPlayer.xPosition=botPlayer.xPosition+botSpeed;
                if(botPlayer.xPosition > (GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding-ObjectDimensions.BatWidth))
                {
                    botPlayer.xPosition=GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding-ObjectDimensions.BatWidth-5;
                }
            }
            //Collisions with the bats
            if (ball.xPosition +ObjectDimensions.BallRadius > botPlayer.xPosition && ball.xPosition-ObjectDimensions.BallRadius < botPlayer.xPosition + ObjectDimensions.BatWidth && ball.yPosition-ObjectDimensions.BallRadius < botPlayer.yPosition+ObjectDimensions.BatHeight && ball.yPosition +ObjectDimensions.BallRadius > botPlayer.yPosition) {
                ball.ySpeed *= -1;
                if(SharedCommon.getSpeakerState(context).equals("On")) {
                    mediaPlayer.start();
                }
            }
            if (ball.xPosition+ObjectDimensions.BallRadius  > myPlayer.xPosition && ball.xPosition-ObjectDimensions.BallRadius < myPlayer.xPosition + ObjectDimensions.BatWidth && ball.yPosition +ObjectDimensions.BallRadius > myPlayer.yPosition && ball.yPosition +ObjectDimensions.BallRadius < myPlayer.yPosition +ObjectDimensions.BatHeight) {
                ball.ySpeed *= -1;
                if(SharedCommon.getSpeakerState(context).equals("On")) {
                    mediaPlayer.start();
                }
            }

        }catch (Exception e)
        {
            DebugHandler.LogException(e);
        }
    }
    public boolean getClickPositions(int xPosition,int yPosition)
    {
        if((yPosition >(GameActivity.SCREEN_HEIGHT/2))&&(yPosition <(GameActivity.SCREEN_HEIGHT- ObjectDimensions.ScreenPadding))){
            if(xPosition  < (GameActivity.SCREEN_WIDTH/2)) {
                myPlayer.xPosition = myPlayer.xPosition - myPlayer.batSpeed;
                if(myPlayer.xPosition<ObjectDimensions.ScreenXPosition)
                {
                    myPlayer.xPosition =ObjectDimensions.ScreenXPosition+AppConstants.PlayerBatSpeed;
                }
            }
            else if(xPosition > (GameActivity.SCREEN_WIDTH/2)){
                myPlayer.xPosition = myPlayer.xPosition + myPlayer.batSpeed;
                if(myPlayer.xPosition > (GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding-ObjectDimensions.BatWidth))
                {
                    myPlayer.xPosition=GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding-ObjectDimensions.BatWidth-5;
                }
            }
        }

        return true;
    }

    void updateScore(Canvas canvas)
    {
        if(canvas!=null) {
            Paint paint = new Paint();
            paint.setTextSize(40);
            paint.setColor(context.getResources().getColor(R.color.gray_4));
            playerScore = ball.myScore;
            botScore = ball.botScore;
            canvas.drawText(ball.botScore + "", GameActivity.SCREEN_WIDTH - 100, GameActivity.SCREEN_HEIGHT / 2 - 100, paint);
            canvas.drawText(ball.myScore + "", GameActivity.SCREEN_WIDTH - 100, GameActivity.SCREEN_HEIGHT / 2 + 100, paint);
            paint.setTextSize(35);
            canvas.drawText("You", GameActivity.SCREEN_WIDTH - 200, GameActivity.SCREEN_HEIGHT / 2 + 100, paint);
        }
    }

    void gameResultMessage(Canvas canvas,String message)
    {
        botScore=0;
        playerScore=0;
        Paint paint = new Paint();
        //bg.draw(canvas,context);
        Paint myPaint = new Paint();



//        paint.setColor(context.getResources().getColor(R.color.light_green));
//        botPlayer.draw(canvas,paint);
//        paint.setColor(context.getResources().getColor(R.color.red));
//        myPlayer.draw(canvas,paint);
        myPaint.setColor(context.getResources().getColor(R.color.black));
        canvas.drawRect(ObjectDimensions.ScreenXPosition, ObjectDimensions.ScreenYPosition, GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding, GameActivity.SCREEN_HEIGHT-ObjectDimensions.ScreenPadding, myPaint);
        paint.setTextSize(60);
        paint.setColor(context.getResources().getColor(R.color.dark_orange));
        canvas.drawText(message,GameActivity.SCREEN_WIDTH/2-230,GameActivity.SCREEN_HEIGHT/2-90,paint);
        paint.setTextSize(40);
        paint.setColor(context.getResources().getColor(R.color.dark_orange));
        canvas.drawText("Do you want to give challenge?  ",80,GameActivity.SCREEN_HEIGHT/2-20,paint);
        paint.setColor(context.getResources().getColor(R.color.light_green));
        canvas.drawCircle(GameActivity.SCREEN_WIDTH-120,GameActivity.SCREEN_HEIGHT-120, 60,paint);
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
        paint.setTextSize(30);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("OK",GameActivity.SCREEN_WIDTH-140,GameActivity.SCREEN_HEIGHT-110,paint);
    }

}

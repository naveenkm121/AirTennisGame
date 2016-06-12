package com.dhisat.naveen.airtennisgame;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.Toast;

import com.dhisat.naveen.airtennisgame.constants.ObjectDimensions;
import com.dhisat.naveen.airtennisgame.gamecomponants.Ball;
import com.dhisat.naveen.airtennisgame.gamecomponants.Player;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;

/**
 * Created by naveen on 11/6/16.
 */
public class GameState {
    private Background bg;
    private Ball  ball;
    private Player botPlayer;
    private Player myPlayer;
    private Context context;
    private int botSpeed =3;


    private int botXposition=(GameActivity.SCREEN_WIDTH/2)-(ObjectDimensions.BatWidth/2);
    private int myPlayerXposition = (GameActivity.SCREEN_WIDTH/2)-(ObjectDimensions.BatWidth/2);
    private int myPlayerYposition = GameActivity.SCREEN_HEIGHT-ObjectDimensions.PlayerYPadding;

    public GameState(Context context) {
        this.context = context;
        bg = new Background();
        ball = new Ball(GameActivity.SCREEN_WIDTH/2,GameActivity.SCREEN_HEIGHT/2);
        botPlayer = new Player(botXposition,ObjectDimensions.PlayerYPadding);
        myPlayer = new Player(myPlayerXposition,myPlayerYposition);
    }


    public void draw(Canvas canvas) {
        try {
            bg.draw(canvas);
            ball.draw(canvas);
            botPlayer.draw(canvas);
            myPlayer.draw(canvas);
            updateScore(canvas);
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
                    botPlayer.xPosition =ObjectDimensions.ScreenXPosition+5;
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
            if (ball.xPosition -ObjectDimensions.BallRadius > botPlayer.xPosition && ball.xPosition+ObjectDimensions.BallRadius < botPlayer.xPosition + ObjectDimensions.BatWidth && ball.yPosition-ObjectDimensions.BallRadius < botPlayer.yPosition+ObjectDimensions.BatHeight) {
                ball.ySpeed *= -1;
            }
            if (ball.xPosition-ObjectDimensions.BallRadius  > myPlayer.xPosition && ball.xPosition+ObjectDimensions.BallRadius < myPlayer.xPosition + ObjectDimensions.BatWidth && ball.yPosition +ObjectDimensions.BallRadius > myPlayer.yPosition) {
                ball.ySpeed *= -1;
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
                    myPlayer.xPosition =ObjectDimensions.ScreenXPosition+5;
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
        Paint paint = new Paint();
        paint.setTextSize(40);
        canvas.drawText(ball.botScore+"",GameActivity.SCREEN_WIDTH-100,GameActivity.SCREEN_HEIGHT/2-100,paint);
        canvas.drawText(ball.myScore+"",GameActivity.SCREEN_WIDTH-100,GameActivity.SCREEN_HEIGHT/2+100,paint);
        paint.setTextSize(40);
        canvas.drawText("You",GameActivity.SCREEN_WIDTH-200,GameActivity.SCREEN_HEIGHT/2+100,paint);
    }

    void showDialog(final Context context)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("Welcome to AndroidHive.info");
        alertDialog.show();
    }
}

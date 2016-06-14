package com.dhisat.naveen.airtennisgame.gamecomponants;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.dhisat.naveen.airtennisgame.GameActivity;
import com.dhisat.naveen.airtennisgame.GameState;
import com.dhisat.naveen.airtennisgame.R;
import com.dhisat.naveen.airtennisgame.constants.ObjectDimensions;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;

/**
 * Created by naveen on 11/6/16.
 */
public class Ball {
    public int xPosition;
    public int yPosition;
    public int xSpeed=6;
    public int ySpeed=6;
    public int botScore;
    public   int myScore;


    public Ball(int xPosition,int yPosition)
    {
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        botScore=0;
        myScore=0;
    }

    public void update() {

        xPosition += xSpeed;
        yPosition += ySpeed;

        if (yPosition > (GameActivity.SCREEN_HEIGHT-ObjectDimensions.ScreenPadding-ObjectDimensions.BallRadius) || yPosition < (ObjectDimensions.ScreenYPosition+ObjectDimensions.BallRadius)) {
            if(yPosition > (GameActivity.SCREEN_HEIGHT-ObjectDimensions.ScreenPadding-ObjectDimensions.BallRadius))
            {
                botScore++;
            }
            if(yPosition < (ObjectDimensions.ScreenYPosition+ObjectDimensions.BallRadius))
            {
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

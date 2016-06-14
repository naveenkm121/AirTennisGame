package com.dhisat.naveen.airtennisgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.dhisat.naveen.airtennisgame.constants.ObjectDimensions;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;

/**
 * Created by naveen on 11/6/16.
 */
public class Background{

    private Bitmap image;
    private int x,y,dx;

    public Background(){
       // image = res;
    }

    public void draw(Canvas canvas, Context context){
        if(canvas!=null)
        {
            Paint myPaint = new Paint();
            myPaint.setColor(context.getResources().getColor(R.color.light_green));
            canvas.drawRect(ObjectDimensions.ScreenXPosition, ObjectDimensions.ScreenYPosition, GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding, GameActivity.SCREEN_HEIGHT-ObjectDimensions.ScreenPadding, myPaint);

            myPaint.setColor(Color.BLACK);
            myPaint.setStrokeWidth(6f);
            int middleY= GameActivity.SCREEN_HEIGHT/2;
            int middleX= GameActivity.SCREEN_WIDTH/2;
            canvas.drawLine(ObjectDimensions.ScreenXPosition, middleY, GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding, middleY, myPaint);
//            myPaint.setStrokeWidth(3f);
//            canvas.drawLine(middleX, ObjectDimensions.ScreenYPosition, middleX, GameActivity.SCREEN_HEIGHT-ObjectDimensions.ScreenPadding, myPaint);

            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(6f);
            myPaint.setColor(Color.WHITE);
            canvas.drawRect(ObjectDimensions.ScreenXPosition, ObjectDimensions.ScreenYPosition, GameActivity.SCREEN_WIDTH-ObjectDimensions.ScreenPadding, GameActivity.SCREEN_HEIGHT-ObjectDimensions.ScreenPadding, myPaint);
        }
    }
}

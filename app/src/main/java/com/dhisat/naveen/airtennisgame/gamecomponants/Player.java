package com.dhisat.naveen.airtennisgame.gamecomponants;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.dhisat.naveen.airtennisgame.GameActivity;
import com.dhisat.naveen.airtennisgame.constants.ObjectDimensions;

/**
 * Created by naveen on 11/6/16.
 */
public class Player {
    public int xPosition;
    public int yPosition;
    public int batSpeed=30;
    private int batWidth=200;
    private int batHeight=20;

    public Player(int xPosition,int yPosition){
        this.xPosition= xPosition;
        this.yPosition = yPosition;
    }

    public void draw(Canvas canvas,Paint paint){
        if(canvas!=null)
        {
            canvas.drawRect(new Rect(xPosition, yPosition, xPosition + ObjectDimensions.BatWidth, yPosition + ObjectDimensions.BatHeight), paint);
        }
    }

}

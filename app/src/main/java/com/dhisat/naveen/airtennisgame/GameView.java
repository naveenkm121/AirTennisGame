package com.dhisat.naveen.airtennisgame;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;

/**
 * Created by naveen on 11/6/16.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {


    private GameThread gameThread;
    private int clickXposition,clickYposition;
    private Context context;
    public GameView(Context context)
    {
        super(context);
        this.context = context;
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(false);
        gameThread = new GameThread(holder, context, new Handler());
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            gameThread.setRunning(true);
            gameThread.start();
        }catch (Exception e)
        {
            DebugHandler.LogException(e);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry)
        {
            try
            {
                gameThread.setRunning(false);
                gameThread.join();
                retry = false;
                gameThread=null;
            }
            catch (Exception e)
            {
                DebugHandler.LogException(e);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(gameThread.getRunning()) {
                    clickXposition = (int) event.getX();
                    clickYposition = (int) event.getY();
                    return gameThread.getGameState().getClickPositions(clickXposition, clickYposition);
                }else{
                    DebugHandler.Log("you lost the gamne");

                }
            default:
                return false;
        }
    }

}

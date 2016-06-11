package com.dhisat.naveen.airtennisgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;

/**
 * Created by naveen on 11/6/16.
 */
public class GameThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameState gameState;
    private boolean running;
    public static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        this.surfaceHolder = surfaceHolder;
        gameState = new GameState();
    }

    @Override
    public void run() {
        while (running)
        {
           canvas=null;
            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    gameState.update();
                    gameState.draw(canvas);
                }
            } catch (Exception e) {
                DebugHandler.LogException(e);
            }
            finally {
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e)
                    {
                        DebugHandler.LogException(e);
                    }
                }
            }
        }
    }

    public GameState getGameState()
    {
        return gameState;
    }
    public void setRunning(boolean b)
    {
        running=b;
    }


}

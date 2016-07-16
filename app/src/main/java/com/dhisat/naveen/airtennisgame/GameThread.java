package com.dhisat.naveen.airtennisgame;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.SurfaceHolder;

import com.dhisat.naveen.airtennisgame.constants.ObjectDimensions;
import com.dhisat.naveen.airtennisgame.presenter.DebugHandler;

/**
 * Created by naveen on 11/6/16.
 */
public class GameThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameState gameState;
    public boolean running;
    public static Canvas canvas;
    private Context context;

    public GameThread(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        this.surfaceHolder = surfaceHolder;
        gameState = new GameState(context);
        this.context = context;
    }

    @Override
    public void run() {
        while (running)
        {
            DebugHandler.Log("running :: ::");
                canvas = null;
                try {
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        if (gameState.botScore == ObjectDimensions.TargetScore || gameState.playerScore==ObjectDimensions.TargetScore) {
                            if(gameState.botScore == ObjectDimensions.TargetScore )
                            {
                                gameState.gameResultMessage(canvas,"You Lose !!!");

                            }else{
                                gameState.gameResultMessage(canvas,"You Won !!!");
                            }
                            gameState.botScore=0;
                            gameState.playerScore=0;
                            running=false;

                        }else
                        {
                            DebugHandler.Log("game target score");
                            gameState.update();
                            gameState.draw(canvas);
                        }
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
        DebugHandler.Log("running ::"+b);
        running=b;
    }
    public boolean getRunning()
    {
        return running;
    }


}

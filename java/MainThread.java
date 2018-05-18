/***********************************************************************
 * Name:           Joseph Schell & Sagnik Bhadra
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          MainThread
 * Date:           1/23/2018
 **********************************************************************/


package edu.pacificu.cs.flappybird;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Creates a MainThread class which allows fro threading
 *
 */

public class MainThread extends Thread
{
  private final int TARGET_FPS = 60;
  private final int THOUSAND = 1000;
  private final int TIME_NANOSECOND = 1000000;
  private double mAvgFPS;
  private SurfaceHolder mSurfaceHolder;
  private GameView mGameView;
  private boolean mRunning;
  public static Canvas mCanvas;

  /**
   * Initializes values
   */

  public MainThread(SurfaceHolder surfaceHolder, GameView gameView)
  {
    super();
    this.mSurfaceHolder = surfaceHolder;
    this.mGameView = gameView;
  }

  /**
   * Deals with FPS
   */

  @Override
  public void run()
  {
    long startTime;
    long timeMillis;
    long waitTime;
    long totalTime = 0;
    int frameCount = 0;
    long targetTime = THOUSAND / TARGET_FPS;

    while (mRunning)
    {
      startTime = System.nanoTime();
      mCanvas = null;

      try
      {
        mCanvas = this.mSurfaceHolder.lockCanvas();

        synchronized (mSurfaceHolder)
        {
          this.mGameView.update();
          this.mGameView.draw(mCanvas);
        }
      }
      catch (Exception e)
      {
      }
      finally
      {
        if (null != mCanvas)
        {
          try
          {
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      }

      timeMillis = (System.nanoTime() - startTime) / TIME_NANOSECOND;
      waitTime = targetTime - timeMillis;

      try
      {
        this.sleep (waitTime);
      }
      catch (Exception e)
      {

      }

      totalTime += System.nanoTime() - startTime;
      frameCount++;

      if (frameCount == TARGET_FPS)
      {
        mAvgFPS = THOUSAND / ((totalTime/frameCount) / TIME_NANOSECOND);
        frameCount = 0;
        totalTime = 0;
        System.out.println(mAvgFPS);
      }
    }
  }

  /**
   * Sets up if the game is running or not
   */

  public void setRunning(boolean isRunning)
  {
    mRunning = isRunning;
  }
}

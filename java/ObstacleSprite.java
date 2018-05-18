/***********************************************************************
 * Name:           Joseph Schell & Sagnik Bhadra
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          ObstacleSprite
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Creates an ObstacleSprite class which creates a pair of pipes
 * which are the obstacles
 *
 */

public class ObstacleSprite extends Sprite
{
  private static final int TWO = 2;
  private Bitmap mImage;
  private Bitmap mImage2;
  public int mXValue, mYValue;
  private int mScreenHeight =
      Resources.getSystem().getDisplayMetrics().heightPixels;

  /**
   * Initializes the original obstacle values
   */

  public ObstacleSprite (Bitmap bmp, Bitmap bmp2, int x, int y)
  {
    mImage = bmp;
    mImage2 = bmp2;
    this.mYValue = y;
    this.mXValue = x;
  }

  /**
   * Draw the obstacles
   */

  @Override
  public void draw(Canvas canvas)
  {
    canvas.drawBitmap(mImage, this.mXValue, -(GameView.GAP_HEIGHT / TWO)
        + this.mYValue, null);
    canvas.drawBitmap(mImage2, this.mXValue, ((mScreenHeight / TWO)
        + (GameView.GAP_HEIGHT / TWO)) + this.mYValue, null);
  }

  /**
   * Moves the obstacles
   */

  public void update()
  {
    this.mXValue -= GameView.GAME_VELOCITY;
  }

  /**
   * Returns the xValue
   */

  public int getXValue ()
  {
    return this.mXValue;
  }

  /**
   * Returns the yValue
   */

  public int getYValue ()
  {
    return this.mYValue;
  }

}

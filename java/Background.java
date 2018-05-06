/***********************************************************************
 * Name:           Joseph Schell
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          Background
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Creates a Background class which is the environment that the user
 * is playing in
 *
 */

public class Background
{
  private Bitmap mImage;
  private int mX, mY, mXMovement;

  /**
   * Initializes the Bitmap to the passed in value
   */

  public Background(Bitmap res)
  {
    mImage = res;
  }

  /**
   * Moves the background
   */

  public void update()
  {
    mX += mXMovement;

    if (mX < -GameView.WIDTH)
    {
      mX = 0;
    }
  }

  /**
   * Redraws the background
   */

  public void draw(Canvas canvas)
  {
    canvas.drawBitmap (mImage, mX, mY, null);

    if (mX < 0)
    {
      canvas.drawBitmap (mImage, mX + GameView.WIDTH, mY, null);
    }
  }

  /**
   * Set the amount the background is going to be moved
   */

  public void setVector (int dx)
  {
    this.mXMovement = dx;
  }

}

/***********************************************************************
 * Name:           Joseph Schell
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          Animal
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Creates an Animal class which extends from Sprite and is the
 * creature that the user is trying to get through the obstacles
 *
 */

public class Animal extends Sprite
{
  private final int X_SPRITE_ADJUST = 100;
  private final int Y_SPRITE_ADJUST = 250;
  private Bitmap mImage;
  private int mXValue, mYValue;
  private int mYVelocity = 4;

  /**
   * Constructor
   */

  public Animal (Bitmap bmp)
  {
    mImage = bmp;
  }

  /**
   * Draws the bitmap
   */

  @Override
  public void draw (Canvas canvas)
  {
    canvas.drawBitmap(mImage, this.mXValue + X_SPRITE_ADJUST,
        this.mYValue + Y_SPRITE_ADJUST, null);
  }

  /**
   * Updates the Sprite
   */

  public void update()
  {
    this.mYValue += this.mYVelocity;
  }

  /**
 * Returns the xPosition
 */

  public int getXValue ()
  {
    return this.mXValue;
  }

  /**
   * Returns the yPosition
   */

  public int getYValue ()
  {
    return this.mYValue;
  }

  /**
   * Sets the yPosition to the passed in value
   */

  public void setYValue (int yValue)
  {
    this.mYValue = yValue;
  }


  public void setXValue (int xValue)
  {
    this.mXValue = xValue;
  }

  /**
   * Returns the velocity
   */

  public int getYVelocity ()
  {
    return this.mYVelocity;
  }

}

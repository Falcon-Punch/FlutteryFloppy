/***********************************************************************
 * Name:           Joseph Schell & Sagnik Bhadra
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          Sprite
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Creates a Sprite class which creates a stationary sprite
 *
 */

public class Sprite
{
  private Bitmap mTheImage;
  private int mXPosition;
  private int mYPosition;

  /**
   * Default constructor
   */

  public Sprite ()
  {
  }

  /**
   * Constructor
   */

  public Sprite (Bitmap image)
  {
    this.mTheImage = image;
  }

  /**
   * Sets the xPosition to the passed in value
   */

  protected void setXPosition (int xPosition)
  {
    this.mXPosition = xPosition;
  }

  /**
   * Sets the yPosition to the passed in value
   */

  protected void setYPosition (int yPosition)
  {
    this.mYPosition = yPosition;
  }

  /**
   * Draws the bitmap
   */

  public void draw (Canvas canvas)
  {
    canvas.drawBitmap (this.mTheImage, this.mXPosition,
        this.mYPosition, null);
  }

  /**
   * Updates the Sprite which is stationary so nothing is updated
   */

  public void update ()
  {

  }

  /**
   * Returns the xPosition
   */

  public int getXValue ()
  {
    return this.mXPosition;
  }

  /**
   * Returns the yPosition
   */

  public int getYValue ()
  {
    return this.mYPosition;
  }

  /**
   * Sets the yPosition to the passed in value
   */

  public void setYValue (int yValue)
  {
    this.mYPosition = yValue;
  }

  /**
   * Returns the velocity which is 0
   */

  public void setXValue (int xValue)
  {
    this.mXPosition = xValue;
  }

  public int getYVelocity ()
  {
    return 0;
  }
}

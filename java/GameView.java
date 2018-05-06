/***********************************************************************
 * Name:           Joesph Schell
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          GameView
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.pacificu.cs.flappybird.MainThread.mCanvas;

/**
 * Creates a GameView class which is the actual game
 *
 */

public class GameView extends SurfaceView implements
    SurfaceHolder.Callback
{
  public static int WIDTH = Resources.getSystem ()
      .getDisplayMetrics ().widthPixels;
  public static int HEIGHT = Resources.getSystem ()
      .getDisplayMetrics ().heightPixels;
  public static final int GAP_HEIGHT = 300;
  public static final int GAME_VELOCITY = 10;
  public static final int SPRITE_WIDTH = 95;
  public static final int SPRITE_HEIGHT = 65;
  public static final int UNDERWATER_MODE = 0;
  public static final int AIR_MODE = 1;
  public static final int SPACE_MODE = 2;
  private static final int NUMBER_DIMENSION = 100;
  private static final int TWO = 2;
  private static final int THREE = 3;
  private static final int FOUR = 4;
  private static final int FIVE = 5;
  private static final int SIX = 6;
  private static final int SEVEN = 7;
  private static final int EIGHT = 8;
  private static final int NINE = 9;
  private static final int TEN = 10;
  private static final int HUNDRED = 100;
  private static final int JUMP_HEIGHT = 40;
  private static final int TIME_TO_CREATE_OBSTACLE = 80;
  private static final int SCORE_Y_HEIGHT = 80;
  private static final int SCORE_X_WIDTH_ONES = 200;
  private static final int SCORE_X_WIDTH_TENS = 300;
  private static final int SCORE_X_WIDTH_HUND = 400;
  private static final int EXPLODE_INDEX = 13;
  private static final int PIPE_DISTANCE = 1000;
  private static final int PIPE_Y_ADJUSTMENT_1 = 300;
  private static final int PIPE_Y_ADJUSTMENT_2 = 150;
  private static final int PIPE_WIDTH = 200;
  private static final int PIPE_COLLISION_GAP_TOP = 492;
  private static final int PIPE_COLLISION_GAP_BOTTOM = 128;
  private static final int PIPE_COLLISION_FRONT = 200;
  private static final int PIPE_COLLISION_BACK = 100;
  private static final int BOTTOM_SCREEN = 300;
  private static final int TOP_SCREEN = -240;

  private int mAnimalAppearanceCounter = 0;
  private List<ObstacleSprite> mcPairObstacles =
      new ArrayList<ObstacleSprite> (10);
  private List<Sprite> mcNumberSprites = new ArrayList<Sprite> (10);
  private EndGameActivity mcEndGame;
  private Intent mEndGameIntent;
  private MainThread mcThread;
  public Background mcBackGround;
  private int mMode;
  private boolean mModeChoosen;
  private int mCounter;
  private int mIndex;
  private int mNumOfAnimals;
  private int mOnesScore;
  private int mTensScore;
  private int mHundredsScore;
  private int mSeed = 0;
  private Random mRand = new Random (mSeed);
  private boolean mGameOver = false;

  /**
   * Sets up content view
   */

  public GameView (Context context, int mode)
  {
    super (context);
    getHolder ().addCallback (this);
    this.mMode = mode;
    this.mModeChoosen = true;

    this.mcEndGame = new EndGameActivity ();

    this.mcEndGame = (EndGameActivity) context;
    this.mcThread = new MainThread (getHolder (), this);

    setFocusable (true);
  }

  @Override
  public void surfaceChanged (SurfaceHolder holder, int format,
      int width,
      int height)
  {

  }

  /**
   * Creates the surface depending on which mode the user
   * wants to play in
   */

  @Override
  public void surfaceCreated (SurfaceHolder holder)
  {

    if (UNDERWATER_MODE == this.mMode)
    {
      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.fish1),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.fish2),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.fish3),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcBackGround = new Background (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.waterbg5));
    }
    else if (AIR_MODE == this.mMode)
    {
      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.bee1),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.bee2),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.bee3),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcBackGround = new Background (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.garden));
    }
    else if (SPACE_MODE == this.mMode)
    {
      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.alien1),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.alien2),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcNumberSprites.add (new Animal (getResizedBitmap (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.alien3),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));
      this.mNumOfAnimals++;

      this.mcBackGround = new Background (
          BitmapFactory.decodeResource (getResources (),
              R.drawable.space));
    }

    this.createObstacle ();
    this.loadNumberSprites (this.mcNumberSprites);

    // Moves screen
    this.mcBackGround.setVector (0);
    this.mcThread.setRunning (true);
    this.mcThread.start ();
  }

  /**
   * Takes care of when the surface is destroyed
   */

  @Override
  public void surfaceDestroyed (SurfaceHolder holder)
  {
    boolean retry = true;

    while (retry)
    {
      try
      {
        this.mcThread.setRunning (false);
        this.mcThread.join ();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace ();
      }

      retry = false;
    }
  }

  /**
   * Deals with the event when the user clicks the screeen.
   * Makes the creature jump
   */

  @Override
  public boolean onTouchEvent (MotionEvent event)
  {
    MediaPlayer jump = MediaPlayer.create(this.mcEndGame,R.raw.jump);


    for (int i = 0; i < this.mNumOfAnimals; i++)
    {
      this.mcNumberSprites.get (i).setYValue (
          this.mcNumberSprites.get (i).getYValue () - (
              this.mcNumberSprites.get (i).getYVelocity () * JUMP_HEIGHT));
      jump.start();
    }

    return super.onTouchEvent (event);
  }

  /**
   * Updates the sprites, obstacles, and creates more
   * obstacles if needed
   */

  public void update ()
  {
    if (!this.mGameOver)
    {
      this.mCounter++;
      if (null != mCanvas)
      {

      }
      this.mcBackGround.update ();

      for (int i = 0; i < this.mNumOfAnimals; i++)
      {
        this.mcNumberSprites.get (i).update ();
      }

      this.logic ();

      for (int i = 0; i < this.mIndex; i++)
      {
        this.mcPairObstacles.get (i).update ();
      }

      if (TIME_TO_CREATE_OBSTACLE / this.mCounter == 0)
      {
        this.createObstacle ();
        this.mCounter = 1;
      }
    }

    if (this.mcPairObstacles.get (
        (this.mHundredsScore * HUNDRED) + (this.mTensScore * TEN) +
            this.mOnesScore)
        .getXValue () == this.mcNumberSprites.get (0).getXValue ())
    {
      incrementScore ();
    }
  }

  /**
   * Draws the background and the score
   */

  @Override
  public void draw (Canvas canvas)
  {
    super.draw (canvas);
    this.mcBackGround.draw (canvas);

    for (int i = 0; i < this.mIndex; i++)
    {
      this.mcPairObstacles.get (i).draw (canvas);
    }

    if (!mGameOver)
    {
      if (this.mAnimalAppearanceCounter % NINE == 0
        || this.mAnimalAppearanceCounter % NINE == 1
        || this.mAnimalAppearanceCounter % NINE == TWO)
      {
        this.mcNumberSprites.get (0).draw (canvas);
      }

      else if (this.mAnimalAppearanceCounter % NINE == THREE
        || this.mAnimalAppearanceCounter % NINE == FOUR
        || this.mAnimalAppearanceCounter % NINE == FIVE)
      {
        this.mcNumberSprites.get (1).draw (canvas);
      }
      else if (this.mAnimalAppearanceCounter % NINE == SIX
        || this.mAnimalAppearanceCounter % NINE == SEVEN
        || this.mAnimalAppearanceCounter % NINE == EIGHT)
      {
        this.mcNumberSprites.get (TWO).draw (canvas);
      }
      this.mAnimalAppearanceCounter++;
    }


    if (0 != this.mHundredsScore)
    {
      this.mcNumberSprites.get (this.mHundredsScore + THREE).
          setYPosition (SCORE_Y_HEIGHT);
      this.mcNumberSprites.get (this.mHundredsScore + THREE).
          setXPosition (SCORE_X_WIDTH_ONES);
      this.mcNumberSprites.get (this.mHundredsScore + THREE).draw (canvas);
    }

    if (0 != this.mTensScore)
    {
      this.mcNumberSprites.get (this.mTensScore + THREE).setYPosition (SCORE_Y_HEIGHT);
      this.mcNumberSprites.get (this.mTensScore + THREE).setXPosition (SCORE_X_WIDTH_TENS);
      this.mcNumberSprites.get (this.mTensScore + THREE).draw (canvas);
    }

    this.mcNumberSprites.get (this.mOnesScore + THREE).setYPosition (SCORE_Y_HEIGHT);
    this.mcNumberSprites.get (this.mOnesScore + THREE).setXPosition (SCORE_X_WIDTH_HUND);
    this.mcNumberSprites.get (this.mOnesScore + THREE).draw (canvas);


    if (mGameOver)
    {
      int xCoord, yCoord;

      this.mcNumberSprites.add (new Animal (getResizedBitmap
        (
          BitmapFactory.decodeResource (getResources (),
            R.drawable.explode1),
          this.SPRITE_WIDTH, this.SPRITE_HEIGHT)));

      this.mNumOfAnimals++;

      xCoord = this.mcNumberSprites.get(TWO).getXValue();
      yCoord = this.mcNumberSprites.get(TWO).getYValue();
      this.mcNumberSprites.get (EXPLODE_INDEX).setYValue(yCoord);
      this.mcNumberSprites.get (EXPLODE_INDEX).setXValue(xCoord);
      this.mcNumberSprites.get (EXPLODE_INDEX).draw (canvas);
    }
  }

  /**
   * Sizes the Sprite to the intended size
   */

  public Bitmap getResizedBitmap (Bitmap bm, int newWidth,
      int newHeight)
  {
    int width = bm.getWidth ();
    int height = bm.getHeight ();
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;

    Matrix matrix = new Matrix ();
    matrix.postScale (scaleWidth, scaleHeight);

    Bitmap resizedBitmap = Bitmap
        .createBitmap (bm, 0, 0, width, height, matrix, false);
    bm.recycle ();

    return resizedBitmap;
  }

  /**
   * Creates the obstacles with the correct size
   */

  public void createObstacle ()
  {
    if (UNDERWATER_MODE == this.mMode)
    {
      this.mcPairObstacles.add (new ObstacleSprite (getResizedBitmap (
          BitmapFactory
              .decodeResource (getResources (),
                  R.drawable.greentoppipe), PIPE_WIDTH,
          Resources.getSystem ().getDisplayMetrics ().heightPixels / TWO),
          getResizedBitmap (BitmapFactory
                  .decodeResource (getResources (),
                      R.drawable.greenbottompipe),
            PIPE_WIDTH,
              Resources.getSystem ().getDisplayMetrics ().
                  heightPixels / TWO),
          PIPE_DISTANCE, mRand.nextInt (PIPE_Y_ADJUSTMENT_1) - PIPE_Y_ADJUSTMENT_2));
      this.mIndex++;
    }
    else if (AIR_MODE == this.mMode)
    {
      this.mcPairObstacles.add (new ObstacleSprite (getResizedBitmap (
          BitmapFactory
              .decodeResource (getResources (), R.drawable.
                  yellowtoppipe), PIPE_WIDTH,
          Resources.getSystem ().getDisplayMetrics ().
              heightPixels / TWO),
          getResizedBitmap (BitmapFactory
                  .decodeResource (getResources (), R.drawable.
                      yellowbottompipe),
            PIPE_WIDTH,
              Resources.getSystem ().getDisplayMetrics ().
                  heightPixels / TWO),
        PIPE_DISTANCE, mRand.nextInt (PIPE_Y_ADJUSTMENT_1) - PIPE_Y_ADJUSTMENT_2));
      this.mIndex++;
    }
    else if (SPACE_MODE == this.mMode)
    {
      this.mcPairObstacles.add (new ObstacleSprite (getResizedBitmap (
          BitmapFactory
              .decodeResource (getResources (),
                  R.drawable.bluetoppipe), PIPE_WIDTH,
          Resources.getSystem ().getDisplayMetrics ().
              heightPixels / TWO),
          getResizedBitmap (BitmapFactory
                  .decodeResource (getResources (), R.drawable.
                      bluebottompipe), PIPE_WIDTH,
              Resources.getSystem ().getDisplayMetrics ().
                  heightPixels / TWO),
        PIPE_DISTANCE, mRand.nextInt (PIPE_Y_ADJUSTMENT_1) - PIPE_Y_ADJUSTMENT_2));
      this.mIndex++;
    }
  }

  /**
   * Checks for collision
   */

  public void logic ()
  {


    for (int i = 0; i < this.mcPairObstacles.size (); i++)
    {
      // If Statement condition list:
      // 1. inside pipes (lower number raises collision line for top)
      // 2. front of pipe (higher number pushes detector forward)
      // 3. back of pipe

      if (this.mcNumberSprites.get (0).getYValue ()
          < this.mcPairObstacles.get (i).getYValue () + (PIPE_COLLISION_GAP_TOP) -
          (this.GAP_HEIGHT)
          && this.mcNumberSprites.get (0).getXValue () + PIPE_COLLISION_FRONT
          > this.mcPairObstacles.get (i).getXValue ()
          && this.mcNumberSprites.get (0).getXValue ()
          < this.mcPairObstacles.get (i).getXValue () + PIPE_COLLISION_BACK &&
          !this.mGameOver)
      {
        endGame ();
      }
      else if (this.mcNumberSprites.get (0).getYValue ()
          > this.mcPairObstacles.get (i).getYValue () + (PIPE_COLLISION_GAP_BOTTOM) +
          (this.GAP_HEIGHT)
          && this.mcNumberSprites.get (0).getXValue () + PIPE_COLLISION_FRONT
          > this.mcPairObstacles.get (i).getXValue ()
          && this.mcNumberSprites.get (0).getXValue ()
          < this.mcPairObstacles.get (i).getXValue () + PIPE_COLLISION_BACK &&
          !this.mGameOver)
      {
        endGame ();
      }
    }

    // Detect if the sprite touches top or bottom

    if (this.mcNumberSprites.get (0).getYValue () < TOP_SCREEN &&
        !this.mGameOver)
    {
      endGame ();
    }
    if (this.mcNumberSprites.get (0).getYValue () > HEIGHT - BOTTOM_SCREEN
        && !this.mGameOver)
    {
      endGame ();
    }
  }

  /**
   * Loads up all the numbers into a List Sprite
   */

  public void loadNumberSprites (List<Sprite> numberSprites)
  {
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num0),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num1),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num2),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num3),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num4),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num5),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num6),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num7),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num8),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
    numberSprites.add (new Sprite (getResizedBitmap (
        BitmapFactory.decodeResource (getResources (), R.drawable.num9),
        NUMBER_DIMENSION, NUMBER_DIMENSION)));
  }

  /**
   * Increments the score
   */

  public void incrementScore ()
  {
    this.mOnesScore++;

    if (TEN == this.mOnesScore)
    {
      this.mTensScore++;
      this.mOnesScore = 0;
    }

    if (TEN == this.mTensScore)
    {
      this.mHundredsScore++;
      this.mTensScore = 0;
    }
  }

  /**
   * Handles end game functionality and starts the endgameactivity
   */

  public void endGame ()
  {
    MediaPlayer die = MediaPlayer.create(this.mcEndGame,R.raw.explode1);
    die.start();

    this.mGameOver = true;

    this.mEndGameIntent = new Intent (this.mcEndGame,
        EndGameActivity.class);
    this.mEndGameIntent.putExtra ("boolean", this.mModeChoosen);
    this.mEndGameIntent.putExtra ("int", this.mMode);
    this.mEndGameIntent.putExtra ("score", this.getScore ());
    this.mcEndGame.startActivity (this.mEndGameIntent);
  }

  /**
   * Returns the score
   */

  public int getScore ()
  {
    return this.mHundredsScore * HUNDRED + this.mTensScore * TEN +
        this.mOnesScore;
  }
}

/***********************************************************************
 * Name:           Joseph Schell & Sagnik Bhadra
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          Game
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Creates a Game class which consists of the menu page
 *
 */

public class Game extends Activity implements View.OnClickListener
{
  private final int SPACE_MODE = 2;
  private final int AIR_MODE = 1;
  private final int WATER_MODE = 0;
  private Intent mEndGameIntent;
  private Intent mHighScoreIntent;
  private Button mButtonNewGame;
  private Button mButtonHighScore;
  private Button mButtonAbout;

  private ImageButton mImageUnderwater;
  private ImageButton mImageAir;
  private ImageButton mImageSpace;
  private Background mcBackGround;
  private int mMode;
  private boolean mModeChoosen;
  private int mScore;

  /**
   * Sets up content view
   */

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);

    // Sets to full screen
    getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    this.requestWindowFeature (Window.FEATURE_NO_TITLE);

    setContentView (R.layout.activity_game);

    MediaPlayer jump = MediaPlayer.create(Game.this,R.raw.jump);
    jump.start();

    init ();
    this.mImageSpace.setPressed (true);
    this.mImageUnderwater.setPressed (true);
  }

  /**
   * Initializes the Buttons, ImagesButtons, Background, mode
   */

  public void init ()
  {
    this.mMode = AIR_MODE;
    this.mButtonNewGame = (Button) findViewById (R.id.btnNewGame);
    this.mButtonHighScore = (Button) findViewById (R.id.btnHighScore);
    this.mButtonAbout = (Button) findViewById (R.id.btnAbout);

    this.mImageUnderwater = (ImageButton) findViewById
        (R.id.imageUnderwater);
    this.mImageAir = (ImageButton) findViewById (R.id.imageAir);
    this.mImageSpace = (ImageButton) findViewById (R.id.imageSpace);

    this.mButtonNewGame.setOnClickListener (this);
    this.mButtonHighScore.setOnClickListener (this);
    this.mButtonAbout.setOnClickListener (this);

    this.mImageUnderwater.setOnClickListener (this);
    this.mImageAir.setOnClickListener (this);
    this.mImageSpace.setOnClickListener (this);

    this.mcBackGround = new Background (
        BitmapFactory.decodeResource (getResources (),
            R.drawable.background));
  }

  /**
   * Figures out what to do when something is clicked
   */

  @Override
  public void onClick (View view)
  {
    switch (view.getId ())
    {
      case R.id.btnNewGame:

        this.mEndGameIntent = new Intent (this,
            EndGameActivity.class);
        this.mEndGameIntent.putExtra ("int", this.mMode);
        this.mEndGameIntent.putExtra ("score", this.mScore);
        this.mEndGameIntent.putExtra ("boolean",
            this.mModeChoosen);

        this.startActivity (this.mEndGameIntent);

        break;
      case R.id.btnHighScore:
        mHighScoreIntent = new Intent (this,
            HighScoreActivity.class);
        this.mHighScoreIntent.putExtra ("score", this.mScore);
        startActivity (mHighScoreIntent);
        break;
      case R.id.btnAbout:
        startActivity (new Intent (this,
            AboutActivity.class));
        break;
      case R.id.imageUnderwater:

        this.mMode = WATER_MODE;
        this.mImageAir.setPressed (true);
        this.mImageSpace.setPressed (true);
        break;
      case R.id.imageAir:

        this.mMode = AIR_MODE;
        this.mImageSpace.setPressed (true);
        this.mImageUnderwater.setPressed (true);

        break;
      case R.id.imageSpace:

        this.mMode = SPACE_MODE;
        this.mImageAir.setPressed (true);
        this.mImageUnderwater.setPressed (true);
        break;
    }
  }
}

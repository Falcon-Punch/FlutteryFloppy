/***********************************************************************
 * Name:           Joseph Schell & Sagnik Bhadra
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          EndGameActivity
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Creates an EndGameActivity class which is the screen that is
 * displayed when the user hits an obstacle. It displays the score
 * and gives the option to restart or go to the main menu.
 *
 */

public class EndGameActivity extends Activity
{
  private final int LAST_INDEX = 8;
  private final int HIGHSCORE_SIZE = 10;
  private final int LIMIT = 11;
  private ImageView mNewHighScore;
  private GameView mcGameView;
  private int mMode;
  private TextView mTextView;
  private int mScore;
  private SharedPreferences mPrefs;
  private SharedPreferences.Editor mEditor;
  private StringBuilder mStr = new StringBuilder ();
  private int mHighScore[] = new int[HIGHSCORE_SIZE];
  private int[] mSavedList;
  private String[] mStringSplit;
  private static boolean mModeChoosen;

  /**
   * Sets up content view accroding to what stage of the
   * game the user is at
   */

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    this.mModeChoosen = getIntent ().getExtras ().getBoolean
        ("boolean");

    this.mScore = getIntent ().getExtras ().getInt ("score");

    mPrefs = this.getSharedPreferences ("myPrefsKey",
        Context.MODE_PRIVATE);
    mEditor = mPrefs.edit ();

    //Uncomment when need to refresh score or if the Shared Preferences
    // don't have any values registered

    /*for (int i = 0; i < mHighScore.length; i++) {
      mStr.append(mHighScore[i]).append(",");
    }

    mEditor.putString("string", mStr.toString());
    mEditor.commit ();*/

    String isPresent = mPrefs.getString("string",null);

    if (isPresent == null)
    {
      for (int i = 0; i < mHighScore.length; i++) {
        mStr.append(mHighScore[i]).append(",");
      }

      mEditor.putString("string", mStr.toString());
      mEditor.commit ();
    }

    String savedString = mPrefs.getString ("string", "");
    mStringSplit = new String[HIGHSCORE_SIZE];
    mStringSplit = savedString.split (",", LIMIT);
    mSavedList = new int[HIGHSCORE_SIZE];
    for (int i = 0; i < HIGHSCORE_SIZE; i++)
    {
      mSavedList[i] = Integer.parseInt (mStringSplit[i]);
    }

    this.mMode = getIntent ().getExtras ().getInt ("int");
    this.mcGameView = new GameView (this, this.mMode);

    getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    this.requestWindowFeature (Window.FEATURE_NO_TITLE);

    if (!this.mModeChoosen)
    {
      setContentView (mcGameView);
    }
    else
    {
      setContentView (R.layout.activity_end_game);

      this.mNewHighScore = (ImageView) findViewById
          (R.id.imageNewHighScore);

      if (this.mSavedList[0] < this.mScore)
      {
        this.mNewHighScore.setVisibility (View.VISIBLE);
      }

      this.isHighScore ();

      this.mTextView = (TextView) findViewById (R.id.editTextScore);

      this.mTextView.setText (String.valueOf (this.mScore));

      ((Button) findViewById (R.id.btnRetry))
          .setOnClickListener (new View.OnClickListener ()
          {
            @Override
            public void onClick (View v)
            {
              setContentView (mcGameView);
            }
          });

      ((Button) findViewById (R.id.btnMainMenu))
          .setOnClickListener (new View.OnClickListener ()
          {
            @Override
            public void onClick (View v)
            {
              startActivity (new Intent (v.getContext (), Game.class));
            }
          });
    }
  }

  /**
   * Commits a string with the high score
   */

  public void setPreference ()
  {
    for (int i = 0; i < mSavedList.length; i++)
    {
      mStr.append (mSavedList[i]).append (",");
    }

    mEditor.putString ("string", mStr.toString ());
    mEditor.commit ();
  }

  /**
   * Checks if the score was a top ten high score and inserts
   * it if it is
   */

  public void isHighScore ()
  {
    boolean bIsChanged = false;

    for (int i = 0; i < HIGHSCORE_SIZE; i++)
    {
      if (this.getScore (i) < this.mScore && !bIsChanged)
      {
        this.changePreferences (i);
      }
    }
  }

  /**
   * Returns the score saved in the index
   */

  public int getScore (int index)
  {
    return this.mSavedList[index];
  }

  /**
   * Changes the stored high scores
   */

  public void changePreferences (int index)
  {
    for (int i = LAST_INDEX; i >= index; i--)
    {
      this.mSavedList[i + 1] = this.mSavedList[i];
    }

    this.mSavedList[index] = this.mScore;

    this.setPreference ();
  }
}

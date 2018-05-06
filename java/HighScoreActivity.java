/***********************************************************************
 * Name:           Joseph Schell
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          HighScoreActivity
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Creates a HighScoreActivity class which displays the top ten
 * highest scores
 *
 */

public class HighScoreActivity extends AppCompatActivity implements
    View.OnClickListener
{
  private TextView mTVFirst, mTVSecond, mTVThird, mTVFourth,
      mTVFifth, mTVSixth, mTVSeventh, mTVEighth, mTVNinth, mTVTenth;
  private static final int TWO = 2;
  private static final int THREE = 3;
  private static final int FOUR = 4;
  private static final int FIVE = 5;
  private static final int SIX = 6;
  private static final int SEVEN = 7;
  private static final int EIGHT = 8;
  private static final int NINE = 9;
  private static final int TEN = 10;
  private static final int LIMIT = 11;
  private int mScore;
  private int[] mSavedList;
  private String[] mStringSplit;
  private SharedPreferences mPrefs;
  private SharedPreferences.Editor mEditor;
  private StringBuilder mStr = new StringBuilder ();
  private int mHighScore[] = new int[TEN];

  /**
   * Sets up content view
   */

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
	      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_high_score);

    this.mScore = getIntent ().getExtras ().getInt ("score");

    mPrefs = this.getSharedPreferences("myPrefsKey",
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

    String savedString = mPrefs.getString("string", "");
    mStringSplit = new String [TEN];
    mStringSplit = savedString.split (",", LIMIT);
    mSavedList = new int[TEN];
    for (int i = 0; i < TEN; i++)
    {
      mSavedList[i] = Integer.parseInt(mStringSplit[i]);
    }

    init ();
    
        ((Button) findViewById(R.id.btnMainMenu)).setOnClickListener
      (new View.OnClickListener ()
      {
        @Override
        public void onClick (View v)
        {
          startActivity (new Intent(v.getContext (),
            Game.class));
        }
      });
  }

  /**
   * Initializes the Buttons and sets it's text
   */

  public void init ()
  {
    this.mTVFirst = (TextView)findViewById(R.id.textView01);
    this.mTVSecond = (TextView)findViewById(R.id.textView02);
    this.mTVThird = (TextView)findViewById(R.id.textView03);
    this.mTVFourth = (TextView)findViewById(R.id.textView04);
    this.mTVFifth = (TextView)findViewById(R.id.textView05);
    this.mTVSixth = (TextView)findViewById(R.id.textView06);
    this.mTVSeventh = (TextView)findViewById(R.id.textView07);
    this.mTVEighth = (TextView)findViewById(R.id.textView08);
    this.mTVNinth = (TextView)findViewById(R.id.textView09);
    this.mTVTenth = (TextView)findViewById(R.id.textView10);

    this.mTVFirst.setText (String.valueOf (mSavedList[0]));
    this.mTVSecond.setText (String.valueOf (mSavedList[1]));
    this.mTVThird.setText (String.valueOf (mSavedList[TWO]));
    this.mTVFourth.setText (String.valueOf (mSavedList[THREE]));
    this.mTVFifth.setText (String.valueOf (mSavedList[FOUR]));
    this.mTVSixth.setText (String.valueOf (mSavedList[FIVE]));
    this.mTVSeventh.setText (String.valueOf (mSavedList[SIX]));
    this.mTVEighth.setText (String.valueOf (mSavedList[SEVEN]));
    this.mTVNinth.setText (String.valueOf (mSavedList[EIGHT]));
    this.mTVTenth.setText (String.valueOf (mSavedList[NINE]));
  }

  /**
   * Terminates the activity when clicked
   */

  public void onClick (View view)
  {
    this.finish ();
  }

}

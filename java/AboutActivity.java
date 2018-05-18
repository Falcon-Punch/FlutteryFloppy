/***********************************************************************
 * Name:           Joseph Schell & Sagnik Bhadra
 * Assignment:     Fluttery Floppy Fish-Bee-Alien
 * Class:          AboutActivity
 * Date:           1/23/2018
 **********************************************************************/

package edu.pacificu.cs.flappybird;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Creates an AboutActivity class which displays info about the game
 *
 */

public class AboutActivity extends Activity
{

  /**
   * Sets up content view
   */

    @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    setContentView(R.layout.activity_about);
    
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

}

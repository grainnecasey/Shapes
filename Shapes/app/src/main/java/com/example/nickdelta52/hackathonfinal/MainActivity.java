/*
Created by: Nick Basti & Gráinne Casey
11/8/15
*/
package com.example.nickdelta52.hackathonfinal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This code just makes the app fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Creates starting dialog box
        AlertDialog.Builder welcome = new AlertDialog.Builder(this);
        welcome.setMessage("Welcome to the game! Drag your square side to side to hit the squares of your color. Be careful not to hit the other squares though!").setTitle("Welcome");
        welcome.setPositiveButton("Start Game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        GameView.Continue = true;
                    }
                });
        welcome.show();

        // Manual construction of a view
        gameView = new GameView(this);
        FrameLayout fl = new FrameLayout(this);
        fl.setLayoutParams(new LayoutParams());
        fl.addView(gameView);
        setContentView(fl);
    }
}

package com.beerme.beer_me;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by kevvd on 11/5/2017.
 */

public class stat_class extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_page);

        SharedPreferences prefs = this.getSharedPreferences("highScore", Context.MODE_PRIVATE);
        int hiScore = prefs.getInt("highScore0", 0);
        String recordHolder = prefs.getString("userHigh", "");
        TextView score = (TextView) findViewById(R.id.updatedScore);
        score.setText("The high score was set by "+ recordHolder + ", with a score of: " + hiScore);
        System.out.println(hiScore);
        System.out.println(drinking_class.userName);

        if (drinking_class._drinks > hiScore) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore0", drinking_class._drinks);
            editor.putString("userHigh", drinking_class.userName);
            editor.commit();
            score.setText("Congratulations, " + drinking_class.userName + "! You have broken the record by drinking " + drinking_class._drinks + " beers!");
        }

        }





}

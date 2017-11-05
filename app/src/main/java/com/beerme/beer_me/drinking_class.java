package com.beerme.beer_me;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ryans on 11/4/2017.
 */

public class drinking_class extends AppCompatActivity {

    TextView _textView;
    int _drinks;
    TextView _timer;
    int _sec;
    double _currentBAC;
    boolean _isMale=true;
    double _weight=200;
    int _min;
    boolean _started = false;
    int _hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drinking_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         _textView = (TextView) findViewById(R.id.textView);
         _timer = (TextView) findViewById(R.id.textView2);








    }

    public void beerMe (View v){
        if(_started) {
            _drinks += 1;
            _textView.setText((_drinks) + "");
            TextView bac = (TextView) (findViewById(R.id.BAC));
            currentBAC();
            double x = Math.floor(_currentBAC*1000)/1000;
            bac.setText(x + "");
        }


    }
    public void crap(View v){
        if(_started) {
            if (_drinks > 0) {
                _drinks -= 1;
                _textView.setText((_drinks) + "");
                TextView bac = (TextView) (findViewById(R.id.BAC));
                currentBAC();
                double x = Math.floor(_currentBAC*1000)/1000;
                bac.setText(x + "");
            }
        }
    }
    public void letsStart(View v){
        _started = true;

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if(_sec == 60){
                            _sec=0;
                            _min=_min+1;

                        }
                        if (_min == 60){
                            _min=0;
                            _hours = _hours +1;

                        }

                        if (_hours > 0) {
                            if(_min<10) {
                                if (_sec < 10) {
                                    _timer.setText(String.valueOf(_hours) + ":0" + String.valueOf(_min) + ":0" + String.valueOf(_sec));
                                } else {
                                    _timer.setText(String.valueOf(_hours) + ":0" + String.valueOf(_min) + ":" + String.valueOf(_sec));
                                }
                            }
                            else{
                                    if (_sec < 10) {
                                        _timer.setText(String.valueOf(_hours) + ":" + String.valueOf(_min) + ":0" + String.valueOf(_sec));
                                    } else {
                                        _timer.setText(String.valueOf(_hours) + ":" + String.valueOf(_min) + ":" + String.valueOf(_sec));
                                    }
                                }


                            _sec += 1;
                        }
                        else{
                            if (_sec < 10) {
                                _timer.setText(String.valueOf(_min) + ":0" + String.valueOf(_sec));
                            } else {
                                _timer.setText(String.valueOf(_min) + ":" + String.valueOf(_sec));
                            }
                            _sec += 1;

                        }


                    }
                });
            }
        }, 0, 1000);
        View start = (Button)findViewById(R.id.button2);
        start.setVisibility(View.GONE);
        //  _startTime
    }

    public void currentBAC(){
        double constant;
        if (_isMale) {
            constant = 0.68;
        } else {
            constant = 0.55;
        }
        int alcInGrams = _drinks * 14;
        double bodyWeight = _weight / 0.0022046;
        double bacPercent = (alcInGrams / (bodyWeight * constant)) * 100;
        double accurateBAC = bacPercent - (hoursSinceFirstDrink()*.015);
        _currentBAC = accurateBAC;

    }



    private double hoursSinceFirstDrink(){
        double x = _min/60.0 + _hours;
        return x;
    }
}

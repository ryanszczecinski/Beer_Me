package com.beerme.beer_me;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ryans on 11/4/2017.
 */

public class drinking_class extends AppCompatActivity {

    TextView _textView;
    int _drinks;
    TextView _timer;
    double _startTime;
    double _currentBAC;
    boolean _isMale;
    double _weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drinking_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         _textView = (TextView) findViewById(R.id.textView);
         _timer = (TextView) findViewById(R.id.textView2);

    }

    public void beerMe (View v){
        _drinks +=1;
        _textView.setText((_drinks)+"");


    }
    public void crap(View v){
        if(_drinks>0) {
            _drinks -= 1;
            _textView.setText((_drinks) + "");
        }
    }
    public void letsStart(View v){
        //FIXME
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
        double accurateBAC = bacPercent - (hoursSinceFirstDrink());
        _currentBAC = accurateBAC;
    }



    private double hoursSinceFirstDrink(){
        //FIXME
        return 0;
    }
}

package com.beerme.beer_me;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ryans on 11/4/2017.
 */

public class drinking_class extends AppCompatActivity {
    int _easterEgg;
    TextView _textView;
    static int _drinks;
    TextView _timer;
    int _sec;
    double _currentBAC;
    static boolean _isMale;
    static double _weight;
    int _min;
    boolean _started = false;
    int _hours;
    Timer _t;
    Button _ls;
    NotificationManager nm;
    static final int uniqueID = 123 ;
    static String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drinking_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         _textView = (TextView) findViewById(R.id.textView);
         _timer = (TextView) findViewById(R.id.textView2);
        View b = (Button) findViewById(R.id.button7);
        b.setVisibility(View.INVISIBLE);
        ImageView iv = (ImageView) findViewById(R.id.imageView2);
        iv.setVisibility(View.INVISIBLE);
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        View beerMe = (Button)findViewById(R.id.button4);
        View crap = (Button)findViewById(R.id.crap);
        beerMe.setEnabled(false);
        crap.setEnabled(false);


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
        _easterEgg = 0;

    }
    public void crap(View v){
        _easterEgg+=1;
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
        if(_easterEgg>=2){
            ImageView iv = (ImageView) findViewById(R.id.imageView2);
            iv.setVisibility(View.VISIBLE);
        }
    }
    public void letsStart(View v){
      //


        _drinks = 0;
        _textView.setText((_drinks) + "");
        View beerMe = (Button)findViewById(R.id.button4);
        View crap = (Button)findViewById(R.id.crap);
        beerMe.setEnabled(true);
        crap.setEnabled(true);

        createNotification();
        _t = new Timer();
        View start = (Button)findViewById(R.id.button2);
        View passOut = (Button)findViewById(R.id.button7);
        _started = true;




        _t.scheduleAtFixedRate(new TimerTask() {

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

        start.setVisibility(View.GONE);
        passOut.setVisibility(View.VISIBLE);
        //  _startTime

    }
    public void end (View v) {
        _min = 0;
        _sec = 0;
        _hours = 0;
        _t.cancel();
        View start = (Button) findViewById(R.id.button2);
        View passOut = (Button) findViewById(R.id.button7);
        start.setVisibility(View.VISIBLE);
        passOut.setVisibility(View.GONE);
        View beerMe = (Button) findViewById(R.id.button4);
        View crap = (Button) findViewById(R.id.crap);
        beerMe.setEnabled(false);
        crap.setEnabled(false);



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

    public void createNotification(){
        Intent intent = new Intent(this, drinking_class.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(drinking_class.this,0,intent,0);
        Notification.Builder nB = new Notification.Builder(drinking_class.this);
        nB.setVisibility(Notification.VISIBILITY_PUBLIC);
        RemoteViews remoteView = new RemoteViews(getPackageName(),R.layout.notification);
        remoteView.setOnClickPendingIntent(R.id.button10, pendingIntent);
        remoteView.setTextViewText(R.id.textView4,_drinks+"");
        remoteView.setTextViewText(R.id.textView5,_currentBAC+"");
        nB.setCustomContentView(remoteView);

        nB.setSmallIcon(R.drawable.transparent_beer);
        Notification n1 = nB.build();

        nm.notify(uniqueID,n1);
    }
    public void update(){

    }
}


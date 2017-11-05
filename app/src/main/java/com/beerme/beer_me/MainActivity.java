package com.beerme.beer_me;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    Button b;
    EditText name;
    EditText weight;
    String nameString = "";
    String weightString;
    CheckBox checkBoxMale;
    CheckBox checkBoxFemale;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        b = (Button) findViewById(R.id.enterButton);
        name = (EditText) findViewById(R.id.editName);
        weight = (EditText) findViewById(R.id.editWeight);

        checkBoxMale = ((CheckBox) findViewById(R.id.checkBoxMale));
        checkBoxFemale = ((CheckBox) findViewById(R.id.checkBoxFemale));



        checkBoxMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxFemale.setChecked(false);
            }
        });

        checkBoxFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxMale.setChecked(false);
            }
        });



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int weightInteger = 0;
                if(name.getText().toString().equals("")){
                    nameString = "";
                }
                else {
                    nameString = name.getText().toString();
                }
                if(weight.getText().toString().equals("")){
                    weightString = "0";
                }
                else {
                        weightString = weight.getText().toString();
                }

                weightInteger = Integer.parseInt(weightString);
                drinking_class._weight = weightInteger;
                boolean maleChecked = checkBoxMale.isChecked();
                drinking_class._isMale = maleChecked;
            }
        });


        mTextMessage = (TextView) findViewById(R.id.message);


    }
    public void buttonOnClick(View v){

        Button button= (Button) v;
        Intent i = new Intent(getApplicationContext(), drinking_class.class);
        startActivity(i);

    }
}

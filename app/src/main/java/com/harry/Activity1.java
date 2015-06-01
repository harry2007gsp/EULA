package com.harry;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.techblogon.loginexample.R;


public class Activity1 extends Activity {
    CheckBox checkBox1;
    CheckBox checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
    }

    public void next(View view) {
        if(checkBox1.isChecked()&&checkBox2.isChecked()){
            Intent intentSignup = new Intent(this,SignUPActivity.class);
            startActivity(intentSignup);
        }
        else{
            Toast.makeText(this,"First select both checkboxes as YES", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View view) {
        Intent intentBack = new Intent(this, HomeActivity.class);
        startActivity(intentBack);
    }
}

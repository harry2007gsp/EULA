package com.harry;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techblogon.loginexample.R;

public class HomeActivity extends Activity {
    Button btnSignIn, btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

    }
    // Methos to handleClick Event of Sign Out Button
    public void signUp(View v) {
        /// Create Intent for SignUpActivity  abd Start The Activity
        Intent intentEula = new Intent(getApplicationContext(), Activity1.class);
        startActivity(intentEula);
    }

    // Methos to handleClick Event of Sign In Button
    public void signIn(View V) {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");

        // get the Refferences of views
        final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if (password.equals(storedPassword)) {
                    Toast.makeText(HomeActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    Intent welcomeActivity = new Intent(HomeActivity.this, Welcome.class);
                    startActivity(welcomeActivity);
                } else {
                    Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}
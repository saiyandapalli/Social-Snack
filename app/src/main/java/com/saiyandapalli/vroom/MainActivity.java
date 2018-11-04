package com.saiyandapalli.vroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saiyandapalli.vroom.R;

import static com.saiyandapalli.vroom.Utils.attemptLogin;
import static com.saiyandapalli.vroom.Utils.attemptSignup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("You have signed in!", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("You have signed out.", "onAuthStateChanged:signed_out");
                }
            }
        };


        ((Button) findViewById(R.id.loginButton)).setOnClickListener(this);


        ((Button) findViewById(R.id.signupButton)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.loginButton || id == R.id.signupButton) {
            String email = ((EditText) findViewById(R.id.emailloginView)).getText().toString();
            String password = ((EditText) findViewById(R.id.passwordView)).getText().toString();
            if (id == R.id.loginButton) {
                attemptLogin(MainActivity.this, email, password);
            } else {
                attemptSignup(MainActivity.this, email, password);
            }
        }
    }
}
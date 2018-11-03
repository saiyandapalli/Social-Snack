package com.saiyandapalli.vroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class Utils{
    public static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public static void attemptLogin(final Context context, String email, String password) {
        if (!email.equals("") && !password.equals("")) {
            MainActivity.mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Log in", "signInWithEmail:onComplete:" + task.isSuccessful());

                            if (!task.isSuccessful()) {
                                Log.w("Aww", "signInWithEmail:failed", task.getException());
                                Toast.makeText(context, "Sign in failed!", Toast.LENGTH_SHORT).show();
                            } else {
                                context.startActivity(new Intent(context, ListActivity.class));
                            }
                        }
                    });
        }
    }

    public static void attemptSignup(final Context context, String email, String password) {

        if (!email.equals("") && !password.equals("")) {
            MainActivity.mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Yay", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            if (!task.isSuccessful()) {
                                Log.d("Yay", task.getException().getMessage());
                                Toast.makeText(context, "Failed Signup", Toast.LENGTH_SHORT).show();
                            } else {
                                context.startActivity(new Intent(context, ListActivity.class));
                            }
                        }
                    });
        }
    }
}

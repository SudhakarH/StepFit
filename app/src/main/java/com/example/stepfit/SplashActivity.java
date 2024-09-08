package com.example.stepfit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 1000; // Duration in milliseconds (3 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay the transition to MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the delay, start the main activity
                Intent intent = new Intent(SplashActivity.this, SignupActivity.class);
                startActivity(intent);
                finish(); // Close the SplashActivity so it's not in the back stack
            }
        }, SPLASH_DURATION); // Delay duration
    }
}

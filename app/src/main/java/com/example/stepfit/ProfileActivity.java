package com.example.stepfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileEmail, profileUsername, profilePassword,profilestep,profilebmi,profilecalorie;
    TextView titleName, titleUsername;
    Button logoutProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profileUsername);
        profilePassword = findViewById(R.id.profilePassword);
        titleName = findViewById(R.id.titleName);
        titleUsername = findViewById(R.id.titleUsername);
        profilestep = findViewById(R.id.profilestep);
        profilebmi = findViewById(R.id.profilebmi);
        profilecalorie = findViewById(R.id.profilecalorie);
        logoutProfile = findViewById(R.id.logoutButton);

        SharedPreferences prefs = getSharedPreferences("PedometerPrefs", MODE_PRIVATE);
        int stepCount = prefs.getInt("stepCount", 0);
        double calorie = stepCount * 0.05;
        int mycal = (int) calorie;

        profilestep.setText(""+ stepCount +"");

        SharedPreferences prefs1 = getSharedPreferences("BMIPrefs", MODE_PRIVATE);
        String bmiResult = prefs1.getString("bmiResult", "0");

        profilebmi.setText(""+ bmiResult +"");
        showAllUserData();

        profilecalorie.setText(""+mycal+"");
    }
    public void showAllUserData(){
        Intent intent = getIntent();
        String nameUsers = intent.getStringExtra("names");
        String emailUsers = intent.getStringExtra("emails");
        String usernameUsers = intent.getStringExtra("usernames");
        String passwordUsers = intent.getStringExtra("passwords");
        System.out.print("printing values");
        System.out.print(nameUsers);
        System.out.print(emailUsers);
        System.out.print(usernameUsers);
        System.out.print(passwordUsers);
        titleName.setText(nameUsers);
        titleUsername.setText(usernameUsers);
        profileName.setText(nameUsers);
        profileEmail.setText(emailUsers);
        profileUsername.setText(usernameUsers);
        profilePassword.setText(passwordUsers);
    }

    public void logout(View view) {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
        startActivity(intent);
        finish();  // Close the current activity
    }
}
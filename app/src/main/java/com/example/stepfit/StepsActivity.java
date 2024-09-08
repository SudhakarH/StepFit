package com.example.stepfit;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StepsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor linearAccelerationSensor;
    private TextView stepCountView;
    private ProgressBar circularProgressBar;
    private int stepCount = 0;

    private static final float STEP_THRESHOLD = 10.0f; // Adjust threshold for step detection
    private static final int STEP_DELAY_NS = 250000000; // Minimum time between steps in nanoseconds

    private long lastStepTimeNs = 0;
    private static final int STEP_GOAL = 10000; // Step goal for the progress bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        stepCountView = findViewById(R.id.stepCountView);
        circularProgressBar = findViewById(R.id.circularProgressBar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        linearAccelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        SharedPreferences prefs = getSharedPreferences("PedometerPrefs", MODE_PRIVATE);
        stepCount = prefs.getInt("stepCount", 0);

        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, linearAccelerationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Calculate the magnitude of the acceleration vector
            float magnitude = (float) Math.sqrt(x * x + y * y + z * z);

            long currentTimeNs = event.timestamp;

            // Detect step based on magnitude and delay
            if (magnitude > STEP_THRESHOLD) {
                if (currentTimeNs - lastStepTimeNs > STEP_DELAY_NS) {
                    stepCount++;
                    updateUI();
                    lastStepTimeNs = currentTimeNs;
                }
            }
        }
    }

    private void updateUI() {
        // Update the text with the current step count
        stepCountView.setText(String.valueOf(stepCount));

        // Update the circular progress bar
        circularProgressBar.setProgress(stepCount);

        // If step count exceeds the goal, set the progress bar to the max value
        if (stepCount >= STEP_GOAL) {
            circularProgressBar.setProgress(STEP_GOAL);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle changes in sensor accuracy if necessary
    }

    public void resetStepsCount(View view) {
        stepCount = 0;
        updateUI();
        Toast.makeText(StepsActivity.this, "Resetting Step Count is Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = getSharedPreferences("PedometerPrefs", MODE_PRIVATE).edit();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }
}

# StepFit Application

## Overview of StepFit Application

The **StepFit App** is a comprehensive Android fitness tracking application that provides users with various functionalities such as step counting, stability checking, BMI calculation, and user profile management. The app is built using Java and Firebase Realtime Database for user authentication and data storage. It integrates sensor technology, using both the **Accelerometer** and **Gyroscope** sensors. The app comprises several key activities, each designed for specific functionality.

## Key Activities and Features

### 1. SplashActivity:
- This is the initial screen that displays the app logo while loading the necessary resources. It acts as a loading screen before the user is directed to the **LoginActivity**.

### 2. LoginActivity:
- This activity provides the interface for users to log into the app. It verifies the user credentials stored in **Firebase Realtime Database**. Once successfully authenticated, the user is directed to the **MainActivity**.

### 3. SignupActivity:
- This activity allows new users to sign up by entering their **name, email, username, and password**. The data is stored in **Firebase Realtime Database**, ensuring secure user registration. Upon successful signup, the user is redirected to the **LoginActivity**.

### 4. MainActivity:
- This is the central activity of the app, containing four main buttons that direct users to different functionalities of the app:

#### a) StepsActivity:
- This activity counts the user’s steps using the **Accelerometer sensor** and displays the current step count. It features a **circular progress bar** to visually represent the step count and provides real-time updates.

#### b) GyroscopeActivity:
- This activity tracks and displays the **rotation values** (X, Y, Z axes) using the **Gyroscope sensor**. It monitors the user's stability and displays rotational data. If no movement is detected for 5 seconds, a message will inform the user about inactivity.

#### c) BMICalculatorActivity:
- In this activity, users can input their **weight** and **height** to calculate their **Body Mass Index (BMI)**. The BMI result is displayed with two decimal precision, and the calculated value is stored locally using **SharedPreferences**.

#### d) ProfileActivity:
- This activity provides an overview of the user's profile, displaying key statistics such as the **step count, BMI**, and **calories burned** (calculated using the step count). The activity also includes a **Logout button** that allows the user to sign out and return to the **LoginActivity**.

## 5. Firebase Integration:
- **Firebase Realtime Database** is used for storing user credentials and data, ensuring secure and real-time synchronization between the app and the cloud.

## 6. Sensor Utilization:
- **Accelerometer Sensor (StepsActivity)**: Used to detect and count steps taken by the user.
- **Gyroscope Sensor (GyroscopeActivity)**: Used to detect rotational motion and check user stability.

## Conclusion
The StepFit app is designed to help users manage their fitness by offering a variety of features such as **step counting, stability checking**, and **BMI calculation**. The app uses sensor technology and **Firebase** for real-time data tracking and user management, with a user-friendly interface across different activities like **StepsActivity, GyroscopeActivity, BMICalculatorActivity**, and **ProfileActivity**.


## Special Notes
- Users will be asked to provide permission to use sensors manually if it is not auto-granted.
- The app uses a basic formula to calculate magnitude from the accelerometer to calculate steps, with `STEP_THRESHOLD = 10.0f` and `STEP_DELAY_NS = 250000000` (in nanoseconds), and sets a goal of **10,000 steps**.
- **Calories Burned** are calculated using only the step count -> `(step count * 0.05)`.

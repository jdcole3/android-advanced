/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.tiltspot;

import android.content.Context;
//import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.view.Display;
//import android.view.Surface;
//import android.view.WindowManager;
//import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    // System sensor manager instance.
    private SensorManager mSensorManager;

    // Accelerometer and magnetometer sensors, as retrieved from the
    // sensor manager.
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;
    private Sensor mSensorPressure;
    private Sensor mSensorGyroscope;
    private Sensor mSensorLight;

    // Current data from accelerometer & magnetometer.  The arrays hold values
    // for X, Y, and Z.


    private TextView mTextSensorAccelX;
    private TextView mTextSensorAccelY;
    private TextView mTextSensorAccelZ;
    private TextView mTextSensorMagX;
    private TextView mTextSensorMagY;
    private TextView mTextSensorMagZ;
    private TextView mTextSensorPressure;
    private TextView mTextSensorGyroX;
    private TextView mTextSensorGyroY;
    private TextView mTextSensorGyroZ;
    private TextView mTextSensorLight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSensorAccelX = (TextView) findViewById(R.id.label_Ax);
        mTextSensorAccelY = (TextView) findViewById(R.id.label_Ay);
        mTextSensorAccelZ = (TextView) findViewById(R.id.label_Az);
        mTextSensorMagX = (TextView) findViewById(R.id.label_Mx);
        mTextSensorMagY = (TextView) findViewById(R.id.label_My);
        mTextSensorMagZ = (TextView) findViewById(R.id.label_Mz);
        mTextSensorPressure = (TextView) findViewById(R.id.label_P);
        mTextSensorGyroX = (TextView) findViewById(R.id.label_Gx);
        mTextSensorGyroY = (TextView) findViewById(R.id.label_Gy);
        mTextSensorGyroZ = (TextView) findViewById(R.id.label_Gz);
        mTextSensorLight = (TextView) findViewById(R.id.label_L);

        // Get accelerometer and magnetometer sensors from the sensor manager.
        // The getDefaultSensor() method returns null if the sensor
        // is not available on the device.
        mSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(
                Sensor.TYPE_MAGNETIC_FIELD);
        mSensorPressure = mSensorManager.getDefaultSensor(
                Sensor.TYPE_PRESSURE);
        mSensorGyroscope = mSensorManager.getDefaultSensor(
                Sensor.TYPE_GYROSCOPE);
        mSensorLight = mSensorManager.getDefaultSensor(
                Sensor.TYPE_LIGHT);


    }

    /**
     * Listeners for the sensors are registered in this callback so that
     * they can be unregistered in onStop().
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Listeners for the sensors are registered in this callback and
        // can be unregistered in onStop().
        //
        // Check to ensure sensors are available before registering listeners.
        // Both listeners are registered with a "normal" amount of delay
        // (SENSOR_DELAY_NORMAL).
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorMagnetometer != null) {
            mSensorManager.registerListener(this, mSensorMagnetometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorPressure != null) {
            mSensorManager.registerListener(this, mSensorPressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorGyroscope != null) {
            mSensorManager.registerListener(this, mSensorGyroscope,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unregister all sensor listeners in this callback so they don't
        // continue to use resources when the app is stopped.
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // The sensor type (as defined in the Sensor class).
        int sensorType = sensorEvent.sensor.getType();

        float currentValue1 = sensorEvent.values[0];
        float currentValue2 = sensorEvent.values[1];
        float currentValue3 = sensorEvent.values[2];

        // The sensorEvent object is reused across calls to onSensorChanged().
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mTextSensorAccelX.setText(getResources().getString(R.string.label_Ax, currentValue1));
                mTextSensorAccelY.setText(getResources().getString(R.string.label_Ay, currentValue2));
                mTextSensorAccelZ.setText(getResources().getString(R.string.label_Az, currentValue3));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mTextSensorMagX.setText(getResources().getString(R.string.label_Mx, currentValue1));
                mTextSensorMagY.setText(getResources().getString(R.string.label_My, currentValue2));
                mTextSensorMagZ.setText(getResources().getString(R.string.label_Mz, currentValue3));
                break;
            case Sensor.TYPE_PRESSURE:
                mTextSensorPressure.setText(getResources().getString(R.string.label_P, currentValue1));
                break;
            case Sensor.TYPE_GYROSCOPE:
                mTextSensorGyroX.setText(getResources().getString(R.string.label_Gx, currentValue1));
                mTextSensorGyroY.setText(getResources().getString(R.string.label_Gy, currentValue2));
                mTextSensorGyroZ.setText(getResources().getString(R.string.label_Gz, currentValue3));
                break;
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(getResources().getString(R.string.label_L, currentValue1));
                break;
            default:
                break;
        }


    }

    /**
     * Must be implemented to satisfy the SensorEventListener interface;
     * unused in this app.
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
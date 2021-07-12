package com.example.wearable;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

    private TextView xAvalue, yAvalue, zAvalue, xGvalue, yGvalue, zGvalue, xMvalue,
            yMvalue,zMvalue, light, pressure,temp, humidity, hearth_rate;
    private SensorManager sensorManager;
    private Sensor mHearth_rate, accelerometer, mGyroscope, mMagnetometer, mLight,
            mPressure, mHumidity, mTemperature, mHearth_beat;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xAvalue = (TextView)findViewById(R.id.xAvalue);
        yAvalue = (TextView)findViewById(R.id.yAvalue);
        zAvalue = (TextView)findViewById(R.id.zAvalue);

        xGvalue = (TextView)findViewById(R.id.xGvalue);
        yGvalue = (TextView)findViewById(R.id.yGvalue);
        zGvalue = (TextView)findViewById(R.id.zGvalue);

        xMvalue =(TextView)findViewById(R.id.xMvalue);
        yMvalue =(TextView)findViewById(R.id.yMvalue);
        zMvalue =(TextView)findViewById(R.id.zMvalue);

        light = (TextView)findViewById(R.id.light);
        pressure = (TextView)findViewById(R.id.pressure);
        temp = (TextView)findViewById(R.id.temp);
        humidity = (TextView)findViewById(R.id.humidity);

        hearth_rate=(TextView)findViewById(R.id.hearth_rate);

        //Iniciaizar sensores
        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null)
        {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");
        }else{
            xAvalue.setText("Accelerometer Not Supported");
        }

        mHearth_rate = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if (mHearth_rate != null)
        {
            sensorManager.registerListener(MainActivity.this, mHearth_rate, SensorManager.SENSOR_DELAY_FASTEST);
            Log.d(TAG, "onCreate: Registered Hearth Rate listener");
        }else{
            hearth_rate.setText("HR not support");
        }

        mHearth_beat = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT);
        if (mHearth_beat != null)
        {
            sensorManager.registerListener(MainActivity.this, mHearth_beat, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Hearth Beat listener");
        }else{
            //hearth_beat.setText("HB not support");
        }

        mGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyroscope != null)
        {
            sensorManager.registerListener(MainActivity.this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope listener");
        }else{
            xGvalue.setText("Gyro not supported");
        }

        mMagnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagnetometer != null)
        {
            sensorManager.registerListener(MainActivity.this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Magnometer listener");
        }else{
            xMvalue.setText("Magnometer Not Supported");

        }

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mLight != null)
        {
            sensorManager.registerListener(MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Light listener");
        }else{
            light.setText("Light Not Supported");
        }

        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (mPressure != null)
        {
            sensorManager.registerListener(MainActivity.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Pressure listener");
        }else{
            light.setText("Pressure Not Supported");
        }

        mHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (mHumidity != null)
        {
            sensorManager.registerListener(MainActivity.this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Humidity listener");
        }else{
            light.setText("Humidity Not Supported");
        }

        mTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (mTemperature != null)
        {
            sensorManager.registerListener(MainActivity.this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Temperature listener");
        }else{
            light.setText("Temp not supported");
        }
        //Iniciaizar sensores
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        //Obtener valores de los sensores
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            xAvalue.setText("X: " + event.values[0]);
            yAvalue.setText("Y: " + event.values[1]);
            zAvalue.setText("Z: " + event.values[2]);
        }else if (sensor.getType() == Sensor.TYPE_HEART_RATE){
            hearth_rate.setText("HR: " + event.values[0]);
        }else if (sensor.getType() == Sensor.TYPE_GYROSCOPE){
            xGvalue.setText("X: " + event.values[0]);
            yGvalue.setText("Y: " + event.values[1]);
            zGvalue.setText("Z: " + event.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            xMvalue.setText("X: " + event.values[0]);
            yMvalue.setText("Y: " + event.values[1]);
            zMvalue.setText("Z: " + event.values[2]);
        }else if (sensor.getType() == Sensor.TYPE_LIGHT){
            light.setText("Light" + event.values[0]);
        }else if (sensor.getType() == Sensor.TYPE_PRESSURE){
            pressure.setText("Pressure: " + event.values[0]);
        }else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            humidity.setText("Humidity: " + event.values[0]);
        }else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            temp.setText("Temp: " + event.values[0]);
        }
        //Obtener valores de los sensores
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        
    }
}
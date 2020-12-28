package com.gavankennedy.miniprojecttimerstepcounterthingy;

import androidx.appcompat.app.AppCompatActivity;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public Button frontButton;
    TextView display,x,y,z,steps;
    CountUpTimer timer;
    //int timeResults=Integer.parseInt(timer.getClass().toString());
    public int TimerResult;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    int stepCount=0;
    //int ste_pmul=stepCount;
    private final int High=13;
    private final int Low=9;
    boolean AtHeight=false;
    Double C_count=0.04;
    //int i=Integer.valueOf(C_count.intValue());
    //int C=ste_pmul*i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new CountUpTimer(86400000) {
            public void onTick(int second) {
                display.setText(String.valueOf(second));
            }
        };


        display = findViewById(R.id.Timer);
        //calories=findViewById(R.id.Calories);
        //calories.setText(String.valueOf(C));
        frontButton = findViewById(R.id.ResultsButton);
        x=findViewById((R.id.XAxis));
        y=findViewById(R.id.YAxis);
        z=findViewById(R.id.ZAxis);
        steps=findViewById(R.id.Steps);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        frontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String StepRESULT=steps.getText().toString();
                String TimeResult=display.getText().toString();
                //String CalorieResult=calories.getText().toString();
                Intent intent = new Intent(MainActivity.this, ResultsPage.class);
                intent.putExtra("Step",StepRESULT);
                intent.putExtra("Time",TimeResult);
                //intent.putExtra("Calorie",CalorieResult);
                startActivity(intent);
            }
        });

    }
    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        AtHeight=false;
        mSensorManager.unregisterListener(this);    // turn off listener to save power
    }

    public void Start(View view) { timer.start(); AtHeight=true;}

    public void Stop(View view) {
        timer.cancel();
        AtHeight=false;
        //TimerResult=timer;
    }

    public void Restart(View view) {
        display.setText("0");
        stepCount=0;
        steps.setText("0");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float X=event.values[0];
        float Y=event.values[1];
        float Z=event.values[2];
        x.setText(String.valueOf(X));
        y.setText(String.valueOf(Y));
        z.setText(String.valueOf(Z));
        double mg=Math.round((X*X)+(Y*Y)+(Z+Z));
        if((mg>High)&&(AtHeight==false)){
            AtHeight=true;
        }
        if((mg<Low) && (AtHeight==true)){
            stepCount++;
            steps.setText(String.valueOf(stepCount));
            AtHeight=false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}



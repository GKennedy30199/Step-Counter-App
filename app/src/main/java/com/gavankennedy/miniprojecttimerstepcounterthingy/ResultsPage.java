package com.gavankennedy.miniprojecttimerstepcounterthingy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class ResultsPage extends AppCompatActivity {

    TextView metre,seconds,calories;
    String Sec,J;
//    String M= getIntent().getStringExtra("Step");
    //Double Mnum=Double.parseDouble(M);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        Calendar date=Calendar.getInstance();
        String current= DateFormat.getDateInstance(DateFormat.SHORT).format(date.getTime());
        TextView Date=findViewById(R.id.TVDate);
        Date.setText(current);
        Button button=findViewById(R.id.BackButton);


        metre=findViewById(R.id.StepsResults);
        seconds=findViewById(R.id.SecondResults);
        calories=findViewById(R.id.CalorieResult);

        metre.setText(getIntent().getStringExtra("Step")+"M");
        seconds.setText(getIntent().getStringExtra("Time")+"Seconds");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back_toMain();
            }
        });
    }
    public void Back_toMain(){
        Intent intent=new Intent(ResultsPage.this,MainActivity.class);
        startActivity(intent);
    }
}
package com.example.catch_emoji;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private TextView point;
    private float x,y;
    private int p,speed;
    private boolean s;
    int levelSleep;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.emoji);
        s=true;
        p = 0;
        speed=1;
        levelSleep=2000;
        point = findViewById(R.id.points);

    }

    public void start(View view) {
        s=true;
        thread = new Thread(() -> {
            try {
                while (true) {
                    startMoveImage();
                    Thread.sleep(levelSleep);

                }

            } catch (Exception e) {

            }
        });
        thread.start();
    }

    public void point(View view){
        if(s)
        {p+=speed;
            point.setText(p+"");}
    }

    public void speed1(View view){
        levelSleep=2000;
        speed=1;
    }
    public void speed2(View view){
        levelSleep=1500;
        speed=2;
    }
    public void speed3(View view){
        levelSleep=1000;
        speed=3;
    }

    private void startMoveImage(){
        x=(float) Math.random()*800;
        y=(float) Math.random()*1600;
        image.setX(x);
        image.setY(y);
    }

    public void stop(View view){
        s=false;
        thread.interrupt();
        showPoint();
    }

    public void showPoint(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("points");
        builder.setMessage("your points:"+p);
        builder.setPositiveButton("ok",null);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


}
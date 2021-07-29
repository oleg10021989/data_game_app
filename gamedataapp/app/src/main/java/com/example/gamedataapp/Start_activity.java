package com.example.gamedataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

//import com.example.gamedataapp.databinding.ActivityMainBinding;

import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class Start_activity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        LinearLayout linearLayout =findViewById(R.id.layout_start);






        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                openActivity();
            }
        },6000 );




//            @NonNull ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
//
//
//            setContentView(binding.getRoot());
//            binding.button2.getAccessibilityClassName();

    }
    public void openActivity() {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
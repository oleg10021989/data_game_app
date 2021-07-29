package com.example.gamedataapp;


import android.content.Intent;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.squareup.picasso.Picasso;
//import static com.example.gamedataapp.MainActivity.EXTRA_NAME;
import static com.example.gamedataapp.MainActivity.EXTRA_ID;
import static com.example.gamedataapp.MainActivity.EXTRA_IMG_URL;
import static com.example.gamedataapp.MainActivity.EXTRA_URL;
import static com.example.gamedataapp.MainActivity.EXTRA_TYPES;
import static com.example.gamedataapp.MainActivity.EXTRA_NAME;
import static com.example.gamedataapp.MainActivity.EXTRA_GAME_DESCRIPTION;
import static com.example.gamedataapp.MainActivity.EXTRA_RELEASE_DATA;
import static com.example.gamedataapp.MainActivity.EXTRA_DEVELOPER;
import static com.example.gamedataapp.MainActivity.EXTRA_PUBLISHER;
import static com.example.gamedataapp.MainActivity.EXTRA_POPULAR_TAGS;
import static com.example.gamedataapp.MainActivity.EXTRA_LANGUAGES;
import static com.example.gamedataapp.MainActivity.EXTRA_GENRE;
import static com.example.gamedataapp.MainActivity.EXTRA_MINIMUM_REQUIREMENTS;
import static com.example.gamedataapp.MainActivity.EXTRA_RECOMMENDED_REQUIREMENTS;
import static com.example.gamedataapp.MainActivity.EXTRA_ORIGINAL_PRICE;


public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_URL_STEAM = "url_steam";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        LinearLayout linearLayout = findViewById(R.id.layout_detail);

        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();


        Intent intent = getIntent();

        String steam_url = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_NAME);
        String developer = intent.getStringExtra(EXTRA_DEVELOPER);
        String game_description = intent.getStringExtra(EXTRA_GAME_DESCRIPTION);
        String game_img_url = intent.getStringExtra(EXTRA_IMG_URL);
        String publisher = intent.getStringExtra(EXTRA_PUBLISHER);
        String genre = intent.getStringExtra(EXTRA_GENRE);
        String languages = intent.getStringExtra(EXTRA_LANGUAGES);
        String release_date = intent.getStringExtra(EXTRA_RELEASE_DATA);
        String original_price = intent.getStringExtra(EXTRA_ORIGINAL_PRICE);
        String popular_tags = intent.getStringExtra(EXTRA_POPULAR_TAGS);


        TextView textViewurl_steam = findViewById(R.id.textView_steam_url_detail);
        TextView textViewCreator = findViewById(R.id.textView_name_detail);
        TextView textViewDeveloper = findViewById(R.id.textView_developer_detail);
        TextView textViewGameDescript = findViewById(R.id.game_description_detail);
        ImageView imageView_img_url = findViewById(R.id.imageView_detail);
        TextView textViewpublisher = findViewById(R.id.textView_publisher_detail);
        TextView textViewpubgenre = findViewById(R.id.textView_genre_detail);
        TextView textViewlanguages = findViewById(R.id.textView_languages_detail);
        TextView textViewrelease_date = findViewById(R.id.textView_release_date_detail);
        TextView textVieworiginal_price = findViewById(R.id.textView_original_price_detail);
        TextView textViewpopular_tags = findViewById(R.id.textView_popular_tags_detail);


        Picasso.with(imageView_img_url.getContext()).load(game_img_url).into(imageView_img_url);


        textViewCreator.setText(creatorName);
        textViewDeveloper.setText(developer);
        textViewGameDescript.setText(game_description);
        textViewpublisher.setText(publisher);
        textViewpubgenre.setText(genre);
        textViewlanguages.setText(languages);
        textViewrelease_date.setText(release_date);
        textVieworiginal_price.setText(original_price);
        textViewpopular_tags.setText(popular_tags);




        SpannableString steam_url_click = new SpannableString(steam_url);
        ClickableSpan clickableSpan_steam = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {


                opensteamActivity(steam_url);

            }
        };
        steam_url_click.setSpan(clickableSpan_steam,0,steam_url.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewurl_steam.setText(steam_url_click);
        textViewurl_steam.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void opensteamActivity(String steam_url) {

        Intent intent;
        intent = new Intent(this, Steam_Activity.class);
        intent.putExtra(EXTRA_URL_STEAM, steam_url);
        startActivity(intent);


    }
}

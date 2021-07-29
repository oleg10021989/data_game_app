package com.example.gamedataapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


/*
Hence a RecyclerView is more customisable when compared to ListView and gives greater control to the users.
 */

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener, DialogGameName.DialogListener ,DialogDeveloperName.DialogListener,DialogPublisherName.DialogListener{
    //ID , url , types , name , game_description , release_date , developer , publisher , popular_tags , languages , genre , minimum_requirements , recommended_requirements , original_price
//
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TYPES = "types";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_GAME_DESCRIPTION = "game_description";
    public static final String EXTRA_RELEASE_DATA = "release_date";
    public static final String EXTRA_DEVELOPER = "developer";
    public static final String EXTRA_PUBLISHER = "publisher";
    public static final String EXTRA_POPULAR_TAGS = "popular_tags";
    public static final String EXTRA_LANGUAGES = "languages";
    public static final String EXTRA_GENRE = "genre";
    public static final String EXTRA_MINIMUM_REQUIREMENTS = "minimum_requirements";
    public static final String EXTRA_RECOMMENDED_REQUIREMENTS = "recommended_requirements";
    public static final String EXTRA_ORIGINAL_PRICE = "original_price";
    public static final String EXTRA_IMG_URL = "img_url";



    private static CustomAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel2> data;
    static View.OnTouchListener myOnClickListener;

    private ProgressBar progressBar;


    private Button btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // myAwesomeTextView = (TextView) findViewById();
        //textView.findViewById(R.id.textView);
        progressBar = findViewById(R.id.progress_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RelativeLayout relativeLayout = findViewById(R.id.background_activity_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
//        btnFilter = (Button) findViewById(R.id.);
//        btnFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showFilterPopup(v);
//            }
//        });

//        new BackgraungTask(this).execute("name Phoenix Wright: Ace Attorney Trilogy");
        new BackgraungTask(this).execute("release_date 2019");
        //String new_str = BackgraungTask().doInBackground();


        //Toast.makeText( getBaseContext(),,Toast.LENGTH_LONG).show();


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
/*


    LinearLayoutManager : it supports both vertical and horizontal lists
    StaggeredLayoutManager : it supports staggered lists
    GridLayoutManager : it supports displaying grids as seen in GalleryView


 */
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*

    The RecyclerView.ItemAnimator class provides better support to animating the views unlike the ListViews
    The RecyclerView.ItemDecorator class provides better support when it comes to adding borders and dividers thereby giving huge control to us

         */

//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        data = new ArrayList<DataModel>();
//            for (int i = 0; i < MyData.nameArray.length; i++) {
//                data.add(new DataModel(
//                        MyData.nameArray[i],
//                        MyData.versionArray[i],
//                        MyData.id_[i],
//                        MyData.drawableArray[i]
//                ));
//        }
//
//        removedItems = new ArrayList<Integer>();
//
//        adapter = new CustomAdapter(data);
//        recyclerView.setAdapter(adapter);

    }

    @Override
    public void applyTextsGameName(String gamename) {
        new BackgraungTask(this).execute("name "+gamename);

    }

    @Override
    public void applyTextsPublisherName(String publishername) {
        new BackgraungTask(this).execute("publisher "+publishername);
    }

    @Override
    public void applyTextsDeveloperName(String developername) {
        new BackgraungTask(this).execute("developer "+developername);
    }

    private class BackgraungTask extends AsyncTask<String, Integer, String> {

        private WeakReference<MainActivity> activityWeakReference;

        BackgraungTask(MainActivity activity) {
            activityWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String sentence;
            String modifiedSentence = null;

            //Context context = getApplicationContext();
            //Toast.makeText( getBaseContext(),"oleg",Toast.LENGTH_LONG).show();
            //Toast.makeText(MainActivity.this,strings[0],Toast.LENGTH_LONG).show();

            try {


                Socket clientSocket = new Socket("172.18.50.16", 10000);
                //192.168.164.190,"172.18.51.250","172.18.51.135",192.168.143.190,,"192.168.198.190"
                //Toast.makeText(MainActivity.this,clientSocket.toString(),Toast.LENGTH_LONG).show();


                DataOutputStream outToServer =
                        new DataOutputStream(clientSocket.getOutputStream());

                BufferedReader inFromServer =
                        new BufferedReader(new
                                InputStreamReader(clientSocket.getInputStream()));


                for (int i = 0, stringsLength = strings.length; i < stringsLength; i++) {
                    String string = strings[i];
                    outToServer.writeBytes(string + '\n');
                    //Toast.makeText(MainActivity.this,strings[0],Toast.LENGTH_LONG).show();
//                    int j=0;
//                    while (j<1000001){
//
//                        publishProgress((j++ /10000) );
//
//
//                    }


                    modifiedSentence = inFromServer.readLine();
//                    modifiedSentence = inFromServer.readUTF();


                    //Toast.makeText(MainActivity.this,strings[0]+"!!",Toast.LENGTH_LONG).show();
                }

                Log.d("FROM SERVER: ", modifiedSentence);


            } catch (ConnectException e) {
                //System.out.println( " 404 C'ant connect to the Server");
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return modifiedSentence;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
//            Log.d("FROM SERVER2: ", s);

            super.onPostExecute(s);
            if (s != null) {
                String[] tokens = s.split("~`");
//                Log.d("FROM SERVER1: ", tokens[0]);
//                Log.d("FROM SERVER2: ", tokens[1]);
//                Log.d("FROM SERVER3: ", tokens[2]);

                recyclerView.setItemAnimator(new DefaultItemAnimator());


                data = new ArrayList<DataModel2>();
                Log.d("FROM SERVER11: ", String.valueOf(tokens.length));

                for (int i = 0; i < tokens.length; i++) {
                    String[] tokens2 = tokens[i].split("`~");
//                    tokens2[3]=StringFormatter.convertUTF8ToString(tokens2[3]);
                    Log.d("FROM SERVER11: ", tokens2[3]);

                    data.add(new DataModel2(
                            tokens2[0], tokens2[1], tokens2[2], tokens2[3], tokens2[4], tokens2[5], tokens2[6], tokens2[7], tokens2[8], tokens2[9], tokens2[10], tokens2[11], tokens2[12], tokens2[13], tokens2[14]
                    ));
                }


//                removedItems = new ArrayList<Integer>();

                MainActivity activity = activityWeakReference.get();
                if (activity == null || activity.isFinishing()) {
                    return;
                }

                activity.progressBar.setProgress(0);
                activity.progressBar.setVisibility(View.INVISIBLE);

                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(MainActivity.this);


//                Toast.makeText(MainActivity.this, tokens[0], Toast.LENGTH_LONG).show();
            }


//            Log.d("FROM SERVER: " , dataSet.get(listPosition).getImg_utl());
            //dataSet.get(listPosition).getImg_utl()

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_developer:
                openDialogDeveloperName();

                return true;
            case R.id.menu_game_name:
                openDialogGameName();

                return true;
            case R.id.menu_publisher:
                openDialogPublisherName();
                return true;
            case R.id.English:
                new BackgraungTask(this).execute("languages English");
                return true;
            case R.id.Russian:
                new BackgraungTask(this).execute("languages Russian");
                return true;
            case R.id.French:
                new BackgraungTask(this).execute("languages French");
                return true;
            case R.id.Italian:
                new BackgraungTask(this).execute("languages Italian");
                return true;
            case R.id.German:
                new BackgraungTask(this).execute("languages German");
                return true;
            case R.id.Spanish:
                new BackgraungTask(this).execute("languages Spanish");
                return true;
            case R.id.Bulgarian:
                new BackgraungTask(this).execute("languages Bulgarian");
                return true;
            case R.id.Czech:
                new BackgraungTask(this).execute("languages Czech");
                return true;
            case R.id.Japanese:
                new BackgraungTask(this).execute("languages Japanese");
                return true;
            case R.id.Chinese:
                new BackgraungTask(this).execute("languages Chinese");
                return true;
            case R.id.Arabic:
                new BackgraungTask(this).execute("languages Arabic");
                return true;
            case R.id.Polish:
                new BackgraungTask(this).execute("languages Polish");
                return true;
            case R.id.Greek:
                new BackgraungTask(this).execute("languages Greek");
                return true;
            case R.id.Korean:
                new BackgraungTask(this).execute("languages Korean");
                return true;
            case R.id.Ukrainian:
                new BackgraungTask(this).execute("languages Ukrainian");
                return true;
            case R.id.Action:
                new BackgraungTask(this).execute("genre Action");
                return true;
            case R.id.Free_to_Play:
                new BackgraungTask(this).execute("genre Free to Play");
                return true;
            case R.id.Massively_Multiplayer:
                new BackgraungTask(this).execute("genre Massively Multiplayer");
                return true;
            case R.id.Adventure:
                new BackgraungTask(this).execute("genre Adventure");
                return true;
            case R.id.Indie:
                new BackgraungTask(this).execute("genre Indie");
                return true;
            case R.id.RPG:
                new BackgraungTask(this).execute("genre RPG");
                return true;
            case R.id.Strategy:
                new BackgraungTask(this).execute("genre Strategy");
                return true;
            case R.id.Simulation:
                new BackgraungTask(this).execute("genre Simulation");
                return true;
            case R.id.Early_Access:
                new BackgraungTask(this).execute("genre Early Access");
                return true;
            case R.id.Casual:
                new BackgraungTask(this).execute("genre Casual");
                return true;
            case R.id.Racing:
                new BackgraungTask(this).execute("genre Racing");
                return true;
            case R.id.Sports:
                new BackgraungTask(this).execute("genre Sports");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


//        return super.onOptionsItemSelected(item);
    }

    private void openDialogPublisherName() {
        DialogPublisherName dialogPublisherName =new DialogPublisherName();
        dialogPublisherName.show(getSupportFragmentManager(),"dialog");
    }

    private void openDialogGameName() {
        DialogGameName dialogGameName = new DialogGameName();
        dialogGameName.show(getSupportFragmentManager(),"dialog");



    }
    private void openDialogDeveloperName() {
        DialogDeveloperName dialogDeveloperName = new DialogDeveloperName();
        dialogDeveloperName.show(getSupportFragmentManager(),"dialog");



    }
    //    // Display anchored popup menu based on view selected
//    private void showFilterPopup(View v) {
//        PopupMenu popup = new PopupMenu(this, v);
//        // Inflate the menu from xml
//        popup.inflate(R.menu.menu_main);
//        // Setup menu item selection
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.menu_developer:
//                        Toast.makeText(MainActivity.this, "developer!", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_game_name:
//                        Toast.makeText(MainActivity.this, "game_name!", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_genre:
//                        Toast.makeText(MainActivity.this, "genre!", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_languages:
//                        Toast.makeText(MainActivity.this, "languages!", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.menu_publisher:
//                        Toast.makeText(MainActivity.this, "publisher!", Toast.LENGTH_SHORT).show();
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//        // Handle dismissal with: popup.setOnDismissListener(...);
//        // Show the menu
//        popup.show();
//    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        DataModel2 clickedItem = data.get(position);
//        detailIntent.putExtra(EXTRA_URL, clickedItem.getID());
        detailIntent.putExtra(EXTRA_ID, clickedItem.getID());
        detailIntent.putExtra(EXTRA_URL, clickedItem.getUrl());
        detailIntent.putExtra(EXTRA_TYPES, clickedItem.getTypes());
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getGame_name());
        detailIntent.putExtra(EXTRA_GAME_DESCRIPTION, clickedItem.getGame_description());
        detailIntent.putExtra(EXTRA_RELEASE_DATA, clickedItem.getRelease_date());
        detailIntent.putExtra(EXTRA_DEVELOPER, clickedItem.getDeveloper());
        detailIntent.putExtra(EXTRA_PUBLISHER, clickedItem.getPublisher());
        detailIntent.putExtra(EXTRA_POPULAR_TAGS, clickedItem.getPopular_tags());
        detailIntent.putExtra(EXTRA_LANGUAGES, clickedItem.getLanguages());
        detailIntent.putExtra(EXTRA_GENRE, clickedItem.getGenre());
        detailIntent.putExtra(EXTRA_MINIMUM_REQUIREMENTS, clickedItem.getMinimum_requirements());
        detailIntent.putExtra(EXTRA_RECOMMENDED_REQUIREMENTS, clickedItem.getRecommended_requirements());
        detailIntent.putExtra(EXTRA_ORIGINAL_PRICE, clickedItem.getOriginal_price());
        detailIntent.putExtra(EXTRA_IMG_URL, clickedItem.getImg_utl());


        startActivity(detailIntent);

    }

    @Override
    public void onBackPressed() {

    }
}
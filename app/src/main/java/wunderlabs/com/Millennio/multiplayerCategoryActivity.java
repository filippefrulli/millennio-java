package wunderlabs.com.Millennio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class multiplayerCategoryActivity extends AppCompatActivity {
    //!!        DA MODIFICARE PER NUOVA CATEGORIA

    Button historyButton, inventionsButton, moviesButton, gamesButton, booksButton, albumsButton, singlesButton, backButton;

    TextView title, historyTitle, inventionsTitle, moviesTitle, gamesTitle, booksTitle, albumsTitle, singlesTitle;

    SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_singleplayer_category);

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");


        title = findViewById(R.id.title);
        title.setText(R.string.categories);
        title.setTypeface(bold_font);

        historyTitle = findViewById(R.id.historyTitle);
        historyTitle.setText(R.string.historyCategory);
        historyTitle.setTypeface(bold_font);

        inventionsTitle = findViewById(R.id.inventionsTitle);
        inventionsTitle.setText(R.string.inventionsCategory);
        inventionsTitle.setTypeface(bold_font);

        moviesTitle = findViewById(R.id.moviesTitle);
        moviesTitle.setText(R.string.moviesCategory);
        moviesTitle.setTypeface(bold_font);

        gamesTitle = findViewById(R.id.gamesTitle);
        gamesTitle.setText(R.string.gamesCategory);
        gamesTitle.setTypeface(bold_font);

        booksTitle = findViewById(R.id.booksTitle);
        booksTitle.setText(R.string.booksCategory);
        booksTitle.setTypeface(bold_font);

        singlesTitle = findViewById(R.id.singlesTitle);
        singlesTitle.setText(R.string.singlesCategory);
        singlesTitle.setTypeface(bold_font);

        albumsTitle = findViewById(R.id.albumsTitle);
        albumsTitle.setText(R.string.albumsCategory);
        albumsTitle.setTypeface(bold_font);


        SP = getSharedPreferences("multiplayer", 0);
        final SharedPreferences.Editor editor = SP.edit();

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(multiplayerCategoryActivity.this, aMainActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

        historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editor.putInt("category", 1);
                editor.apply();
                startActivity(new Intent(multiplayerCategoryActivity.this, multiplayerStartActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

        inventionsButton = findViewById(R.id.inventionsButton);
        inventionsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editor.putInt("category", 2);
                editor.apply();
                startActivity(new Intent(multiplayerCategoryActivity.this, multiplayerStartActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });


        moviesButton = findViewById(R.id.moviesButton);
        moviesButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editor.putInt("category", 3);
                editor.apply();
                startActivity(new Intent(multiplayerCategoryActivity.this, multiplayerStartActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });


        gamesButton = findViewById(R.id.gamesButton);
        gamesButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editor.putInt("category", 4);
                editor.apply();
                startActivity(new Intent(multiplayerCategoryActivity.this, multiplayerStartActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });


        booksButton = findViewById(R.id.booksButton);
        booksButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editor.putInt("category", 5);
                editor.apply();
                startActivity(new Intent(multiplayerCategoryActivity.this, multiplayerStartActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

        singlesButton = findViewById(R.id.singlesButton);
        singlesButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editor.putInt("category", 6);
                editor.apply();
                startActivity(new Intent(multiplayerCategoryActivity.this, multiplayerStartActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

        albumsButton = findViewById(R.id.albumsButton);
        albumsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editor.putInt("category", 7);
                editor.apply();
                startActivity(new Intent(multiplayerCategoryActivity.this, multiplayerStartActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });



        //INIZIALMENTE LA DIFFICOLTA' E SU NORMALE
        editor.putInt("historyDifficulty",1);
        editor.putInt("inventionsDifficulty",1);
        editor.putInt("moviesDifficulty",1);
        editor.putInt("gamesDifficulty",1);
        editor.putInt("booksDifficulty",1);
        editor.putInt("singlesDifficulty",1);
        editor.putInt("albumsDifficulty",1);
        editor.apply();

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(multiplayerCategoryActivity.this, aMainActivity.class));
        overridePendingTransition(0,0);
        finish();
    }
}
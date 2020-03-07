package wunderlabs.com.Millennio;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class singleplayerStartActivity extends AppCompatActivity {

    Button startButton, backButton, leftButton, rightButton;

    TextView title, difficulty, highScore, highScoreInt;

    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_singleplayer_start);

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        backButton = findViewById(R.id.backButton);
        startButton = findViewById(R.id.startButton);
        startButton.setTypeface(bold_font);
        title = findViewById(R.id.title);
        title.setLetterSpacing((float) 0.30);
        title.setTypeface(bold_font);
        icon = findViewById(R.id.icon);

        difficulty = findViewById(R.id.difficulty);

        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);

        highScore = findViewById(R.id.highScore);
        highScore.setText(R.string.highScore);
        highScore.setTypeface(bold_font);

        highScoreInt = findViewById(R.id.highScoreInt);
        highScoreInt.setTypeface(bold_font);

        SharedPreferences SP = getSharedPreferences("singleplayer", Activity.MODE_PRIVATE);
        int category = SP.getInt("category", 0);
        final SharedPreferences.Editor editor = SP.edit();

        if(category == 6 || category == 7){
            difficulty.setText(R.string.post2010);
            difficulty.setTextSize(18);
        }else difficulty.setText(R.string.normalDifficulty);

        switch (category){
            case 1://HISTORY
                editor.putInt("historyPoints", 400);
                editor.apply();

                final int historyHighScore = SP.getInt("historyHighScore", 0);
                final int historyHighScoreHard = SP.getInt("historyHighScoreHard", 0);

                highScoreInt.setText("" + historyHighScore);

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("historyDifficulty",1);
                        editor.putInt("historyPoints", 400);
                        editor.apply();
                        difficulty.setText(R.string.normalDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_up);
                        highScoreInt.setText("" + historyHighScore);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("historyDifficulty",2);
                        editor.putInt("historyPoints", 800);
                        editor.apply();
                        difficulty.setText(R.string.hardDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_down);
                        highScoreInt.setText("" + historyHighScoreHard);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });

                title.setText(R.string.historyCategory);
                icon.setBackgroundResource(R.drawable.storia);
                break;

            case 2://INVENTIONS
                editor.putInt("inventionsPoints", 2000);
                editor.apply();

                leftButton.setAlpha((float) 0.4);
                rightButton.setAlpha((float) 0.4);

                int inventionsHighScore = SP.getInt("inventionsHighScore", 0);
                highScoreInt.setText("" + inventionsHighScore);

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {

                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {

                    }
                });

                title.setText(R.string.inventionsCategory);
                icon.setBackgroundResource(R.drawable.invenzioni);
                break;

            case 3://MOVIES
                editor.putInt("moviesPoints", 40);
                editor.apply();

                final int moviesHighScore = SP.getInt("moviesHighScore", 0);
                final int moviesHighScoreHard = SP.getInt("moviesHighScoreHard", 0);
                highScoreInt.setText("" + moviesHighScore);

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("moviesDifficulty",1);
                        editor.putInt("moviesPoints", 40);
                        editor.apply();
                        difficulty.setText(R.string.normalDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_up);
                        highScoreInt.setText("" + moviesHighScore);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("moviesDifficulty",2);
                        editor.putInt("moviesPoints", 80);
                        editor.apply();
                        difficulty.setText(R.string.hardDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_down);
                        highScoreInt.setText("" + moviesHighScoreHard);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });

                title.setText(R.string.moviesCategory);
                icon.setBackgroundResource(R.drawable.film);
                break;

            case 4:
                editor.putInt("gamesPoints", 20);
                editor.apply();

                final int gamesHighScore = SP.getInt("gamesHighScore", 0);
                final int gamesHighScoreHard = SP.getInt("gamesHighScoreHard", 0);
                highScoreInt.setText("" + gamesHighScore);

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("gamesDifficulty",1);
                        editor.putInt("gamesPoints", 20);
                        editor.apply();
                        difficulty.setText(R.string.normalDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_up);
                        highScoreInt.setText("" + gamesHighScore);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("gamesDifficulty",2);
                        editor.putInt("gamesPoints", 40);
                        editor.apply();
                        difficulty.setText(R.string.hardDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_down);
                        highScoreInt.setText("" + gamesHighScoreHard);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });

                title.setText(R.string.gamesCategory);
                icon.setBackgroundResource(R.drawable.giochi);
                break;

            case 5://LETTERATURA
                editor.putInt("booksPoints", 500);
                editor.apply();

                int booksHighScore = SP.getInt("booksHighScore", 0);
                highScoreInt.setText("" + booksHighScore);

                leftButton.setAlpha((float) 0.4);
                rightButton.setAlpha((float) 0.4);

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {

                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {

                    }
                });

                title.setText(R.string.booksCategory);
                icon.setBackgroundResource(R.drawable.libri);
                break;

            case 6:
                editor.putInt("singlesPoints", 10);
                editor.apply();

                icon.getLayoutParams().width = 150;

                final int singlesHighScore = SP.getInt("singlesHighScore", 0);
                final int singlesHighScoreHard = SP.getInt("singlesHighScoreHard", 0);
                highScoreInt.setText("" + singlesHighScore);

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("singlesDifficulty",1);
                        editor.putInt("singlesPoints", 10);
                        editor.apply();
                        difficulty.setText(R.string.post2010);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_up);
                        highScoreInt.setText("" + singlesHighScore);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("singlesDifficulty",2);
                        editor.putInt("singlesPoints", 100);
                        editor.apply();
                        difficulty.setText(R.string.influencial);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_down);
                        highScoreInt.setText("" + singlesHighScoreHard);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });

                title.setText(R.string.singlesCategory);
                icon.setBackgroundResource(R.drawable.singoli);
                break;

            case 7:
                editor.putInt("albumsPoints", 10);
                editor.apply();

                final int albumsHighScore = SP.getInt("albumsHighScore", 0);
                final int albumsHighScoreHard = SP.getInt("albumsHighScoreHard", 0);
                highScoreInt.setText("" + albumsHighScore);

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("albumsDifficulty",1);
                        editor.putInt("albumsPoints", 10);
                        editor.apply();
                        difficulty.setText(R.string.post2010);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_up);
                        highScoreInt.setText("" + albumsHighScore);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("albumsDifficulty",2);
                        editor.putInt("albumsPoints", 100);
                        editor.apply();
                        difficulty.setText(R.string.influencial);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_down);
                        highScoreInt.setText("" + albumsHighScoreHard);
                        highScoreInt.clearAnimation();
                        highScoreInt.startAnimation(b);
                    }
                });

                title.setText(R.string.albumsCategory);
                icon.setBackgroundResource(R.drawable.album);
                break;
        }




        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                editor.putInt("Score",0);
                editor.apply();
                startActivity(new Intent(singleplayerStartActivity.this, singleplayerGameActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(singleplayerStartActivity.this, singleplayerCategoryActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(singleplayerStartActivity.this, singleplayerCategoryActivity.class));
        overridePendingTransition(0,0);
        finish();
    }
}

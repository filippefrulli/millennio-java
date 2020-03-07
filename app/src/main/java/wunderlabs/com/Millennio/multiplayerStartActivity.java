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

public class multiplayerStartActivity extends AppCompatActivity {

    Button startButton, backButton, leftButton, rightButton;

    TextView title, difficulty;

    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_multiplayer_start);

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

        SharedPreferences SP = getSharedPreferences("multiplayer", Activity.MODE_PRIVATE);
        int category = SP.getInt("category", 0);
        final SharedPreferences.Editor editor = SP.edit();

        if(category == 6 || category == 7){
            difficulty.setText(R.string.post2010);
            difficulty.setTextSize(18);
        }else{
            difficulty.setText(R.string.normalDifficulty);
        }

        switch (category){
            case 1://HISTORY
                editor.putInt("Points1",400);
                editor.putInt("Points2",400);
                editor.apply();

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("historyDifficulty",1);
                        editor.putInt("Points1",400);
                        editor.putInt("Points2",400);
                        editor.apply();
                        difficulty.setText(R.string.normalDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("historyDifficulty",2);
                        editor.putInt("Points1",800);
                        editor.putInt("Points2",800);
                        editor.apply();
                        difficulty.setText(R.string.hardDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });

                title.setText(R.string.historyCategory);
                icon.setBackgroundResource(R.drawable.storia);
                break;

            case 2://INVENTIONS
                editor.putInt("Points1",2000);
                editor.putInt("Points2",2000);
                editor.apply();

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

                title.setText(R.string.inventionsCategory);
                icon.setBackgroundResource(R.drawable.invenzioni);
                break;

            case 3://MOVIES
                editor.putInt("Points1",40);
                editor.putInt("Points2",40);
                editor.apply();


                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("moviesDifficulty",1);
                        editor.putInt("Points1",40);
                        editor.putInt("Points2",40);
                        editor.apply();
                        editor.apply();
                        difficulty.setText(R.string.normalDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("moviesDifficulty",2);
                        editor.putInt("Points1",80);
                        editor.putInt("Points2",80);
                        editor.apply();
                        editor.apply();
                        difficulty.setText(R.string.hardDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });

                title.setText(R.string.moviesCategory);
                icon.setBackgroundResource(R.drawable.film);
                break;

            case 4:
                editor.putInt("Points1",20);
                editor.putInt("Points2",20);
                editor.apply();


                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("gamesDifficulty",1);
                        editor.putInt("Points1",20);
                        editor.putInt("Points2",20);
                        editor.apply();
                        difficulty.setText(R.string.normalDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("gamesDifficulty",2);
                        editor.putInt("Points1",40);
                        editor.putInt("Points2",40);
                        editor.apply();
                        difficulty.setText(R.string.hardDifficulty);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });

                title.setText(R.string.gamesCategory);
                icon.setBackgroundResource(R.drawable.giochi);
                break;

            case 5://LETTERATURA
                editor.putInt("Points1",500);
                editor.putInt("Points2",500);
                editor.apply();


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

            case 6://SINGLES
                editor.putInt("Points1",10);
                editor.putInt("Points2",10);
                editor.apply();

                icon.getLayoutParams().width = 150;

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("singlesDifficulty",1);
                        editor.putInt("Points1",10);
                        editor.putInt("Points2",10);
                        editor.apply();
                        difficulty.setText(R.string.post2010);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("singlesDifficulty",2);
                        editor.putInt("Points1",100);
                        editor.putInt("Points2",100);
                        editor.apply();
                        difficulty.setText(R.string.influencial);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });

                title.setText(R.string.singlesCategory);
                icon.setBackgroundResource(R.drawable.singoli);
                break;

            case 7://ALBUMS
                editor.putInt("Points1",10);
                editor.putInt("Points2",10);
                editor.apply();

                leftButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("albumsDifficulty",1);
                        editor.putInt("Points1",10);
                        editor.putInt("Points2",10);
                        editor.apply();
                        difficulty.setText(R.string.post2010);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });
                rightButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        editor.putInt("albumsDifficulty",2);
                        editor.putInt("Points1",100);
                        editor.putInt("Points2",100);
                        editor.apply();
                        difficulty.setText(R.string.influencial);
                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
                        difficulty.clearAnimation();
                        difficulty.startAnimation(a);

                    }
                });

                title.setText(R.string.albumsCategory);
                icon.setBackgroundResource(R.drawable.album);
                break;
        }

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                editor.putInt("Round", 1);
                editor.apply();
                startActivity(new Intent(multiplayerStartActivity.this, multiplayerStartp1Activity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(multiplayerStartActivity.this, multiplayerCategoryActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(multiplayerStartActivity.this, singleplayerCategoryActivity.class));
        overridePendingTransition(0,0);
        finish();
    }
}

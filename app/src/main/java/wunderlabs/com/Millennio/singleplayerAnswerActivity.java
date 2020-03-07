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

public class singleplayerAnswerActivity extends AppCompatActivity {

    TextView userAnswer, correctAnswer, total;

    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_singleplayer_answer);

        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        userAnswer = findViewById(R.id.YourAnswer);
        correctAnswer = findViewById(R.id.CorrectAnswer);
        total = findViewById(R.id.Total);
        closeButton = findViewById(R.id.closeButton);

        userAnswer.setTypeface(bold_font);
        correctAnswer.setTypeface(bold_font);
        total.setTypeface(bold_font);
        closeButton.setTypeface(bold_font);

        SharedPreferences SP = getSharedPreferences("singleplayer", 0);
        final SharedPreferences.Editor editor = SP.edit();

        final int category = SP.getInt("category", 0);
//!!        DA MODIFICARE PER NUOVA CATEGORIA


        int Input = getIntent().getIntExtra("Input", 0);
        final int Score = getIntent().getIntExtra("Score", 0);
        final int Points = getIntent().getIntExtra("Points", 0);
        int Answer = getIntent().getIntExtra("Answer", 0);
        final int toRemove = getIntent().getIntExtra("toRemove", 0);

        userAnswer.setText(" " + (Input));
        correctAnswer.setText(" " + (Answer));
        total.setText(" " + (-toRemove));

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SE I PUNTI SONO <= A 0 APRO L'ACTIVITY DI GAME OVER
                if ((Points <= 0)) {
                    switch (category) {
                        case 1:
                            editor.putInt("historyScore", Score);
                            editor.apply();
                            break;
                        case 2:
                            editor.putInt("inventionsScore", Score);
                            editor.apply();
                            break;
                        case 3:
                            editor.putInt("moviesScore", Score);
                            editor.apply();
                            break;
                        case 4:
                            editor.putInt("gamesScore", Score);
                            editor.apply();
                            break;
                        case 5:
                            editor.putInt("booksScore", Score);
                            editor.apply();
                            break;
                        case 6:
                            editor.putInt("singlesScore", Score);
                            editor.apply();
                            break;
                        case 7:
                            editor.putInt("albumsScore", Score);
                            editor.apply();
                            break;
                    }
                    startActivity(new Intent(singleplayerAnswerActivity.this, singleplayerResultActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }else{
                    startActivity(new Intent(singleplayerAnswerActivity.this, singleplayerGameActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }
            }

        });

    }

    public void onBackPressed() {}

}

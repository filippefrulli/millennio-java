package wunderlabs.com.Millennio;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class multiplayerResultActivity extends AppCompatActivity {

    TextView gameover, winner, winnerNumber;

    Button tryAgainButton, quitButton;

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_multiplayer_result);

        MobileAds.initialize(this, "ca-app-pub-6054126061208799~4555928851");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6054126061208799/9834631107");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        final SharedPreferences SP = getSharedPreferences("multiplayer", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = SP.edit();

        final SharedPreferences questionNumber = getSharedPreferences("questionNumber", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor numbersEditor = questionNumber.edit();

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        gameover = findViewById(R.id.gameOver);
        gameover.setText(R.string.gameOver);
        gameover.setTypeface(bold_font);

        winner = findViewById(R.id.winner);
        winner.setTypeface(bold_font);

        winnerNumber = findViewById(R.id.winnerNumber);
        winnerNumber.setTypeface(bold_font);

        tryAgainButton = findViewById(R.id.tryAgainButton);
        tryAgainButton.setTypeface(bold_font);

        quitButton = findViewById(R.id.quitButton);
        quitButton.setTypeface(bold_font);

        final int player = SP.getInt("winner", 0);

        switch (player){
            case 1:
                winnerNumber.setText(" " + player);
                winnerNumber.setTextColor(getResources().getColor(R.color.blue_light));
                winner.setTextColor(getResources().getColor(R.color.blue_light));
                break;

            case 2:
                winnerNumber.setText(" " + player);
                winnerNumber.setTextColor(getResources().getColor(R.color.green_light));
                winner.setTextColor(getResources().getColor(R.color.green_light));
                break;

            case 3:
                winnerNumber.setText(" (wow)");
                winnerNumber.setTextColor(getResources().getColor(R.color.white));
                winner.setTextColor(getResources().getColor(R.color.white));
                winner.setText(R.string.draw);
                break;
        }



        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putInt("Round", 1);
                editor.apply();
                initiateQuestionNumbers(numbersEditor);
                startActivity(new Intent(multiplayerResultActivity.this, multiplayerStartp1Activity.class));
                finish();
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }

                int category = SP.getInt("category", 0);

                switch (category){
                    case 1:
                        editor.putInt("Points1",400);
                        editor.putInt("Points2",400);
                        editor.putInt("Round", 1);
                        editor.apply();
                        break;
                    case 2:
                        editor.putInt("Points1",2000);
                        editor.putInt("Points2",2000);
                        editor.putInt("Round", 1);
                        editor.apply();
                        break;
                    case 3:
                        editor.putInt("Points1",40);
                        editor.putInt("Points2",40);
                        editor.putInt("Round", 1);
                        editor.apply();
                        break;
                    case 4:
                        editor.putInt("Points1",20);
                        editor.putInt("Points2",20);
                        editor.putInt("Round", 1);
                        editor.apply();
                        break;
                    case 5:
                        editor.putInt("Points1",500);
                        editor.putInt("Points2",500);
                        editor.putInt("Round", 1);
                        editor.apply();
                        break;
                    case 6:
                        final int singlesDifficulty = SP.getInt("singlesDifficulty", 0);
                        if (singlesDifficulty == 1){
                            editor.putInt("Points1",10);
                            editor.putInt("Points2",10);
                        }else{
                            editor.putInt("Points1",100);
                            editor.putInt("Points2",100);
                        }
                        editor.putInt("Round", 1);
                        editor.apply();
                        break;
                    case 7:
                        final int albumsDifficulty = SP.getInt("albumsDifficulty", 0);
                        if (albumsDifficulty == 1){
                            editor.putInt("Points1",10);
                            editor.putInt("Points2",10);
                        }else{
                            editor.putInt("Points1",100);
                            editor.putInt("Points2",100);
                        }
                        editor.putInt("Round", 1);
                        editor.apply();
                        break;
                }

            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(multiplayerResultActivity.this, aMainActivity.class));
                finish();
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
            }
        });

    }

    void initiateQuestionNumbers(SharedPreferences.Editor editor){
        for(int i = 0; i < 315; i++){
            String x = ("" + i);
            editor.putInt(x, 0);
            editor.apply();
        }
    }


}

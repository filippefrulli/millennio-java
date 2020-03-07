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

public class singleplayerResultActivity extends AppCompatActivity {


    TextView mResult, mHighScoreText, gameOver, highScoreInt;

    Button tryAgainButton, quitButton;

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_singleplayer_result);

        MobileAds.initialize(this, "ca-app-pub-6054126061208799~4555928851");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6054126061208799/1303079559");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        final SharedPreferences questionNumber = getSharedPreferences("questionNumber", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor numbersEditor = questionNumber.edit();

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        gameOver = findViewById(R.id.gameOver);
        gameOver.setText(R.string.gameOver);
        gameOver.setTypeface(bold_font);

        final SharedPreferences SP = getSharedPreferences("singleplayer" , 0);
        final SharedPreferences.Editor editor = SP.edit();
        int category = SP.getInt("category", 0);


        //ASSEGNO I DUE TASTI
        tryAgainButton = findViewById(R.id.tryAgainButton);
        tryAgainButton.setTypeface(bold_font);

        quitButton = findViewById(R.id.quitButton);
        quitButton.setTypeface(bold_font);

        //ASSEGNO LE TEXTVIEW
        mResult = findViewById(R.id.result);
        mResult.setTypeface(bold_font);

        mHighScoreText = findViewById(R.id.highScoreText);
        mHighScoreText.setText(R.string.highScore);
        mHighScoreText.setTypeface(bold_font);

        highScoreInt = findViewById(R.id.highScoreInt);
        highScoreInt.setTypeface(bold_font);

        switch (category){
            case 1://HISTORY
                //PRENDO I PUNTI
                int historyScore = SP.getInt("historyScore", 0);
                int historyHighScore = SP.getInt("historyHighScore", 0);
                int historyHighScoreHard = SP.getInt("historyHighScoreHard", 0);
                final int historyDifficulty = SP.getInt("historyDifficulty", 0);

                //MOSTRO IL PUNTEGGIO OTTENUTO
                mResult.setText("" + (historyScore));

                if (historyDifficulty == 1){

                    //SE IL PUNTEGGIO SUPERA L'HIGH SCORE VIENE AGGIORNATO
                    if(historyScore > historyHighScore){
                        historyHighScore = historyScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + historyHighScore);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("historyHighScore", historyHighScore);
                    editor.apply();
                }else{

                    if(historyScore > historyHighScoreHard){
                        historyHighScoreHard = historyScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + historyHighScoreHard);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("historyHighScoreHard", historyHighScoreHard);
                    editor.apply();
                }


                break;

            case 2://INVENTIONS
                //PRENDO I PUNTI
                int inventionsScore = SP.getInt("inventionsScore", 0);
                int inventionsHighScore = SP.getInt("inventionsHighScore", 0);
                final int inventionsDifficulty = SP.getInt("inventionsDifficulty", 0);

                //MOSTRO IL PUNTEGGIO OTTENUTO
                mResult.setText("" + (inventionsScore));

                //SE IL PUNTEGGIO SUPERA L'HIGH SCORE VIENE AGGIORNATO
                if(inventionsScore > inventionsHighScore){
                    inventionsHighScore = inventionsScore;
                    mHighScoreText.setText(R.string.newHighScore);
                    highScoreInt.setText("");
                }else {
                    //ALTRIMENTI RIMANE QUELLO VECCHIO
                    mHighScoreText.setText(R.string.highScore);
                    highScoreInt.setText(": " + inventionsHighScore);
                }
                //SALVO L'HIGH SCORE
                editor.putInt("inventionsHighScore", inventionsHighScore);
                editor.apply();

                break;

            case 3://MOVIES

                int moviesScore = SP.getInt("moviesScore", 0);
                int moviesHighScore = SP.getInt("moviesHighScore", 0);
                int moviesHighScoreHard = SP.getInt("moviesHighScoreHard", 0);
                final int moviesDifficulty = SP.getInt("moviesDifficulty", 0);

                //MOSTRO IL PUNTEGGIO OTTENUTO
                mResult.setText("" + (moviesScore));
                mResult.setTypeface(bold_font);

                if (moviesDifficulty == 1){
                    //SE IL PUNTEGGIO SUPERA L'HIGH SCORE VIENE AGGIORNATO
                    if(moviesScore > moviesHighScore){
                        int currentTotalEasy = SP.getInt("moviesAverageScoreEasy", 0);
                        editor.putInt("moviesAverageScoreEasy", currentTotalEasy+moviesScore);
                        editor.apply();
                        moviesHighScore = moviesScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + moviesHighScore);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("moviesHighScore", moviesHighScore);
                    editor.apply();
                }else{
                    if(moviesScore > moviesHighScoreHard){
                        moviesHighScoreHard = moviesScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + moviesHighScoreHard);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("moviesHighScoreHard", moviesHighScoreHard);
                    editor.apply();
                }
                break;

            case 4:

                int gamesScore = SP.getInt("gamesScore", 0);
                int gamesHighScore = SP.getInt("gamesHighScore", 0);
                int gamesHighScoreHard = SP.getInt("gamesHighScoreHard", 0);
                final int gamesDifficulty = SP.getInt("gamesDifficulty", 0);

                //MOSTRO IL PUNTEGGIO OTTENUTO
                mResult.setText("" + (gamesScore));
                mResult.setTypeface(bold_font);

                if (gamesDifficulty == 1){
                    //SE IL PUNTEGGIO SUPERA L'HIGH SCORE VIENE AGGIORNATO
                    if(gamesScore > gamesHighScore){
                        gamesHighScore = gamesScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + gamesHighScore);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("gamesHighScore", gamesHighScore);
                    editor.apply();
                }else{
                    if(gamesScore > gamesHighScoreHard){
                        gamesHighScoreHard = gamesScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + gamesHighScoreHard);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("gamesHighScoreHard", gamesHighScoreHard);
                    editor.apply();
                }
                break;

            case 5://LETTERATURA
                //PRENDO I PUNTI
                int booksScore = SP.getInt("booksScore", 0);
                int booksHighScore = SP.getInt("booksHighScore", 0);

                //MOSTRO IL PUNTEGGIO OTTENUTO
                mResult.setText("" + (booksScore));

                //SE IL PUNTEGGIO SUPERA L'HIGH SCORE VIENE AGGIORNATO
                if(booksScore > booksHighScore){
                    booksHighScore = booksScore;
                    mHighScoreText.setText(R.string.newHighScore);
                    highScoreInt.setText("");
                }else {
                    //ALTRIMENTI RIMANE QUELLO VECCHIO
                    mHighScoreText.setText(R.string.highScore);
                    highScoreInt.setText(": " + booksHighScore);
                }
                //SALVO L'HIGH SCORE
                editor.putInt("booksHighScore", booksHighScore);
                editor.apply();

                break;

            case 6://HISTORY
                //PRENDO I PUNTI
                int singlesScore = SP.getInt("singlesScore", 0);
                int singlesHighScore = SP.getInt("singlesHighScore", 0);
                int singlesHighScoreHard = SP.getInt("singlesHighScoreHard", 0);
                final int singlesDifficulty = SP.getInt("singlesDifficulty", 0);

                //MOSTRO IL PUNTEGGIO OTTENUTO
                mResult.setText("" + (singlesScore));

                if (singlesDifficulty == 1){

                    //SE IL PUNTEGGIO SUPERA L'HIGH SCORE VIENE AGGIORNATO
                    if(singlesScore > singlesHighScore){
                        singlesHighScore = singlesScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + singlesHighScore);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("singlesHighScore", singlesHighScore);
                    editor.apply();
                }else{

                    if(singlesScore > singlesHighScoreHard){
                        singlesHighScoreHard = singlesScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + singlesHighScoreHard);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("singlesHighScoreHard", singlesHighScoreHard);
                    editor.apply();
                }


                break;

            case 7:
                //PRENDO I PUNTI
                int albumsScore = SP.getInt("albumsScore", 0);
                int albumsHighScore = SP.getInt("albumsHighScore", 0);
                int albumsHighScoreHard = SP.getInt("albumsHighScoreHard", 0);
                final int albumsDifficulty = SP.getInt("albumsDifficulty", 0);

                //MOSTRO IL PUNTEGGIO OTTENUTO
                mResult.setText("" + (albumsScore));

                if (albumsDifficulty == 1){

                    //SE IL PUNTEGGIO SUPERA L'HIGH SCORE VIENE AGGIORNATO
                    if(albumsScore > albumsHighScore){
                        albumsHighScore = albumsScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + albumsHighScore);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("albumsHighScore", albumsHighScore);
                    editor.apply();
                }else{

                    if(albumsScore > albumsHighScoreHard){
                        albumsHighScoreHard = albumsScore;
                        mHighScoreText.setText(R.string.newHighScore);
                        highScoreInt.setText("");
                    }else {
                        //ALTRIMENTI RIMANE QUELLO VECCHIO
                        mHighScoreText.setText(R.string.highScore);
                        highScoreInt.setText(": " + albumsHighScoreHard);
                    }
                    //SALVO L'HIGH SCORE
                    editor.putInt("albumsHighScoreHard", albumsHighScoreHard);
                    editor.apply();
                }


                break;

        }

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initiateQuestionNumbers(numbersEditor);
                startActivity(new Intent(getApplicationContext(), singleplayerStartActivity.class));
                overridePendingTransition(0,0);
                finish();
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), aMainActivity.class));
                overridePendingTransition(0,0);
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

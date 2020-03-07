package wunderlabs.com.Millennio;

//!!        DA MODIFICARE PER NUOVA CATEGORIA
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Arrays;
import java.util.List;

public class singleplayerGameActivity extends AppCompatActivity {

    public Activity h;

    private TextView mScoreView, mQuestion, mPointsView;

    //!!        DA MODIFICARE PER NUOVA CATEGORIA
    private int mAnswer;
    private int mScore;
    private int historyPoints;
    private int inventionsPoints;
    private int moviesPoints;
    private int gamesPoints;
    private int booksPoints;
    private int singlesPoints;
    private int albumsPoints;
    private int random;

    LinearLayout inputLayout;

    Button submitButton, exitButton;

    EditText numberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_singleplayer_game);

        final SharedPreferences questionNumber = getSharedPreferences("questionNumber", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor numbersEditor = questionNumber.edit();

        h = this;

        inputLayout = findViewById(R.id.inputLayout);

        mScoreView = findViewById(R.id.score);
        mQuestion =  findViewById(R.id.question);
        mPointsView = findViewById(R.id.points);
        numberInput = findViewById(R.id.input);

        submitButton = findViewById(R.id.submitButton);
        exitButton = findViewById(R.id.exitButton);

        //FONT DIVERSO PER PUNTI E PUNTEGGIO
        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        mQuestion.setTypeface(bold_font);
        mScoreView.setTypeface(bold_font);
        mPointsView.setTypeface(bold_font);
        submitButton.setTypeface(bold_font);

        final SharedPreferences SP = getSharedPreferences("singleplayer", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = SP.edit();
        int category = SP.getInt("category", 0);

        mScoreView.setTextColor(getResources().getColor(R.color.white));
        mPointsView.setTextColor(getResources().getColor(R.color.white));

        switch (category){
            case 1:

                final int historyDifficulty = SP.getInt("historyDifficulty", 0);

                mScore = SP.getInt("Score", 0);
                mScoreView.setText("" + mScore);

                historyPoints = SP.getInt("historyPoints", 0);
                mPointsView.setText("" + historyPoints);

                if(historyDifficulty == 1){
                    random = getRandomNumber(164, questionNumber, numbersEditor);
                    showRandomQuestion(random, 1, 1);
                }else{
                    random = getRandomNumber(84, questionNumber, numbersEditor);
                    showRandomQuestion(random, 2, 1);
                }

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        //CONTROLLA SE L'INPUT E' CORRETTO
                        try {
                            mAnswer = Integer.parseInt(numberInput.getText().toString());
                        } catch (NumberFormatException ex) {
                            return;
                        }

                        //PRENDE L'INPUT
                        mAnswer = Integer.parseInt(numberInput.getText().toString());

                        //CAlCOLA I PUNTI DA RIMUOVERE

                        int mPointsToRemove;

                        if(historyDifficulty == 1){
                            mPointsToRemove = calculatePoints(random, mAnswer, -20, historyDifficulty, 1);
                        }else{
                            mPointsToRemove = calculatePoints(random, mAnswer, -100, historyDifficulty, 1);
                        }

                        historyPoints = historyPoints - mPointsToRemove;

                        editor.putInt("historyPoints", historyPoints);

                        //APRO L'ACTIVITY CHE MOSTRA LA RISPOSTA E IL RISULTATO
                        Intent intent = new Intent(getBaseContext(), singleplayerAnswerActivity.class);
                        overridePendingTransition(0, 0);
                        //LE PASSO LE VARIE VARIABILI
                        intent.putExtra("Input", mAnswer);
                        intent.putExtra("Score", mScore);
                        intent.putExtra("Points", historyPoints);

                        if (historyDifficulty == 1)intent.putExtra("Answer", historyQuestionLibrary.historyAnswers[random]);
                        else intent.putExtra("Answer", historyQuestionLibrary.historyAnswersHard[random]);

                        intent.putExtra("toRemove", mPointsToRemove);
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        //CREO UN DELAY DI 1 SECOND0 IN MODO CHE NON SI INTRAVEDA LA DOMANDA SUCESSIVA
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editor.putInt("Score", mScore+1);
                                editor.apply();
                                mScore = SP.getInt("Score", 0);
                                mScoreView.setText("" + mScore);
                                updatePoints(historyPoints);

                                //NUOVA DOMANDA
                                if(historyDifficulty == 1){
                                    random = getRandomNumber(164, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 1, 1);
                                }else{
                                    random = getRandomNumber(84, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 2, 1);
                                }

                                numberInput.setText(null);
                            }
                        }, 1000);

                    }
                });
                break;

            case 2:

                inventionsPoints = SP.getInt("inventionsPoints", 0);
                mPointsView.setText("" + inventionsPoints);

                mScore = SP.getInt("Score", 0);
                mScoreView.setText("" + mScore);

                random = getRandomNumber(146, questionNumber, numbersEditor);
                showRandomQuestion(random, 1, 2);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        //CONTROLLA SE L'INPUT E' CORRETTO
                        try {
                            mAnswer = Integer.parseInt(numberInput.getText().toString());
                        } catch (NumberFormatException ex) {
                            return;
                        }

                        //PRENDE L'INPUT
                        mAnswer = Integer.parseInt(numberInput.getText().toString());

                        //CAlCOLA I PUNTI DA RIMUOVERE
                        int mPointsToRemove = calculatePoints(random, mAnswer, -250, 1, 2);

                        inventionsPoints = inventionsPoints - mPointsToRemove;

                        editor.putInt("inventionsPoints", inventionsPoints);
                        editor.apply();

                        //APRO L'ACTIVITY CHE MOSTRA LA RISPOSTA E IL RISULTATO
                        Intent intent = new Intent(getBaseContext(), singleplayerAnswerActivity.class);
                        overridePendingTransition(0, 0);
                        //LE PASSO LE VARIE VARIABILI
                        intent.putExtra("Input", mAnswer);
                        intent.putExtra("Score", mScore);
                        intent.putExtra("Points", inventionsPoints);
                        intent.putExtra("Answer", inventionsQuestionLibrary.inventionsAnswers[random]);
                        intent.putExtra("toRemove", mPointsToRemove);
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        //CREO UN DELAY DI 1 SECOND0 IN MODO CHE NON SI INTRAVEDA LA DOMANDA SUCESSIVA
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editor.putInt("Score", mScore+1);
                                editor.apply();
                                mScore = SP.getInt("Score", 0);
                                mScoreView.setText("" + mScore);
                                updatePoints(inventionsPoints);

                                //NUOVA DOMANDA
                                random = getRandomNumber(146, questionNumber, numbersEditor);

                                showRandomQuestion(random, 1, 2);

                                numberInput.setText(null);
                            }
                        }, 1000);

                    }
                });
                break;

            case 3:

                final int moviesDifficulty = SP.getInt("moviesDifficulty", 0);

                moviesPoints = SP.getInt("moviesPoints", 0);
                mPointsView.setText("" + moviesPoints);

                mScore = SP.getInt("Score", 0);
                mScoreView.setText("" + mScore);

                if(moviesDifficulty == 1){
                    random = getRandomNumber(267, questionNumber, numbersEditor);
                    showRandomQuestion(random, 1, 3);
                }else{
                    random = getRandomNumber(231, questionNumber,numbersEditor);
                    showRandomQuestion(random, 2, 3);
                }

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        //CONTROLLA SE L'INPUT E' CORRETTO
                        try {
                            mAnswer = Integer.parseInt(numberInput.getText().toString());
                        } catch (NumberFormatException ex) {
                            return;
                        }

                        //PRENDE L'INPUT
                        mAnswer = Integer.parseInt(numberInput.getText().toString());

                        //CAlCOLA I PUNTI DA RIMUOVERE
                        int mPointsToRemove = calculatePoints(random, mAnswer, -4, moviesDifficulty, 3);

                        moviesPoints = moviesPoints - mPointsToRemove;

                        editor.putInt("moviesPoints", moviesPoints);
                        editor.apply();

                        //APRO L'ACTIVITY CHE MOSTRA LA RISPOSTA E IL RISULTATO
                        Intent intent = new Intent(getBaseContext(), singleplayerAnswerActivity.class);
                        overridePendingTransition(0, 0);
                        //LE PASSO LE VARIE VARIABILI
                        intent.putExtra("Input", mAnswer);
                        intent.putExtra("Score", mScore);
                        intent.putExtra("Points", moviesPoints);

                        if (moviesDifficulty == 1)intent.putExtra("Answer", moviesQuestionLibrary.moviesAnswers[random]);
                        else intent.putExtra("Answer", moviesQuestionLibrary.moviesAnswersHard[random]);

                        intent.putExtra("toRemove", mPointsToRemove);
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        //CREO UN DELAY DI 1 SECOND0 IN MODO CHE NON SI INTRAVEDA LA DOMANDA SUCESSIVA
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editor.putInt("Score", mScore+1);
                                editor.apply();
                                mScore = SP.getInt("Score", 0);
                                mScoreView.setText("" + mScore);
                                updatePoints(moviesPoints);

                                //NUOVA DOMANDA
                                if(moviesDifficulty == 1){
                                    random = getRandomNumber(267, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 1, 3);
                                }else{
                                    random = getRandomNumber(231,questionNumber, numbersEditor);
                                    showRandomQuestion(random, 2, 3);
                                }

                                numberInput.setText(null);
                            }
                        }, 1000);

                    }
                });
                break;
//!!        DA MODIFICARE PER NUOVA CATEGORIA
            case 4:
                final int gamesDifficulty = SP.getInt("gamesDifficulty", 0);

                gamesPoints = SP.getInt("gamesPoints", 0);
                mPointsView.setText("" + gamesPoints);

                mScore = SP.getInt("Score", 0);
                mScoreView.setText("" + mScore);

                if(gamesDifficulty == 1){
                    random = getRandomNumber(160, questionNumber, numbersEditor);
                    showRandomQuestion(random, 1, 4);
                }else{
                    random = getRandomNumber(170, questionNumber, numbersEditor);
                    showRandomQuestion(random, 2, 4);
                }

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        //CONTROLLA SE L'INPUT E' CORRETTO
                        try {
                            mAnswer = Integer.parseInt(numberInput.getText().toString());
                        } catch (NumberFormatException ex) {
                            return;
                        }

                        //PRENDE L'INPUT
                        mAnswer = Integer.parseInt(numberInput.getText().toString());

                        //CAlCOLA I PUNTI DA RIMUOVERE
                        int mPointsToRemove = calculatePoints(random, mAnswer, -2, gamesDifficulty, 4);

                        gamesPoints = gamesPoints - mPointsToRemove;

                        editor.putInt("gamesPoints", gamesPoints);
                        editor.apply();

                        //APRO L'ACTIVITY CHE MOSTRA LA RISPOSTA E IL RISULTATO
                        Intent intent = new Intent(getBaseContext(), singleplayerAnswerActivity.class);
                        overridePendingTransition(0, 0);
                        //LE PASSO LE VARIE VARIABILI
                        intent.putExtra("Input", mAnswer);
                        intent.putExtra("Score", mScore);
                        intent.putExtra("Points", gamesPoints);

                        if (gamesDifficulty == 1)intent.putExtra("Answer", gamesQuestionLibrary.gamesAnswers[random]);
                        else intent.putExtra("Answer", gamesQuestionLibrary.gamesAnswersHard[random]);

                        intent.putExtra("toRemove", mPointsToRemove);
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        //CREO UN DELAY DI 1 SECOND0 IN MODO CHE NON SI INTRAVEDA LA DOMANDA SUCESSIVA
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editor.putInt("Score", mScore+1);
                                editor.apply();
                                mScore = SP.getInt("Score", 0);
                                mScoreView.setText("" + mScore);
                                updatePoints(gamesPoints);

                                //NUOVA DOMANDA
                                if(gamesDifficulty == 1){
                                    random = getRandomNumber(160, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 1, 4);
                                }else{
                                    random = getRandomNumber(170, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 2, 4);
                                }

                                numberInput.setText(null);
                            }
                        }, 1000);

                    }
                });
                break;

            case 5:
                booksPoints = SP.getInt("booksPoints", 0);
                mPointsView.setText("" + booksPoints);

                mScore = SP.getInt("Score", 0);
                mScoreView.setText("" + mScore);

                random = getRandomNumber(99, questionNumber, numbersEditor);
                showRandomQuestion(random, 1, 5);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        //CONTROLLA SE L'INPUT E' CORRETTO
                        try {
                            mAnswer = Integer.parseInt(numberInput.getText().toString());
                        } catch (NumberFormatException ex) {
                            return;
                        }

                        //PRENDE L'INPUT
                        mAnswer = Integer.parseInt(numberInput.getText().toString());

                        //CAlCOLA I PUNTI DA RIMUOVERE
                        int mPointsToRemove = calculatePoints(random, mAnswer, -100, 1, 5);

                        booksPoints = booksPoints - mPointsToRemove;

                        editor.putInt("booksPoints", booksPoints);
                        editor.apply();

                        //APRO L'ACTIVITY CHE MOSTRA LA RISPOSTA E IL RISULTATO
                        Intent intent = new Intent(getBaseContext(), singleplayerAnswerActivity.class);
                        overridePendingTransition(0, 0);
                        //LE PASSO LE VARIE VARIABILI
                        intent.putExtra("Input", mAnswer);
                        intent.putExtra("Score", mScore);
                        intent.putExtra("Points", booksPoints);
                        intent.putExtra("Answer", booksQuestionLibrary.booksAnswers[random]);
                        intent.putExtra("toRemove", mPointsToRemove);
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        //CREO UN DELAY DI 1 SECOND0 IN MODO CHE NON SI INTRAVEDA LA DOMANDA SUCESSIVA
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editor.putInt("Score", mScore+1);
                                editor.apply();
                                mScore = SP.getInt("Score", 0);
                                mScoreView.setText("" + mScore);
                                updatePoints(booksPoints);

                                //NUOVA DOMANDA
                                random = getRandomNumber(99, questionNumber, numbersEditor);

                                showRandomQuestion(random, 1, 5);

                                numberInput.setText(null);
                            }
                        }, 1000);

                    }
                });
                break;

            case 6:
                final int singlesDifficulty = SP.getInt("singlesDifficulty", 0);

                singlesPoints = SP.getInt("singlesPoints", 0);
                mPointsView.setText("" + singlesPoints);

                mScore = SP.getInt("Score", 0);
                mScoreView.setText("" + mScore);

                if(singlesDifficulty == 1){
                    random = getRandomNumber(134, questionNumber, numbersEditor);
                    showRandomQuestion(random, 1, 6);
                }else{
                    random = getRandomNumber(93, questionNumber, numbersEditor);
                    showRandomQuestion(random, 2, 6);
                }

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        //CONTROLLA SE L'INPUT E' CORRETTO
                        try {
                            mAnswer = Integer.parseInt(numberInput.getText().toString());
                        } catch (NumberFormatException ex) {
                            return;
                        }

                        //PRENDE L'INPUT
                        mAnswer = Integer.parseInt(numberInput.getText().toString());

                        //CAlCOLA I PUNTI DA RIMUOVERE
                        int mPointsToRemove;

                        if(singlesDifficulty == 1){
                            mPointsToRemove = calculatePoints(random, mAnswer, -1, singlesDifficulty, 6);
                        }else{
                            mPointsToRemove = calculatePoints(random, mAnswer, -10, singlesDifficulty, 6);
                        }

                        singlesPoints = singlesPoints - mPointsToRemove;

                        editor.putInt("singlesPoints", singlesPoints);
                        editor.apply();

                        //APRO L'ACTIVITY CHE MOSTRA LA RISPOSTA E IL RISULTATO
                        Intent intent = new Intent(getBaseContext(), singleplayerAnswerActivity.class);
                        overridePendingTransition(0, 0);
                        //LE PASSO LE VARIE VARIABILI
                        intent.putExtra("Input", mAnswer);
                        intent.putExtra("Score", mScore);
                        intent.putExtra("Points", singlesPoints);

                        if (singlesDifficulty == 1)intent.putExtra("Answer", singlesQuestionLibrary.singlesAnswersEasy[random]);
                        else intent.putExtra("Answer", singlesQuestionLibrary.singlesAnswersHard[random]);

                        intent.putExtra("toRemove", mPointsToRemove);
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        //CREO UN DELAY DI 1 SECOND0 IN MODO CHE NON SI INTRAVEDA LA DOMANDA SUCESSIVA
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editor.putInt("Score", mScore+1);
                                editor.apply();
                                mScore = SP.getInt("Score", 0);
                                mScoreView.setText("" + mScore);
                                updatePoints(singlesPoints);

                                //NUOVA DOMANDA
                                if(singlesDifficulty == 1){
                                    random = getRandomNumber(134, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 1, 6);
                                }else{
                                    random = getRandomNumber(93, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 2, 6);
                                }

                                numberInput.setText(null);
                            }
                        }, 1000);

                    }
                });
                break;

            case 7:
                final int albumsDifficulty = SP.getInt("albumsDifficulty", 0);

                albumsPoints = SP.getInt("albumsPoints", 0);
                mPointsView.setText("" + albumsPoints);

                mScore = SP.getInt("Score", 0);
                mScoreView.setText("" + mScore);

                if(albumsDifficulty == 1){
                    random = getRandomNumber(91, questionNumber, numbersEditor);
                    showRandomQuestion(random, 1, 7);
                }else{
                    random = getRandomNumber(150, questionNumber, numbersEditor);
                    showRandomQuestion(random, 2, 7);
                }

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        //CONTROLLA SE L'INPUT E' CORRETTO
                        try {
                            mAnswer = Integer.parseInt(numberInput.getText().toString());
                        } catch (NumberFormatException ex) {
                            return;
                        }

                        //PRENDE L'INPUT
                        mAnswer = Integer.parseInt(numberInput.getText().toString());

                        //CAlCOLA I PUNTI DA RIMUOVERE

                        int mPointsToRemove;

                        if(albumsDifficulty == 1){
                            mPointsToRemove = calculatePoints(random, mAnswer, -1, albumsDifficulty, 7);
                        }else{
                            mPointsToRemove = calculatePoints(random, mAnswer, -10, albumsDifficulty, 7);
                        }

                        albumsPoints = albumsPoints - mPointsToRemove;

                        editor.putInt("albumsPoints", albumsPoints);
                        editor.apply();

                        //APRO L'ACTIVITY CHE MOSTRA LA RISPOSTA E IL RISULTATO
                        Intent intent = new Intent(getBaseContext(), singleplayerAnswerActivity.class);
                        overridePendingTransition(0, 0);
                        //LE PASSO LE VARIE VARIABILI
                        intent.putExtra("Input", mAnswer);
                        intent.putExtra("Score", mScore);
                        intent.putExtra("Points", albumsPoints);

                        if (albumsDifficulty == 1)intent.putExtra("Answer", albumsQuestionLibrary.albumsAnswersEasy[random]);
                        else intent.putExtra("Answer", albumsQuestionLibrary.albumsAnswersHard[random]);

                        intent.putExtra("toRemove", mPointsToRemove);
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        //CREO UN DELAY DI 1 SECOND0 IN MODO CHE NON SI INTRAVEDA LA DOMANDA SUCESSIVA
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editor.putInt("Score", mScore+1);
                                editor.apply();
                                mScore = SP.getInt("Score", 0);
                                mScoreView.setText("" + mScore);
                                updatePoints(albumsPoints);

                                //NUOVA DOMANDA
                                if(albumsDifficulty == 1){
                                    random = getRandomNumber(91, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 1, 7);
                                }else{
                                    random = getRandomNumber(150, questionNumber, numbersEditor);
                                    showRandomQuestion(random, 2, 7);
                                }

                                numberInput.setText(null);
                            }
                        }, 1000);

                    }
                });
                break;
        }

        exitButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                startActivity(new Intent(singleplayerGameActivity.this, aMainActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });
    }

    public void updateScore(int score){

        mScoreView.setText("" + score);
    }

    public void updatePoints(int mPoints){

        mPointsView.setText("" + mPoints);
    }

    private void showToast(){
        Toast.makeText(singleplayerGameActivity.this, "you beat the game", Toast.LENGTH_SHORT).show();
    }

    //PRENDE UN NUMERO A CASO ALLINTERNO DI UN RANGE
    public int getRandomNumber(int range, SharedPreferences SP, SharedPreferences.Editor editor){

        int counter = 0;
        int randomNum = 0;
        Boolean flag = true;
        while(flag){
            randomNum = (int) (Math.random() * range);
            String x = ("" + randomNum);
            int y = SP.getInt(x, 0);
            if(y == 0){
                editor.putInt(x, 1);
                editor.apply();
                flag = false;
            }
            counter++;
            if(counter == range){
                showToast();
                startActivity(new Intent(singleplayerGameActivity.this, splashActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        }
        return randomNum;
    }

    public int calculatePoints(int random, int mAnswer, int pointsToRemove, int difficulty, int category){

        int mPointsToRemove;

        switch (category) {
            case 1:
                if (difficulty == 1) {
                    if (mAnswer == historyQuestionLibrary.historyAnswers[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > historyQuestionLibrary.historyAnswers[random]) {
                        mPointsToRemove = (mAnswer - historyQuestionLibrary.historyAnswers[random]);
                    } else {
                        mPointsToRemove = (historyQuestionLibrary.historyAnswers[random] - mAnswer);
                    }
                } else {
                    if (mAnswer == historyQuestionLibrary.historyAnswersHard[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > historyQuestionLibrary.historyAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - historyQuestionLibrary.historyAnswersHard[random]);
                    } else {
                        mPointsToRemove = (historyQuestionLibrary.historyAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
            case 2:
                if (mAnswer == inventionsQuestionLibrary.inventionsAnswers[random]) {
                    mPointsToRemove = pointsToRemove;
                } else if (mAnswer > inventionsQuestionLibrary.inventionsAnswers[random]) {
                    mPointsToRemove = (mAnswer - inventionsQuestionLibrary.inventionsAnswers[random]);
                } else {
                    mPointsToRemove = (inventionsQuestionLibrary.inventionsAnswers[random] - mAnswer);
                }
                return mPointsToRemove;
            case 3:
                if (difficulty == 1) {
                    if (mAnswer == moviesQuestionLibrary.moviesAnswers[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > moviesQuestionLibrary.moviesAnswers[random]) {
                        mPointsToRemove = (mAnswer - moviesQuestionLibrary.moviesAnswers[random]);
                    } else {
                        mPointsToRemove = (moviesQuestionLibrary.moviesAnswers[random] - mAnswer);
                    }
                } else {
                    if (mAnswer == moviesQuestionLibrary.moviesAnswersHard[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > moviesQuestionLibrary.moviesAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - moviesQuestionLibrary.moviesAnswersHard[random]);
                    } else {
                        mPointsToRemove = (moviesQuestionLibrary.moviesAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
            case 4:
                if (difficulty == 1) {
                    if (mAnswer == gamesQuestionLibrary.gamesAnswers[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > gamesQuestionLibrary.gamesAnswers[random]) {
                        mPointsToRemove = (mAnswer - gamesQuestionLibrary.gamesAnswers[random]);
                    } else {
                        mPointsToRemove = (gamesQuestionLibrary.gamesAnswers[random] - mAnswer);
                    }
                } else {
                    if (mAnswer == gamesQuestionLibrary.gamesAnswersHard[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > gamesQuestionLibrary.gamesAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - gamesQuestionLibrary.gamesAnswersHard[random]);
                    } else {
                        mPointsToRemove = (gamesQuestionLibrary.gamesAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
            case 5:
                if (mAnswer == booksQuestionLibrary.booksAnswers[random]) {
                    mPointsToRemove = pointsToRemove;
                } else if (mAnswer > booksQuestionLibrary.booksAnswers[random]) {
                    mPointsToRemove = (mAnswer - booksQuestionLibrary.booksAnswers[random]);
                } else {
                    mPointsToRemove = (booksQuestionLibrary.booksAnswers[random] - mAnswer);
                }
                return mPointsToRemove;
            case 6:
                if (difficulty == 1) {
                    if (mAnswer == singlesQuestionLibrary.singlesAnswersEasy[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > singlesQuestionLibrary.singlesAnswersEasy[random]) {
                        mPointsToRemove = (mAnswer - singlesQuestionLibrary.singlesAnswersEasy[random]);
                    } else {
                        mPointsToRemove = (singlesQuestionLibrary.singlesAnswersEasy[random] - mAnswer);
                    }
                } else {
                    if (mAnswer == singlesQuestionLibrary.singlesAnswersHard[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > singlesQuestionLibrary.singlesAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - singlesQuestionLibrary.singlesAnswersHard[random]);
                    } else {
                        mPointsToRemove = (singlesQuestionLibrary.singlesAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
            case 7:
                if (difficulty == 1) {
                    if (mAnswer == albumsQuestionLibrary.albumsAnswersEasy[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > albumsQuestionLibrary.albumsAnswersEasy[random]) {
                        mPointsToRemove = (mAnswer - albumsQuestionLibrary.albumsAnswersEasy[random]);
                    } else {
                        mPointsToRemove = (albumsQuestionLibrary.albumsAnswersEasy[random] - mAnswer);
                    }
                } else {
                    if (mAnswer == albumsQuestionLibrary.albumsAnswersHard[random]) {
                        mPointsToRemove = pointsToRemove;
                    } else if (mAnswer > albumsQuestionLibrary.albumsAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - albumsQuestionLibrary.albumsAnswersHard[random]);
                    } else {
                        mPointsToRemove = (albumsQuestionLibrary.albumsAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
        }
        return 0;
    }

    public void showRandomQuestion(int random, int difficulty, int category){

        switch (category){
            case 1:
                if (difficulty == 1) {
                    List<String> historyList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.historyEasy));
                    mQuestion.setText(historyList.get(random));
                }else{
                    List<String> historyList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.historyHard));
                    mQuestion.setText(historyList.get(random));
                }
                break;
            case 2:
                List<String> inventionsList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.inventions));
                mQuestion.setText(inventionsList.get(random));
                break;
            case 3:
                if (difficulty == 1) {
                    List<String> moviesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.moviesEasy));
                    mQuestion.setText(moviesList.get(random));
                }else{
                    List<String> moviesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.moviesHard));
                    mQuestion.setText(moviesList.get(random));
                }
                break;
            case 4:
                if (difficulty == 1) {
                    List<String> gamesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.gamesEasy));
                    mQuestion.setText(gamesList.get(random));
                }else{
                    List<String> gamesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.gamesHard));
                    mQuestion.setText(gamesList.get(random));
                }
                break;
            case 5:
                List<String> booksList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.booksEasy));
                mQuestion.setText(booksList.get(random));
                break;
            case 6:
                if (difficulty == 1) {
                    List<String> singlesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.singlesEasy));
                    mQuestion.setText(singlesList.get(random));
                }else{
                    List<String> singlesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.singlesHard));
                    mQuestion.setText(singlesList.get(random));
                }
                break;
            case 7:
                if (difficulty == 1) {
                    List<String> albumsList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.albumsEasy));
                    mQuestion.setText(albumsList.get(random));
                }else{
                    List<String> albumsList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.albumsHard));
                    mQuestion.setText(albumsList.get(random));
                }
                break;

        }
    }


    @Override
    public void onBackPressed() {
    }

}

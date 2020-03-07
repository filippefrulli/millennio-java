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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class multiplayerGameActivity extends AppCompatActivity {

    Button exitButton, submitButton;

    TextView scoreText, question;
    EditText numberInput;

    //!!        DA MODIFICARE PER NUOVA CATEGORIA

    private int mAnswer;
    private int historyPoints;
    private int inventionsPoints;
    private int moviesPoints;
    private int gamesPoints;
    private int booksPoints;
    private int singlesPoints;
    private int albumsPoints;

    private int random;


    LinearLayout inputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_multiplayer_game);

        final SharedPreferences questionNumber = getSharedPreferences("questionNumber", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor numbersEditor = questionNumber.edit();

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        final SharedPreferences SP = getSharedPreferences("multiplayer", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = SP.edit();

        scoreText = findViewById(R.id.points);
        scoreText.setTypeface(bold_font);

        question = findViewById(R.id.question);
        question.setTypeface(bold_font);

        numberInput = findViewById(R.id.input);
        exitButton = findViewById(R.id.exitButton);
        inputLayout = findViewById(R.id.inputLayout);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(multiplayerGameActivity.this, aMainActivity.class));
                overridePendingTransition(0,0);
            }
        });

        submitButton = findViewById(R.id.submitButton);

        int category = SP.getInt("category", 0);

        //LEGGO CHE GIOCATORE STA GIOCANDO
        final int player = SP.getInt("Player", 0);

        switch (category){
            case 1:

                final int historyDifficulty = SP.getInt("historyDifficulty", 0);

                if (player == 1) {
                    historyPoints = SP.getInt("Points1", 0);

                    scoreText.setText("" + historyPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.blue_light));
                }else {
                    historyPoints = SP.getInt("Points2", 0);

                    scoreText.setText("" + historyPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.green_light));
                }

                if (player == 1) {

                    if(historyDifficulty == 1){
                        random = getRandomNumber(164, questionNumber, numbersEditor);
                        showRandomQuestion(random, 1, 1);
                    }else{
                        random = getRandomNumber(84, questionNumber, numbersEditor);
                        showRandomQuestion(random, 2, 1);
                    }

                }else{
                    random = SP.getInt("question", 0);
                    if(historyDifficulty == 1){
                        showRandomQuestion(random, 1, 1);
                    }else{
                        showRandomQuestion(random, 2, 1);
                    }
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

                        if (player == 1) {
                            historyPoints = SP.getInt("Points1", 0);

                            historyPoints = historyPoints - mPointsToRemove;

                            editor.putInt("question", random);
                            editor.putInt("Input1", mAnswer);
                            editor.putInt("Points1", historyPoints);
                            if(historyDifficulty == 1){
                                editor.putInt("Answer", historyQuestionLibrary.historyAnswers[random]);
                            }else{
                                editor.putInt("Answer", historyQuestionLibrary.historyAnswersHard[random]);
                            }
                            editor.putInt("toRemove1", mPointsToRemove);
                            editor.apply();
                            //APRO L'ACTIVITY DEL GIOCATORE 2
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerStartp2Activity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }else {

                            historyPoints = SP.getInt("Points2", 0);

                            historyPoints = historyPoints - mPointsToRemove;

                            editor.putInt("Input2", mAnswer);
                            editor.putInt("Points2", historyPoints);
                            if(historyDifficulty == 1){
                                editor.putInt("Answer", historyQuestionLibrary.historyAnswers[random]);
                            }else{
                                editor.putInt("Answer", historyQuestionLibrary.historyAnswersHard[random]);
                            }
                            editor.putInt("toRemove2", mPointsToRemove);
                            editor.apply();
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerAnswerActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    }
                });
                break;

            case 2:

                if (player == 1) {
                    inventionsPoints = SP.getInt("Points1", 0);

                    scoreText.setText("" + inventionsPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.blue_light));
                }else {
                    inventionsPoints = SP.getInt("Points2", 0);

                    scoreText.setText("" + inventionsPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.green_light));
                }

                if (player == 1) {
                    random = getRandomNumber(146, questionNumber, numbersEditor);
                    showRandomQuestion(random, 1, 2);
                }else {
                    random = SP.getInt("question", 0);
                    showRandomQuestion(random, 1, 2);
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
                        int mPointsToRemove = calculatePoints(random, mAnswer, -500, 1, 2);

                        inventionsPoints = inventionsPoints - mPointsToRemove;

                        if (player == 1) {
                            inventionsPoints = SP.getInt("Points1", 0);

                            inventionsPoints = inventionsPoints - mPointsToRemove;

                            editor.putInt("question", random);
                            editor.putInt("Input1", mAnswer);
                            editor.putInt("Points1", inventionsPoints);
                            editor.putInt("Answer", inventionsQuestionLibrary.inventionsAnswers[random]);
                            editor.putInt("toRemove1", mPointsToRemove);
                            editor.apply();
                            //APRO L'ACTIVITY DEL GIOCATORE 2
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerStartp2Activity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }else {
                            inventionsPoints = SP.getInt("Points2", 0);

                            inventionsPoints = inventionsPoints - mPointsToRemove;

                            editor.putInt("Input2", mAnswer);
                            editor.putInt("Points2", inventionsPoints);
                            editor.putInt("Answer", inventionsQuestionLibrary.inventionsAnswers[random]);
                            editor.putInt("toRemove2", mPointsToRemove);
                            editor.apply();
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerAnswerActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    }
                });
                break;

            case 3:

                final int moviesDifficulty = SP.getInt("moviesDifficulty", 0);

                if (player == 1) {
                    moviesPoints = SP.getInt("Points1", 0);

                    scoreText.setText("" + moviesPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.blue_light));
                }else {
                    moviesPoints = SP.getInt("Points2", 0);

                    scoreText.setText("" + moviesPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.green_light));
                }

                if (player == 1) {

                    if(moviesDifficulty == 1){
                        random = getRandomNumber(267, questionNumber, numbersEditor);
                        showRandomQuestion(random, 1, 3);
                    }else{
                        random = getRandomNumber(231, questionNumber, numbersEditor);
                        showRandomQuestion(random, 2, 3);
                    }

                }else{
                    random = SP.getInt("question", 0);
                    if(moviesDifficulty == 1){
                        showRandomQuestion(random, 1, 3);
                    }else{
                        showRandomQuestion(random, 2, 3);
                    }
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
                        int mPointsToRemove = calculatePoints(random, mAnswer, -5, moviesDifficulty, 3);

                        moviesPoints = moviesPoints - mPointsToRemove;

                        if (player == 1) {
                            moviesPoints = SP.getInt("Points1", 0);

                            moviesPoints = moviesPoints - mPointsToRemove;

                            editor.putInt("question", random);
                            editor.putInt("Input1", mAnswer);
                            editor.putInt("Points1", moviesPoints);
                            if(moviesDifficulty == 1){
                                editor.putInt("Answer", moviesQuestionLibrary.moviesAnswers[random]);
                            }else{
                                editor.putInt("Answer", moviesQuestionLibrary.moviesAnswersHard[random]);
                            }
                            editor.putInt("toRemove1", mPointsToRemove);
                            editor.apply();
                            //APRO L'ACTIVITY DEL GIOCATORE 2
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerStartp2Activity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }else {
                            moviesPoints = SP.getInt("Points2", 0);

                            moviesPoints = moviesPoints - mPointsToRemove;

                            editor.putInt("Input2", mAnswer);
                            editor.putInt("Points2", moviesPoints);
                            if(moviesDifficulty == 1){
                                editor.putInt("Answer", moviesQuestionLibrary.moviesAnswers[random]);
                            }else{
                                editor.putInt("Answer", moviesQuestionLibrary.moviesAnswersHard[random]);
                            }
                            editor.putInt("toRemove2", mPointsToRemove);
                            editor.apply();
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerAnswerActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    }
                });
                break;

            case 4:

                final int gamesDifficulty = SP.getInt("gamesDifficulty", 0);

                if (player == 1) {
                    gamesPoints = SP.getInt("Points1", 0);

                    scoreText.setText("" + gamesPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.blue_light));
                }else {
                    gamesPoints = SP.getInt("Points2", 0);

                    scoreText.setText("" + gamesPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.green_light));
                }

                if (player == 1) {

                    if(gamesDifficulty == 1){
                        random = getRandomNumber(160, questionNumber, numbersEditor);
                        showRandomQuestion(random, 1, 4);
                    }else{
                        random = getRandomNumber(170, questionNumber, numbersEditor);
                        showRandomQuestion(random, 2, 4);
                    }

                }else{
                    random = SP.getInt("question", 0);
                    if(gamesDifficulty == 1){
                        showRandomQuestion(random, 1, 4);
                    }else{
                        showRandomQuestion(random, 2, 4);
                    }
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
                        int mPointsToRemove = calculatePoints(random, mAnswer, -5, gamesDifficulty, 4);

                        gamesPoints = gamesPoints - mPointsToRemove;

                        if (player == 1) {
                            gamesPoints = SP.getInt("Points1", 0);

                            gamesPoints = gamesPoints - mPointsToRemove;

                            editor.putInt("question", random);
                            editor.putInt("Input1", mAnswer);
                            editor.putInt("Points1", gamesPoints);
                            if(gamesDifficulty == 1){
                                editor.putInt("Answer", gamesQuestionLibrary.gamesAnswers[random]);
                            }else{
                                editor.putInt("Answer", gamesQuestionLibrary.gamesAnswersHard[random]);
                            }
                            editor.putInt("toRemove1", mPointsToRemove);
                            editor.apply();
                            //APRO L'ACTIVITY DEL GIOCATORE 2
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerStartp2Activity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }else {
                            gamesPoints = SP.getInt("Points2", 0);

                            gamesPoints = gamesPoints - mPointsToRemove;

                            editor.putInt("Input2", mAnswer);
                            editor.putInt("Points2", gamesPoints);
                            if(gamesDifficulty == 1){
                                editor.putInt("Answer", gamesQuestionLibrary.gamesAnswers[random]);
                            }else{
                                editor.putInt("Answer", gamesQuestionLibrary.gamesAnswersHard[random]);
                            }
                            editor.putInt("toRemove2", mPointsToRemove);
                            editor.apply();
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerAnswerActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    }
                });
                break;
            case 5:

                if (player == 1) {
                    booksPoints = SP.getInt("Points1", 0);

                    scoreText.setText("" + booksPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.blue_light));
                }else {
                    booksPoints = SP.getInt("Points2", 0);

                    scoreText.setText("" + booksPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.green_light));
                }

                if (player == 1) {
                    random = getRandomNumber(99, questionNumber, numbersEditor);
                    showRandomQuestion(random, 1, 5);
                }else {
                    random = SP.getInt("question", 0);
                    showRandomQuestion(random, 1, 5);
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
                        int mPointsToRemove = calculatePoints(random, mAnswer, -250, 1, 5);

                        booksPoints = booksPoints - mPointsToRemove;

                        if (player == 1) {
                            booksPoints = SP.getInt("Points1", 0);

                            booksPoints = booksPoints - mPointsToRemove;

                            editor.putInt("question", random);
                            editor.putInt("Input1", mAnswer);
                            editor.putInt("Points1", booksPoints);
                            editor.putInt("Answer", booksQuestionLibrary.booksAnswers[random]);
                            editor.putInt("toRemove1", mPointsToRemove);
                            editor.apply();
                            //APRO L'ACTIVITY DEL GIOCATORE 2
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerStartp2Activity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }else {
                            booksPoints = SP.getInt("Points2", 0);

                            booksPoints = booksPoints - mPointsToRemove;

                            editor.putInt("Input2", mAnswer);
                            editor.putInt("Points2", booksPoints);
                            editor.putInt("Answer", booksQuestionLibrary.booksAnswers[random]);
                            editor.putInt("toRemove2", mPointsToRemove);
                            editor.apply();
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerAnswerActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    }
                });
                break;

            case 6:

                final int singlesDifficulty = SP.getInt("singlesDifficulty", 0);

                if (player == 1) {
                    singlesPoints = SP.getInt("Points1", 0);
                    scoreText.setText("" + singlesPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.blue_light));

                    if(singlesDifficulty == 1){
                        random = getRandomNumber(134, questionNumber, numbersEditor);
                        showRandomQuestion(random, 1, 6);
                    }else{
                        random = getRandomNumber(93, questionNumber, numbersEditor);
                        showRandomQuestion(random, 2, 6);
                    }
                }else {

                    singlesPoints = SP.getInt("Points2", 0);
                    scoreText.setText("" + singlesPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.green_light));

                    random = SP.getInt("question", 0);
                    if(singlesDifficulty == 1){
                        showRandomQuestion(random, 1, 6);
                    }else{
                        showRandomQuestion(random, 2, 6);
                    }
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

                        int mPointsToRemove;

                        //CAlCOLA I PUNTI DA RIMUOVERE
                        if(singlesDifficulty == 1){
                            mPointsToRemove = calculatePoints(random, mAnswer, -1, singlesDifficulty, 6);
                        }else{
                            mPointsToRemove = calculatePoints(random, mAnswer, -10, singlesDifficulty, 6);
                        }

                        singlesPoints = singlesPoints - mPointsToRemove;

                        if (player == 1) {
                            singlesPoints = SP.getInt("Points1", 0);

                            singlesPoints = singlesPoints - mPointsToRemove;

                            editor.putInt("question", random);
                            editor.putInt("Input1", mAnswer);
                            editor.putInt("Points1", singlesPoints);
                            if(singlesDifficulty == 1){
                                editor.putInt("Answer", singlesQuestionLibrary.singlesAnswersEasy[random]);
                            }else{
                                editor.putInt("Answer", singlesQuestionLibrary.singlesAnswersHard[random]);
                            }
                            editor.putInt("toRemove1", mPointsToRemove);
                            editor.apply();
                            //APRO L'ACTIVITY DEL GIOCATORE 2
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerStartp2Activity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }else {

                            singlesPoints = SP.getInt("Points2", 0);

                            singlesPoints = singlesPoints - mPointsToRemove;

                            editor.putInt("Input2", mAnswer);
                            editor.putInt("Points2", singlesPoints);
                            if(singlesDifficulty == 1){
                                editor.putInt("Answer", singlesQuestionLibrary.singlesAnswersEasy[random]);
                            }else{
                                editor.putInt("Answer", singlesQuestionLibrary.singlesAnswersHard[random]);
                            }
                            editor.putInt("toRemove2", mPointsToRemove);
                            editor.apply();
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerAnswerActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    }
                });
                break;

            case 7:

                final int albumsDifficulty = SP.getInt("albumsDifficulty", 0);

                if (player == 1) {
                    albumsPoints = SP.getInt("Points1", 0);

                    scoreText.setText("" + albumsPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.blue_light));
                }else {
                    albumsPoints = SP.getInt("Points2", 0);

                    scoreText.setText("" + albumsPoints);
                    scoreText.setTextColor(getResources().getColor(R.color.green_light));
                }

                if (player == 1) {

                    if(albumsDifficulty == 1){
                        random = getRandomNumber(164, questionNumber, numbersEditor);
                        showRandomQuestion(random, 1, 7);
                    }else{
                        random = getRandomNumber(84, questionNumber, numbersEditor);
                        showRandomQuestion(random, 2, 7);
                    }

                }else{
                    random = SP.getInt("question", 0);
                    if(albumsDifficulty == 1){
                        showRandomQuestion(random, 1, 7);
                    }else{
                        showRandomQuestion(random, 2, 7);
                    }
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

                        int mPointsToRemove;

                        //CAlCOLA I PUNTI DA RIMUOVERE
                        if(albumsDifficulty == 1){
                            mPointsToRemove = calculatePoints(random, mAnswer, -1, albumsDifficulty, 7);
                        }else{
                            mPointsToRemove = calculatePoints(random, mAnswer, -10, albumsDifficulty, 7);
                        }


                        albumsPoints = albumsPoints - mPointsToRemove;

                        if (player == 1) {
                            albumsPoints = SP.getInt("Points1", 0);

                            albumsPoints = albumsPoints - mPointsToRemove;

                            editor.putInt("question", random);
                            editor.putInt("Input1", mAnswer);
                            editor.putInt("Points1", albumsPoints);
                            if(albumsDifficulty == 1){
                                editor.putInt("Answer", albumsQuestionLibrary.albumsAnswersEasy[random]);
                            }else{
                                editor.putInt("Answer", albumsQuestionLibrary.albumsAnswersHard[random]);
                            }
                            editor.putInt("toRemove1", mPointsToRemove);
                            editor.apply();
                            //APRO L'ACTIVITY DEL GIOCATORE 2
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerStartp2Activity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }else {

                            albumsPoints = SP.getInt("Points2", 0);

                            albumsPoints = albumsPoints - mPointsToRemove;

                            editor.putInt("Input2", mAnswer);
                            editor.putInt("Points2", albumsPoints);
                            if(albumsDifficulty == 1){
                                editor.putInt("Answer", albumsQuestionLibrary.albumsAnswersHard[random]);
                            }else{
                                editor.putInt("Answer", albumsQuestionLibrary.albumsAnswersHard[random]);
                            }
                            editor.putInt("toRemove2", mPointsToRemove);
                            editor.apply();
                            startActivity(new Intent(multiplayerGameActivity.this, multiplayerAnswerActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                        }
                    }
                });
                break;
        }
    }

    //CAlCOLA I PUNTI DA RIMUOVERE
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
                if(mAnswer == inventionsQuestionLibrary.inventionsAnswers[random] ){
                    mPointsToRemove = pointsToRemove;
                }
                else if (mAnswer > inventionsQuestionLibrary.inventionsAnswers[random]) {
                    mPointsToRemove = (mAnswer - inventionsQuestionLibrary.inventionsAnswers[random]);
                } else {
                    mPointsToRemove = (inventionsQuestionLibrary.inventionsAnswers[random] - mAnswer);
                }
                return mPointsToRemove;
            case 3:
                if(difficulty == 1){
                    if(mAnswer == moviesQuestionLibrary.moviesAnswers[random] ){
                        mPointsToRemove = pointsToRemove;
                    }
                    else if (mAnswer > moviesQuestionLibrary.moviesAnswers[random]) {
                        mPointsToRemove = (mAnswer - moviesQuestionLibrary.moviesAnswers[random]);
                    } else {
                        mPointsToRemove = (moviesQuestionLibrary.moviesAnswers[random] - mAnswer);
                    }
                }else{
                    if(mAnswer == moviesQuestionLibrary.moviesAnswersHard[random] ){
                        mPointsToRemove = pointsToRemove;
                    }
                    else if (mAnswer > moviesQuestionLibrary.moviesAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - moviesQuestionLibrary.moviesAnswersHard[random]);
                    } else {
                        mPointsToRemove = (moviesQuestionLibrary.moviesAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
            case 4:
                if(difficulty == 1){
                    if(mAnswer == gamesQuestionLibrary.gamesAnswers[random] ){
                        mPointsToRemove = pointsToRemove;
                    }
                    else if (mAnswer > gamesQuestionLibrary.gamesAnswers[random]) {
                        mPointsToRemove = (mAnswer - gamesQuestionLibrary.gamesAnswers[random]);
                    } else {
                        mPointsToRemove = (gamesQuestionLibrary.gamesAnswers[random] - mAnswer);
                    }
                }else{
                    if(mAnswer == gamesQuestionLibrary.gamesAnswersHard[random] ){
                        mPointsToRemove = pointsToRemove;
                    }
                    else if (mAnswer > gamesQuestionLibrary.gamesAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - gamesQuestionLibrary.gamesAnswersHard[random]);
                    } else {
                        mPointsToRemove = (gamesQuestionLibrary.gamesAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
            case 5:
                if(mAnswer == booksQuestionLibrary.booksAnswers[random] ){
                    mPointsToRemove = pointsToRemove;
                }
                else if (mAnswer > booksQuestionLibrary.booksAnswers[random]) {
                    mPointsToRemove = (mAnswer - booksQuestionLibrary.booksAnswers[random]);
                } else {
                    mPointsToRemove = (booksQuestionLibrary.booksAnswers[random] - mAnswer);
                }
                return mPointsToRemove;
            case 6:
                if(difficulty == 1){
                    if(mAnswer == singlesQuestionLibrary.singlesAnswersEasy[random] ){
                        mPointsToRemove = pointsToRemove;
                    }
                    else if (mAnswer > singlesQuestionLibrary.singlesAnswersEasy[random]) {
                        mPointsToRemove = (mAnswer - singlesQuestionLibrary.singlesAnswersEasy[random]);
                    } else {
                        mPointsToRemove = (singlesQuestionLibrary.singlesAnswersEasy[random] - mAnswer);
                    }
                }else{
                    if(mAnswer == singlesQuestionLibrary.singlesAnswersHard[random] ){
                        mPointsToRemove = pointsToRemove;
                    }
                    else if (mAnswer > singlesQuestionLibrary.singlesAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - singlesQuestionLibrary.singlesAnswersHard[random]);
                    } else {
                        mPointsToRemove = (singlesQuestionLibrary.singlesAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
            case 7:
                if(difficulty == 1){
                    if(mAnswer == albumsQuestionLibrary.albumsAnswersEasy[random] ){
                        mPointsToRemove = pointsToRemove;
                    }
                    else if (mAnswer > albumsQuestionLibrary.albumsAnswersEasy[random]) {
                        mPointsToRemove = (mAnswer - albumsQuestionLibrary.albumsAnswersEasy[random]);
                    } else {
                        mPointsToRemove = (albumsQuestionLibrary.albumsAnswersEasy[random] - mAnswer);
                    }
                }else{
                    if(mAnswer == albumsQuestionLibrary.albumsAnswersHard[random] ){
                        mPointsToRemove = pointsToRemove;
                    }
                    else if (mAnswer > albumsQuestionLibrary.albumsAnswersHard[random]) {
                        mPointsToRemove = (mAnswer - albumsQuestionLibrary.albumsAnswersHard[random]);
                    } else {
                        mPointsToRemove = (albumsQuestionLibrary.albumsAnswersHard[random] - mAnswer);
                    }
                }
                return mPointsToRemove;
        }
        return 0;
    }

    public void showRandomQuestion(int random, int difficulty, int category) {

        switch (category){
            case 1:
                if (difficulty == 1) {
                    List<String> historyList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.historyEasy));
                    question.setText(historyList.get(random));
                }else{
                    List<String> historyList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.historyHard));
                    question.setText(historyList.get(random));
                }
                break;
            case 2:
                List<String> inventionsList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.inventions));
                question.setText(inventionsList.get(random));
                break;
            case 3:
                if (difficulty == 1) {
                    List<String> moviesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.moviesEasy));
                    question.setText(moviesList.get(random));
                }else{
                    List<String> moviesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.moviesHard));
                    question.setText(moviesList.get(random));
                }
                break;
            case 4:
                if (difficulty == 1) {
                    List<String> gamesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.gamesEasy));
                    question.setText(gamesList.get(random));
                }else{
                    List<String> gamesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.gamesHard));
                    question.setText(gamesList.get(random));
                }
                break;
            case 5:
                List<String> booksList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.booksEasy));
                question.setText(booksList.get(random));
                break;
            case 6:
                if (difficulty == 1) {
                    List<String> singlesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.singlesEasy));
                    question.setText(singlesList.get(random));
                }else{
                    List<String> singlesList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.singlesHard));
                    question.setText(singlesList.get(random));
                }
                break;
            case 7:
                if (difficulty == 1) {
                    List<String> albumsList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.albumsEasy));
                    question.setText(albumsList.get(random));
                }else{
                    List<String> albumsList = Arrays.asList(getBaseContext().getResources().getStringArray(R.array.albumsHard));
                    question.setText(albumsList.get(random));
                }
                break;

        }
    }

    //PRENDE UN NUMERO A CASO ALLINTERNO DI UN RANGE PASSATO ALLA FUNZIONE
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
                startActivity(new Intent(multiplayerGameActivity.this, multiplayerResultActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        }
        return randomNum;
    }

    private void showToast(){
        Toast.makeText(multiplayerGameActivity.this, "You Beat The Game", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
    }
}

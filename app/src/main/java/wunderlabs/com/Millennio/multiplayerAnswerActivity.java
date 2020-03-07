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
import android.widget.TextView;

public class multiplayerAnswerActivity extends AppCompatActivity {

    TextView player1answer, player2answer, correctAnswer, player1result, player2result, player1points, player2points;

    Button closeButton;

    int p1answerInt, p2answerInt, p1toRemoveInt, p2toRemoveInt, correctAnswerInt, bluePoints, greenPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_multiplayer_answer);

        Typeface Bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");


        final SharedPreferences SP = getSharedPreferences("multiplayer", Activity.MODE_PRIVATE);

        final SharedPreferences.Editor editor = SP.edit();


        //ASSEGNO I DUE TASTI
        closeButton = findViewById(R.id.closeButton);
        closeButton.setTypeface(Bold_font);

        bluePoints = SP.getInt("Points1", 0);
        greenPoints = SP.getInt("Points2", 0);

        p1answerInt = SP.getInt("Input1", 0);
        p2answerInt = SP.getInt("Input2", 0);

        p1toRemoveInt = SP.getInt("toRemove1", 0);
        p2toRemoveInt = SP.getInt("toRemove2", 0);

        correctAnswerInt = SP.getInt("Answer", 0);


        //ASSEGNO LE TEXTVIEW
        player1answer = findViewById(R.id.player1answer);
        player1answer.setText("" + p1answerInt);
        player1answer.setTypeface(Bold_font);

        player2answer = findViewById(R.id.player2answer);
        player2answer.setText("" + p2answerInt);
        player2answer.setTypeface(Bold_font);

        correctAnswer = findViewById(R.id.correctAnswer);
        correctAnswer.setText("" + correctAnswerInt);
        correctAnswer.setTypeface(Bold_font);

        player1result = findViewById(R.id.player1result);
        if(p1toRemoveInt < 0){
            p1toRemoveInt = -p1toRemoveInt;
            player1result.setText("+" + p1toRemoveInt);
        }else player1result.setText("-" + p1toRemoveInt);
        player1result.setTypeface(Bold_font);

        player2result = findViewById(R.id.player2result);
        if(p2toRemoveInt < 0){
            p2toRemoveInt = -p2toRemoveInt;
            player2result.setText("+" + p2toRemoveInt);
        }else player2result.setText("-" + p2toRemoveInt);
        player2result.setTypeface(Bold_font);

        player1points = findViewById(R.id.player1points);
        player1points.setText("" + bluePoints);
        player1points.setTypeface(Bold_font);

        player2points = findViewById(R.id.player2points);
        player2points.setText("" + greenPoints);
        player2points.setTypeface(Bold_font);



        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SE I PUNTI SONO <= A 0 APRO L'ACTIVITY DI GAME OVER
                if (bluePoints <= 0 && greenPoints > bluePoints){
                    editor.putInt("winner", 2);
                    editor.putInt("Round", 0);
                    editor.apply();
                    startActivity(new Intent(multiplayerAnswerActivity.this, multiplayerResultActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                }else if (greenPoints <= 0 && bluePoints > greenPoints) {
                    editor.putInt("winner", 1);
                    editor.putInt("Round", 0);
                    editor.apply();
                    startActivity(new Intent(multiplayerAnswerActivity.this, multiplayerResultActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }else if(greenPoints <= 0 && greenPoints == bluePoints){
                    editor.putInt("winner", 3);
                    editor.putInt("Round", 0);
                    editor.apply();
                    startActivity(new Intent(multiplayerAnswerActivity.this, multiplayerResultActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }else{
                    int roundInt = SP.getInt("Round", 0);
                    editor.putInt("Round", roundInt + 1);
                    editor.apply();
                    startActivity(new Intent(multiplayerAnswerActivity.this, multiplayerStartp1Activity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}

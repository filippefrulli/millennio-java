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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class multiplayerStartp1Activity extends AppCompatActivity {

    Button startButton, closeButton;

    TextView playerText, round, title, playerNumber, roundNumber;

    int roundInt;

    RelativeLayout circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_multiplayer_p1_start);

        SharedPreferences SP = getSharedPreferences("multiplayer", Activity.MODE_PRIVATE);

        roundInt = SP.getInt("Round", 0);

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        title = findViewById(R.id.title);

        circle = findViewById(R.id.circle);
        circle.setBackgroundResource(R.drawable.white_circle);

        roundNumber= findViewById(R.id.roundNumber);
        roundNumber.setTypeface(bold_font);
        roundNumber.setText(" " + roundInt);

        playerNumber= findViewById(R.id.playerNumber);
        playerNumber.setTypeface(bold_font);
        playerNumber.setText(" " + 1);

        playerText = findViewById(R.id.playerTextView);
        playerText.setTypeface(bold_font);
        playerText.setText(R.string.player);

        round = findViewById(R.id.round);
        round.setTypeface(bold_font);
        round.setText("ROUND");

        startButton = findViewById(R.id.startButton);
        startButton.setTypeface(bold_font);
        closeButton = findViewById(R.id.closeButton);
        closeButton.setTypeface(bold_font);

        int category = SP.getInt("category", 0);

        //!!        DA MODIFICARE PER NUOVA CATEGORIA
        switch (category){
            case 1:
                title.setBackgroundResource(R.drawable.storia);
                break;

            case 2:
                title.setBackgroundResource(R.drawable.invenzioni);
                break;

            case 3:
                title.setBackgroundResource(R.drawable.film);
                break;

            case 4:
                title.setBackgroundResource(R.drawable.giochi);
                break;

            case 5:
                title.setBackgroundResource(R.drawable.libri);
                break;

            case 6:
                title.getLayoutParams().width = 90;
                title.setBackgroundResource(R.drawable.singoli);
                break;

            case 7:
                title.setBackgroundResource(R.drawable.album);
                break;
        }

        //GUARDO CHE GIOCATORE E'
        final SharedPreferences.Editor editor = SP.edit();
        editor.putInt("Player", 1);
        editor.apply();


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(multiplayerStartp1Activity.this, multiplayerGameActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(multiplayerStartp1Activity.this, aMainActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}

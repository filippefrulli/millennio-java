package wunderlabs.com.Millennio;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import java.util.Locale;

import io.paperdb.Paper;


public class aMainActivity extends AppCompatActivity {

    Button singleplayerButton, multiplayerButton, shareButton, rateButton, leftButton, rightButton, infoButton;

    TextView language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.aactivity_main);

        loadLocale();

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        final SharedPreferences questionNumber = getSharedPreferences("questionNumber", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = questionNumber.edit();

        final SharedPreferences languageNumber = getSharedPreferences("languageNumber", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor languageEditor = languageNumber.edit();

        final SharedPreferences SP = getSharedPreferences("multiplayer", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editorSP = SP.edit();

        final int lang = languageNumber.getInt("languageNumber", 0);
        if(lang == 0){
            changeLang("en");
            languageEditor.putInt("languageNumber", 1);
            languageEditor.apply();
        }

        language = findViewById(R.id.language);
        language.setText(R.string.language);
        language.setTypeface(bold_font);

        initiateQuestionNumbers(editor);

        Paper.init(this);

        leftButton = findViewById(R.id.leftButton);
        leftButton.setTypeface(bold_font);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lang == 1){
                    changeLang("es");
                    languageEditor.putInt("languageNumber", 2);
                    languageEditor.apply();
                    startActivity(new Intent(aMainActivity.this, aMainActivity.class));
                    overridePendingTransition(0,0);
                }
                else if(lang == 2){
                    changeLang("it");
                    languageEditor.putInt("languageNumber", 3);
                    languageEditor.apply();
                    startActivity(new Intent(aMainActivity.this, aMainActivity.class));
                    overridePendingTransition(0,0);
                }else{
                    changeLang("en");
                    languageEditor.putInt("languageNumber", 1);
                    languageEditor.apply();
                    startActivity(new Intent(aMainActivity.this, aMainActivity.class));
                    overridePendingTransition(0,0);
                }
            }
        });

        rightButton = findViewById(R.id.rightButton);
        rightButton.setTypeface(bold_font);

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lang == 1){
                    changeLang("it");
                    languageEditor.putInt("languageNumber", 3);
                    languageEditor.apply();
                    startActivity(new Intent(aMainActivity.this, aMainActivity.class));
                    overridePendingTransition(0,0);
                }
                else if(lang == 2){
                    changeLang("en");
                    languageEditor.putInt("languageNumber", 1);
                    languageEditor.apply();
                    startActivity(new Intent(aMainActivity.this, aMainActivity.class));
                    overridePendingTransition(0,0);
                }else{
                    changeLang("es");
                    languageEditor.putInt("languageNumber", 2);
                    languageEditor.apply();
                    startActivity(new Intent(aMainActivity.this, aMainActivity.class));
                    overridePendingTransition(0,0);
                }
            }
        });


        singleplayerButton = findViewById(R.id.singleplayButton);
        singleplayerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(aMainActivity.this, singleplayerCategoryActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

        multiplayerButton = findViewById(R.id.multiplayerButton);
        multiplayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editorSP.putInt("Round", 1);
                editorSP.apply();
                startActivity(new Intent(aMainActivity.this, multiplayerCategoryActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

        singleplayerButton.setText(R.string.singlePlayer);
        multiplayerButton.setText(R.string.oneVone);

        singleplayerButton.setTypeface(bold_font);
        multiplayerButton.setTypeface(bold_font);

        rateButton = findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=wunderlabs.com.Millennio");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        shareButton = findViewById(R.id.shareButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://play.google.com/store/apps/details?id=wunderlabs.com.Millennio";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.shareTitle)));
            }
        });

        infoButton = findViewById(R.id.info);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(aMainActivity.this, tutorial1.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {}


    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.apply();
    }

    void initiateQuestionNumbers(SharedPreferences.Editor editor){
        for(int i = 0; i < 315; i++){
            String x = ("" + i);
            editor.putInt(x, 0);
            editor.apply();
        }
    }
}

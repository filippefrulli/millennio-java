package wunderlabs.com.Millennio;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splashActivity extends AppCompatActivity {

    TextView millennio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Light.ttf");

        millennio = findViewById(R.id.millennio);
        millennio.setTypeface(bold_font);
        millennio.setLetterSpacing((float) 0.3);

        Animation b = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash);
        millennio.clearAnimation();
        millennio.startAnimation(b);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(splashActivity.this, aMainActivity.class);
                startActivity(main);
                overridePendingTransition(0,0);
                finish();
            }
        }, 3000);


    }
}

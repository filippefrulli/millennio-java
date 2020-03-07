package wunderlabs.com.Millennio;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class tutorial1 extends AppCompatActivity {

    TextView zero, title, first, second, third, fourth;

    Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_tutorial1);

        Typeface bold_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        zero = findViewById(R.id.nineth);
        title = findViewById(R.id.title);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);

        zero.setTypeface(bold_font);
        title.setTypeface(bold_font);
        first.setTypeface(bold_font);
        second.setTypeface(bold_font);
        third.setTypeface(bold_font);
        fourth.setTypeface(bold_font);

        quitButton = findViewById(R.id.quitButton);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), aMainActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {}
}

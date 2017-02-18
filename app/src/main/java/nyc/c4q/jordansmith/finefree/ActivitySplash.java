package nyc.c4q.jordansmith.finefree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class ActivitySplash extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        prefs = getSharedPreferences("nyc.c4q.jordansmith.finefree", MODE_PRIVATE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("WAITING... 5s");

                String firstPlate = prefs.getString("firstplate", null);
                if (firstPlate != null) {
                    System.out.println("SECOND TIME");
                    Intent intent = new Intent(ActivitySplash.this, ActivityMain.class);
                    startActivity(intent);
                } else {
                    System.out.println("FIRST TIME");
                    Intent intent = new Intent(ActivitySplash.this, ActivityFirstPlate.class);
                    startActivity(intent);
                }
            }
        }, 5000);




    }
}

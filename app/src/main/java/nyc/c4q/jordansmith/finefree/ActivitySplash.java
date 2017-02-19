package nyc.c4q.jordansmith.finefree;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import nyc.c4q.jordansmith.finefree.model.Car;
import nyc.c4q.jordansmith.finefree.sqlite.CarDatabaseHelper;
import nyc.c4q.jordansmith.finefree.sqlite.SqlHelper;

public class ActivitySplash extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        CarDatabaseHelper helper = CarDatabaseHelper.getInstance(this);
        db = helper.getReadableDatabase();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("WAITING... 5s");

                List<Car> cars = SqlHelper.selectAllCars(db);

                if (cars.size() > 0) {
                    System.out.println("SECOND TIME");
                    Intent intent = new Intent(ActivitySplash.this, ActivityMain.class);
                    startActivity(intent);
                    finish();
                } else {
                    System.out.println("FIRST TIME");
                    Intent intent = new Intent(ActivitySplash.this, ActivityFirstPlate.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 5000);

    }
}

package nyc.c4q.jordansmith.finefree;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nyc.c4q.jordansmith.finefree.model.Car;
import nyc.c4q.jordansmith.finefree.sqlite.CarDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class ActivityFirstPlate extends AppCompatActivity {
    private EditText enterCarNameEtv;
    private EditText enterPlateEtv;
    private Button enterPlateBtn;

    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_plate);
        CarDatabaseHelper helper = CarDatabaseHelper.getInstance(this);
        db = helper.getWritableDatabase();

        enterCarNameEtv = (EditText) findViewById(R.id.etv_enter_car_name);
        enterPlateEtv = (EditText) findViewById(R.id.etv_enter_plate);
        enterPlateBtn = (Button) findViewById(R.id.btn_enter_plate);

        enterPlateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePlate();
            }
        });
    }

    private void savePlate() {
        String carName = enterCarNameEtv.getText().toString().trim();
        String plateNumber = enterPlateEtv.getText().toString().trim();

        if (TextUtils.isEmpty(carName)) {
            Toast.makeText(this, "Enter a plate number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(plateNumber)) {
            Toast.makeText(this, "Enter a plate number", Toast.LENGTH_SHORT).show();
            return;
        }

        Car car = new Car(carName, plateNumber);
        cupboard().withDatabase(db).put(car);

        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
    }
}

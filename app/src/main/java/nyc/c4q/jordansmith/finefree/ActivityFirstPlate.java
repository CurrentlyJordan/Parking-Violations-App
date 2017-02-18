package nyc.c4q.jordansmith.finefree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityFirstPlate extends AppCompatActivity {
//    @BindView(R.id.etv_enter_plate) EditText enterPlateEtv;
//    @BindView(R.id.btn_enter_plate) Button enterPlateBtn;
    private EditText enterPlateEtv;
    private Button enterPlateBtn;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_plate);

        enterPlateEtv = (EditText) findViewById(R.id.etv_enter_plate);
        enterPlateBtn = (Button) findViewById(R.id.btn_enter_plate);

//        ButterKnife.bind(this);

        prefs = getSharedPreferences("nyc.c4q.jordansmith.finefree", MODE_PRIVATE);

        enterPlateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePlate();
            }
        });

//        if (prefs.getBoolean("firstrun", true)) {
//            // Do first run stuff here then set 'firstrun' as false
//            // using the following line to edit/commit prefs
//            prefs.edit().putBoolean("firstrun", false).commit();
//        }
    }

    private void savePlate() {
        String plateNumber = enterPlateEtv.getText().toString().trim();

        if (TextUtils.isEmpty(plateNumber)) {
            Toast.makeText(this, "Enter a plate number", Toast.LENGTH_SHORT).show();
            return;
        }

        prefs.edit().putString("firstplate", plateNumber).commit();

        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
    }
}

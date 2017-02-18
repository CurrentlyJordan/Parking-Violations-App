package nyc.c4q.jordansmith.finefree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;
import nyc.c4q.jordansmith.finefree.network.parking_camera_violations.ParkingCameraViolationsClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

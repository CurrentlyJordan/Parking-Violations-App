package nyc.c4q.jordansmith.finefree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.jordansmith.finefree.models.ParkingTicketResponse;
import nyc.c4q.jordansmith.finefree.network.ParkingTicketService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by helenchan on 2/18/17.
 */

public class ViolationsActivity extends AppCompatActivity {
    List<ParkingTicketResponse> violationsList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violations);
        recyclerView = (RecyclerView) findViewById(R.id.violations_recyclerview);
        fetchViolations();
    }

    private void fetchViolations(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ParkingTicketService service = retrofit.create(ParkingTicketService.class);
        Call<List<ParkingTicketResponse>> call = service.getViolations();
        call.enqueue(new Callback<List<ParkingTicketResponse>>() {
            @Override
            public void onResponse(Call<List<ParkingTicketResponse>> call, Response<List<ParkingTicketResponse>> response) {
                violationsList = response.body();
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(new ViolationsAdapter(violationsList));
            }

            @Override
            public void onFailure(Call<List<ParkingTicketResponse>> call, Throwable t) {

            }
        });

    }
}

package nyc.c4q.jordansmith.finefree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;
import nyc.c4q.jordansmith.finefree.network.parking_camera_violations.ParkingCameraViolationsClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jordansmith on 2/18/17.
 */

public class HomeFragment extends Fragment {
    private RecyclerView violationRV;
    private SharedPreferences preferences;
    List<ParkingCameraResponse> violationsList = new ArrayList<>();
    String licensePlate = "GXE1257";
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment_layout, container, false);
        violationRV = (RecyclerView) rootView.findViewById(R.id.violations_recyclerview);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchViolations();
    }

    private void fetchViolations() {
        ParkingCameraViolationsClient
                .getInstance()
                .getResponseByPlate(licensePlate)
                .enqueue(new Callback<List<ParkingCameraResponse>>() {
                    @Override
                    public void onResponse(Call<List<ParkingCameraResponse>> call, Response<List<ParkingCameraResponse>> response) {
                        violationsList = response.body();
                        violationRV.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                        violationRV.setAdapter(new ViolationsAdapter(violationsList));

                    }

                    @Override
                    public void onFailure(Call<List<ParkingCameraResponse>> call, Throwable t) {
                        Toast.makeText(rootView.getContext(), "Unable to Download Data", Toast.LENGTH_SHORT).show();
                    }
                });
        ;
    }
}

package nyc.c4q.jordansmith.finefree;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;
import nyc.c4q.jordansmith.finefree.network.parking_camera_violations.ParkingCameraViolationsClient;
import nyc.c4q.jordansmith.finefree.recyclerview.ViolationsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jordansmith on 2/18/17.
 */

public class FragmentHome extends Fragment {
    private RecyclerView violationRV;
    private TextView ifRVIsEmpty;
    private ViolationsAdapter mViolationsAdapter = new ViolationsAdapter();
    private String licensePlate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment_layout, container, false);
        violationRV = (RecyclerView) rootView.findViewById(R.id.violations_recyclerview);
        ifRVIsEmpty = (TextView) rootView.findViewById(R.id.replace_rv_when_empty);

        Bundle bundle = getArguments();
        if(bundle != null){
            licensePlate = bundle.getString(ActivityMain.PLATE_KEY);
        }

        violationRV.setLayoutManager(new LinearLayoutManager(getContext()));
        violationRV.setAdapter(mViolationsAdapter);
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
                .getResponseByPlate(licensePlate.toUpperCase())
                .enqueue(new Callback<List<ParkingCameraResponse>>() {
                    @Override
                    public void onResponse(Call<List<ParkingCameraResponse>> call, Response<List<ParkingCameraResponse>> response) {
                        List<ParkingCameraResponse> violationsList = parseResponseForOutstandingViolations(response.body());
                        if(violationsList.isEmpty()){
                            violationRV.setVisibility(View.GONE);
                            ifRVIsEmpty.setVisibility(View.VISIBLE);
                        }
                        mViolationsAdapter.setViolationsList(violationsList);
                    }

                    @Override
                    public void onFailure(Call<List<ParkingCameraResponse>> call, Throwable t) {
                        Toast.makeText(getContext(), "Unable to Download Data", Toast.LENGTH_SHORT).show();
                    }
                });
        ;
    }

    private List<ParkingCameraResponse> parseResponseForOutstandingViolations(List<ParkingCameraResponse> body) {
        List<ParkingCameraResponse> responseViolations = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            ParkingCameraResponse response = body.get(i);
            if(response.getAmountDue() != 0){
                responseViolations.add(response);
            }
        }
        return responseViolations;
    }
}

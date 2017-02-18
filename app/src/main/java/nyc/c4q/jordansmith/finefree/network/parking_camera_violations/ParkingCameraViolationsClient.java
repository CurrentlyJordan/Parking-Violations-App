package nyc.c4q.jordansmith.finefree.network.parking_camera_violations;

import java.util.List;

import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;
import nyc.c4q.jordansmith.finefree.network.NetworkServices;
import retrofit2.Call;

/**
 * Created by andresarango on 2/18/17.
 */

public class ParkingCameraViolationsClient {

    private static final String BASE_URL = "https://data.cityofnewyork.us";

    private final ParkingCameraViolationsService apiService;
    private static volatile ParkingCameraViolationsClient instance;


    private ParkingCameraViolationsClient() {
        apiService = (new NetworkServices()).getJSONService(BASE_URL, ParkingCameraViolationsService.class);
    }

    public static ParkingCameraViolationsClient getInstance() {
        if (instance == null) {
            synchronized (ParkingCameraViolationsClient.class) {
                if (instance == null) {
                    instance = new ParkingCameraViolationsClient();
                }
            }
        }
        return instance;
    }

    public Call<List<ParkingCameraResponse>> getResponse() {
        return apiService.getAllViolations();
    }

    public Call<List<ParkingCameraResponse>> getResponseByPlate(String plate) {
        return apiService.getViolationsByPlate(plate);
    }


}

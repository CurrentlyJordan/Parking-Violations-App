package nyc.c4q.jordansmith.finefree.network.new_york_311;

import nyc.c4q.jordansmith.finefree.model.model311.Response311;
import nyc.c4q.jordansmith.finefree.network.NetworkServices;
import nyc.c4q.jordansmith.finefree.network.parking_camera_violations.ParkingCameraViolationsClient;
import retrofit2.Call;

/**
 * Created by jordansmith on 2/19/17.
 */

public class NewYork311Client {

    private final String BASE_URL = "https://api.cityofnewyork.us";
    private final NewYork311Service apiService;
    private static volatile NewYork311Client instance;

    private NewYork311Client() {
        apiService = (new NetworkServices()).getJSONService(BASE_URL, NewYork311Service.class);
    }


    public static NewYork311Client getInstance() {
        if (instance == null) {
            synchronized (ParkingCameraViolationsClient.class) {
                if (instance == null) {
                    instance = new NewYork311Client();
                }
            }
        }
        return instance;
    }


    public Call<Response311> get311Response(String apiId, String apiKey) {
        return apiService.getAlternateSideParkingStatus(apiId,apiKey);
    }









}

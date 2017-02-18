package nyc.c4q.jordansmith.finefree.network.parking_camera_violations;

import java.util.List;

import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andresarango on 2/18/17.
 */
public interface ParkingCameraViolationsService {
    final String JSON_PATH = "/resource/uvbq-3m68.json";

    @GET(JSON_PATH)
    Call<List<ParkingCameraResponse>> getAllViolations();

    @GET(JSON_PATH)
    Call<List<ParkingCameraResponse>> getViolationsByPlate(@Query("plate") String plate);
}

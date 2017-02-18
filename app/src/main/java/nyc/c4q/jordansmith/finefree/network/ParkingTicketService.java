package nyc.c4q.jordansmith.finefree.network;

import java.util.List;

import nyc.c4q.jordansmith.finefree.models.ParkingTicketResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by helenchan on 2/18/17.
 * https://data.cityofnewyork.us/resource/uvbq-3m68.json
 */

public interface ParkingTicketService {

    @GET("resource/uvbq-3m68.json")
    Call<List<ParkingTicketResponse>> getViolations();
}

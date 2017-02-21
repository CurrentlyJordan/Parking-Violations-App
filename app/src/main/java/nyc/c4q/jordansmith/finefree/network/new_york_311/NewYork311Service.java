package nyc.c4q.jordansmith.finefree.network.new_york_311;

import nyc.c4q.jordansmith.finefree.model.model311.Response311;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jordansmith on 2/20/17.
 */

public interface NewYork311Service {

    @GET("https://api.cityofnewyork.us/311/v1/municipalservices")
    Call<Response311> getAlternateSideParkingStatus(@Query("app_id") String appId,
                                                    @Query("app_key") String appKey);
}

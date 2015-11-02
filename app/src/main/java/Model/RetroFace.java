package Model;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface RetroFace {

    @GET ("/maps/api/place/textsearch/json?&sensor=true&location=51.508315,-0.123013&radius=50000&key=AIzaSyDpx-qOM4MKqkU-VLn8TDgBGIQ9o64_GlU")
    Call <RetrofitModel> loadApi (@Query("query") String query);
}

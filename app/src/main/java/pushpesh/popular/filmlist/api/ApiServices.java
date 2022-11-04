package pushpesh.popular.filmlist.api;

import java.util.HashMap;

import io.reactivex.Single;
import pushpesh.popular.filmlist.common.Constant;
import pushpesh.popular.filmlist.model.ResultResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiServices {
    @Headers("Content-Type: application/json")
    // To get Popular Film List
    @GET("3/movie/popular?api_key=" + Constant.API_KEY)
    Single<ResultResponse> getFilmList();
}

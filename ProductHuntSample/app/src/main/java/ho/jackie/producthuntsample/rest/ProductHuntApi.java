package ho.jackie.producthuntsample.rest;

import ho.jackie.producthuntsample.model.ProductHuntResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by JHADI on 8/19/16.
 */
public interface ProductHuntApi {

    public static final String BASE_URL = "https://api.producthunt.com/";

    @GET("v1/posts")
    Observable<ProductHuntResult> getTodaysAndroidPosts(@Query("day") String todaysDate,
                                                        @Query("search[topic]") String topic);

}

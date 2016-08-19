package ho.jackie.producthuntsample.mvp;

import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ho.jackie.producthuntsample.BuildConfig;
import ho.jackie.producthuntsample.model.ProductHuntResult;
import ho.jackie.producthuntsample.rest.ProductHuntApi;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JHADI on 8/19/16.
 */
public class TodayPostPresenter implements TodayPostContract.Presenter
{
    public static final String TAG = TodayPostPresenter.class.getSimpleName();
    private TodayPostContract.View mView;
    private Retrofit mRetrofit;
    private ProductHuntResult savedData;


    public TodayPostPresenter(){
        mRetrofit = buildRetrofit();

    }
    @Override
    public void bindView(TodayPostContract.View view) {
        this.mView = view;
        if (savedData != null){
            mView.showPosts(savedData.getPosts());
        } else {
            loadData();
        }
    }

    @Override
    public void unBindView() {
        mView = null;
    }

    public void loadData() {
        ProductHuntApi productHuntApi = mRetrofit.create(ProductHuntApi.class);

        productHuntApi.getTodaysAndroidPosts(getTodaysDate(), "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductHuntResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        mView.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(ProductHuntResult productHuntResult) {
                        Log.i(TAG, "Size of list is "+ productHuntResult.getPosts().size());
                        if(productHuntResult.getPosts().size()<= 0){
                           onError(new Throwable("No Android posts for today"));
                        }
                        if (mView != null){
                            mView.showPosts(productHuntResult.getPosts());
                        }
                        savedData = productHuntResult;
                    }
                });
    }

    private String getTodaysDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date currentDate = new Date();
        return sdf.format(currentDate);
    }

    private Retrofit buildRetrofit(){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttp())
                .baseUrl(ProductHuntApi.BASE_URL)
                .build();
    }

    private OkHttpClient buildOkHttp(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer "+ BuildConfig.PRODUCT_HUNT_TOKEN)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        builder.addInterceptor(loggingInterceptor);
        return builder.build();

    }
}

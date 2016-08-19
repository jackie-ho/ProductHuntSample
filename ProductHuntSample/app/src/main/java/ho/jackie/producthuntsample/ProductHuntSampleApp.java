package ho.jackie.producthuntsample;

import android.app.Application;

import ho.jackie.producthuntsample.mvp.TodayPostPresenter;

/**
 * Created by JHADI on 8/19/16.
 */
public class ProductHuntSampleApp extends Application {

    TodayPostPresenter todayPostPresenter;
    @Override
    public void onCreate() {
        super.onCreate();

        todayPostPresenter = new TodayPostPresenter();
    }

    public TodayPostPresenter getTodayPostPresenter(){
        return todayPostPresenter;
    }
}

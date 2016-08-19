package ho.jackie.producthuntsample.mvp;

import java.util.List;

import ho.jackie.producthuntsample.model.ProductHuntPost;

/**
 * Created by JHADI on 8/19/16.
 */
public interface TodayPostContract {
    interface Presenter {
        void bindView(TodayPostContract.View view);
        void unBindView();
    }

    interface View {
        void showError(String error);
        void showPosts(List<ProductHuntPost> posts);
    }

}

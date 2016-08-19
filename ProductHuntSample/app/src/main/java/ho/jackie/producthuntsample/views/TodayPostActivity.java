package ho.jackie.producthuntsample.views;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ho.jackie.producthuntsample.ProductHuntSampleApp;
import ho.jackie.producthuntsample.R;
import ho.jackie.producthuntsample.model.PostRecyclerAdapter;
import ho.jackie.producthuntsample.model.ProductHuntPost;
import ho.jackie.producthuntsample.mvp.TodayPostContract;
import ho.jackie.producthuntsample.mvp.TodayPostPresenter;

/**
 * Created by JHADI on 8/19/16.
 */
public class TodayPostActivity extends AppCompatActivity implements TodayPostContract.View{

    private TodayPostPresenter mPresenter;
    private RecyclerView mRecycler;
    private TextView mErrorText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = ((ProductHuntSampleApp)getApplicationContext()).getTodayPostPresenter();
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.bindView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unBindView();
    }

    @Override
    public void showError(String error) {
        if (error != null && error.length() > 0){
            mErrorText.setText(error);
        }
        mErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPosts(List<ProductHuntPost> posts) {
        PostRecyclerAdapter adapter = new PostRecyclerAdapter(this, posts);
        mRecycler.setAdapter(adapter);
    }

    private void initViews(){
        mRecycler = (RecyclerView)findViewById(R.id.today_post_recyclerlist);
        mErrorText = (TextView)findViewById(R.id.error_text);
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(llm);
    }
}

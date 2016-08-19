package ho.jackie.producthuntsample.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ho.jackie.producthuntsample.R;

/**
 * Created by JHADI on 8/19/16.
 */
public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder> {

    private List<ProductHuntPost> mPosts;
    private final Context mContext;

    public PostRecyclerAdapter(Context context, List<ProductHuntPost> postList){
        this.mContext = context;
        this.mPosts = postList;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_post,parent,false);
        PostViewHolder pvh = new PostViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        ProductHuntPost post = mPosts.get(position);
        holder.postLink.setText(post.getDiscussionUrl());
        holder.postTitle.setText(post.getName());
        holder.upvoteText.setText(post.getVotes());
        Picasso.with(mContext).load(post.getThumbnailUrl().getImageUrl()).fit().into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{

        TextView upvoteText;
        ImageView postImage;
        TextView postTitle;
        TextView postLink;

        public PostViewHolder(View itemView) {
            super(itemView);
            upvoteText = (TextView)itemView.findViewById(R.id.post_upvote);
            postImage = (ImageView)itemView.findViewById(R.id.post_image);
            postTitle = (TextView)itemView.findViewById(R.id.post_title);
            postLink = (TextView)itemView.findViewById(R.id.post_link);
        }
    }
}

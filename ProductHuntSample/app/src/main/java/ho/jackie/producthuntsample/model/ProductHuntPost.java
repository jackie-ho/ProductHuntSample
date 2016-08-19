package ho.jackie.producthuntsample.model;

/**
 * Created by JH on 8/19/16.
 */
public class ProductHuntPost {

    private int votes_count;
    private String name;
    private ProductHuntThumbnail thumbnail;
    private String discussion_url;
    private String redirect_url;

    public String getVotes() {
        return String.valueOf(votes_count);
    }

    public String getName() {
        return name;
    }

    public ProductHuntThumbnail getThumbnailUrl() {
        return thumbnail;
    }

    public String getDiscussionUrl() {
        return discussion_url;
    }

    public String getRedirectUrl() {
        return redirect_url;
    }
}

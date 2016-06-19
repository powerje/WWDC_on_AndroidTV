package lepoer.com.wwtv;

import java.util.List;

/**
 * Created by jep on 2/28/16.
 */
public class Track {
    public String name;
    public List<Movie> videos;

    public String toString() {
        return name + " contains " + videos.size() + " videos";
    }
}

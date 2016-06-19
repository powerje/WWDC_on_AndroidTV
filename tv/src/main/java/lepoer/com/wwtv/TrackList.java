package lepoer.com.wwtv;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jep on 2/28/16.
 */
public class TrackList {
    private static List<Track> tracks;

    public static List<Track> getConferenceList() {
        return tracks;
    }

    private static List<Movie> allMovies;
    public static List<Movie> allMovies() {
        if (allMovies == null) {
            allMovies = new ArrayList<>();
            for (Track c : getConferenceList()) {
                allMovies.addAll(c.videos);
            }
        }

        return allMovies;
    }

    /**
     * Asynchronously load tracks from a jsonString. Once done calls to
     * getConferenceList will return the list of tracks.
     * @param jsonString
     * @return
     */
    public static Observable<List<Track>> conferences(final String jsonString) {
        return Observable.create(new Observable.OnSubscribe<List<Track>>() {
            @Override
            public void call(Subscriber<? super List<Track>> subscriber) {
                Moshi moshi = new Moshi.Builder().build();
                Type listMyData = Types.newParameterizedType(List.class, Track.class);
                JsonAdapter<List<Track>> adapter = moshi.adapter(listMyData);
                try {
                    List<Track> tracks = adapter.fromJson(jsonString);
                    TrackList.tracks = tracks;
                    subscriber.onNext(tracks);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}

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
public class ConferenceList {
    private static List<Conference> conferences;

    public static List<Conference> getConferenceList() {
        return conferences;
    }

    private static List<Movie> allMovies;
    public static List<Movie> allMovies() {
        if (allMovies == null) {
            allMovies = new ArrayList<>();
            for (Conference c : getConferenceList()) {
                allMovies.addAll(c.videos);
            }
        }

        return allMovies;
    }

    /**
     * Asynchronously load conferences from a jsonString. Once done calls to
     * getConferenceList will return the list of conferences.
     * @param jsonString
     * @return
     */
    public static Observable<List<Conference>> conferences(final String jsonString) {
        return Observable.create(new Observable.OnSubscribe<List<Conference>>() {
            @Override
            public void call(Subscriber<? super List<Conference>> subscriber) {
                Moshi moshi = new Moshi.Builder().build();
                Type listMyData = Types.newParameterizedType(List.class, Conference.class);
                JsonAdapter<List<Conference>> adapter = moshi.adapter(listMyData);
                try {
                    List<Conference> conferences = adapter.fromJson(jsonString);
                    ConferenceList.conferences = conferences;
                    subscriber.onNext(conferences);
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

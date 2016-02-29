package lepoer.com.wwtv;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jep on 2/28/16.
 */
public class AssetReader {
    public static String stringFromFile(String filename, AssetManager manager) {
        StringBuilder buf = new StringBuilder();
        try {
            InputStream json = manager.open(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();

            return buf.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String conferenceJson(AssetManager assetManager) {
        return stringFromFile("android_videos.json", assetManager);
    }
}

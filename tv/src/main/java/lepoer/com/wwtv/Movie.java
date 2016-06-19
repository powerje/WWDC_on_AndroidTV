/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package lepoer.com.wwtv;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

/*
 * Movie class represents video entity with title, description, image thumbs and video url.
 *
 */
public class Movie implements Serializable {
    static final long serialVersionUID = 727566175075960653L;
    private static long count = 0;
    private long id;
    private String title;
    private String description;
    private String download_hd;
    private String location;
    private String track;

    public Movie() {
    }

    public static long getCount() {
        return count;
    }

    public static void incCount() {
        count++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDownload_hd() {
        return download_hd;
    }

    public void setDownload_hd(String download_hd) {
        this.download_hd = download_hd;
    }

    public String getBackgroundImageUrl() {
        return "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg";
    }

    public void setBackgroundImageUrl(String bgImageUrl) {

    }

    public String getCardImageUrl() {
        return "";
    }

    public void setCardImageUrl(String cardImageUrl) {
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public URI getBackgroundImageURI() {
        try {
            return new URI(getBackgroundImageUrl());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public URI getCardImageURI() {
        try {
            return new URI(getCardImageUrl());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", download_hd='" + download_hd + '\'' +
//                ", backgroundImageUrl='" + bgImageUrl + '\'' +
                ", backgroundImageURI='" + getBackgroundImageURI().toString() + '\'' +
//                ", cardImageUrl='" + cardImageUrl + '\'' +
                '}';
    }
}

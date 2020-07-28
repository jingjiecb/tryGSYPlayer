package com.example.trygsyvideoplayer.Pojo;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("_id")
    public String id;
    @SerializedName("feedurl")
    public String videoUrl;
    @SerializedName("nickname")
    public String author;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public int stars;
    @SerializedName("avatar")
    public String pictureUrl;

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", stars=" + stars +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}



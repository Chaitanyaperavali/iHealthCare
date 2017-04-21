
package com.ase.team22.ihealthcare.jsonmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("review_count")
    @Expose
    private int reviewCount;
    @SerializedName("image_url_small")
    @Expose
    private String imageUrlSmall;
    @SerializedName("image_url_small_2x")
    @Expose
    private String imageUrlSmall2x;
    @SerializedName("image_url_large")
    @Expose
    private String imageUrlLarge;
    @SerializedName("image_url_large_2x")
    @Expose
    private String imageUrlLarge2x;

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
    }

    public String getImageUrlSmall2x() {
        return imageUrlSmall2x;
    }

    public void setImageUrlSmall2x(String imageUrlSmall2x) {
        this.imageUrlSmall2x = imageUrlSmall2x;
    }

    public String getImageUrlLarge() {
        return imageUrlLarge;
    }

    public void setImageUrlLarge(String imageUrlLarge) {
        this.imageUrlLarge = imageUrlLarge;
    }

    public String getImageUrlLarge2x() {
        return imageUrlLarge2x;
    }

    public void setImageUrlLarge2x(String imageUrlLarge2x) {
        this.imageUrlLarge2x = imageUrlLarge2x;
    }

}

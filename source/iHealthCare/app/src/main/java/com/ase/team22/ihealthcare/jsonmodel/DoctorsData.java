
package com.ase.team22.ihealthcare.jsonmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorsData {

    @SerializedName("practices")
    @Expose
    private List<Doctor> doctors = null;
    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("ratings")
    @Expose
    private List<Rating> ratings = null;
    @SerializedName("specialties")
    @Expose
    private List<Specialty> specialties = null;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("npi")
    @Expose
    private String npi;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

}

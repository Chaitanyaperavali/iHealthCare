
package com.ase.team22.ihealthcare.jsonmodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor implements Serializable{

    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("lon")
    @Expose
    private float lon;
    @SerializedName("uid")
    @Expose
    private String uid;// dont use this
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("visit_address")
    @Expose
    private VisitAddress visitAddress;
    @SerializedName("office_hours")
    @Expose
    private List<Object> officeHours = null;
    @SerializedName("phones")
    @Expose
    private List<Phone> phones = null;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;
    @SerializedName("location_slug")
    @Expose
    private String locationSlug;
    @SerializedName("within_search_area")
    @Expose
    private boolean withinSearchArea;
    @SerializedName("distance")
    @Expose
    private float distance;
    @SerializedName("accepts_new_patients")
    @Expose
    private boolean acceptsNewPatients;
    @SerializedName("insurance_uids")
    @Expose
    private List<String> insuranceUids = null;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VisitAddress getVisitAddress() {
        return visitAddress;
    }

    public void setVisitAddress(VisitAddress visitAddress) {
        this.visitAddress = visitAddress;
    }

    public List<Object> getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(List<Object> officeHours) {
        this.officeHours = officeHours;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getLocationSlug() {
        return locationSlug;
    }

    public void setLocationSlug(String locationSlug) {
        this.locationSlug = locationSlug;
    }

    public boolean isWithinSearchArea() {
        return withinSearchArea;
    }

    public void setWithinSearchArea(boolean withinSearchArea) {
        this.withinSearchArea = withinSearchArea;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public boolean isAcceptsNewPatients() {
        return acceptsNewPatients;
    }

    public void setAcceptsNewPatients(boolean acceptsNewPatients) {
        this.acceptsNewPatients = acceptsNewPatients;
    }

    public List<String> getInsuranceUids() {
        return insuranceUids;
    }

    public void setInsuranceUids(List<String> insuranceUids) {
        this.insuranceUids = insuranceUids;
    }

}

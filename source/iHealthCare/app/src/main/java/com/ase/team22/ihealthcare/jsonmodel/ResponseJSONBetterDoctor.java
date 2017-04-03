
package com.ase.team22.ihealthcare.jsonmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseJSONBetterDoctor {

    @SerializedName("data")
    @Expose
    private List<DoctorsData> data = null;

    public List<DoctorsData> getData() {
        return data;
    }

    public void setData(List<DoctorsData> data) {
        this.data = data;
    }

}

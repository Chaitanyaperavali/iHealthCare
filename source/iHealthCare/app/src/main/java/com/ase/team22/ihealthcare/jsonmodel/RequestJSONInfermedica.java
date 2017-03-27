
package com.ase.team22.ihealthcare.jsonmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestJSONInfermedica {

    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("age")
    @Expose
    private int age;
    @SerializedName("evidence")
    @Expose
    private List<Condition> evidence = null;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Condition> getCondition() {
        return evidence;
    }

    public void setCondition(List<Condition> evidence) {
        this.evidence = evidence;
    }

}

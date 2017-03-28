
package com.ase.team22.ihealthcare.jsonmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseJSONInfermedica {

    @SerializedName("question")
    @Expose
    private Question question;
    @SerializedName("conditions")
    @Expose
    private List<ResponseCondition> conditions = null;
    @SerializedName("extras")
    @Expose
    private Extras_ extras;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<ResponseCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<ResponseCondition> conditions) {
        this.conditions = conditions;
    }

    public Extras_ getExtras() {
        return extras;
    }

    public void setExtras(Extras_ extras) {
        this.extras = extras;
    }

}

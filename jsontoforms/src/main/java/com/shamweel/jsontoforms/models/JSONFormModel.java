package com.shamweel.jsontoforms.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONFormModel {
    @SerializedName("form_no")
    @Expose
    private Integer formNo;
    @SerializedName("data")
    @Expose
    private List<JSONModel> data = null;

    public Integer getFormNo() {
        return formNo;
    }

    public void setFormNo(Integer formNo) {
        this.formNo = formNo;
    }

    public List<JSONModel> getData() {
        return data;
    }

    public void setData(List<JSONModel> data) {
        this.data = data;
    }
}

package com.aman.contacttask.models;

import com.aman.contacttask.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleUserResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("support")
    @Expose
    private Support support;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

}
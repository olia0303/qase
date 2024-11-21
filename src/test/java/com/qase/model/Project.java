package com.qase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    @Expose
    @SerializedName("title")
    private String name;
    @Expose
    @SerializedName("code")
    private String code;
    @Expose
    @SerializedName("description")
    private String description;
}

package com.qase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Suite {
    @Expose
    @SerializedName("title")
    private String suiteName;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("preconditions")
    private String preconditions;
    @Expose
    @SerializedName("id")
    private int suiteId;
}

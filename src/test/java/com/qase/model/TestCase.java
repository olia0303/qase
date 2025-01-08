package com.qase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCase {
    @Expose
    @SerializedName("id")
    private int caseId;
    @Expose
    @SerializedName("title")
    private String title;
    private String status;
    private String type;

    private String severity;
    private String priority;
    private String layer;
    private String isFlaky;
    private String behavior;
    private String automationStatus;
    @Expose
    @SerializedName("suite_id")
    private int suiteId;
    @Expose
    @SerializedName("severity")
    private int severityApi;
    @Expose
    @SerializedName("priority")
    private int priorityApi;
    @Expose
    @SerializedName("behavior")
    private int behaviorApi;
    @Expose
    @SerializedName("type")
    private int typeApi;
    @Expose
    @SerializedName("layer")
    private int layerApi;
    @Expose
    @SerializedName("is_flaky")
    private int isFlakyApi;

    @Expose
    @SerializedName("automation")
    private int automation;
    @Expose
    @SerializedName("status")
    private int statusApi;
}

package com.qase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCase {
    private String title;
    private String status;
    private String type;
    private String severity;
    private String priority;
    private String layer;
    private String isFlaky;
    private String behavior;
    private String automationStatus;
}

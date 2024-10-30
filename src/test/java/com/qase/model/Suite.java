package com.qase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Suite {
    private String suiteName;
    private String description;
    private String preconditions;
}

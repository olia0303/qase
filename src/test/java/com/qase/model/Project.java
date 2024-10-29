package com.qase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    private String name;
    private String code;
    private String description;
}

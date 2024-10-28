package com.qase.model;

import com.qase.other.TestData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectFactory {
    public static Project getProject() {
        return Project.builder()
                .name(new TestData().PROJECT_NAME)
                .code(new TestData().PROJECT_CODE)
                .description("bbbggggg")
                .build();
    }
}

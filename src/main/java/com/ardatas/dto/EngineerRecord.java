package com.ardatas.dto;

import jakarta.validation.constraints.NotBlank;
import org.jspecify.annotations.NonNull;
import java.util.List;

public record EngineerRecord(
        @NonNull
        Integer id,
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Tech stack is mandatory")
        String techStack,
        List<ProjectRecord> projects) {};



package com.ardatas.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CreateProjectRecord(
        @NotBlank(message = "Project name is mandatory")
        String projectName,

        @NotNull(message = "Date is mandatory")
        LocalDate startDate
) {}
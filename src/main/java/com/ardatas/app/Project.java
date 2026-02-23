package com.ardatas.app;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Project name is mandatory")
    private String projectName;
    @NotNull(message = "Date is mandatory")
    private LocalDate startDate;

    @Setter
    @ManyToOne
    @JoinColumn(name ="engineer_id", nullable = false)
    private Engineer engineer;

    public Project(String projectName, LocalDate startDate) {
        this.projectName = projectName;
        this.startDate = startDate;
    }
}

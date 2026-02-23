package com.ardatas.app;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Project name is mandatory")
    private String projectName;
    @NotNull(message = "Date is mandatory")
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name ="engineer_id", nullable = false)
    private Engineer engineer;

    protected Project() {}

    public Project(String projectName, LocalDate startDate) {
        this.projectName = projectName;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }
}

package com.ardatas.app;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// A JPA entity is a database row that is mapped to a java object,
// Every instance of the entity repesents a row int the table

// Entities are passed directly to the api,
// instead i need to use java records as DTOs

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Engineer {

    // GENERATE AUTOMATIC ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // prevent lombok from creating a setter for id
    private Integer id;

    // VALIDATION WITH NOT BLANK
    // DEPENDENCIES FOR VALIDATION: H2 SQL, SP Starter Validation (Bean)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Tech stack is mandatory")
    private String techStack;

    // defines one engineer -> many projects relationship
    @Size(max=10)
    @OneToMany(mappedBy =  "engineer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    public Engineer(String name, String techStack) {
        this.name = name;
        this.techStack = techStack;
    }

    public void addProject(Project project) {
        projects.add(project);
        project.setEngineer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Engineer engineer = (Engineer) o;
        return Objects.equals(id, engineer.id) && Objects.equals(name, engineer.name) && Objects.equals(techStack, engineer.techStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, techStack);
    }
}

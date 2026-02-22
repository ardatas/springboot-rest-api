package com.ardatas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;


// A JPA entity is a database row that is mapped to a java object,
// Every instance of the entity repesents a row int the table
@Entity
public class Engineer {

    // GENERATE AUTOMATIC ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //this is a row
    private Integer id;
    // this is a row
    private String name;
    private String techStack;


    protected Engineer() {}

    public Engineer (String name,
                    String techStack) {
        this.name = name;
        this.techStack = techStack;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
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

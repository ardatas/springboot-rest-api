package com.ardatas.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("""
        SELECT p FROM Project p
        WHERE p.id = :projectId
                AND p.engineer.id = :engineerId        
            """)
    Optional<Project> findProjectById(Integer engineerId, Integer projectId);

}

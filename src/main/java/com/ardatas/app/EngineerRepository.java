package com.ardatas.app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EngineerRepository extends JpaRepository<Engineer, Integer> {

    Optional<Engineer> findTopByOrderByIdAsc();

}

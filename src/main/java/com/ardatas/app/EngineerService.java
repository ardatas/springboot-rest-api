package com.ardatas.app;

import com.ardatas.exception.EngineerNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.catalina.Engine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineerService {

    private final EngineerRepository engineerRepository;

    public EngineerService(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    public List<Engineer> getEngineers() {
        return engineerRepository.findAll();
    }

    public Engineer addEngineer(Engineer engineer) {
        return engineerRepository.save(engineer);
    }


    // Excpetion handling is necessary
    public Engineer getOneEngineer(Integer id) {
        return engineerRepository.findById(id)
                .orElseThrow(() -> new EngineerNotFoundException("Engineer not found!"));
    }

    public void deleteEngineerById(Integer id) {

        Engineer engineer = engineerRepository.findById(id)
                        .orElseThrow(() -> new EngineerNotFoundException("Engineer with id " + id + " is not found!"));

        engineerRepository.delete(engineer);

    }

    @Transactional
    public void deleteEngineerFromTop() {
        engineerRepository.findTopByOrderByIdAsc()
                .ifPresent(engineerRepository::delete);
    }

}

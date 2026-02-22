package com.ardatas;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        engineerRepository.deleteById(id);
    }

    @Transactional
    public void deleteEngineerFromTop() {
        engineerRepository.findTopByOrderByIdAsc()
                .ifPresent(engineerRepository::delete);
    }

}

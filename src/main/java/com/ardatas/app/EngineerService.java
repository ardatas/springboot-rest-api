package com.ardatas.app;

import com.ardatas.dto.CreateEngineerRecord;
import com.ardatas.dto.EngineerRecord;
import com.ardatas.exception.EngineerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// BUSINESS LOGIC OF THE APPLICATION
public class EngineerService {

    private final EngineerRepository engineerRepository;

    public EngineerService(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    private static EngineerRecord convertToRecord(Engineer engineer) {
        return new EngineerRecord(engineer.getId(), engineer.getName(), engineer.getTechStack());
    }

    public List<EngineerRecord> getEngineers() {
        return engineerRepository.findAll()
        .stream()
        .map(EngineerService::convertToRecord)
        .toList();
    }    

    public EngineerRecord addEngineer(CreateEngineerRecord create) {
        Engineer entity = new Engineer(create.name(), create.techStack());
        Engineer saved = engineerRepository.save(entity);
        return convertToRecord(saved);
    }

    public EngineerRecord getOneEngineer(Integer id) {
        Engineer engineer = engineerRepository.findById(id)
                .orElseThrow(() -> new EngineerNotFoundException("Engineer not found!"));
        return convertToRecord(engineer);
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

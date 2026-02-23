package com.ardatas.app;

import com.ardatas.dto.*;
import com.ardatas.exception.EngineerNotFoundException;
import com.ardatas.exception.ProjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// BUSINESS LOGIC OF THE APPLICATION
public class EngineerService {

    private final EngineerRepository engineerRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public EngineerService(EngineerRepository engineerRepository, ProjectRepository projectRepository) {
        this.engineerRepository = engineerRepository;
        this.projectRepository = projectRepository;
    }

    private static EngineerRecord convertToRecord(Engineer engineer) {
        List<ProjectRecord> projectRecordList = engineer.getProjects().stream()
                .map(p -> new ProjectRecord(p.getId(), p.getProjectName(), p.getStartDate(), p.getEngineer().getId()))
                .toList();

        return new EngineerRecord(engineer.getId(), engineer.getName(), engineer.getTechStack(), projectRecordList);
    }

    private static ProjectRecord convertToRecord(Project project) {
        return new ProjectRecord(project.getId(), project.getProjectName(), project.getStartDate(), project.getEngineer().getId());
    }

    public Page<EngineerRecord> getEngineers(Pageable pageable) {
        Page<Engineer> page = engineerRepository.findAll(pageable);
        // static method stream
        return page.map(EngineerService::convertToRecord);
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

    public EngineerRecord updateEngineer(Integer id, UpdateEngineerRecord update) {
        Engineer engineer = engineerRepository.findById(id)
                .orElseThrow(() -> new EngineerNotFoundException("Engineer with id " + id + " is not found!"));

        if(update.name() != null) {
            engineer.setName(update.name());
        }

        if(update.techStack() != null) {
            engineer.setTechStack(update.techStack());
        }

        engineerRepository.save(engineer);
        return convertToRecord(engineer);
    }


    // PROJECT METHODS

    public ProjectRecord addProject(Integer id, CreateProjectRecord create) {
        Engineer engineer = engineerRepository.findById(id)
                .orElseThrow(() -> new EngineerNotFoundException("Engineer with id " + id + " is not found!"));

        Project entity = new Project(create.projectName(), create.startDate());
        engineer.addProject(entity);
        engineerRepository.save(engineer);

        return convertToRecord(entity);

    }

    // Can and should work with entities in the service !
    public void deleteProjectById(Integer engineerId, Integer projectId) {

        Project project = projectRepository.findProjectById(engineerId, projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found!"));

        projectRepository.delete(project);
    }
}

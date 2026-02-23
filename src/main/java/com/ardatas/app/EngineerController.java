package com.ardatas.app;

import com.ardatas.dto.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/engineers")

public class EngineerController {
    private final EngineerService engineerService;
    private final EngineerRepository engineerRepository;

    public EngineerController(EngineerService engineerService, EngineerRepository engineerRepository) {
        this.engineerService = engineerService;
        this.engineerRepository = engineerRepository;
    }

    // everything exposed to API is called Controller

    // FOR RETRIEVEL
    @GetMapping()
    public List<EngineerRecord> getEngineers() {
        return engineerService.getEngineers();
    }

    @GetMapping("/{id}")
    public EngineerRecord getOneEngineer(@PathVariable Integer id) {
        return engineerService.getOneEngineer(id);
    }

    @PostMapping
    public EngineerRecord addEngineer(@Valid @RequestBody CreateEngineerRecord create) {
        return engineerService.addEngineer(create);
    }

    @DeleteMapping("/{id}")
    public void deleteEngineerById(@PathVariable Integer id) {
        engineerService.deleteEngineerById(id);
    }

    @DeleteMapping("/top")
    public void deleteEngineerFromTop() {
        engineerService.deleteEngineerFromTop();
    }

    @PatchMapping("/{id}")
    public EngineerRecord updateEngineer(@PathVariable Integer id, @RequestBody UpdateEngineerRecord engineerRecord) {
        return engineerService.updateEngineer(id, engineerRecord);
    }

    // Projects

    @PostMapping("/{id}/projects")
    public ProjectRecord addProject(@PathVariable Integer id, @Valid @RequestBody CreateProjectRecord create) {
        return engineerService.addProject(id, create);
    }

    @DeleteMapping("/{engineerId}/projects/{projectId}")
    public void deleteProjectById(@PathVariable Integer engineerId, @PathVariable Integer projectId) {
        engineerService.deleteProjectById(engineerId, projectId);
    }


}


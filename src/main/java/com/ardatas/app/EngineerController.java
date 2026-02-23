package com.ardatas.app;

import com.ardatas.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/engineers")

public class EngineerController {
    private final EngineerService engineerService;

    public EngineerController(EngineerService engineerService) {
        this.engineerService = engineerService;
    }

    // everything exposed to the API is called Controller

    // PAGINATION OF THE RECORD, @PageableDefault for default paging vals
    // paginaton is zero indexed
    @GetMapping()
    public Page<EngineerRecord> getEngineers(@PageableDefault(size = 5, sort="name")
                                                 Pageable pageable) {
        return engineerService.getEngineers(pageable);
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

    // Partly modify
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

    @Transactional
    @DeleteMapping("/{id}/projects/top")
    public void deleteProjectOnTop(@PathVariable Integer id) {
        engineerService.deleteProjectOnTop(id);
    }


}


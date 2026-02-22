package com.ardatas.app;

import jakarta.validation.Valid;
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

    // everything exposed to API is called Controller

    // FOR RETRIEVEL
    @GetMapping()
    public List<Engineer> getEngineers() {
        return engineerService.getEngineers();
    }

    @GetMapping("/{id}")
    public Engineer getOneEngineer(@PathVariable Integer id) {
        return engineerService.getOneEngineer(id);
    }

    // Requestbody uses the object in the json and makes it into an Engineer Object
    @PostMapping
    public Engineer putEngineer(@Valid @RequestBody Engineer engineer) {
        return engineerService.addEngineer(engineer);
    }

    @DeleteMapping("/{id}")
    public void deleteEngineerById(@PathVariable Integer id) {
        engineerService.deleteEngineerById(id);
    }

    @DeleteMapping("/top")
    public void deleteEngineerFromTop() {
        engineerService.deleteEngineerFromTop();
    }
}


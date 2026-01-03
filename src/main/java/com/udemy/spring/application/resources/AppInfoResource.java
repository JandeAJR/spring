package com.udemy.spring.application.resources;

import java.util.Map;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.spring.config.ProjectInfo;

@RestController
@RequestMapping("/info")
public class AppInfoResource {
    private final ProjectInfo projectInfo; // Injection via Constructor

    public AppInfoResource(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    @GetMapping(produces = "application/json")
    public Map<String, Object> info() {
    	return Map.of(
		    "name", Objects.toString(projectInfo.name(), "unknown"),
		    "version", Objects.toString(projectInfo.version(), "dev")
		);
    }
}

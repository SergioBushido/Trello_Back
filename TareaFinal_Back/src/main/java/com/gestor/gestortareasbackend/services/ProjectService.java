package com.gestor.gestortareasbackend.services;

import com.gestor.gestortareasbackend.model.project.Project;
import com.gestor.gestortareasbackend.model.project.dto.ProjectRequestMapper;
import com.gestor.gestortareasbackend.model.project.dto.ProjectResponseMapper;
import com.gestor.gestortareasbackend.model.project.dto.RequestProject;
import com.gestor.gestortareasbackend.model.project.dto.ResponseProject;
import com.gestor.gestortareasbackend.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectRequestMapper projectRequestMapper;
    private final ProjectResponseMapper projectResponseMapper;

    @Transactional(readOnly = true)
    public Optional<ResponseProject> getProjectById(Long id) {
        final Optional<Project> projectOptional = projectRepository.findById(id);
        return projectOptional.map(projectResponseMapper::entityToResponse);
    }

    @Transactional(readOnly = true)
    public List<ResponseProject> getAllProjects() {
        return projectResponseMapper.entitiesToResponses(projectRepository.findAll());
    }

    @Transactional
    public ResponseProject createProject(RequestProject requestProject) {
        Project project = projectRequestMapper.dtoToEntity(requestProject);
        project = projectRepository.save(project);
        return projectResponseMapper.entityToResponse(project);
    }

    @Transactional
    public Optional<ResponseProject> updateProject(Long id, RequestProject projectDetails) {
        Optional<Project> existingProject = projectRepository.findById(id);
        existingProject.ifPresent(project -> {
            projectResponseMapper.updateEntityFromDto(projectDetails, project);
            projectRepository.save(project);
        });
        return existingProject.map(projectResponseMapper::entityToResponse);
    }

    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<ResponseProject> findByProjectName(String name) {
        final var projects = projectRepository.findByNameContainingIgnoreCase(name);
        return projectResponseMapper.entitiesToResponses(projects);
    }
}

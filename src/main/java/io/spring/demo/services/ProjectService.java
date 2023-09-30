package io.spring.demo.services;

import io.spring.demo.domain.Project;
import io.spring.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){
        return projectRepository.save(project);
    }

}

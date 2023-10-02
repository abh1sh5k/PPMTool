package io.spring.demo.services;

import io.spring.demo.domain.Project;
import io.spring.demo.exceptions.ProjectIdException;
import io.spring.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){

        try{
            project.setProjectIdentifier((project.getProjectIdentifier()).toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception e){
            throw new ProjectIdException("Project ID '" +project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }
    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier((projectId.toUpperCase()));

        if(project == null){
            throw new ProjectIdException("Project ID '" +projectId.toUpperCase()+"' does not exist");
        }

        return project;
    }
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }
    public void deleteProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project ID '" +projectId.toUpperCase()+"' does not exist");
        }
        projectRepository.delete(project);
    }

    public Project UpdateProject(Project project){
        String projectId = project.getProjectIdentifier();

        deleteProjectByIdentifier(projectId);
        return saveOrUpdateProject(project);

    }

}

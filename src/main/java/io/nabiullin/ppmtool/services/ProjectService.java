package io.nabiullin.ppmtool.services;

import io.nabiullin.ppmtool.domain.Project;
import io.nabiullin.ppmtool.exeptions.ProjectIdException;
import io.nabiullin.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
                throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"'already exists");

        }
    }

    public Project findProjectByIdentifier (String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project==null){
            throw new ProjectIdException("Project ID '"+projectId+"'doesn't exists");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }


    public void deleteProjectByIdentifier(String projectId){
    Project project = projectRepository.findByProjectIdentifier(projectId);

    if (project==null){
        throw new ProjectIdException("Cannot Project with ID'"+projectId+"'. This Project doesn't exists");
    }
    projectRepository.delete(project);
    }
}

package io.nabiullin.ppmtool.services;

import io.nabiullin.ppmtool.domain.Backlog;
import io.nabiullin.ppmtool.domain.ProjectTask;
import io.nabiullin.ppmtool.repositories.BacklogRepository;
import io.nabiullin.ppmtool.repositories.ProjectRepository;
import io.nabiullin.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        //PTs to be added to a specific project, project != null, backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        //set the bl to pt
        projectTask.setBacklog(backlog);
        //we want our project sequence to be like this: IDPRO-1, IDPRO-2 ... 100 101
        Integer BacklogSequence = backlog.getPTSequence();
        //Update the backlog sequence
        BacklogSequence++;

        backlog.setPTSequence(BacklogSequence);
        //add sequence to project task
        projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        //initial priority when priority is null
        if (projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }
        //initial status when status i null
        if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
            projectTask.setStatus("to_do");
        }
        return projectTaskRepository.save(projectTask);
    }
    public Iterable<ProjectTask>findBacklogById(String id){
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
}

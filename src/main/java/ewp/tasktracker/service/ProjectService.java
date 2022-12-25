package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateProjectRq;
import ewp.tasktracker.api.dto.ProjectDto;

import java.util.List;


public interface ProjectService {
    ProjectDto create(CreateProjectRq dto);

    ProjectDto findById(String id);

    List<ProjectDto> findAll();
}

package mum.edu.ea.extracreditprj.dao;

import mum.edu.ea.extracreditprj.entity.Project;
import mum.edu.ea.extracreditprj.entity.ProjectStatus;
import mum.edu.ea.extracreditprj.entity.ResourceType;

import java.util.Date;
import java.util.List;

public interface ProjectDao {
	Project findById(int id);
	boolean addProject(Project project);
	List<Project> findAllProjects();
	List<Project> findProjectByStatus(ProjectStatus status);
	boolean deleteProject(Project project);
	boolean updateProject(Project project);
	List<Project> findProjectsByResource(ResourceType resourceType);
	List<Project> findProjectsByTimeframe(Date start, Date end);
	List<Project> findProjectsByKeyword(String keyword);
	List<Project> findProjectsByLocation(String location);
}

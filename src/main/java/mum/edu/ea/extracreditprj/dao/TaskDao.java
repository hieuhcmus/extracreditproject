package mum.edu.ea.extracreditprj.dao;

import mum.edu.ea.extracreditprj.entity.Project;
import mum.edu.ea.extracreditprj.entity.Task;

import java.util.List;

public interface TaskDao {
	List<Task> findTaskByProject(Project project);
	Task findById(int id);
}

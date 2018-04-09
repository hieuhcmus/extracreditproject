package mum.edu.ea.extracreditprj.dao;

import mum.edu.ea.extracreditprj.entity.Project;
import mum.edu.ea.extracreditprj.entity.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskDaoImpl implements TaskDao{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Task> findTaskByProject(Project project) {
		Query query = entityManager.createQuery("SELECT tasks FROM Project project" +
				" join project.tasks tasks where project = :project");
		query.setParameter("project", project);
		return query.getResultList();
	}

	@Override
	public Task findById(int id) {
		return entityManager.find(Task.class, id);
	}
}

package mum.edu.ea.extracreditprj.dao;

import mum.edu.ea.extracreditprj.entity.Project;
import mum.edu.ea.extracreditprj.entity.ProjectStatus;
import mum.edu.ea.extracreditprj.entity.ResourceType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Project findById(int id) {
		return entityManager.find(Project.class, id);
	}

	@Override
	public boolean addProject(Project project) {
		try{
			entityManager.persist(project);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Project> findAllProjects() {
		return entityManager.createQuery("FROM Project", Project.class).getResultList();
	}

	@Override
	public List<Project> findProjectByStatus(ProjectStatus status) {
		Query query = entityManager.createQuery("SELECT project FROM Project project WHERE project.status = :status");
		query.setParameter("status", status);
		return query.getResultList();
	}

	@Override
	public boolean deleteProject(Project project) {
		try{
			entityManager.remove(project);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateProject(Project project) {
		try{
			entityManager.merge(project);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Project> findProjectsByResource(ResourceType resourceType) {
		Query query = entityManager.createQuery("select distinct project from Project" +
				" project join project.tasks tasks join tasks.taskResources taskResources where " +
				"taskResources.resourceType = :resourceType");
		query.setParameter("resourceType", resourceType);
		return query.getResultList();
	}

	@Override
	public List<Project> findProjectsByTimeframe(Date start, Date end) {
		Query query = entityManager.createQuery("select project from Project project where startDate > :startDate " +
				"and endDate < :endDate");
		query.setParameter("startDate", start);
		query.setParameter("endDate", end);
		return query.getResultList();
	}

	@Override
	public List<Project> findProjectsByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		Query query = entityManager.createQuery("select project from Project project " +
				"where project.description like :keyword or project.status like :keyword " +
				"or project.address.city like :keyword or project.address.state like :keyword " +
				"or project.address.country like :keyword or project.address.zipcode like :keyword");
		query.setParameter("keyword", keyword);
		return query.getResultList();
	}

	@Override
	public List<Project> findProjectsByLocation(String location) {
		return null;
	}
}

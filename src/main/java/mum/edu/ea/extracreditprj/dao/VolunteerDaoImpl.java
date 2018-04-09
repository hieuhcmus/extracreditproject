package mum.edu.ea.extracreditprj.dao;

import mum.edu.ea.extracreditprj.entity.Project;
import mum.edu.ea.extracreditprj.entity.ResourceType;
import mum.edu.ea.extracreditprj.entity.Volunteer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class VolunteerDaoImpl implements VolunteerDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Volunteer> findVolunteersBySkill(ResourceType skill) {
		Query query = entityManager.createQuery("Select volunteer From Volunteer volunteer join volunteer.skills skills" +
				" where skills = :skill order by volunteer.id");
		query.setParameter("skill", skill);
		return query.getResultList();
	}

	@Override
	public List<Project> findProjectsOfAVolunteer(Volunteer volunteer) {
		Query query = entityManager.createQuery("select projects from Volunteer volunteer join volunteer.projects projects " +
				"where volunteer = :volunteer");
		query.setParameter("volunteer", volunteer);
		return query.getResultList();
	}

	@Override
	public Volunteer findById(int id) {
		return entityManager.find(Volunteer.class, id);
	}
}

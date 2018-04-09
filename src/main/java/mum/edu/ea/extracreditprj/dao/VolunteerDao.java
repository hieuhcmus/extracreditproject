package mum.edu.ea.extracreditprj.dao;

import mum.edu.ea.extracreditprj.entity.Project;
import mum.edu.ea.extracreditprj.entity.ResourceType;
import mum.edu.ea.extracreditprj.entity.Volunteer;

import java.util.List;

public interface VolunteerDao {
	List<Volunteer> findVolunteersBySkill(ResourceType skill);
	List<Project> findProjectsOfAVolunteer(Volunteer volunteer);
	Volunteer findById(int id);
}

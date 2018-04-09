package mum.edu.ea.extracreditprj;

import mum.edu.ea.extracreditprj.dao.VolunteerDao;
import mum.edu.ea.extracreditprj.entity.Project;
import mum.edu.ea.extracreditprj.entity.ResourceType;
import mum.edu.ea.extracreditprj.entity.Volunteer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class VolunteerDaoTest {
	@Inject
	private VolunteerDao volunteerDao;

	@Test
	public void testFindVolunteersBySkill() {
		List<Volunteer> volunteers = volunteerDao.findVolunteersBySkill(ResourceType.DEVELOPER);
		Assert.assertNotNull(volunteers);
		Assert.assertTrue(volunteers.size() == 1);
		Assert.assertEquals(-1, volunteers.get(0).getId());
		Assert.assertTrue(volunteers.get(0).getSkills().contains(ResourceType.DEVELOPER));

		volunteers = volunteerDao.findVolunteersBySkill(ResourceType.BA);
		Assert.assertNotNull(volunteers);
		Assert.assertTrue(volunteers.size() == 2);
		Assert.assertTrue(volunteers.get(0).getSkills().contains(ResourceType.BA));
		Assert.assertTrue(volunteers.get(1).getSkills().contains(ResourceType.BA));
	}

	@Test
	public void testFindProjectsByVolunteer() {
		Volunteer volunteer = volunteerDao.findById(-1);
		List<Project> projects = volunteerDao.findProjectsOfAVolunteer(volunteer);
		Assert.assertNotNull(projects);
		Assert.assertEquals(1, projects.size());
		Assert.assertTrue(projects.get(0).getVolunteers().contains(volunteer));
	}
}

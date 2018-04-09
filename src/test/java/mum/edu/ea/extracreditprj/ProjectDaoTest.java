package mum.edu.ea.extracreditprj;

import mum.edu.ea.extracreditprj.dao.ProjectDao;
import mum.edu.ea.extracreditprj.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ProjectDaoTest {
	@Inject
	private ProjectDao projectDao;

	@Test
	public void testAddProject() {
		Project project = new Project();
		project.setDescription("Description");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setStatus(ProjectStatus.STARTED);

		Assert.assertTrue(projectDao.addProject(project));

		Project persistedProject = projectDao.findById(project.getId());
		Assert.assertNotNull(persistedProject);
		Assert.assertEquals(project.getDescription(), persistedProject.getDescription());
		Assert.assertEquals(project.getStartDate(), persistedProject.getStartDate());
		Assert.assertEquals(project.getEndDate(), persistedProject.getEndDate());
		Assert.assertEquals(project.getStatus(), persistedProject.getStatus());
	}

	@Test
	public void testFindProjectById() {
		Project project = projectDao.findById(-1);
		Assert.assertNotNull(project);
		Assert.assertEquals(ProjectStatus.STARTED, project.getStatus());
		Assert.assertEquals("This is a project description", project.getDescription());
	}

	@Test
	public void testUpdateProject() {
		Project project = projectDao.findById(-1);
		Assert.assertNotNull(project);
		Assert.assertEquals(ProjectStatus.STARTED, project.getStatus());
		Assert.assertEquals("This is a project description", project.getDescription());

		project.setDescription("Changed description");
		project.setStatus(ProjectStatus.FINISHED);
		Project persistedProject = projectDao.findById(-1);
		Assert.assertEquals("Changed description", persistedProject.getDescription());
		Assert.assertEquals(ProjectStatus.FINISHED, persistedProject.getStatus());
	}

	@Test
	public void testFindAllProjects() {
		List<Project> projects = projectDao.findAllProjects();
		Assert.assertNotNull(projects);
		Assert.assertTrue(projects.size() > 0);
	}

	@Test
	public void testFindProjectsByStatus() {
		List<Project> projects = projectDao.findProjectByStatus(ProjectStatus.STARTED);
		Assert.assertNotNull(projects);
		Assert.assertTrue(projects.size() > 0);
		for (Project project : projects) {
			Assert.assertEquals(ProjectStatus.STARTED, project.getStatus());
		}
	}

	@Test
	public void testDeleteProject() {
		Project project = projectDao.findById(-1);
		Assert.assertNotNull(project);

		Assert.assertTrue(projectDao.deleteProject(project));
		Assert.assertNull(projectDao.findById(-1));
	}

//	@Test
//	public void testFindProjectsByResource() {
//		List<Project> projects = projectDao.findProjectsByResource(ResourceType.DEVELOPER);
//		Assert.assertNotNull(projects);
//		Assert.assertTrue(projects.size() == 1);
//		for (Project pj : projects) {
//			boolean developerTask = false;
//			for (Task task : pj.getTasks()) {
//				boolean contain = false;
//				for (TaskResource taskResource : task.getTaskResources()) {
//					if (taskResource.getResourceType() == ResourceType.DEVELOPER) {
//						contain = true;
//						break;
//					}
//				}
//				Assert.assertTrue(contain);
//			}
//			Assert.assertTrue(developerTask);
//		}
//	}

	@Test
	public void testFindProjectsByTimeframe() throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date start = f.parse("2017-12-01");
		Date end = f.parse("2018-10-02");
		List<Project> projects = projectDao.findProjectsByTimeframe(start, end);
		Assert.assertNotNull(projects);
		Assert.assertTrue(projects.size() == 2);
	}

	@Test
	public void testFindProjectsByKeyword() {
		List<Project> projects = projectDao.findProjectsByKeyword("project description 2");
		Assert.assertNotNull(projects);
		Assert.assertTrue(projects.size() == 1);
		Assert.assertEquals("This is a project description 2", projects.get(0).getDescription());
	}
}

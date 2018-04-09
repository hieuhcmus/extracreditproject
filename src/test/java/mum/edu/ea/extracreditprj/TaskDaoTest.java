package mum.edu.ea.extracreditprj;

import mum.edu.ea.extracreditprj.dao.ProjectDao;
import mum.edu.ea.extracreditprj.dao.TaskDao;
import mum.edu.ea.extracreditprj.entity.Project;
import mum.edu.ea.extracreditprj.entity.Task;
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
public class TaskDaoTest {
	@Inject
	private TaskDao taskDao;

	@Inject
	private ProjectDao projectDao;

	@Test
	public void testFindTasksByProject() {
		Project project = projectDao.findById(-1);
		Assert.assertNotNull(project);
		List<Task> tasks = taskDao.findTaskByProject(project);
		Assert.assertNotNull(tasks);
		Assert.assertTrue(tasks.size() == 2);
	}

	@Test
	public void testFindTaskById() {
		Task task = taskDao.findById(-1);
		Assert.assertNotNull(task);
		Assert.assertEquals(-1, task.getId());
	}
}

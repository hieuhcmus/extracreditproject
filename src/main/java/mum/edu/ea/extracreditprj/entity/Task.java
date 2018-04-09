package mum.edu.ea.extracreditprj.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TASK")
public class Task {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project;


	@OneToMany(mappedBy = "task")
	private List<TaskResource> taskResources;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<TaskResource> getTaskResources() {
		return taskResources;
	}

	public void setTaskResources(List<TaskResource> taskResources) {
		this.taskResources = taskResources;
	}
}

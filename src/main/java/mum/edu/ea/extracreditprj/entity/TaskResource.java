package mum.edu.ea.extracreditprj.entity;

import javax.persistence.*;

@Entity
@Table(name = "TASK_RESOURCE")
public class TaskResource {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "REQUIRED_RESOURCES")
	private int requiredResources;

	@Column(name = "RESOURCE_TYPE")
	@Enumerated(EnumType.STRING)
	private ResourceType resourceType;

	@ManyToOne
	@JoinColumn(name = "TASK_ID")
	private Task task;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequiredResources() {
		return requiredResources;
	}

	public void setRequiredResources(int requiredResources) {
		this.requiredResources = requiredResources;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}

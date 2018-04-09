package mum.edu.ea.extracreditprj.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PROJECT")
public class Project {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "DETAILED_DESCRIPTION")
	@Lob
	private byte[] detailedDescription;

	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	@Column(name = "START_DATE")
	@Temporal(value = TemporalType.DATE)
	private Date startDate;

	@Column(name = "END_DATE")
	@Temporal(value = TemporalType.DATE)
	private Date endDate;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private ProjectStatus status;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Task> tasks;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Beneficiary> beneficiaries;

	@ManyToMany
	@JoinTable(name = "PROJECT_VOLUNTEER", joinColumns = @JoinColumn(name = "PROJECT_ID"),
		inverseJoinColumns = @JoinColumn(name = "VOLUNTEER_ID"))
	private List<Volunteer> volunteers;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void addTask(Task task) {
		if (tasks == null) {
			tasks = new ArrayList<>();
		}
		tasks.add(task);
		task.setProject(this);
	}

	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	public List<Volunteer> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

	public byte[] getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(byte[] detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
}

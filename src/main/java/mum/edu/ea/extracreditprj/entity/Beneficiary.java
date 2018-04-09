package mum.edu.ea.extracreditprj.entity;

import javax.persistence.*;

@Entity
@Table(name = "BENIFICIARY")
public class Beneficiary {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}

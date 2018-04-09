package mum.edu.ea.extracreditprj.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "VOLUNTEER")
public class Volunteer {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<ResourceType> skills;

	@ManyToMany(mappedBy = "volunteers")
	private List<Project> projects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResourceType> getSkills() {
		return Collections.unmodifiableList(skills);
	}

	public void setSkills(List<ResourceType> skills) {
		this.skills = skills;
	}

	public void addSkill(ResourceType skill) {
		if (skills == null) {
			skills = new ArrayList<>();
		}
		skills.add(skill);
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}

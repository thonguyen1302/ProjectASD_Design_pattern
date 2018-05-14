package mum.asd.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;


//    @ManyToMany(mappedBy="skills")
//    private List<Volunteer> volunteers = new ArrayList<Volunteer>();

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public List<Volunteer> getVolunteers() { return volunteers; }
//	public void setVolunteers(List<Volunteer> volunteers) { this.volunteers = volunteers; }

//	public mum.asd.domain.Skill(Volunteer volunteer) {
//		this.volunteer = volunteer;
//	}

	public Skill() {
	}
}

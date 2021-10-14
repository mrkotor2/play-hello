package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject extends Model {

    @Column(name = "name")
    @Required(message = "First name required")
    private String name;

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "membership")
//    public List<Student> students;
// owning side
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    public List<Student> students = new ArrayList<>();

    public Subject() {

    }

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}

package models;

import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student extends Model {

    @Column(name = "first_name")
    @Required(message = "First name required")
    private String firstName;

    @Column(name = "last_name")
    @Required(message = "Last name required")
    private String lastName;

    @Column(name = "age")
    @Required(message = "Age required")
    @Min(value = 15, message = "Should be more than 15")
    private int age;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "membership")
//    public List<Subject> subjects;

    // inverse side of the relation
    @ManyToMany(mappedBy = "students")
    public List<Subject> subjects;

    public Student() {
    }

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
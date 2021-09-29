package controllers;

import models.Student;
import play.Logger;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.results.Result;
import views.*;

import java.util.List;

public class StudentController extends Controller {

    public static void show() {
        Logger.error("dfdfdf");
        List<Student> students = Student.findAll();
        System.out.println(students);
        render(students);
    }

    public static void create() {
        render();
    }
    public static void edit() {
        render();
    }

//        public static void save(String fname, String lname, Integer age) {
    public static void save(Student student) {
//        new Student(fname, lname, age).save();

//        request.params.get();

        System.out.println("Starting save");

        student.save();

        System.out.println("Completing save");
        create();

    }

    public static void change(Student student) {

        System.out.println("Starting change");

        student.save();

        System.out.println("Completing change");
        edit();

    }



}

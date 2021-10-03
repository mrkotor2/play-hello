package controllers;

import com.google.gson.Gson;
import models.Student;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class StudentController extends Controller {

    public static void show() {
        Logger.error("ERROR LOG");
        List<Student> students = Student.findAll();
        System.out.println(students);
        render(students);
    }

    public static void create() {
        render();
    }

//    public static void edit(Student student) {
//        System.out.println("ID " + student.getId());
//        System.out.println(student);
//        render(student);
//    }

    public static void edit(Long id) {
        Student student = Student.findById(id);
//        new Gson().toJson(student);
        renderArgs.put("studentAsJson", new Gson().toJson(student));
        renderArgs.put("student", student);
        render();
//        renderJSON(student);//@TODO
    }

    public static void save(Student student) {


        System.out.println("Starting save");

        student.save();

        System.out.println("Completing save");
        create();

    }

    public static void change(Student student) {

        System.out.println("Starting change");
        System.out.println(student);
        ;

        if (student.isPersistent()) {
            student.save();
            System.out.println("Completing change");
        } else {
            System.out.println("No such student exists");
        }
        show();
    }

    public static void delete(Student student) {

        System.out.println("Starting delete");
        System.out.println(student);

        if (student.isPersistent()) {
            student.delete();
            System.out.println("Completing delete");
        }
        else {
            System.out.println("No such student exists");
        }

        show();

    }


}

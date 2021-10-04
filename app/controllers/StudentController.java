package controllers;

import com.google.gson.Gson;
import models.Student;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class StudentController extends Controller {

    public static void show() {

//        Logger.error("ERROR LOG");
        List<Student> students = Student.findAll();
        Logger.info("Students: " + students);
        render(students);
    }

    public static void create() {
        render();
    }

    public static void save(Student student) {

        Logger.info("Starting save");

        student.save();
        Logger.info("Completing save");
        Logger.info(" " + student);

        create();
    }

    public static void edit(Long id) {

        Student student = Student.findById(id);

        Logger.info("EDIT STUDENT " + student);
//        new Gson().toJson(student);
        renderArgs.put("studentAsJson", new Gson().toJson(student));
        renderArgs.put("student", student);
        render();
//        renderJSON(student);//@TODO
    }

    public static void change(Student student) {

        Logger.info("Starting change");
        Logger.info("" + student);

        if (student.isPersistent()) {
            student.save();
            Logger.info("Completing change");
        } else {
            Logger.info("No such student exists");
        }
        show();
    }

    public static void delete(Student student) {

        Logger.info("Starting delete");
        Logger.info("" + student);

        if (student.isPersistent()) {
            student.delete();
            Logger.info("Completing delete");
        } else {
            Logger.info("No such student exists");
        }
        show();
    }


}

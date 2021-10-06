package controllers;

import models.Student;
import play.Logger;
import play.data.validation.Error;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;

import java.util.List;

public class StudentController extends Controller {

    public static void show() {

        try {
            List<Student> students = Student.findAll();
            Logger.info("Students: " + students);
            render(students);
        } catch (Exception e) {
            Logger.error("Error occurred during students page initialization, caused by: " + e);
        }

    }

    public static void edit() {
        try {
            render();
        } catch (Exception e) {
            Logger.error("Error occurred during edit page initialization, caused by: " + e);
        }
    }

    public static void edit(Long id) {
        try {
            Student student = null;

            if (id == null) {
                Logger.info("Add new student");
            } else {
                student = Student.findById(id);
                Logger.info("EDIT STUDENT " + student);

            }
//            Student student = Student.findById(id);

//            renderArgs.put("studentAsJson", new Gson().toJson(student));

            renderArgs.put("student", student);
            render();
        } catch (Exception e) {
            Logger.error("Error occurred during student editOld page init, caused by: " + e);
        }

//        new Gson().toJson(student);

//        renderJSON(student);//@TODO
    }

    public static void save(@Valid Student student) {
        Logger.info("Starting save");

        if (Validation.hasErrors()) {
            for (Error error : Validation.errors()) {
                Logger.error(error.message());
            }
            params.flash(); // add http parameters to the flash scope
//            flash.put("some", student);
            Validation.keep(); // keep the errors for the next request

        } else {
            try {
                student.save();
                flash.put("CREATED", student);
            } catch (Exception e) {
                Logger.error("Error occurred during student save, caused by: " + e);
            }
            Logger.info("Completing save");
            Logger.info(" " + student);
        }
        edit();
    }

    public static void change(Student student) {

        Logger.info("Starting change");
        try {
            Logger.info("" + student);

            if (student.isPersistent()) {
                student.save();
                Logger.info("Completing change");
            } else {
                Logger.info("No such student exists");
            }
        } catch (Exception e) {
            Logger.error("Error occurred during student change, caused by: " + e);
        }

        show();
    }

    public static void delete(Student student) {

        Logger.info("Starting delete");
        try {
            Logger.info("" + student);
            if (student.isPersistent()) {
                student.delete();
                Logger.info("Completing delete");
            } else {
                Logger.info("No such student exists");
            }
        } catch (Exception e) {
            Logger.error("Error occurred during student delete, caused by: " + e);
        }

        show();
    }


}

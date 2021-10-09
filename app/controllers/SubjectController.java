package controllers;

import models.Student;
import models.Subject;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class SubjectController extends Controller {

    public static void show() {

        try {
            List<Subject> subjects = Subject.findAll();
            Logger.info("Subjects: " + subjects);
            render(subjects);
        } catch (Exception e) {
            Logger.error("Error occurred during subjects page initialization, caused by: " + e);
        }

    }

    public static void edit(Long id) {
        try {
            Subject subject = null;

            if (id == null) {
                Logger.info("Add new subject");
            } else {
                subject = Subject.findById(id);
                Logger.info("EDIT SUBJECT " + subject);

            }

            renderArgs.put("subject", subject);
            render();
        } catch (Exception e) {
            Logger.error("Error occurred during subject editOld page init, caused by: " + e);
        }

    }

    public static void save(Subject subject) {
        Logger.info("Starting save");

        try {
            subject.save();
            flash.put("CREATED", subject);
        } catch (Exception e) {
            Logger.error("Error occurred during subject save, caused by: " + e);
        }
        Logger.info("Completing save");
        Logger.info(" " + subject);
        show();
    }

    public static void change(Subject subject) {

        Logger.info("Starting change");
        try {
            Logger.info("" + subject);

            if (subject.isPersistent()) {
                subject.save();
                Logger.info("Completing change");
            } else {
                Logger.info("No such subject exists");
            }
        } catch (Exception e) {
            Logger.error("Error occurred during subject change, caused by: " + e);
        }

        show();
    }

    public static void delete(Subject subject) {

        Logger.info("Starting delete");
        try {
            Logger.info("" + subject);
            if (subject.isPersistent()) {
                subject.delete();
                Logger.info("Completing delete");
            } else {
                Logger.info("No such subject exists");
            }
        } catch (Exception e) {
            Logger.error("Error occurred during subject delete, caused by: " + e);
        }

        show();
    }

    public static void membership(Long id) {

        Logger.info("GOING TO MEMBERSHIP");

        try {
            Subject subject = Subject.findById(id);
            List<Student> studentsOfSubject = subject.students;

            renderArgs.put("studentsOfSubject", studentsOfSubject);
            render();
        } catch (Exception e) {
            Logger.error("Error occurred during membership init, caused by: " + e);
        }

    }

}

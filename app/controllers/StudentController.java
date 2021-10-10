package controllers;

import models.Student;
import models.Subject;
import play.Logger;
import play.data.validation.Error;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;

import java.util.ArrayList;
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
        edit(null);
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

    public static void membership(Long id) {

        Logger.info("GOING TO MEMBERSHIP");

        try {
            Student student = Student.findById(id);

            List<Subject> subjectsOfStudent = student.subjects;

            renderArgs.put("student", student);
            renderArgs.put("subjectsOfStudent", subjectsOfStudent);
            render();
        } catch (Exception e) {
            Logger.error("Error occurred during membership init, caused by: " + e);
        }

    }

    public static void addMembershipCheckSubjects(Long id) {

        Logger.info("GOING TO init Add membership form");

        try {
            List<Subject> subjects = Subject.findAll();
            Logger.info("LIST OF ALL SUBS: " + subjects);

            Student student = Student.findById(id);
            Logger.info("STUDENT: " + student);

            List<Subject> subjectsOfStudent = student.subjects;
            Logger.info("LIST OF SUBJECTS OF STUDENT: " + subjectsOfStudent);

            List<Subject> subjectsExcept = new ArrayList<Subject>();

            for (Subject subject : subjects) {
                boolean contains = false;

                for (Subject subjectOfStudent : subjectsOfStudent) {
                    if (subjectOfStudent.getId().equals(subject.getId())) {
                        Logger.info("Found it: " + subject.getId());
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    subjectsExcept.add(subject);
                }
            }

            Logger.info("LIST OF EXCEPTS: " + subjectsExcept);

            if (subjectsOfStudent.size() <= 0) {
                renderArgs.put("subjectsExcept", subjects);
            } else {
                renderArgs.put("subjectsExcept", subjectsExcept);
            }

            renderArgs.put("student", student);
            render();

        } catch (Exception e) {
            Logger.error("Error occurred during add membership form init, caused by: " + e);
        }

    }

    public static void addMembership(Long subjectId, Long studentId) {

        Logger.info("GOING TO Add membership");
        Logger.info("SUB " + subjectId);
        Logger.info("STUD " + studentId);

        try {
            Subject subject = Subject.findById(subjectId);

            Student student = Student.findById(studentId);

            List<Student> students = subject.students;
            Logger.info("LIST OF STUDENTS OF SUBJECT OLD: " + students);

            if (!students.contains(student)) {
                students.add(student);
                Logger.info("LIST OF STUDENTS OF SUBJECT NOW: " + students);
                subject.save();

                Logger.info("ADDED SUB FOR STUDENT");

            } else {
                Logger.info("THIS STUDENT ALREADY HERE");
                throw new RuntimeException("someException");
            }

            show();
        } catch (Exception e) {
            Logger.error("Error occurred during add membership, caused by: " + e);
        }
    }


    public static void removeMembership(Long subjectId, Long studentId) {

        Logger.info("GOING TO REMOVE membership");

        try {
            Subject subject = Subject.findById(subjectId);

            Student student = Student.findById(studentId);

            List<Student> students = subject.students;

            if (students.contains(student)) {
                students.remove(student);
                subject.save();
                Logger.info("Student has been unsubscribed");
            } else {
                Logger.info("THIS STUDENT DOESNT HAVE THIS SUBJECT");
                throw new RuntimeException("someException");
            }

            show();
        } catch (Exception e) {
            Logger.error("Error occurred during add membership, caused by: " + e);
        }
    }


}
